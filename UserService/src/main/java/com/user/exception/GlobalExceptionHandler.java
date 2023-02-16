package com.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ApiResponse> handleException(Exception exception) {
		exception.printStackTrace();
		ApiResponse apiResponse = ApiResponse.builder().message(exception.getMessage())
				.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		return ResponseEntity.internalServerError().body(apiResponse);
	}
	
	@ExceptionHandler(value = UserException.class)
	public ResponseEntity<ApiResponse> handleException(UserException exception) {
		log.error("UserException in User-Service");
		exception.printStackTrace();
		ApiResponse apiResponse = ApiResponse.builder().message(exception.getMessage())
				.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.badRequest().body(apiResponse);
	}

	@Getter
	@Setter
	@Builder
	@ToString
	static class ApiResponse {
		private HttpStatus status;
		private String message;
	}
}