package egovframework.msa.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Value("${server.port}")
	private int serverPort;
	
	@GetMapping("/{customerId}")
	public String getCustomerDetail(HttpServletRequest request, @PathVariable String customerId) {
		//System.out.println("#8082 : request customerId :" + customerId);
		System.out.println("#" + serverPort + ": request customerId :" + customerId);
		
		return "[서비스포트는 #" + serverPort +", 요청포트는 #" + request.getRequestURL() +",  Customer id = " + customerId + " at " + System.currentTimeMillis() + "]";
	}
}