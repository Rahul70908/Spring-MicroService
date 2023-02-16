package com.rating.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RatingDto {

	private String userId;
	private String hotelId;
	private Integer ratingg;
	private String feedback;
}