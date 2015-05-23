package com.gazecode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/test")
	public String index(){
		return "test/index";
	}
	
	@RequestMapping("/customers")
	public String customers(){
		return "test/customers";
	}
}
