package com.papara.base;

import java.util.Map;

/**
 * APICallPreHandler holds methods for getting payload, headermap and endpoint
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */

public interface APICallPreHandler {


	Map<String, String> getHeaderMap();

	/**
	 * Returns the payload for the API call. The implementation should take care
	 * in formatting the payload appropriately
	 * 
	 * @return Payload as String
	 */
	String getPayLoad();

	/**
	 * Returns the endpoint for the API call. The implementation may calculate
	 * the endpoint depending on parameters set on it. If no endpoint is found
	 * in the passed configuration, then SANDBOX endpoints (hardcoded in
	 * {@link Constants})are taken to be default for the API call.
	 * 
	 * @return Endpoint String.
	 */
	String getEndPoint();

	/**
	 * Returns {@link RequestOptions} configured for the api call
	 * 
	 * @return ICredential object
	 */
	RequestOptions getCredential();


	
	/**
	 * Return configurationMap
	 * 
	 * @return configurationMap in this call pre-handler
	 */
	public Map<String, String> getConfigurationMap();

}
