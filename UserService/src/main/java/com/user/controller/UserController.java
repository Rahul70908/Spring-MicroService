package com.user.controller;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.user.dto.UserDto;
import com.user.exception.UserException;
import com.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/save")
	public ResponseEntity<Map<String, Object>> saveUser(@RequestBody UserDto userDto) {
		log.info("Enter in saveUser()");
		Map<String, Object> responseMap = Maps.newHashMap();
		try {
			responseMap = userService.saveUser(userDto);
		} catch (UserException e) {
			log.error("Exception in Saving User {}", e);
			responseMap.put("status", e.getMessage());
			return ResponseEntity.badRequest().body(responseMap);
		}
		return ResponseEntity.ok(responseMap);
	}

	@GetMapping("/getById")
	public ResponseEntity<Map<String, Object>> getUserById(@RequestParam("id") Long userId) {
		Map<String, Object> responseMap = Maps.newHashMap();
		try {
			responseMap = userService.getUserById(userId);
		} catch (UserException e) {
			responseMap.put("status", e.getMessage());
			return ResponseEntity.badRequest().body(responseMap);
		}
		return ResponseEntity.ok(responseMap);
	}

	@GetMapping("/allUsers")
	public ResponseEntity<Map<String, Object>> getAllUsers() {
		Map<String, Object> responseMap = Maps.newHashMap();
		responseMap = userService.getAllUsers();
		return ResponseEntity.ok(responseMap);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Map<String, Object>> deleteUser(@RequestParam("id") Long userId) {
		return ResponseEntity.of(Optional.of(userService.deleteUser(userId)));
	}

	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> updateUser(@RequestBody UserDto userDto) {
		Map<String, Object> responseMap = Maps.newHashMap();
		try {
			responseMap = userService.updateUser(userDto);
		} catch (UserException e) {
			return ResponseEntity.badRequest().body(Collections.emptyMap());
		}
		return ResponseEntity.ok(responseMap);
	}
}