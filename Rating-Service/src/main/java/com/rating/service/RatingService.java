package com.rating.service;

import java.util.Map;

import com.rating.dto.RatingDto;

public interface RatingService {

	Map<String, Object> saveRating(RatingDto ratingDto);
	
	Map<String, Object> getAllRating();
	
	Map<String, Object> getByUserId(String userId);
	
	Map<String, Object> getAllByHotel(String hotelId);
}