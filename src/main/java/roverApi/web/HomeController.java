package roverApi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import roverApi.Dto.HomeDto;
import roverApi.response.MarsRoverApiResponse;
import roverApi.services.MarsRoverApiService;

@Controller
public class HomeController {
	
	// this spring annotation helps us from initializing
	@Autowired
	private MarsRoverApiService roverService;
	
	@GetMapping("/")
	public String getHomeView(Model model, HomeDto homeDto) {
		if (!StringUtils.hasLength(homeDto.getMarsApiRoverData())) {
			homeDto.setMarsApiRoverData("Opportunity");
		}
		if (homeDto.getMarsSol() == null) {
			homeDto.setMarsSol(1);
		}
		MarsRoverApiResponse roverData = roverService.getRoverData(homeDto);
		
		model.addAttribute("roverData", roverData);
		model.addAttribute("homeDto", homeDto);
		
		return "index";
	}
	
}
