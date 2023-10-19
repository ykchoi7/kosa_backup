package com.my.di.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.my.di.dto.A;
import com.my.di.dto.B;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration  //xml 설정파일을 대신하는 스프링 설정 클래스
@ComponentScan(basePackages = {"com.my.di.dto", 
								"com.my.product.dao", 
								"com.my.product.service", 
								"com.my.customer.dao", 
								"com.my.customer.service", 
								"com.my.order.dao", 
								"com.my.order.service"}) //component-scan 태그를 대신
public class MyApplicationContext {
	
	@Bean
	public A a() {
		//A a = new A("welcome");
		
		A a = new A(); // A타입의 객체 생성
		a.setMsg("안녕하세요");
		return a;
	}
	
	@Bean
	public B b() {
		B b = new B();
		b.setNo(123);
		
		A a = a(); //a객체 반환받기 
		b.setA(a); //xml에 있는 ref="a"와 동일한 개념
		return b;
	}
	
	@Bean
	public SimpleDriverDataSource dataSource() {
		SimpleDriverDataSource sdds = new SimpleDriverDataSource();
		sdds.setDriverClass(oracle.jdbc.OracleDriver.class);
		sdds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		sdds.setUsername("hr");
		sdds.setPassword("hr");
		return sdds;
	}
	
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		config.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:xe");
		config.setUsername("hr");
		config.setPassword("hr");
		config.setMinimumIdle(3);
		return config;
	}
	
	@Bean
	public HikariDataSource dataSourceHikari() {
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSourceHikari());
		
		Resource resource = new ClassPathResource("mybatis-config.xml");
		ssfb.setConfigLocation(resource);
		return (SqlSessionFactory)ssfb.getObject();
	}

	
}
