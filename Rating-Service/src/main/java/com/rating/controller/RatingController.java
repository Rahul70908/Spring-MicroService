package com.rating.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rating.dto.RatingDto;
import com.rating.entity.Rating;
import com.rating.service.RatingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rating")
@Slf4j
public class RatingController {

	@Autowired
	RatingService ratingService;
	
	@Autowired
	ObjectMapper objectMapper;

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
	public ResponseEntity<List<Rating>> getByUserId(@RequestParam String userId) {
		return ResponseEntity.ok(ratingService.getByUserId(userId));
	}

	@GetMapping("/byhotelId")
	public ResponseEntity<Map<String, Object>> getByHotelId(@RequestParam String hotelId) {
		Map<String, Object> responseMap = ratingService.getAllByHotel(hotelId);
		return ResponseEntity.ok(responseMap);
	}
	
	@GetMapping("/getAllInList")
	public ResponseEntity<Map<String, Object>> getAllRatingsByUserId(@RequestParam String userIds) throws IOException {
		log.info("data {}", userIds);
		byte[] decodedUserIds = Base64.getDecoder().decode(userIds.getBytes());
		List readValue = objectMapper.readValue(decodedUserIds, List.class);
		Map<String, Object> responseMap = ratingService.getAllRatingsByUserIds(readValue);
		return ResponseEntity.ok(responseMap);
	}
}