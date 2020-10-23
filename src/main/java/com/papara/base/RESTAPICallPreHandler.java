package com.papara.base;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * RESTApiCallPreHandler acts as a {@link APICallPreHandler} for REST API calls. The implementation is Papara specific,
 * To do custom implementation override the protected methods
 */
public class RESTAPICallPreHandler implements APICallPreHandler {

    /**
     * Default {@link Properties}
     */
    private static final Properties DEFAULT_PROPERTIES;
    /**
     * Map View of internal Default {@link Properties}
     */
    private static Map<String, String> defaultMapView = null;

    // Initialize DEFAULT_PROPERTIES
    static {
        DEFAULT_PROPERTIES = new Properties();
        DEFAULT_PROPERTIES.put(Constants.HTTP_CONNECTION_TIMEOUT, "5000");
        DEFAULT_PROPERTIES.put(Constants.HTTP_CONNECTION_RETRY, "1");
        DEFAULT_PROPERTIES.put(Constants.HTTP_CONNECTION_READ_TIMEOUT, "30000");
        DEFAULT_PROPERTIES.put(Constants.HTTP_CONNECTION_MAX_CONNECTION, "100");
        defaultMapView = new HashMap<String, String>();
        for (Object object : DEFAULT_PROPERTIES.keySet()) {
            defaultMapView.put(object.toString().trim(), DEFAULT_PROPERTIES
                    .getProperty(object.toString()).trim());
        }
    }

    /**
     * RESTApiCallPreHandler requires a configuration system to function
     * properly. The configuration is initialized to default in PayPalResource
     * class if no configuration methods initConfig(..) was attempted before
     * making the API call. The users can override this default file
     * 'sdk_config.properties' by choosing different version of
     * initConfi(...) and passing their custom configuration.
     * Initializing to default means the system looks for a file specifically
     * named 'sdk_config.properties' in the classpath and reads the
     * configuration from there. 'Dynamic Configuration' enables the users to
     * pass custom configuration (per call basis) as a Map object to override
     * the default behavior for the system to function. For Dynamic
     * configuration to take effect create a Map of custom configuration and set
     * it in APIContext object, choose the overloaded method of the Resource
     * class that takes APIContext object as a parameter and pass the APIContext
     * object.
     * Configuration Map used for dynamic configuration
     */
    private Map<String, String> configurationMap = null;

    /**
     * Base URL for the service
     */
    private URL url;

    /**
     * Authorization token
     */
    private String apiKey;

    /**
     * Resource URI as defined in the WSDL
     */
    private String resourcePath;

    /**
     * Custom headers Map
     */
    private Map<String, String> headersMap;

    /**
     * Request Payload
     */
    private String payLoad;

    /**
     * Instantiates a new Restapi call pre handler.
     *
     * @param configurationMap the configuration map
     */
    public RESTAPICallPreHandler(Map<String, String> configurationMap) {
        this.configurationMap = combineDefaultMap(configurationMap);
    }

    /**
     * Instantiates a new Restapi call pre handler.
     *
     * @param configurationMap the configuration map
     * @param headersMap       the headers map
     */
    public RESTAPICallPreHandler(Map<String, String> configurationMap,
                                 Map<String, String> headersMap) {
        this(configurationMap);
        this.headersMap = (headersMap == null) ? Collections
                .<String, String>emptyMap() : headersMap;
    }

    /**
     * Gets default map.
     *
     * @return the default map
     */
    public static Map<String, String> getDefaultMap() {
        return new HashMap<String, String>(defaultMapView);
    }

    /**
     * Combine default map map.
     *
     * @param receivedMap the received map
     * @return the map
     */
    public static Map<String, String> combineDefaultMap(Map<String, String> receivedMap) {
        return combineMap(receivedMap, getDefaultMap());
    }

    /**
     * Combine map map.
     *
     * @param highMap the high map
     * @param lowMap  the low map
     * @return the map
     */
    public static Map<String, String> combineMap(Map<String, String> highMap, Map<String, String> lowMap) {
        lowMap = lowMap != null ? lowMap : new HashMap<String, String>();
        highMap = highMap != null ? highMap : new HashMap<String, String>();
        lowMap.putAll(highMap);
        return lowMap;
    }

    /**
     * Sets api key.
     *
     * @param apiKey the api key
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Sets resource path.
     *
     * @param resourcePath the resource path
     */
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public Map<String, String> getHeaderMap() {
        return getProcessedHeaderMap();
    }

