package com.atsistemas.sanitas.calculadora.exceptions;

import org.springframework.http.HttpStatus;

public class OperacionesException extends RuntimeException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6547895839817063681L;

	private HttpStatus			httpStatus;

	public OperacionesException() {
		super();
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}

	public OperacionesException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
		super( message, cause, enableSuppression, writableStackTrace );
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}

	public OperacionesException( String message, Throwable cause ) {
		super( message, cause );
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}

	public OperacionesException( String message, HttpStatus httpStatus ) {
		super( message );
		this.httpStatus = httpStatus;
	}

	public OperacionesException( String message, HttpStatus httpStatus, Throwable cause ) {
		super( message, cause );
		this.httpStatus = httpStatus;
	}

	public OperacionesException( Throwable cause ) {
		super( cause );
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}

	public HttpStatus getHttpStatus() { return httpStatus; }

	public void setHttpStatus( HttpStatus httpStatus ) { this.httpStatus = httpStatus; }

}
