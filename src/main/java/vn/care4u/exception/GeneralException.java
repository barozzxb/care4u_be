package vn.care4u.exception;

import vn.care4u.enumeration.ErrorCode;

public class GeneralException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int status = 400;
	private final ErrorCode errorCode;

    public GeneralException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    
    public GeneralException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

    public ErrorCode getErrorCode() {
        return errorCode;
    }
    
    public int getStatus() {
    	return status;
    }
}
