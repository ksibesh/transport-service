package com.transport.exception;

public class ConnectionException extends TransportException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7676959120378812754L;

	public ConnectionException() {
	}

	public ConnectionException(String message) {
		super(message);
	}

	public ConnectionException(Throwable e) {
		super(e);
	}

	public ConnectionException(String message, Throwable e) {
		super(message, e);
	}
}
