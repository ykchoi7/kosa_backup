package egovframework.msa.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("egovframework.*")
@SpringBootApplication
@EnableEurekaClient
public class CustomersApplication {

	public static void main(String[] args) {
//		String profile = System.getProperty("spring.profiles.active");
//		if (profile == null) {
//			System.setProperty("spring.profiles.active", "dev");
//		}

		SpringApplication.run(CustomersApplication.class, args);
	}

}
