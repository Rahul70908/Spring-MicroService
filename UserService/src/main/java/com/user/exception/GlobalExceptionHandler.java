package com.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ApiResponse> handleException(Exception exception) {
		exception.printStackTrace();
		ApiResponse apiResponse = ApiResponse.builder().message(exception.getMessage())
				.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		return ResponseEntity.badRequest().body(apiResponse);
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@ToString
	static class ApiResponse {
		private HttpStatus status;
		private String message;
	}
}