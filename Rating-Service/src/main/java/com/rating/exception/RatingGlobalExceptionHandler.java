package com.rating.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RatingGlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ApiResponse> handleException(Exception exception) {
		log.error("Exception in Rating Service");
		exception.printStackTrace();
		return ResponseEntity.internalServerError().body(
				ApiResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build());
	}

	@ExceptionHandler(value = RatingException.class)
	public ResponseEntity<ApiResponse> handleRatingException(RatingException exception) {
		log.error("Rating Exception in rating-service");
		exception.printStackTrace();
		return ResponseEntity.badRequest().body(ApiResponse.builder().status(HttpStatus.BAD_REQUEST)
				.message(exception.getMessage()).build());
	}
	
	@Getter
	@Setter
	@Builder
	static class ApiResponse {
		private HttpStatus status;
		private String message;
	}
}