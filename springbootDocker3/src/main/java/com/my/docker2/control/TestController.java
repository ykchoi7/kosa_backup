package com.my.docker2.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.docker2.dao.DockerARepository;
import com.my.docker2.entity.DockerA;

@Controller
public class TestController {
	@Autowired
	private DockerARepository repository;
	
	@GetMapping("/a")
	@ResponseBody
	public String a() {
		DockerA entity = DockerA.builder().a1("a1_1").a2("a2_1").build();
		repository.save(entity);
		return entity.toString();
	}
}
