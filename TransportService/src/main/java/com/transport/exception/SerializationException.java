package com.transport.exception;

public class SerializationException extends TransportException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 572156066575653873L;

	public SerializationException() {
	}

	public SerializationException(String message) {
		super(message);
	}

	public SerializationException(Throwable e) {
		super(e);
	}

	public SerializationException(String message, Throwable e) {
		super(message, e);
	}
}
