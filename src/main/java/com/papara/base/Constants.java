package com.papara.base;

/**
 * Constants class holds constants for client
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public final class Constants {

    private Constants() {}

    /**
     * The constant ENCODING_FORMAT (UTF-8)
     */
    public static final String ENCODING_FORMAT = "UTF-8";

    /**
     * The constant HTTP_CONTENT_TYPE_HEADER
     */
    public static final String HTTP_CONTENT_TYPE_HEADER = "Content-Type";

    /**
     * The constant HTTP_ACCEPT_HEADER
     */
    public static final String HTTP_ACCEPT_HEADER = "Accept";

    /**
     * The constant ENDPOINT.
     */
    public static final String ENDPOINT = "service.EndPoint";

    /**
     * The constant HTTP_CONNECTION_TIMEOUT.
     */
    public static final String HTTP_CONNECTION_TIMEOUT = "http.ConnectionTimeOut";

    /**
     * The constant HTTP_CONNECTION_RETRY.
     */
    public static final String HTTP_CONNECTION_RETRY = "http.Retry";

    /**
     * The constant HTTP_CONNECTION_READ_TIMEOUT.
     */
    public static final String HTTP_CONNECTION_READ_TIMEOUT = "http.ReadTimeOut";

    /**
     * The constant HTTP_CONNECTION_MAX_CONNECTION.
     */
    public static final String HTTP_CONNECTION_MAX_CONNECTION = "http.MaxConnection";

    /**
     * The constant HTTP_CONFIG_DEFAULT_HTTP_METHOD.
     */
    public static final String HTTP_CONFIG_DEFAULT_HTTP_METHOD = "POST";

    /**
     * The constant HTTP_CONTENT_TYPE_JSON.
     */
    public static final String HTTP_CONTENT_TYPE_JSON = "application/json";

    /**
     * The constant MODE. Mode can be sandbox or live
     */
    public static final String MODE = "mode";

    /**
     * The constant SANDBOX.
     */
    public static final String SANDBOX = "sandbox";

    /**
     * The constant LIVE.
     */
    public static final String LIVE = "live";

    /**
     * The constant REST_LIVE_ENDPOINT. Open Id redirect URI Constant Live
     */
    public static final String REST_LIVE_ENDPOINT = "https://merchant-api.papara.com";

    /**
     * The constant REST_SANDBOX_ENDPOINT. Open Id redirect URI Constant Sandbox
     */
    public static final String REST_SANDBOX_ENDPOINT = "https://merchant.test.api.papara.com";

    /**
     * The constant ACCOUNT_NUM. Used for Client ID
     */
    public static final String ACCOUNT_NUM = "accountNum";

    /**
     * The constant API_KEY.
     */
    public static final String API_KEY = "apiKey";

}
