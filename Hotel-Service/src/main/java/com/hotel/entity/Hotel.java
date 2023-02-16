package com.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel")
@IdClass(HotelCompositeKey.class)
public class Hotel {

	@Id
	@Column(name = "hotel_name", length = 20)
	private String name;
	
	@Id
	@Column(name = "hotel_location")
	private String location;
	
	@Column(name = "hotel_about")
	private String about;
}