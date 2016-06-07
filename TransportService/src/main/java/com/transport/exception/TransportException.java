package com.transport.exception;

public class TransportException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8916346042935515883L;

	public TransportException() {
	}

	public TransportException(String message) {
		super(message);
	}

	public TransportException(Throwable e) {
		super(e);
	}

	public TransportException(String message, Throwable e) {
		super(message, e);
	}
}
