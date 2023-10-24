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
// 부가 기능을 언제 핵심로직에 적용할지를 정의한 것 -> Advice
// Controller 이외에서 CORS 문제를 해결하려면 여기서 처리해준다
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
//	public Map<String, Object> maxUploadSize(MaxUploadSizeExceededException e) {
	public ResponseEntity<?> maxUploadSize(MaxUploadSizeExceededException e) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);
		map.put("msg", e.getMaxUploadSize() + "파일크기가 너무 큽니다");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "http://192.168.1.13:5500");
		headers.add("Access-Control-Allow-Credentials", "true");
		return new ResponseEntity<>(map, headers, HttpStatus.OK);
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