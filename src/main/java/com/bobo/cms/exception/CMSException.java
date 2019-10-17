package com.bobo.cms.exception;

public class CMSException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -4047031880260577803L;

	
	public CMSException() {
		
	} 
	
	public CMSException(String message) {
		super(message);
		
	} 
}
