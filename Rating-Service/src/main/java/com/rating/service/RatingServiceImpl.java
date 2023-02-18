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
	public List<Rating> getByUserId(String userId) {
		List<Rating> ratings;
		if (StringUtils.isEmpty(userId)) {
			throw new RatingException("Rating Not Found!!!");
		} else {
			ratings = ratingRepository.findByUserId(userId);
		}
		return ratings;
	}

	@Override
	public Map<String, Object> getAllByHotel(String hotelId) {
		Map<String, Object> responseMap = Maps.newHashMap();
		List<Rating> ratings = ratingRepository.findByHotelId(hotelId)
				.orElseThrow(() -> new RatingException("Rating Not Found"));
		responseMap.put("ratings", ratings);
		return responseMap;
	}

	@Override
	public Map<String, Object> getAllRatingsByUserIds(List<Integer> userId) {
		Map<String, Object> responseMap = Maps.newHashMap();
		List<Rating> ratings;
		Map<String, Object> ratingsMap = Maps.newHashMap();
		for(Integer s: userId) {
			ratings = ratingRepository.findByUserId(String.valueOf(s));
			ratingsMap.put(String.valueOf(s), ratings);
			responseMap.put("ratings", ratingsMap);
		}
		return ratingsMap;
	}
}
