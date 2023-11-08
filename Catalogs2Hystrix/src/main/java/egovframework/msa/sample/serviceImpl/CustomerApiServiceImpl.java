package egovframework.msa.sample.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import egovframework.msa.sample.service.CustomerApiService;

@Service
public class CustomerApiServiceImpl implements CustomerApiService {

	@Autowired
	private RestTemplate restTemplate;

	/*
	 * Circuit Open 
	 * 요청이 다음단계로 넘어가지 않고 미리 반환하는 작업
	 * 기본설정은 
	 * 10초동안 20개이상의 @HystrixCommand메서드호출이 발생했을때,
	 * 50%이상의 호출에서 에러가 발생되면 자동 Circuit Open된다.
	 *  Circuit Open되면 fallbackMethod로 처리
	 *  프로퍼티 : requestVolumeThreshold - @HystrixCommand메서드 호출건수(1)
	 *          errorThresholdPercentage - 에러발생확률(50)
	 */
	@Override
	@HystrixCommand(fallbackMethod = "getCustomerDetailFallback"
////	,commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="500") }
////	,commandProperties = {@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="1"),
////					      @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50")}      
	)
	
	//성공
	public String getCustomerDetail(String customerId) {
		return restTemplate.getForObject("http://localhost:8082/customers/" + customerId, String.class);
	}
	
	//실패
	public String getCustomerDetailFallback(String customerId, Throwable ex) {
		System.out.println("Error:" + ex); //에러메시지 출력
		return "고객(" + customerId + ")정보 조회가 지연되고 있습니다.";
	}
}
