package com.ph.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


//	@Value("${hehe}")
	String aa;
	
	@RequestMapping("/test")
	public String test(){
		
		return aa;
	}
	
}
