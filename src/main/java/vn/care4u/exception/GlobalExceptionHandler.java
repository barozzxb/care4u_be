package vn.care4u.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.care4u.enumeration.ErrorCode;
import vn.care4u.model.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(GeneralException.class)
	public ApiResponse<Object> handleGeneralException(GeneralException ex) {
		ErrorCode errorCode = ex.getErrorCode();
		return ApiResponse.builder()
				.status(ex.getStatus())
				.message(errorCode.getMessage())
				.errorCode(errorCode.name())
				.body(null)
				.build();
	}
}
