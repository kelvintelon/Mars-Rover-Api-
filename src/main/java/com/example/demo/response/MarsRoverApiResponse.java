package com.example.demo.response;

import java.util.ArrayList;
import java.util.List;

public class MarsRoverApiResponse {
	
	// starting point from response
	List<MarsPhotos> photos = new ArrayList<>();
 
 
	public List<MarsPhotos> getPhotos() {
		return photos;
	}


	public void setPhotos(List<MarsPhotos> photos) {
		this.photos = photos;
	}


	@Override
	public String toString() {
		return "MarsRoverApiResponse [photos=" + photos + "]";
	}

}
