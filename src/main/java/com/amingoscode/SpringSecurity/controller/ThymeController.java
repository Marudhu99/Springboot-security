package com.amingoscode.SpringSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ThymeController {
	
	@GetMapping("login")
	public String getLoginReview() {
		return "login";
	}
	
	@GetMapping("course")
	public String getCourses() {
		return "courses";
	}

}
