package com.papara.base;

import com.papara.api.IPaparaService;

/**
 * Papara Base Exception class
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @see Exception
 * @version 0.0.1
 * @since 0.0.1
 */
public class BaseException extends Exception {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5345825923487658213L;

	/**
	 * Instantiates a new Base exception.
	 *
	 * @param msg the msg
	 */
	public BaseException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new Base exception.
	 *
	 * @param msg       the msg
	 * @param exception the exception
	 */
	public BaseException(String msg, Throwable exception) {
		super(msg, exception);
	}

}