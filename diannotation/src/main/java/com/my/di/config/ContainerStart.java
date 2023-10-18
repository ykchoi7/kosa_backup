package com.my.di.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.customer.dao.CustomerRepository;
import com.my.customer.service.CustomerService;
import com.my.di.dto.A;
import com.my.di.dto.B;
import com.my.exception.FindException;
import com.my.order.dao.OrderRepository;
import com.my.order.service.OrderService;
import com.my.product.dao.ProductRepository;
import com.my.product.service.ProductService;

//spring container를 구동시켜줄 클래스
public class ContainerStart {
	
	//스프링엔진 = 스프링 컨테이너 = 스프링 어플리케이션컨택스트
	public static void main(String[] args) {
		String configFileName = "myApplicationContext.xml";
		
		//스프링엔진이 시작한다 = 스프링 컨테이너가 구동된다, 스프링 어플리케이션컨택스트가 생성됐다
		ApplicationContext ctx;
		ctx = new ClassPathXmlApplicationContext(configFileName);
		
		//스프링컨테이너에 있는 스프링 객체를 찾는다
		A a1 = ctx.getBean("a", com.my.di.dto.A.class);
		System.out.println(a1);
		
		//spring bean은 싱글톤 타입으로 관리된다
		A a2 = ctx.getBean("a", com.my.di.dto.A.class);
		System.out.println(a2);
		
		System.out.println("싱글톤여부: " + (a1 == a2)); //true
		System.out.println("msg: " + a1.getMsg());
		
		B b1 = ctx.getBean("b", com.my.di.dto.B.class);
		System.out.println("b의 no:" + b1.getNo()); //999 
		//=> 값을 바꾸고 싶으면 xml 파일만 수정하면 된다! 결합도 떨어트리는 작업 (의존성 주입)
		
		
		//Product
		ProductRepository r1 = ctx.getBean("productDAO", com.my.product.dao.ProductRepository.class);
		System.out.println(r1);
		
		ProductService ps1 = ctx.getBean("productService", com.my.product.service.ProductService.class);
		try {
			System.out.println(ps1.findAll(1));
		} catch (FindException e) {
			e.printStackTrace();
		}
		
//		//Customer
//		CustomerRepository c1 = ctx.getBean("customerDAO", com.my.customer.dao.CustomerRepository.class);
//		System.out.println(c1);
//		CustomerService cs1 = ctx.getBean("customerService", com.my.customer.service.CustomerService.class);
////		System.out.println(cs1);
//		try {
//			cs1.login("B", "b");
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
//
//		//Order
//		OrderRepository o1 = ctx.getBean("orderDAO", com.my.order.dao.OrderRepository.class);
//		System.out.println(o1);
//		OrderService os1 = ctx.getBean("orderService", com.my.order.service.OrderService.class);
////		System.out.println(os1);
//		try {
//			System.out.println(os1.findById("B"));
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
	}
	
}
