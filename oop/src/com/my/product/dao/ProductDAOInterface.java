package com.my.product.dao;

import com.my.product.dto.Product; //ctrl+shift+o -> import

public interface ProductDAOInterface {
	//interface가 갖는 모든 메서드는 public abstract 메서드이다!
	
	/**
	 * 상품을 저장소에 저장한다. 저장소가 꽉 찬 경우 "저장소가 꽉찼습니다" 메시지 출력되고 저장이 안된다
	 * @param product 상품
	 */
	void insert(Product product); //public abstract void insert(Product product);와 같음
	
	/**
	 * 상품번호에 해당하는 상품을 저장소에서 검색하여 반환한다
	 * @param prodNo 상품번호
	 * @return 상품객체. 번호에 해당 상품을 찾지 못하면 null을 반환한다
	 */
	Product selectByProdNo(String no);

	/**
	 * 저장소에 저장된 상품들만 반환한다
	 * @return 상품들. 저장소에 저장된 상품이 한 개도 없으면 null을 반환한다
	 */
	Product[] selectAll();
	
}

