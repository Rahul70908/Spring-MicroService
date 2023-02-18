package com.user.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "HOTEL-SERVICE/hotel")//, url = "${hotel-service-url}")
public interface HotelFeign {

	@GetMapping("/getById")
	Map<String, Object> getHotelById(@RequestParam("id") String hotelId);
}