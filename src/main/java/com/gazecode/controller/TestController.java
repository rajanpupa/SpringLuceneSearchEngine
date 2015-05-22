package com.gazecode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("/test")
public class TestController {

	@RequestMapping("/test")
	public String index(){
		return "test/index";
	}
}
