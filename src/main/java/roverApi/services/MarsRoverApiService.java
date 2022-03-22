package roverApi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import roverApi.Dto.HomeDto;
import roverApi.response.MarsPhoto;
import roverApi.response.MarsRoverApiResponse;

@Service
public class MarsRoverApiService {
	
	private static final String API_KEY = "UWi1dpxXxyMNCjMSdoIQzV97os2O3cyacywDpjZp";

	public MarsRoverApiResponse getRoverData(HomeDto homeDto) {
		
		RestTemplate rest = new RestTemplate();
		
		List<String> apiUrlEndpoints = getApiUrlEndPoints(homeDto);
		List<MarsPhoto> photos = new ArrayList<>();
		MarsRoverApiResponse response = new MarsRoverApiResponse();
		
		// wrapper
		apiUrlEndpoints.stream()
						.forEach(url -> {
							
						MarsRoverApiResponse apiResponse = rest.getForObject(url, MarsRoverApiResponse.class);
						photos.addAll(apiResponse.getPhotos());
						});
		
//		ResponseEntity<MarsRoverApiResponse> response = rest.getForEntity("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY, MarsRoverApiResponse.class);
		
	 response.setPhotos(photos);
	 return response;
	}
	
	public List<String> getApiUrlEndPoints (HomeDto homeDto) {
		List<String> urls = new ArrayList<>();
		
		if (Boolean.TRUE.equals(homeDto.getCameraFhaz())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=FHAZ");

		}
		if (Boolean.TRUE.equals(homeDto.getCameraChemcam()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=CHEMCAM");

		}
		if (Boolean.TRUE.equals(homeDto.getCameraMahli()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=MAHLI");

		}
		if (Boolean.TRUE.equals(homeDto.getCameraRhaz())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=RHAZ");

		}
		if (Boolean.TRUE.equals(homeDto.getCameraMast()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=MAST");

		}
		if (Boolean.TRUE.equals(homeDto.getCameraMardi()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=MARDI");

		}
		if (Boolean.TRUE.equals(homeDto.getCameraNavCam())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=NAVCAM");

		}
		if (Boolean.TRUE.equals(homeDto.getCameraPancam()) && !"curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=PANCAM");

		}
		if (Boolean.TRUE.equals(homeDto.getCameraMinites()) && !"curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=MINITES");

		}
		return urls;
	}
}
