package roverApi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import roverApi.response.MarsRoverApiResponse;
import roverApi.services.MarsRoverApiService;

@Controller
public class HomeController {
	
	// this spring annotation helps us from initializing
	@Autowired
	private MarsRoverApiService roverService;
	
	@GetMapping("/")
	public String getHomeView(Model model) {
		MarsRoverApiResponse roverData = roverService.getRoverData("curiosity");
		
		model.addAttribute("roverData", roverData);
		
		return "index";
	}
}
