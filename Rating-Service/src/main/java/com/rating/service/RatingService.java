package com.rating.service;

import java.util.List;
import java.util.Map;

import com.rating.dto.RatingDto;
import com.rating.entity.Rating;

public interface RatingService {

	Map<String, Object> saveRating(RatingDto ratingDto);
	
	Map<String, Object> getAllRating();
	
	List<Rating> getByUserId(String userId);
	
	Map<String, Object> getAllByHotel(String hotelId);
	
	Map<String, Object> getAllRatingsByUserIds(List<Integer> userId);
}