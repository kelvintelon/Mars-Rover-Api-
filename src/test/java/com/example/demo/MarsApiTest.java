package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.response.MarsRoverApiResponse;

public class MarsApiTest {

	
	@Test
	public void simpleGetTest () {
		RestTemplate rest = new RestTemplate();
		
		ResponseEntity<MarsRoverApiResponse> response = rest.getForEntity("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=2&api_key=DEMO_KEY", MarsRoverApiResponse.class);
		System.out.println(response.getBody());
	
	}
}
