package com.user.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.dto.Rating;

@FeignClient(name = "RATING-SERVICE/rating")//, url = "${rating-service-url}")
public interface RatingFeign {

	@GetMapping("/ratingById")
	List<Rating> getByUserId(@RequestParam String userId);
	
	@GetMapping("/getAllInList")
	Map<String, Object> getAllRatingsByUserId(@RequestParam String userIds);
}