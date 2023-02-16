package com.hotel.service;

import java.util.List;
import java.util.Map;

import com.hotel.dto.HotelDto;
import com.hotel.entity.Hotel;

public interface HotelService {

	Map<String,Object> createHotel(HotelDto hotelDto);
	
	List<Hotel> getAllHotels();
	
	Map<String, Object> getHotelById(String hotelName, String location);
	
}