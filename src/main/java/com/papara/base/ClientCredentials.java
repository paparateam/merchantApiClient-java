package com.papara.base;

/**
 * Client Credentials holds Client ID and Client Secret.
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @see Exception
 * @version 0.0.1
 * @since 0.0.1
 */
public class ClientCredentials {


	private String clientID;

	private String clientSecret;

	/**
	 * Instantiates a new Client credentials.
	 */
	public ClientCredentials() {
		super();
	}

	/**
	 * Gets client id.
	 *
	 * @return the client id
	 */
	public String getClientID() {
		return clientID;
	}

	/**
	 * Sets client id.
	 *
	 * @param clientID the client id
	 */
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	/**
	 * Gets client secret.
	 *
	 * @return the client secret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * Sets client secret.
	 *
	 * @param clientSecret the client secret
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

}
