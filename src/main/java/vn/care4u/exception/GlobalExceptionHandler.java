package vn.care4u.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.care4u.enumeration.ErrorCode;
import vn.care4u.model.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(GeneralException.class)
	public ErrorResponse handleGeneralException(GeneralException ex) {
		ErrorCode errorCode = ex.getErrorCode();
		return new ErrorResponse(errorCode.name(), errorCode.getMessage());
	}
}
