package egovframework.msa.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan("egovframework.*")
@SpringBootApplication
@EnableCircuitBreaker
public class CatalogsApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() { //이 객체를 통해서 getCustomerDetail 메소드 호출
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(CatalogsApplication.class, args);
	}

}
