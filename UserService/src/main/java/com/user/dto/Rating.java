package com.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

	private String ratingId;
	private String userId;
	private String hotelId;
	private Integer ratingg;
	private String feedback;
	private Hotel hotel;
}
