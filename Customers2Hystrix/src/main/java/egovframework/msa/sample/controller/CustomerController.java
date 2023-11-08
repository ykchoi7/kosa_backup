package egovframework.msa.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	int cnt=0;
	@GetMapping("/{customerId}")
	public String getCustomerDetail(@PathVariable String customerId) {

//	1) timeout test : 기본 timeout값은 1000 (1초)
//		long milli = 3*1000;
//		try {
//			Thread.sleep(milli);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("request customerId :" + customerId);
//		return "[Customer id = " + customerId + " at " + System.currentTimeMillis() + "]";
//		//1초가 넘는 시간동안 응답이 없는 것도 오류라고 판단 -> 전파 차단 진행
		
//	2) exception test
//		throw new RuntimeException("I/O Exception");
		
//	3)circuit open test
		System.out.println("처리횟수:" + (++cnt));
		throw new RuntimeException("I/O Exception");

	}
}
