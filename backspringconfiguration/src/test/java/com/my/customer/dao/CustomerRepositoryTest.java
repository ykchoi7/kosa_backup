package com.my.customer.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.customer.dto.Customer;
import com.my.exception.FindException;

@ExtendWith(SpringExtension.class) //스프링용 단위테스트를 위해 필요한 부분
@ContextConfiguration(classes = {config.MyApplicationContext.class}) //ApplicationContext(=스프링컨테이너 =스프링엔진) 생성 
class CustomerRepositoryTest {
	@Autowired
	CustomerRepository repository;
	//spring bean으로 관리되는 객체이기 때문에 바로 new 초기화하는 것이 아니다!

	@Test
	void test() {
		int i = 10; //실제 처리결과 데이터
		int expectedI = 10; //성공될 예상 데이터
		assertTrue(i == expectedI); //실제 값과 예상 값이 같을 것이라고 단정짓기 => 성공
		
		String msg = "hello";
		String expectedMsg = "hi";
		assertEquals(expectedMsg, msg); //테스트 실패 (AssertionFailedError)
	}
	
	@Test
	void testInsert() {
	
	}
	
	@Test
	@DisplayName("아이디로 고객검색테스트")
	void testSelectById() throws FindException {
		String id = "B";
		Customer c = repository.selectById(id);
		String expectedPwd = "b";
		String expectedName = "B";
		assertEquals(expectedPwd, c.getPwd());
		assertEquals(expectedName, c.getName());
	}

}
