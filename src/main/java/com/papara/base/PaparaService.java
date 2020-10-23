package com.papara.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Papara Service class contains service methods to be used in service classes.
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @see PaparaModel
 * @version 0.0.1
 * @since 0.0.1
 */
public class PaparaService extends PaparaModel {

    private static final Logger log = LoggerFactory.getLogger(PaparaService.class);
    private static final ThreadLocal<String> LASTREQUEST = new ThreadLocal<String>();
    private static final ThreadLocal<String> LASTRESPONSE = new ThreadLocal<String>();
    private static Map<String, String> configurationMap;


    /**
     * Configure and execute t.
     *
     * @param <T>          the type parameter
     * @param apiKey       the api key
     * @param httpMethod   the http method
     * @param resourcePath the resource path
     * @param payLoad      the pay load
     * @param clazz        the clazz
     * @param type         the type
     * @return the t
     * @throws PaparaRESTException the papara rest exception
     */
    public static <T> T configureAndExecute(String apiKey,
                                            HttpMethod httpMethod, String resourcePath, String payLoad,
                                            Class<T> clazz, Type type) throws PaparaRESTException {
        return configureAndExecute(new APIContext(apiKey), httpMethod, resourcePath, payLoad, clazz, type);
    }


    /**
     * Configure and execute t.
     *
     * @param <T>          the type parameter
     * @param apiContext   the api context
     * @param httpMethod   the http method
     * @param resourcePath the resource path
     * @param payLoad      the pay load
     * @param clazz        the clazz
     * @param type         the type
     * @return the t
     * @throws PaparaRESTException the papara rest exception
     */
    public static <T> T configureAndExecute(APIContext apiContext,
                                            HttpMethod httpMethod, String resourcePath, String payLoad,
                                            Class<T> clazz, Type type) throws PaparaRESTException {
        return configureAndExecute(apiContext, httpMethod, resourcePath, payLoad, clazz, null, type);
    }

    /**
     * Configure and execute t.
     *
     * @param <T>          the type parameter
     * @param apiContext   the api context
     * @param httpMethod   the http method
     * @param resourcePath the resource path
     * @param payLoad      the pay load
     * @param clazz        the clazz
     * @param apiKey       the api key
     * @param type         the type
     * @return the t
     * @throws PaparaRESTException the papara rest exception
     */
    public static <T> T configureAndExecute(APIContext apiContext,
                                            HttpMethod httpMethod, String resourcePath, String payLoad,
                                            Class<T> clazz, String apiKey, Type type) throws PaparaRESTException {
        T t = null;
        Map<String, String> configurationMap = null;
        String requestId;
        Map<String, String> headersMap;
        if (apiContext != null) {
            if (apiContext.getHTTPHeader(Constants.HTTP_CONTENT_TYPE_HEADER) == null || apiContext.getHTTPHeader(Constants.HTTP_ACCEPT_HEADER) == null) {
                apiContext.addHTTPHeader(Constants.HTTP_CONTENT_TYPE_HEADER, Constants.HTTP_CONTENT_TYPE_JSON);
                apiContext.addHTTPHeader(Constants.HTTP_ACCEPT_HEADER, Constants.HTTP_ACCEPT_HEADER);
            }

            headersMap = apiContext.getHTTPHeaders();
            configurationMap = apiContext.getConfigurationMap();

            if (apiKey == null) {
                apiKey = apiContext.fetchApiKey();
            }

            if (apiKey == null) {
                throw new IllegalArgumentException("ApiKey cannot be null or empty");
            }

            APICallPreHandler apiCallPreHandler = createAPICallPreHandler(configurationMap,
                    payLoad, resourcePath, headersMap, apiKey);
            HttpConfiguration httpConfiguration = createHttpConfiguration(configurationMap,
                    httpMethod, apiCallPreHandler);
            t = execute(apiCallPreHandler, httpConfiguration, clazz, type);
        }
        return t;
    }

    /**
     * Configure and execute t.
     *
     * @param <T>          the type parameter
     * @param apiContext   the api context
     * @param httpMethod   the http method
     * @param resourcePath the resource path
     * @param headersMap   the headers map
     * @param payLoad      the pay load
     * @param clazz        the clazz
     * @param type         the type
     * @return the t
     * @throws PaparaRESTException the papara rest exception
     */
    public static <T> T configureAndExecute(APIContext apiContext,
                                            HttpMethod httpMethod, String resourcePath,
                                            Map<String, String> headersMap, String payLoad, Class<T> clazz, Type type) throws PaparaRESTException {
        if (apiContext != null) {
            apiContext.addHTTPHeaders(headersMap);
        }
        return configureAndExecute(apiContext, httpMethod, resourcePath, payLoad, clazz, type);
    }


