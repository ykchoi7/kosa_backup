package com.my.docker1.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@GetMapping("/a")
	@ResponseBody
	public String a() {
		return "WELCOME";
	}
}
