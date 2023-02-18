package com.hotel.controller;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.hotel.dto.HotelDto;
import com.hotel.exception.HotelServiceException;
import com.hotel.service.HotelService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hotel")
@Slf4j
public class HotelController {

	@Autowired
	HotelService hotelService;

	@PostMapping("/save")
	public ResponseEntity<Map<String, Object>> saveHotel(@RequestBody HotelDto hotelDto) {
		log.info("Enter in createHotel()");
		Map<String, Object> responseMap = Maps.newHashMap();
		if (ObjectUtils.isNotEmpty(hotelDto)) {
			responseMap = hotelService.createHotel(hotelDto);
		}
		return ResponseEntity.ok(responseMap);
	}

	@GetMapping("/allHotel")
	public ResponseEntity<Map<String, Object>> getAllHotel() {
		Map<String, Object> responseMap = Maps.newHashMap();
		responseMap.put("hotelDetails", hotelService.getAllHotels());
		return ResponseEntity.ok(responseMap);
	}

	@GetMapping("/getById")
	public ResponseEntity<Map<String, Object>> getHotelById(@RequestParam("id") String hotelId) {
		Map<String, Object> responseMap = Maps.newHashMap();
		try {
			responseMap = hotelService.getHotelById(hotelId);
		} catch (HotelServiceException e) {
			log.error("Exception in Fetching Hotel Detals");
			e.printStackTrace();
			responseMap.put("status", HttpStatus.BAD_REQUEST);
			responseMap.put("message", e.getMessage());
			return ResponseEntity.badRequest().body(responseMap);
		}
		return ResponseEntity.ok(responseMap);
	}
}