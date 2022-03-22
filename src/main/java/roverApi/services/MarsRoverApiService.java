package roverApi.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import roverApi.response.MarsRoverApiResponse;

@Service
public class MarsRoverApiService {
	
	private static final String API_KEY = "UWi1dpxXxyMNCjMSdoIQzV97os2O3cyacywDpjZp";

	public MarsRoverApiResponse getRoverData(String roverType) {
		
		RestTemplate rest = new RestTemplate();
		
		ResponseEntity<MarsRoverApiResponse> response = rest.getForEntity("https://api.nasa.gov/mars-photos/api/v1/rovers/"+roverType+"/photos?sol=2&api_key=" + API_KEY, MarsRoverApiResponse.class);
		
		return response.getBody();
	}
}
