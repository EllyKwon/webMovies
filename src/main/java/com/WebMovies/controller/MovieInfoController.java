package com.WebMovies.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.webMovies.model.InfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import bit.com.a.model.CGVInfoDto;

@Controller
@Slf4j
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieInfoController {

	@ResponseBody
	@RequestMapping(value = "/crawling",  produces="text/plain;charset=UTF-8")
	public String getCrawling() {
		log.info("크롤리잉" + new Date());
		Document doc;
		String gson = "";
		
		try {
			 doc = Jsoup.connect("http://www.cgv.co.kr/movies/").get();
			/* Elements */
			 Elements ranks = doc.select(".rank");
			/* logger.info("rank" + ranks); */
			 
			 Elements imgs = doc.select(".thumb-image > img");
			/* logger.info("imgs" + imgs); */
			 
			 Elements movieAges = doc.select(".ico-grade");
			/* logger.info("ico-grade" + movieAges); */
			 
			 Elements movieTitles = doc.select("div.box-contents strong.title");
			/* logger.info("titles" + movieTitles); */
			 
			 Elements movieRates = doc.select(".percent span");
			/* logger.info("percents" + movieRates); */
			 
			 
			 Elements movieOpenDates = doc.select(".txt-info strong");
			/* logger.info("percents" + movieOpenDates); */
			
			 Elements likes = doc.select(".count strong>i");
			/* logger.info("counts" + likes); */
			 List<InfoVO> list = new ArrayList<InfoVO>();
			 
			 for(int i = 0; i < ranks.size(); i++) {
				
				 String rank = ranks.get(i).text();
				 String img = imgs.get(i).attr("src");
				 String movieAge = movieAges.get(i).text();
				 String movieTitle = movieTitles.get(i).text();
				 String movieRate = movieRates.get(i).text();
				 String movieOpenDate = movieOpenDates.get(i).text();
				 String like = likes.get(i).text();
				 int seq = i;
				 InfoVO cgvInfoDto = new InfoVO(rank, img, movieAge, movieTitle, movieRate, movieOpenDate, like, seq);
				 
				 log.info(cgvInfoDto.toString());
				 list.add(cgvInfoDto);
			 }
			 	gson = new Gson().toJson(list);
			 
		} catch (IOException e) {
			log.error("ERROR = {}", e.getMessage());
		}

		return gson;
	}
}
