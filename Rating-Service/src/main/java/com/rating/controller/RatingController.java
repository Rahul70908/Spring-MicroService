package com.rating.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rating.dto.RatingDto;
import com.rating.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	RatingService ratingService;

	@PostMapping("/save")
	public ResponseEntity<Map<String, Object>> saveRating(@RequestBody RatingDto ratingDto) {
		Map<String, Object> responseMap = ratingService.saveRating(ratingDto);
		return ResponseEntity.ok(responseMap);
	}

	@GetMapping("/allRating")
	public ResponseEntity<Map<String, Object>> getAllRating() {
		Map<String, Object> responseMap = ratingService.getAllRating();
		return ResponseEntity.ok(responseMap);
	}

	@GetMapping("/ratingById")
	public ResponseEntity<Map<String, Object>> getByUserId(@RequestParam String userId) {
		Map<String, Object> responseMap = ratingService.getByUserId(userId);
		return ResponseEntity.ok(responseMap);
	}

	@GetMapping("/byhotelId")
	public ResponseEntity<Map<String, Object>> getByHotelId(@RequestParam String hotelId) {
		Map<String, Object> responseMap = ratingService.getAllByHotel(hotelId);
		return ResponseEntity.ok(responseMap);
	}
}