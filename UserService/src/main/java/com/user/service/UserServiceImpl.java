package com.user.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.user.dto.Hotel;
import com.user.dto.Rating;
import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.exception.UserException;
import com.user.feign.HotelFeign;
import com.user.feign.RatingFeign;
import com.user.repository.UserRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RatingFeign ratingFeign;

	@Autowired
	HotelFeign hotelFeign;

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public Map<String, Object> saveUser(UserDto userDto) {
		User user = new User();
		Map<String, Object> responseMap = new HashMap<>();
		if (Optional.ofNullable(userRepository.findByEmail(userDto.getEmail())).isEmpty()) {
			BeanUtils.copyProperties(userDto, user);
			userRepository.save(user);
		} else {
			throw new UserException("User Already Exist!!!");
		}
		responseMap.put("message", "User Saved SuccessFully");
		responseMap.put("status", HttpStatus.OK);
		return responseMap;
	}

	@Override
	public Map<String, Object> getUserById(Long userId) {
		Map<String, Object> responseMap = new HashMap<>();
		User user = userRepository.findById(userId).orElseThrow(() -> new UserException("User Not Found!!"));
		List<Rating> ratings = ratingFeign.getByUserId(String.valueOf(userId));
		Hotel hotel = null;
		for (Rating r : ratings) {
			Map<String, Object> hotelById = hotelFeign.getHotelById(r.getHotelId());
			hotel = objectMapper.convertValue(hotelById.get("data"), Hotel.class);
			r.setHotel(hotel);
		}
		user.setRatings(ratings);
		responseMap.put("UserDetails", user);
		responseMap.put("status", HttpStatus.OK);
		return responseMap;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getAllUsers() {
		Map<String, Object> responseMap = Maps.newHashMap();
		List<User> users = userRepository.findAll();
		List<Long> userIds = users.stream().map(User::getUserId).collect(Collectors.toList());
		String encodedUserIds = Base64.getEncoder().encodeToString(String.valueOf(userIds).getBytes());
		Map<String, Object> map = ratingFeign.getAllRatingsByUserId(encodedUserIds);
		if (!map.isEmpty()) {
			for (User u : users)
				u.setRatings((List<Rating>) map.get(String.valueOf(u.getUserId())));
		}
		responseMap.put("users", users);
		return responseMap;
	}

	@Override
	public Map<String, Object> deleteUser(Long userId) {
		Map<String, Object> responseMap = Maps.newHashMap();
		userRepository.deleteById(userId);
		responseMap.put("status", HttpStatus.OK);
		responseMap.put("message", "User Deleted SuccessFully");
		return responseMap;
	}

	@Override
	public Map<String, Object> updateUser(UserDto userDto) {
		Map<String, Object> responseMap = Maps.newHashMap();
		User user = userRepository.findByEmail(userDto.getEmail());
		if (ObjectUtils.isNotEmpty(userDto) && Optional.ofNullable(user).isPresent()) {
			BeanUtils.copyProperties(userDto, user);
			userRepository.save(user);
			responseMap.put("status", HttpStatus.OK);
			responseMap.put("message", "User Update SuccessFully!!");
		} else
			throw new UserException("User Not Found!!!");
		return responseMap;
	}

	@Data
	static class r {
		List<Rating> ratings;
	}
}