    /**
     * Create api call pre handler.
     *
     * @param configurationMap the configuration map
     * @param payLoad          the pay load
     * @param resourcePath     the resource path
     * @param headersMap       the headers map
     * @param apiKey           the api key
     * @return the api call pre handler
     */
    public static APICallPreHandler createAPICallPreHandler(
            Map<String, String> configurationMap, String payLoad,
            String resourcePath, Map<String, String> headersMap,
            String apiKey) {
        APICallPreHandler apiCallPreHandler = null;
        RESTAPICallPreHandler restAPICallPreHandler = new RESTAPICallPreHandler(
                configurationMap, headersMap);
        restAPICallPreHandler.setResourcePath(resourcePath);
        restAPICallPreHandler.setApiKey(apiKey);
        restAPICallPreHandler.setPayLoad(payLoad);
        apiCallPreHandler = restAPICallPreHandler;
        return apiCallPreHandler;
    }

    /**
     * Execute
     *
     * @param APICallPreHandler the api call pre handler
     * @param HttpConfiguration the http configuration
     * @param <T>               class
     * @param type              type
     * @return t
     * @throws PaparaRESTException
     */
    private static <T> T execute(APICallPreHandler apiCallPreHandler,
                                 HttpConfiguration httpConfiguration, Class<T> clazz, Type type) throws PaparaRESTException {
        T t = null;
        ConnectionManager connectionManager;
        HttpConnection httpConnection;
        Map<String, String> headers;
        String responseString;
        try {

            // REST Headers
            headers = apiCallPreHandler.getHeaderMap();

            // HttpConnection Initialization
            connectionManager = ConnectionManager.getInstance();
            httpConnection = connectionManager.getConnection(httpConfiguration);
            httpConnection.createAndconfigureHttpConnection(httpConfiguration);

            // capture request and log if conditions are met
            LASTREQUEST.set(apiCallPreHandler.getPayLoad());
            String mode = "";
            if (configurationMap != null) {
                mode = configurationMap.get(Constants.MODE);
            } else if (apiCallPreHandler.getConfigurationMap() != null) {
                mode = apiCallPreHandler.getConfigurationMap().get(Constants.MODE);
            }
            if (Constants.LIVE.equalsIgnoreCase(mode) && log.isDebugEnabled()) {
                log.warn("Log level cannot be set to DEBUG in " + Constants.LIVE + " mode. Skipping request/response logging...");
            }
            if (!Constants.LIVE.equalsIgnoreCase(mode)) {
                log.debug("request header: " + headers.toString());
                log.debug("request body: " + LASTREQUEST.get());
            }

            // send request and receive response
            responseString = httpConnection.execute(null,
                    apiCallPreHandler.getPayLoad(), headers);

            // System.out.println(responseString);

            // capture response and log if conditions are met
            LASTRESPONSE.set(responseString);
            if (!Constants.LIVE.equalsIgnoreCase(mode)) {
                log.debug("response: " + LASTRESPONSE.get());
            }
            if (clazz != null) {
                t = JSONFormatter.fromJSON(responseString, clazz, type);
            }
        } catch (Exception e) {
            throw new PaparaRESTException(e.getMessage(), e);
        }
        return t;
    }

    /**
     * Create http configuration
     *
     * @param configurationMap the configurationMap
     * @param httpMethod          the http method
     * @param apiCallPreHandler   the api call pre handler
     * @return httpConfiguration  http configuration
     * @throws PaparaRESTException
     */
    private static HttpConfiguration createHttpConfiguration(
            Map<String, String> configurationMap, HttpMethod httpMethod,
            APICallPreHandler apiCallPreHandler) throws PaparaRESTException {
        HttpConfiguration httpConfiguration = new HttpConfiguration();
        httpConfiguration.setHttpMethod(httpMethod.toString());
        String endpoint = apiCallPreHandler.getEndPoint();
        if (endpoint == null || endpoint.isEmpty()) {
            throw new PaparaRESTException("The endpoint could not be fetched properly. You may be missing `env` in your configuration.");
        }
        httpConfiguration.setEndPointUrl(apiCallPreHandler.getEndPoint());

        httpConfiguration.setConnectionTimeout(Integer
                .parseInt(apiCallPreHandler.getConfigurationMap()
                        .get(Constants.HTTP_CONNECTION_TIMEOUT)));
        httpConfiguration.setMaxRetry(Integer.parseInt(apiCallPreHandler.getConfigurationMap()
                .get(Constants.HTTP_CONNECTION_RETRY)));
        httpConfiguration.setReadTimeout(Integer.parseInt(apiCallPreHandler.getConfigurationMap()
                .get(Constants.HTTP_CONNECTION_READ_TIMEOUT)));
        return httpConfiguration;
    }

    /**
     * Returns ClientCredentials with client id and client secret from configuration Map
     *
     * @return Client credentials
     */
    public static ClientCredentials getCredential() {
        ClientCredentials credentials = new ClientCredentials();
        credentials.setClientID(configurationMap.get(Constants.ACCOUNT_NUM));
        credentials.setClientSecret(configurationMap.get(Constants.API_KEY));
        return credentials;
    }

    /**
     * Gets client credential.
     *
     * @return Client credentials
     * @deprecated Please use static method `getCredential` instead. <p> Returns ClientCredentials with client id and
     * client secret from configuration Map.
     */
    public ClientCredentials getClientCredential() {
        return PaparaService.getCredential();
    }


}