    public String getPayLoad() {
        return getProcessedPayLoad();
    }

    /**
     * Sets pay load.
     *
     * @param payLoad the pay load
     */
    public void setPayLoad(String payLoad) {
        this.payLoad = payLoad;
    }

    public String getEndPoint() {

        String endPoint = null;
        try {
            endPoint = getBaseURL().toURI().resolve(resourcePath).toString();
        } catch (MalformedURLException e) {
            //
        } catch (URISyntaxException e) {
            //
        }
        return endPoint;
    }

    public RequestOptions getCredential() {
        return null;
    }

    /**
     * Returns the base URL configured in application resources or {@link Map} passed for dynamic configuration
     *
     * @return BaseUrl ending with a '/' character {@link URL}
     * @throws MalformedURLException if endpoint cannot be found or formed
     */
    public URL getBaseURL() throws MalformedURLException {

        /**
         * Check for property 'mode' property in the configuration, if not
         * found, check for 'service.EndPoint' property in the configuration and default
         * endpoint to Papara sandbox or live endpoints. Throw exception if the
         * above rules fail
         */
        if (url == null) {
            String mode = this.configurationMap.get(Constants.MODE);
            // Default to Endpoint param.
            String urlString = this.configurationMap.get(Constants.ENDPOINT);
            if (urlString == null || urlString.trim().isEmpty()) {
                if (Constants.SANDBOX.equalsIgnoreCase(mode)) {
                    urlString = Constants.REST_SANDBOX_ENDPOINT;
                } else if (Constants.LIVE.equalsIgnoreCase(mode)) {
                    urlString = Constants.REST_LIVE_ENDPOINT;
                }
            }

            // If none of the option works, throw exception.
            if (urlString == null || urlString.trim().length() <= 0) {
                throw new MalformedURLException(
                        "service.EndPoint not set (OR) mode not configured to sandbox/live ");
            }

            if (!urlString.endsWith("/")) {
                urlString += "/";
            }
            url = new URL(urlString);
        }
        return url;
    }

    /**
     * Sets url.
     *
     * @param urlString the url to set
     * @throws MalformedURLException the malformed url exception
     */
    public void setUrl(String urlString) throws MalformedURLException {
        if (urlString != null && urlString.length() > 0) {
            String uString = urlString.endsWith("/") ? urlString : urlString
                    + "/";
            this.url = new URL(uString);
        } else {
            this.url = getBaseURL();
        }
    }


    /**
     * Return Client ID from configuration Map
     */
    private String getClientID() {
        return this.configurationMap.get(Constants.ACCOUNT_NUM);
    }

    /**
     * Returns Client Secret from configuration Map
     */
    private String getClientSecret() {
        return this.configurationMap.get(Constants.API_KEY);
    }


    /**
     * Override this method to return a {@link Map} of HTTP headers
     *
     * @return {@link Map} of HTTP headers
     */
    protected Map<String, String> getProcessedHeaderMap() {

        /**
         * The implementation is Papara specific. The Authorization header is
         * formed for OAuth or Basic, for OAuth system the authorization token
         * passed as a parameter is used in creation of HTTP header, for Basic
         * Authorization the ClientID and ClientSecret passed as parameters are
         * used after a Base64 encoding.
         */
        Map<String, String> headers = new HashMap<String, String>();
        // Add any custom headers
        if (headersMap != null && headersMap.size() > 0) {
            headers.putAll(headersMap);
        }

        if (apiKey != null
                && apiKey.trim().length() > 0) {
            headers.put(Constants.API_KEY, apiKey);
        }

        // Add application/json as the default Content-Type
        // backward compatibility for Papara rest sdks which
        // does not add Content-Type HTTP header in the sdk
        // stubs
        if (!headers.containsKey(Constants.HTTP_CONTENT_TYPE_HEADER)) {
            headers.put(Constants.HTTP_CONTENT_TYPE_HEADER,
                    Constants.HTTP_CONTENT_TYPE_JSON);
        }
        return headers;
    }

    /**
     * Override this method to process payload for processing
     *
     * @return PayLoad as String
     */
    protected String getProcessedPayLoad() {
        /**
         * Since the REST API of Papara depends on json, which is well formed,
         * no additional processing is required.
         */
        return payLoad;
    }

    /**
     * Return configurationMap
     *
     * @return configurationMap in this call pre-handler
     */
    public Map<String, String> getConfigurationMap() {
        return this.configurationMap;
    }

}
