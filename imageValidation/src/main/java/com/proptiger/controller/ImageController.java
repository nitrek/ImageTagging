package com.proptiger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proptiger.service.ImageService;

@Controller
public class ImageController {

	@Autowired
	private ImageService imageService;

	@RequestMapping("fetch")
	@ResponseBody
	public String getTags() {
		imageService.fetchFromApi();
		return "Fetching done successfully";
	}
}
