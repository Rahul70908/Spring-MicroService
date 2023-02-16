package com.hotel.entity;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelCompositeKey implements Serializable {
	
	private static final long serialVersionUID = 3826336749868295565L;

	@Column(name = "hotel_name", length = 20)
	private String name;
	
	@Column(name = "hotel_location")
	private String location;
}