package egovframework.msa.sample.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import egovframework.msa.sample.service.CustomerApiService;

@RestController
@RequestMapping("/catalogs/customerinfo")
public class CatalogsController {
	@Autowired
	private CustomerApiService customerApiService;

	@GetMapping(path = "/{customerId}")
	public String getCustomerInfo(@PathVariable String customerId) {
		String customerInfo = customerApiService.getCustomerDetail(customerId);
		System.out.println("response customerInfo : " + customerInfo);
		
		long now = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss.SSS");
		return String.format("catalog:[Customer id = %s at %s %s ]", customerId, sdf.format(now), customerInfo);
	}

}
