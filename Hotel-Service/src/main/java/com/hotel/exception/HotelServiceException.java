package com.hotel.exception;

public class HotelServiceException extends RuntimeException {

	private static final long serialVersionUID = -208197251845540795L;

	public HotelServiceException() {
		super();
	}

	public HotelServiceException(String message) {
		super(message);
	}
}