package egovframework.msa.sample.controller;

import java.text.SimpleDateFormat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@GetMapping("/{customerId}")
	public String getCustomerDetail(@PathVariable String customerId) {
		System.out.println("request customerId :" + customerId);
		long now = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss.SSS");
		return "[Customer id = " + customerId + " at " + sdf.format(now) + "]";
	}
}
