package org.joel.content.exception;

@SuppressWarnings("serial")
public class TrxException extends RuntimeException {

	public TrxException(String message, Throwable cause) {
		super(message, cause);
	}

	public TrxException(String message) {
		super(message);
	}

}
