package com.papara.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * While sending a request to API, request options help configurating the request.
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @see PaparaModel
 * @version 0.0.1
 * @since 0.0.1
 */
public final class RequestOptions {

	private static final Logger log = LoggerFactory.getLogger(RequestOptions.class);

	private String accountNum;

	private String apiKey;

	private Map<String, String> headers = new HashMap<String, String>();

	private String refreshToken;

	private Map<String, String> configurationMap;

	/**
	 * Instantiates a new Request options.
	 *
	 * @param apiKey the api key
	 */
	public RequestOptions(String apiKey) {
		super();
		this.apiKey = apiKey;
	}

	/**
	 * Instantiates a new Request options.
	 *
	 * @param apiKey     the api key
	 * @param accountNum the account num
	 */
	public RequestOptions(String apiKey, String accountNum) {
		super();
		this.accountNum = accountNum;
		this.apiKey = apiKey;
	}

	/**
	 * Has api key and account num boolean.
	 *
	 * @return the boolean
	 */
	public boolean hasApiKeyAndAccountNum() {
		return (this.accountNum != null) && (this.apiKey != null);
	}

	/**
	 * Sets headers.
	 *
	 * @param headers the headers
	 * @return the headers
	 */
	public RequestOptions setHeaders(Map<String, String> headers) {
		this.headers = headers;
		return this;
	}

	/**
	 * Add headers request options.
	 *
	 * @param headers the headers
	 * @return the request options
	 */
	public RequestOptions addHeaders(Map<String, String> headers) {
		this.headers.putAll(headers);
		return this;
	}

	/**
	 * Add header request options.
	 *
	 * @param key   the key
	 * @param value the value
	 * @return the request options
	 */
	public RequestOptions addHeader(String key, String value) {
		this.headers.put(key, value);
		return this;
	}

	/**
	 * Gets headers.
	 *
	 * @return the headers
	 */
	public Map<String, String> getHeaders() {
		return this.headers;
	}

	/**
	 * Gets header.
	 *
	 * @param key the key
	 * @return the header
	 */
	public String getHeader(String key) {
		return this.headers.get(key);
	}

	/**
	 * Gets Papara account num.
	 *
	 * @return the account num
	 */
	public String getAccountNum() {
		return this.accountNum;
	}

	/**
	 * Gets api key.
	 *
	 * @return the api key
	 */
	public String getApiKey() {
		return this.apiKey;
	}

	/**
	 * Add configuration request options.
	 *
	 * @param key   the key
	 * @param value the value
	 * @return the request options
	 */
	public RequestOptions addConfiguration(String key, String value) {
		if (this.configurationMap == null) {
			this.configurationMap = new HashMap<String, String>();
		}
		this.configurationMap.put(key, value);
		return this;
	}

	/**
	 * Add configurations request options.
	 *
	 * @param configurations the configurations
	 * @return the request options
	 */
	public RequestOptions addConfigurations(Map<String, String> configurations) {
		if (this.configurationMap == null) {
			this.configurationMap = new HashMap<String, String>();
		}
		this.configurationMap.putAll(configurations);
		return this;
	}

	/**
	 * Sets configurations.
	 *
	 * @param configurations the configurations
	 * @return the configurations
	 */
	public RequestOptions setConfigurations(Map<String, String> configurations) {
		this.configurationMap = configurations;
		return this;
	}

	/**
	 * Gets configurations.
	 *
	 * @return the configurations
	 */
	public Map<String, String> getConfigurations() {
		return this.configurationMap;
	}

	/**
	 * Gets configuration.
	 *
	 * @param key the key
	 * @return the configuration
	 */
	public String getConfiguration(String key) {
		if (this.configurationMap != null) {
			return this.configurationMap.get(key);
		}
		return null;
	}

}
