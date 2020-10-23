package com.papara.base;

import com.papara.api.IPaparaService;

import java.util.Map;

/**
 * Papara merchant service facade.
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @see IPaparaService
 * @version 0.0.1
 * @since 0.0.1
 */
public class APIContext {

	private RequestOptions credential;

	/**
	 * Instantiates a new Api context.
	 */
	public APIContext() {
		super();
		this.credential = new RequestOptions(null);
	}

	/**
	 * Instantiates a new Api context.
	 *
	 * @param apiKey the api key
	 */
	public APIContext(String apiKey) {
		super();
		if (apiKey == null || apiKey.length() <= 0) {
			throw new IllegalArgumentException("AccessToken cannot be null");
		}
		this.credential = new RequestOptions(apiKey);
	}


	/**
	 * Instantiates a new Api context.
	 *
	 * @param apiKey     the api key
	 * @param accountNum the account num
	 */
	public APIContext(String apiKey, String accountNum) {
		super();
		if (apiKey == null || apiKey.length() <= 0) {
			throw new IllegalArgumentException("AccessToken cannot be null");
		}
		this.credential = new RequestOptions(apiKey, accountNum);
	}


	/**
	 * Instantiates a new Api context.
	 *
	 * @param apiKey         the api key
	 * @param accountNum     the Papara account number
	 * @param mode           the mode, Sandbox or Live
	 * @param configurations the configurations
	 */
	public APIContext(String apiKey, String accountNum, String mode, Map<String, String> configurations) {
		this.credential = new RequestOptions(apiKey, accountNum);
		if (configurations != null && configurations.size() > 0) {
			this.credential.addConfigurations(configurations);
		}
		this.setMode(mode);
	}

	/**
	 * Instantiates a new Api context.
	 *
	 * @param apiKey     the api key
	 * @param accountNum the account num
	 * @param mode       the mode
	 */
	public APIContext(String apiKey, String accountNum, String mode) {
		this(apiKey, accountNum, mode, null);
	}

	/**
	 * Sets mode.
	 *
	 * @param mode the mode
	 * @return the mode
	 */
	public APIContext setMode(String mode) {
		if (mode == null || !(mode.equals(Constants.LIVE) || mode.equals(Constants.SANDBOX))) {
			throw new IllegalArgumentException("Mode needs to be either `sandbox` or `live`.");
		}
		this.credential.addConfiguration(Constants.MODE, mode);
		return this;
	}

	/**
	 * Gets http headers.
	 *
	 * @return the http headers
	 */
	public Map<String, String> getHTTPHeaders() {
		return this.credential.getHeaders();
	}

	/**
	 * Gets http header.
	 *
	 * @param key the key
	 * @return the http header
	 */
	public String getHTTPHeader(String key) {
		return this.credential.getHeader(key);
	}

	/**
	 * Sets http headers.
	 *
	 * @param httpHeaders the http headers
	 * @return the http headers
	 */
	public APIContext setHTTPHeaders(Map<String, String> httpHeaders) {
		this.credential.setHeaders(httpHeaders);
		return this;
	}

	/**
	 * Add http headers api context.
	 *
	 * @param httpHeaders the http headers
	 * @return the api context
	 */
	public APIContext addHTTPHeaders(Map<String, String> httpHeaders) {
		this.credential.addHeaders(httpHeaders);
		return this;
	}

	/**
	 * Add http header api context.
	 *
	 * @param key   the key
	 * @param value the value
	 * @return the api context
	 */
	public APIContext addHTTPHeader(String key, String value) {
		this.credential.addHeader(key, value);
		return this;
	}

	/**
	 * Gets configuration map.
	 *
	 * @return the configuration map
	 */
	public Map<String, String> getConfigurationMap() {
		return this.credential.getConfigurations();
	}

	/**
	 * Sets configuration map.
	 *
	 * @param configurationMap the configuration map
	 * @return the configuration map
	 */
	public APIContext setConfigurationMap(Map<String, String> configurationMap) {
		this.credential.setConfigurations(configurationMap);
		return this;
	}

	/**
	 * Add configurations api context.
	 *
	 * @param configurations the configurations
	 * @return the api context
	 */
	public APIContext addConfigurations(Map<String, String> configurations) {
		this.credential.addConfigurations(configurations);
		return this;
	}

	/**
	 * Add configuration api context.
	 *
	 * @param key   the key
	 * @param value the value
	 * @return the api context
	 */
	public APIContext addConfiguration(String key, String value) {
		this.credential.addConfiguration(key, value);
		return this;
	}

	/**
	 * Gets configuration.
	 *
	 * @param key the key
	 * @return the configuration
	 */
	public String getConfiguration(String key) {
		return this.credential.getConfiguration(key);
	}

	/**
	 * Gets headers map.
	 *
	 * @return the headers map
	 */
	public Map<String, String> getHeadersMap() {
		return this.getHTTPHeaders();
	}

	/**
	 * Sets headers map.
	 *
	 * @param headersMap the headers map
	 */
	public void setHeadersMap(Map<String, String> headersMap) {
		this.setHTTPHeaders(headersMap);
	}

	/**
	 * Gets account num.
	 *
	 * @return the account num
	 */
	public String getAccountNum() {
		if (this.credential == null) {
			throw new IllegalArgumentException(
					"Papara Account Number is required. Please use APIContext(String clientID, String clientSecret, String mode)");
		}
		return this.credential.getAccountNum();
	}

	/**
	 * Fetch api key string.
	 *
	 * @return the string
	 */
	public String fetchApiKey() {
		if (this.credential == null) {
			throw new IllegalArgumentException(
					"ApiKey is required. Please use APIContext(String apiKey)");
		}
		return this.credential.getApiKey();
	}
}
