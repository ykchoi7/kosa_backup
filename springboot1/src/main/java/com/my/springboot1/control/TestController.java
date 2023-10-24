package com.my.springboot1.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@GetMapping("/a")
	public String a() {
		return "a.jsp"; //view name
	}
	
	@GetMapping("/b")
	@ResponseBody
	public String b() {
		return "welcome"; //응답내용
	}
	
	@GetMapping("/c")
	@ResponseBody
	public Map<String, Object> c() {
		Map<String, Object> map = new HashMap<>();
		map.put("status", 1);
		map.put("msg", "JSON문자열");
		return map;
	}
	
	@GetMapping("/d")
	@ResponseBody
	//http://localhost:8881/boot1/d?id=aaa&sal=123
	//http://localhost:8881/boot1/d?sal=123 -> id 기본 null값
//	public void d(String id, int sal) {
//		System.out.println(id + ":" + sal);
//	}
	public void d(@RequestParam(name="id") Optional<String> optId, int sal) {
//		if (optId.isPresent()) { //.isPresent(): null인지 아닌지 확인 가능 -> 값이 있는 경우에만 아래 코드 실행
//			String id = optId.get();
//			System.out.println(id.length() + ":" + sal);
//		}
		
		//람다식 표현
		optId.ifPresent(id -> {
			System.out.println(id.length() + " : " + sal);
		});
	}
	
}
