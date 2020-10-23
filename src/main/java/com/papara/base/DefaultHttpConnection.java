package com.papara.base;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.*;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;


public class DefaultHttpConnection extends HttpConnection {


    /**
     * Instantiates a new Default http connection.
     */
    public DefaultHttpConnection() {
    }

    /**
     * Create a new Http connection and configure connection object
     */
    @Override
    public void createAndconfigureHttpConnection(
            HttpConfiguration clientConfiguration) throws IOException {
        this.config = clientConfiguration;

        // Create url from config
        URL url = new URL(this.config.getEndPointUrl());

        // create empty proxy
        Proxy proxy = null;

        // create proxyhost from config
        String proxyHost = this.config.getProxyHost();

        // create proxyport from config
        int proxyPort = this.config.getProxyPort();

        // setup proxy and socket adress if proxyhost and proxyport are valid
        if ((proxyHost != null) && (proxyPort > 0)) {
            SocketAddress addr = new InetSocketAddress(proxyHost, proxyPort);
            proxy = new Proxy(Proxy.Type.HTTP, addr);
        }

        // setup connection if proxy is not null, else open connection without proxy
        if (proxy != null) {
            this.connection = (HttpURLConnection) url.openConnection(proxy);
        } else {
            this.connection = (HttpURLConnection) url
                    .openConnection(Proxy.NO_PROXY);
        }
        if (this.connection instanceof HttpsURLConnection) {

        }

        // setup authenticator if username and password are not null
        if (this.config.getProxyUserName() != null
                && this.config.getProxyPassword() != null) {
            final String username = this.config.getProxyUserName();
            final String password = this.config.getProxyPassword();
            Authenticator authenticator = new DefaultPasswordAuthenticator(
                    username, password);
            Authenticator.setDefault(authenticator);
        }

        // setup connection object
        System.setProperty("http.maxConnections",
                String.valueOf(this.config.getMaxHttpConnection()));
        System.setProperty("sun.net.http.errorstream.enableBuffering", "true");
        this.connection.setDoInput(true);
        this.connection.setDoOutput(true);
        setRequestMethodViaJreBugWorkaround(this.connection, config.getHttpMethod());
        this.connection.setConnectTimeout(this.config.getConnectionTimeout());
        this.connection.setReadTimeout(this.config.getReadTimeout());

    }

    /**
     * Workaround for a bug in {@code HttpURLConnection.setRequestMethod(String)}
     * The implementation of Sun/Oracle is throwing a {@code ProtocolException}
     * when the method is other than the HTTP/1.1 default methods. So to use {@code PATCH}
     * and others, we must apply this workaround.
     *
     */
    private static void setRequestMethodViaJreBugWorkaround(final HttpURLConnection httpURLConnection, final String method) {
        try {
            // Check whether if it is running on a buggy JRE
            httpURLConnection.setRequestMethod(method);
        } catch (final ProtocolException pe) {
            try {
                httpURLConnection.getClass();
                AccessController
                        .doPrivileged(new PrivilegedExceptionAction<Object>() {
                            public Object run() throws NoSuchFieldException,
                                    IllegalAccessException {
                                try {
                                    httpURLConnection.setRequestMethod(method);

                                    // Check whether if it is running on a buggy JRE
                                } catch (final ProtocolException pe) {
                                    Class<?> connectionClass = httpURLConnection
                                            .getClass();
                                    Field delegateField = null;
                                    try {
                                        delegateField = connectionClass
                                                .getDeclaredField("delegate");
                                        delegateField.setAccessible(true);
                                        HttpURLConnection delegateConnection = (HttpURLConnection) delegateField
                                                .get(httpURLConnection);
                                        setRequestMethodViaJreBugWorkaround(
                                                delegateConnection, method);
                                    } catch (NoSuchFieldException e) {
                                        // Ignore for now, keep going
                                    } catch (IllegalArgumentException e) {
                                        throw new RuntimeException(e);
                                    } catch (IllegalAccessException e) {
                                        throw new RuntimeException(e);
                                    }
                                    try {
                                        Field methodField;
                                        while (connectionClass != null) {
                                            try {
                                                methodField = connectionClass
                                                        .getDeclaredField("method");
                                            } catch (NoSuchFieldException e) {
                                                connectionClass = connectionClass
                                                        .getSuperclass();
                                                continue;
                                            }
                                            methodField.setAccessible(true);
                                            methodField.set(httpURLConnection,
                                                    method);
                                            break;
                                        }
                                    } catch (final Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                return null;
                            }
                        });
            } catch (final PrivilegedActionException e) {
                final Throwable cause = e.getCause();
                if (cause instanceof RuntimeException) {
                    throw (RuntimeException) cause;
                } else {
                    throw new RuntimeException(cause);
                }
            }
        }
    }

    /**
     * Private class for password based authentication
     *
     */
    private static class DefaultPasswordAuthenticator extends Authenticator {

        /**
         * Username
         */
        private String userName;

        /**
         * Password
         */
        private String password;

        /**
         * Instantiates a new Default password authenticator.
         *
         * @param userName the user name
         * @param password the password
         */
        public DefaultPasswordAuthenticator(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        /**
         * Gets password authentication.
         *
         * @return the password authentication
         */
        public PasswordAuthentication getPasswordAuthentication() {
            return (new PasswordAuthentication(userName, password.toCharArray()));
        }
    }

}

