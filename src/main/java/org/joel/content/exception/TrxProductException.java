package org.joel.content.exception;

@SuppressWarnings("serial")
public class TrxProductException extends TrxException {

	public TrxProductException(String message) {
		super(message);
	}

	public TrxProductException(String message, Throwable cause) {
		super(message, cause);
	}

}
