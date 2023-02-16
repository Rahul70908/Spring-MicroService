package com.hotel.exception;

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
public class HotelGlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ApiResponse> exceptionResponse(Exception exception) {
		log.info("Exception in Hotel Service");
		exception.printStackTrace();
		return ResponseEntity.internalServerError().body(
				ApiResponse.builder().message(exception.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR).build());
	}
	
	@ExceptionHandler(value = HotelServiceException.class)
	public ResponseEntity<ApiResponse> exceptionResponse(HotelServiceException exception) {
		log.info("HotelException in Hotel Service");
		exception.printStackTrace();
		return ResponseEntity.badRequest().body(
				ApiResponse.builder().message(exception.getMessage()).status(HttpStatus.BAD_REQUEST).build());
	}

	@Getter
	@Setter
	@Builder
	static class ApiResponse {
		private HttpStatus status;
		private String message;
	}
}