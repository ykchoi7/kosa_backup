package com.my.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.my.jpa.dto.ADTO;
import com.my.jpa.entity.A;
import com.my.jpa.entity.QA;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

public class ASearchImpl extends QuerydslRepositorySupport implements ASearch {

	public ASearchImpl() {
		super(A.class);
	}

	public List<A> search1() {
		QA qa = QA.a; //pom.xml 등록
		JPQLQuery<A> query = from(qa); //SELECT * FROM a_tbl
		query.where(qa.a_1.eq("one")); //WHERE a_1='one'
		List<A> list = query.fetch();
		return list;
	}

	@Override
	public List<A> search2(int a_2) {
		QA qa = QA.a;
		JPQLQuery<A> query = from(qa); //SELECT * FROM a_tbl
		query.where(qa.a_2.goe(a_2)); //WHERE a_2 >= 3
		return query.fetch();
	}

	@Override
	public List<A> search3(String word) {
		QA qa = QA.a;
		JPQLQuery<A> query = from(qa);
		query.where(qa.a4.contains(word));
		return query.fetch();
	}

	@Override
	public List<A> search4(String[] types, String word) {
		QA qa = QA.a;
		JPQLQuery<A> query = from(qa); //SELECT * FROM a_tbl
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		for (String type : types) {
			switch(type) {
			case "a_1":
				booleanBuilder.or(qa.a_1.contains(word));
				break;
			case "a4":
				booleanBuilder.or(qa.a4.contains(word));
				break;
			}
			query.where(booleanBuilder); //WHERE a1 LIKE '%t%' OR a4 LIKE '%t%'	
		}
		return query.fetch();
	}

	@Override
	public List<A> search5(int a_2, String word) {
		QA qa = QA.a;
		JPQLQuery<A> query = from(qa);
		query.where(qa.a_2.goe(a_2)); //WHERE a_2>=3
		query.where(qa.a4.contains(word)); //AND a4 LIKE '%4f%' --and는 그냥 where문 나열하면됨
		return query.fetch();
	}

	
	@PersistenceContext
	private EntityManager em; //entitymanager 찾기 먼저
	@Override
	public void add(ADTO dto) {
		em
        .createNativeQuery("INSERT INTO a_tbl(a_1, a_2, a4) VALUES (?,?,?)")
                .setParameter(1, dto.getA1())
                .setParameter(2, dto.getA2())
                .setParameter(3, dto.getA4())
                .executeUpdate();
	}
}
