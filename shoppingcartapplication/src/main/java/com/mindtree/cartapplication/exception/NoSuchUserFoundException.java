package com.mindtree.cartapplication.exception;

public class NoSuchUserFoundException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchUserFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoSuchUserFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoSuchUserFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoSuchUserFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoSuchUserFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
