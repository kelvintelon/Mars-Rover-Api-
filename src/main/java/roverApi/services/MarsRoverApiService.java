package roverApi.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import roverApi.Dto.HomeDto;
import roverApi.response.MarsPhoto;
import roverApi.response.MarsRoverApiResponse;

@Service
public class MarsRoverApiService {
	
	private static final String API_KEY = "UWi1dpxXxyMNCjMSdoIQzV97os2O3cyacywDpjZp";
	
	private Map<String, List<String>> validCameras = new HashMap<>();
	
	public MarsRoverApiService () {
		validCameras.put("Opportunity", Arrays.asList("FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"));
		validCameras.put("Curiosity", Arrays.asList("FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "MARDI", "NAVCAM"));
		validCameras.put("Spirit", Arrays.asList("FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"));

	}

	public MarsRoverApiResponse getRoverData(HomeDto homeDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
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
	
	public List<String> getApiUrlEndPoints (HomeDto homeDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<String> urls = new ArrayList<>();
		
		// from Java reflection, gets all the methods from homeDto
		Method[] methods = homeDto.getClass().getMethods();
		
		// get the name of the getters of the cameras in homeDto into a string so we can refactor our code
		for (Method method : methods) {
			// if it finds the get Camera method and that method invoked(is called) returns true
			if (method.getName().indexOf("getCamera") > -1 && Boolean.TRUE.equals(method.invoke(homeDto))) {
				//  after we split we get the right hand side[1] and turn it uppercase, that is now our cameraName.
				String cameraName = method.getName().split("getCamera")[1].toUpperCase();
				// if the valid Camera map we made contains the cameraName of the rover then our API URL calls in the specific rover and camera to fetch pictures
				if (validCameras.get(homeDto.getMarsApiRoverData()).contains(cameraName)) {
					urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=" + cameraName);
				}
			}
		}
		
//		if (Boolean.TRUE.equals(homeDto.getCameraFhaz())) {
//			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=FHAZ");
//
//		}
//		if (Boolean.TRUE.equals(homeDto.getCameraChemcam()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
//			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=CHEMCAM");
//
//		}
//		if (Boolean.TRUE.equals(homeDto.getCameraMahli()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
//			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=MAHLI");
//
//		}
//		if (Boolean.TRUE.equals(homeDto.getCameraRhaz())) {
//			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=RHAZ");
//
//		}
//		if (Boolean.TRUE.equals(homeDto.getCameraMast()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
//			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=MAST");
//
//		}
//		if (Boolean.TRUE.equals(homeDto.getCameraMardi()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
//			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=MARDI");
//
//		}
//		if (Boolean.TRUE.equals(homeDto.getCameraNavCam())) {
//			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=NAVCAM");
//
//		}
//		if (Boolean.TRUE.equals(homeDto.getCameraPancam()) && !"curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
//			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=PANCAM");
//
//		}
//		if (Boolean.TRUE.equals(homeDto.getCameraMinites()) && !"curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
//			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY +"&camera=MINITES");
//
//		}
		return urls;
	}
}
