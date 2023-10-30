package com.my.jpa.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.jpa.entity.A;

@SpringBootTest
public class JPATest {
	@PersistenceContext
	EntityManager em;
	
	@Test
	@Transactional
	@Commit
	void test() {
		A a = new A();
		a.setA_1("two");
		a.setA_2(new BigDecimal(2));
		em.persist(a); //Managed 상태로 만드는 메소드
		em.flush();
		em.clear();
	}
}
