package com.user.service;

import java.util.Map;

import com.user.dto.UserDto;

public interface UserService {

	public Map<String, Object> saveUser(UserDto userDto);
	
	public Map<String, Object> getUserById(Long id);
	
	public Map<String, Object> getAllUsers();
	
	public Map<String, Object> deleteUser(Long id);
	
	public Map<String, Object> updateUser(UserDto userDto);
}