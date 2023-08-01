package com.my.product.dao;

import com.my.product.dto.Product;

//interface의 하위클래스가 여러개
public class ProductDAOList implements ProductDAOInterface{

	@Override
	public void insert(Product product) {
		
	}

	@Override
	public Product selectByProdNo(String no) {
		return null;
	}

	@Override
	public Product[] selectAll() {
		return null;
	}

}
