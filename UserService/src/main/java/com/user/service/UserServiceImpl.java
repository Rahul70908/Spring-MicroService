package com.user.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.exception.UserException;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

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
		responseMap.put("UserDetails", user);
		responseMap.put("status", HttpStatus.OK);
		return responseMap;
	}

	@Override
	public Map<String, Object> getAllUsers() {
		Map<String, Object> responseMap = Maps.newHashMap();
		responseMap.put("status", HttpStatus.OK);
		responseMap.put("userList", userRepository.findAll());
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
		if (ObjectUtils.isNotEmpty(userDto)
				&& Optional.ofNullable(user).isPresent()) {
			BeanUtils.copyProperties(userDto, user);
			userRepository.save(user);
			responseMap.put("status", HttpStatus.OK);
			responseMap.put("message", "User Update SuccessFully!!");
		} else {
			throw new UserException("User Not Found!!!");
		}
		return responseMap;
	}
}