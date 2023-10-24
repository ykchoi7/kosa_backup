package control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.product.dto.Product;

@Controller
public class TestJsonController {
	
	@GetMapping(value = "/h", produces = "text/html;charset=UTF-8")
	@ResponseBody //Handler가 직접 응답을 하고 싶은 경우 (viewer를 사용하지 않음)
	public String h() {
		return "응답내용입니다";
	}
	
	@GetMapping(value="/i", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String i() {
		String jsonStr = "{"; //json 문자열 출력
		jsonStr += "\"status\":0";
		jsonStr += "}";
		return jsonStr;
	}
	
	@GetMapping(value="/j", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> j() {
		Map<String, Object> map = new HashMap<>();
		map.put("status", 1);
		map.put("msg", "JSON"); //map이 JSON 문자열 형태로 변환되어 출력
		return map;
	}
	
	@GetMapping(value="/k")
	@ResponseBody
	public Product k() {
		Product p = new Product();
		p.setProdNo("C0001");
		p.setProdName("아메리카노");
		return p;
	}
	
	@GetMapping(value="/m", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<String> m() { //응답을 ResponseEntity 타입으로 반환 (가장 많이 사용)
		String body = "응답내용입니다";
		HttpStatus status = HttpStatus.NOT_FOUND; //404 응답코드
		ResponseEntity<String> entity = new ResponseEntity<>(body, status);
		return entity;
	}
	
	@PostMapping("/p")
	@ResponseBody
	//ex) /p?prodNo=C0001&prodPrice=1500 요청전달데이터로 전달되지 않고
	//ex) /p요청시 JSON문자열형태로 요청전달데이터가 전달되면 @RequestBody로 데이터를 받아낼 수 있다: Rest 
	public void p(@RequestBody Product data) {
		System.out.println(data);
	}
	
}







