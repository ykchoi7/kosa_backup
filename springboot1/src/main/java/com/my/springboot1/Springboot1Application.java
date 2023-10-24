package com.my.springboot1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication //Config 설정용 class
//@ComponentScan(basePackages= {"control"}) 
//=> control패키지 자체를 springboot1 패키지의 하위로 바꿔주니 basePackages를 따로 설정해주지 않아도 동작한다 
public class Springboot1Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot1Application.class, args);
	}
	//이게 실행되어야 Dispatcher Servlet의 효과가 나타난다

}
