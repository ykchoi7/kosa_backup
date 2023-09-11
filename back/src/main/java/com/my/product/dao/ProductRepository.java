package com.my.product.dao;

import java.util.List;

import com.my.exception.FindException;
import com.my.product.dto.Product;

public interface ProductRepository {
	List<Product> selectAll(int startRow, int endRow) throws FindException;
}
