package advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class MyControllerAdvice {
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
//	public Map<String, Object> maxUploadSize(MaxUploadSizeExceededException e) {
	public ResponseEntity<?> maxUploadSize(MaxUploadSizeExceededException e) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("status", 0);
//		map.put("msg", e.getMaxUploadSize() + "파일크기가 너무 큽니다");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		headers.add("Access-Control-Allow-Origin", "http://192.168.1.13:5500");
		headers.add("Access-Control-Allow-Credentials", "true");
		return new ResponseEntity<>("파일 크기가 너무 큽니다", headers, HttpStatus.BAD_REQUEST); //js의 error로 이동
//		return new ResponseEntity<>(map, headers, HttpStatus.OK); //이 상태면 js의 success 부분으로 이동
//		return map;
	}
	
	
//	@ExceptionHandler(Exception.class)
//	@ResponseBody
//	public Map<String, Object> exceptionHandler(Exception e) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("status", 0);
//		map.put("msg", e.getMessage());
//		return map;
//	}
	
}