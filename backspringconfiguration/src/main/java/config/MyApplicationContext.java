package config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@EnableTransactionManagement
public class MyApplicationContext {
	
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy"); //로그를 모두 보기 (기존에는 oracle.jdbc.OracleDriver 사용)
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
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager tx = new DataSourceTransactionManager();
		tx.setDataSource(dataSourceHikari());
		return tx;
	}
	
}
