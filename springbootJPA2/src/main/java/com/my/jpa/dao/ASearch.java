package com.my.jpa.dao;

import java.util.List;

import com.my.jpa.dto.ADTO;
import com.my.jpa.entity.A;

public interface ASearch {
	/**
	 * 전체 A Entity들을 Qdsl로 검색한다
	 * SELECT * FROM a_tbl;
	 * @return 
	 */
	List<A> search1();
	
	/**
	 * SELECT * FROM a_tbl WHERE a_2 >= 3
	 * @param a_2
	 * @return
	 */
	List<A> search2(int a_2);
	
	/**
	 * SELECT * FROM a_tbl WHERE a4 LIKE '%4f%'
	 * @param word ex) "4f" 문자열 전달
	 * @return
	 */
	List<A> search3(String word);
	
	/**
	 * SELECT * FROM a_tbl WHERE a1 LIKE '%t%' OR a4 LIKE '%t%'
	 * @param types - ex) {"a_1", "a4"} 배열로 선언
	 * @param word - ex) "t"
	 */
	List<A> search4(String[] types, String word);
	
	/**
	 * SELECT * FROM a_tbl WHERE a_2>=3 AND a4 LIKE '%4f%' 
	 * @param a_2 ex)3
	 * @param word ex)"4f"
	 * @return
	 */
	List<A> search5(int a_2, String word);

	
	void add(ADTO dto);
}
