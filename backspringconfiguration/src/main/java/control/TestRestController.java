package control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController //모든 메소드에 @ResponseBody 어노테이션을 사용하고자 할 때 클래스 자체에 @RestController 선언
public class TestRestController {
	
	@GetMapping("/n")
	//@ResponseBody
	public void n() {
	}
	
	@GetMapping("/o")
	//@ResponseBody
	public void o() {
	}
}
