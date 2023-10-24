package com.my.product.dao;

import java.util.List;

import com.my.exception.FindException;
import com.my.product.dto.Product;

public interface ProductRepository {
	/**
	 * 상품번호로 오름차순정렬된 상품들 중 시작행에서부터 끝행까지 상품들을 검색한다
	 * @param startRow 시작행
	 * @param endRow  끝행
	 * @return 상품들
	 * @throws FindException DB와의 연결실패하면 예외발생한다
	 */
	List<Product> selectAll(int startRow, int endRow) throws FindException;
	/**
	 * 전체상품수를 검색한다
	 * @return 전체상품수
	 * @throws FindException DB와의 연결실패하면 예외발생한다
	 */
	int selectCount() throws FindException;
	
	/**
	 * 상품번호에 해당하는 상품객체를 검색한다
	 * @param prodNo 상품번호
	 * @return 상품객체
	 * @throws FindException 상품이 없거나 DB와의 연결실패하면 예외발생한다
	 */
	Product selectByProdNo(String prodNo) throws FindException;
	
	
	
	
	
}
