package com.webMovies.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InfoVO {

	String rank;
	String img;
	String movieAge;
	String movieTitle;
	String movieRate; //예매율
	String movieOpenDate; //개봉일
	String like;
	Integer seq;

	
}
	