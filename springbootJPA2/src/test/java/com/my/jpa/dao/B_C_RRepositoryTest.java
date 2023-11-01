package com.my.jpa.dao;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.jpa.entity.B;
import com.my.jpa.entity.C;
import com.my.jpa.entity.R;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class B_C_RRepositoryTest {
	@Autowired
	BRepository br;
	
	@Autowired
	CRepository cr;
	
	@Autowired
	RRepository rr;
	
	@Test
	@Transactional
	@Commit
	void test1C_Save() {
		for (int i = 1; i < 5; i++) {
//			C c = new C("id"+i, "n"+i);
			C c = C.builder()	//dto에서 @Builder를 추가함으로써 사용가능
					.cId("id"+i)
					.cName("n"+i)
					.build();
			cr.save(c);
		}
	}
	
	@Test
	@Transactional
	@Commit
	void test2B_Save() {
		Optional<C> optC = cr.findById("id1");
		Assertions.assertTrue(optC.isPresent());
		
		C c = optC.get();
		for (int i = 1; i <= 2; i++) {
			B b = new B();
			b.setBC(c); //게시글작성자
			br.save(b);
		}
	}
	
	@Test
	@Transactional
	@Commit
	void test3B_Find() {
		br.findAll();
		Iterable<B> it = br.findAll();
		log.error("br.findAll(): {}" + it);
		//br.findAll(): {}[B(bNo=1, bC=C(cId=id1, cName=n1)), B(bNo=2, bC=C(cId=id1, cName=n1))]
	}
	
	@Test
	@Transactional
	@Commit
	void test4R_Save() {
		for (long no=2L; no<=4L; no++) {
			R r = new R();
			r.setRNo(no); //댓글번호
			r.setBNo(1L); //게시글번호 - 1번글에 대한 댓글(long타입이기 때문에 1L로 표시)
			r.setRContent("댓글" + no); //댓글내용
			rr.save(r);
		}
	}
	
	@Test
	@Transactional
	@Commit
	void test5B_DeleteById() {
		br.deleteById(1L);
	}
	
	@Test
	@Transactional
	@Commit
	void test6C_findById() {
		Optional<C> optC = cr.findById("id1");
		Assertions.assertTrue(optC.isPresent());
		C c = optC.get();
		log.error("회원정보 {}, 게시글들 {}", c.getCName(), c.getBs());
	}
	
}
