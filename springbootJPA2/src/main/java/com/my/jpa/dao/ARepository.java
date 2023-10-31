package com.my.jpa.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.my.jpa.entity.A;

//public interface ARepository extends CrudRepository<A, String> {
	public interface ARepository extends JpaRepository<A, String>, ASearch {  //crud 기능 제공됨
													// id, 키 타입
	//쿼리메서드작성 가능 : findBy멤버변수명, 반환형은 List자료형
	List<A> findByA4(String a4);
	
	/**
	 * 단어를 포함한 a4멤버변수를 갖는 엔티티객체들을 반환한다
	 * @param word 단어
	 * @return
	 */
	List<A> findByA4Like(String word); //SELECT FROM WHERE a4 LIKE '%?%'
	
	/**
	 * 단어를 포함한 a4멤버변수를 갖는 엔티티객체들을 반환하고 a4 기준으로 오름차순 정렬한다
	 * @param word 단어
	 * @return
	 */
	List<A> findByA4LikeOrderByA4(String word); //where a4 like '%?%' order by a4
	
	
//	-------------------JPQL문법(JPA 전용 쿼리)을 사용--------------------
//	@Query("SELECT p FROM 엔티티클래스 AS p WHERE 멤버변수 LIKE %:word%")
	@Query(value="SELECT * FROM 테이블", nativeQuery = true)
    public List<A> findByName(
    		@org.springframework.data.repository.query.Param("word") 
    		String name);
	
//	@Query("SELECT a FROM A AS a WHERE a4 LIKE %:word%") //jpql 사용 버전
	@Query(value="SELECT * FROM a_tbl WHERE a4 LIKE :word", //해당 변수를 사용하겠다 - :word
			nativeQuery = true) 						//native query 버전 --> 이걸 더 권장
	public List<A> findByA4LikeJpql(String word);
	
	
	//특정 칼럼만 추출하는 경우 -> List<A>타입으로 반환할 수 없다! Object 배열 타입으로만 반환 가능!!
	@Query(value="SELECT a_1, a4 FROM a_tbl WHERE a4 LIKE :word",
			nativeQuery = true)
	public List<Object[]> findByA4LikeJpqlA1A4(String word);
	
	
	//select 말고 다른 기능들을 수행할 때
	@Modifying
	@Query(value="UPDATE a_tbl SET a_2=:a_2 WHERE a_1=:a_1", nativeQuery = true)
	public int modify(String a_1, BigDecimal a_2);
	
	//@Query - 정적 쿼리만 생성 가능
	//@Query DSL - 동적 쿼리 생성 가능, 사용하지 위해서는 queue domain, interface, 구현체 먼저 필요!!

}
