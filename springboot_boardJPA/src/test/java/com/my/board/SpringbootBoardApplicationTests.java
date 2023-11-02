package com.my.board;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class SpringbootBoardApplicationTests {
//	Logger log = LoggerFactory.getLogger(getClass()); -> 이걸 대신할 수 있는 어노테이션이 @Slf4j
	@Autowired
	Environment env;
	
	@Value("${my.info.name}")
	private String name;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@DisplayName("Environment")
	void testEnv() {
		log.error("현재 사용중인 profile={}", Arrays.toString(env.getActiveProfiles()));
		//현재사용중인 profile=[dev]
		log.error("현재 사용중인 profile=" + Arrays.toString(env.getActiveProfiles()));
		//위 방법은 비추천! 플러스 연산을 하면 String 객체가 하나씩 새로 생겨서 필요없는 메모리 소모가 발생한다
		log.error("현재 사용중인 my.info.deploy.msg={}", env.getProperty("my.info.deploy.msg"));
		log.error("현재 사용중인 my.info.dev.msg={}", env.getProperty("my.info.dev.msg"));
		log.error("현재 사용중인 중복된 my.info.name={}", env.getProperty("my.info.name")); //dev
		log.error("@Value(my.info.name)={}", name); //dev
		env.getProperty("a", "없음"); //값이 없을 때 사용
	}

}
