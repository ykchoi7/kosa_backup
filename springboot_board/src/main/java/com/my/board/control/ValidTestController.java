package com.my.board.control;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.board.dto.Board;

@RestController
@Validated //유효성 검사
public class ValidTestController {
	@GetMapping(value = "/a", produces = "text/html;charset=UTF-8") 
										//MediaType.TEXT_HTML_VALUE - 문자만 바꿔주려면 필요없음
	public String a(String no, int sal) {
		//유효성검사
		if (no == null) {
			return "no값을 정확히 입력하세요";
		} else if (no.length()<3) {
			return "no는 3자리 이상으로 입력하세요";
		}
		return "성공";
	}

	//조건이 여러개이면 위 메소드처럼 조건문이 길어짐 -> 이걸 어노테이션을 통해 간단하게 표현 가능
	@GetMapping(value="/b", produces = "text/html;charset=utf-8")
	public String b(@NotNull(message="no값을 반드시 입력하세요") 
					@Size(min=3, message="no는 3자리 이상으로 입력하세요") 
					String no, int sal) {
		return "성공";
		//@NotNull	: no말고 sal변수만 전달하면 -> 500 에러 b.no: 널이어서는 안됩니다
		//@Size()	: no에 3자리 이하 값을 전달하면 -> 500에러 b.no: 크기가 3에서 2147483647 사이여야 합니다
	}
	
	//PathVariable 유효성검사
	@GetMapping("value=/e/{no}")
	public String e(@NotNull(message="no값을 반드시 입력하세요")
					@Size(min=3, message="no는 3자리 이상으로 입력하세요")
					@PathVariable String no) {
		return "성공";
	}
	
	//요청전달데이터 형태
	@GetMapping(value="/c", produces = "text/html;charset=utf-8")
	public String c(@Validated Board b) {
		return "성공";
		//DTO에서 설정한 유효성에 따라서 msg출력 ex)validation.BindException-글내용은 반드시 입력하세요
	}
	
	//JSON문자열 형태로 요청전달
	@GetMapping(value="/d", produces = "text/html;charset=utf-8")
	public String d(@Validated @RequestBody Board b) {
		return "성공";
	}
}
