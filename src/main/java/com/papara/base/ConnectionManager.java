package com.papara.base;

/**
 * ConnectionManager acts as a interface to retrieve {@link HttpConnection} bjects used by API service
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public final class ConnectionManager {

    /**
     * Singleton instance
     */
    private static ConnectionManager instance;


    /**
     * Private Constructor
     */
    private ConnectionManager() {
    }

    /**
     * Singleton accessor method
     *
     * @return {@link ConnectionManager} singleton object
     */
    public static ConnectionManager getInstance() {
        synchronized (ConnectionManager.class) {
            if (instance == null) {
                instance = new ConnectionManager();
            }
        }
        return instance;
    }

    /**
     * @return HttpConnection object
     */
    public HttpConnection getConnection() {
            return new DefaultHttpConnection();
    }

    /**
     * Overloaded method used factory to load GoogleAppEngineSpecific connection
     *
     * @param httpConfig
     *            {@link HttpConfiguration} object
     * @return {@link HttpConnection} object
     */
    public HttpConnection getConnection(HttpConfiguration httpConfig) {
            return getConnection();
    }

}
