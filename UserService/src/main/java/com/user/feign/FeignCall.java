package com.user.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "Rating-Call", url = "${rating-service-url}")
public interface FeignCall {

	@GetMapping("/ratingById")
	Map<String, Object> getByUserId(@RequestParam String userId);
	
	@GetMapping("/getAllInList")
	Map<String, Object> getAllRatingsByUserId(@RequestParam String userIds);
}