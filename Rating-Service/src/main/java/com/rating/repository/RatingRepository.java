package com.rating.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rating.entity.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {
	
	Optional<List<Rating>> findByHotelId(String hotelId);
	
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByUserIdIn(List<Integer> userIds);
}