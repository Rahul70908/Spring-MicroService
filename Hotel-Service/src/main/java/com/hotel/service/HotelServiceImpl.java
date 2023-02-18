package com.hotel.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.hotel.dto.HotelDto;
import com.hotel.entity.Hotel;
import com.hotel.exception.HotelServiceException;
import com.hotel.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelRepository hotelRepository;

	@Override
	public Map<String, Object> createHotel(HotelDto hotelDto) {
		Map<String, Object> responseMap = Maps.newHashMap();
		Hotel hotel = hotelRepository.findByNameAndLocation(hotelDto.getName(), hotelDto.getLocation());
		if (ObjectUtils.isEmpty(hotel)) {
			hotel = new Hotel();
			BeanUtils.copyProperties(hotelDto, hotel);
			hotelRepository.save(hotel);
		} else {
			throw new HotelServiceException("Hotel Already Exist!!!");
		}
		responseMap.put("status", HttpStatus.OK);
		responseMap.put("message", "Hotel Saved SuccessFully");
		return responseMap;
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Map<String, Object> getHotelById(String hotelId) {
		Map<String, Object> responseMap = Maps.newHashMap();
		if (ObjectUtils.isEmpty(hotelId)) {
			throw new HotelServiceException("Required Fields can't be null!!!");
		}
		Hotel hotel = hotelRepository.findById(Long.valueOf(hotelId))
				.orElseThrow(() -> new HotelServiceException("Hotel Not Found!!!"));
		responseMap.put("data", hotel);
		responseMap.put("status", HttpStatus.OK);
		return responseMap;
	}
}