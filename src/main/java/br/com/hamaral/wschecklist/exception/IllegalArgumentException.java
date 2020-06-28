package br.com.hamaral.wschecklist.exception;

public class IllegalArgumentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IllegalArgumentException(String msg) {
		super(msg);
	}

	public IllegalArgumentException(String msg, Throwable cause) {
		super(msg, cause);
	}

}