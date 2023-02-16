package com.rating.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.rating.dto.RatingDto;
import com.rating.entity.Rating;
import com.rating.exception.RatingException;
import com.rating.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingRepository;

	@Override
	public Map<String, Object> saveRating(RatingDto ratingDto) {
		Map<String, Object> responseMap = Maps.newHashMap();
		Rating rating = new Rating();
		BeanUtils.copyProperties(ratingDto, rating);
		ratingRepository.save(rating);
		responseMap.put("status", HttpStatus.OK);
		responseMap.put("message", "Rating Saved Successfully");
		return responseMap;
	}

	@Override
	public Map<String, Object> getAllRating() {
		Map<String, Object> responseMap = Maps.newHashMap();
		responseMap.put("ratings", ratingRepository.findAll());
		return responseMap;
	}

	@Override
	public Map<String, Object> getByUserId(String userId) {
		List<Rating> ratings;
		Map<String, Object> responseMap = Maps.newHashMap();
		if (StringUtils.isEmpty(userId)) {
			throw new RatingException("Rating Not Found!!!");
		} else {
			ratings = ratingRepository.findByUserId(userId);
			if (!ratings.isEmpty()) {
				responseMap.put("status", HttpStatus.OK);
				responseMap.put("ratings", ratings);
			} else {
				responseMap.put("status", HttpStatus.NOT_FOUND);
				responseMap.put("message", "No Data Found");
			}
		}
		return responseMap;
	}

	@Override
	public Map<String, Object> getAllByHotel(String hotelId) {
		Map<String, Object> responseMap = Maps.newHashMap();
		List<Rating> ratings = ratingRepository.findByHotelId(hotelId)
				.orElseThrow(() -> new RatingException("Rating Not Found"));
		responseMap.put("ratings", ratings);
		return responseMap;
	}

}
