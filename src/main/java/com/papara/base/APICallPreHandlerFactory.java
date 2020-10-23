package com.papara.base;

/**
 * APICallPreHandlerFactory factory for returning implementations if {@link APICallPreHandler}
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public interface APICallPreHandlerFactory {

	/**
	 * Creates an instance of {@link APICallPreHandler}
	 * 
	 * @return APICallPreHandler
	 */
	APICallPreHandler createAPICallPreHandler();

}
