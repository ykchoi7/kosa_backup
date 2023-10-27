package com.my.board.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration  //xml 설정파일을 대신하는 스프링 설정 클래스
				//모두 com.my.board의 하위 패키지로 들어왔기 때문에 component-scan 필요없음
@EnableTransactionManagement
@PropertySource("classpath:/db.properties")
public class MyApplicationContext {
	@Value("${spring.datasource.hikari.driver-class-name}")
	private String hikariDriverClassName; //db.properties에 있는 driver-class-name과 자동 맵핑
	@Autowired
	Environment env;

	/*
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
//		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy"); //로그를 모두 보기 (기존에는 oracle.jdbc.OracleDriver 사용)
		config.setDriverClassName(hikariDriverClassName); //외부 properties파일에서 불러오는 방법
		config.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:xe");
//		config.setUsername("hr");
		config.setUsername(env.getProperty("spring.datasource.hikari.username"));
		config.setPassword("hr");
		config.setMinimumIdle(3);
		return config;
	}
	*/
	
	@Bean
	@ConfigurationProperties("spring.datasource.hikari") 
	//Configuration에서만 사용되는 어노테이션 - db.properties의 상위 property 맵핑
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
		//config.setPassword(); -> 이런 setter들을 자동 호출 (따로 작성해줄 필요 없음!!)
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
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager tx = new DataSourceTransactionManager();
		tx.setDataSource(dataSourceHikari());
		return tx;
	}
	
}
