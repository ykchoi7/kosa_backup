package com.my.product.dao;

import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.dto.Product;

//interface의 하위클래스가 여러개
public class ProductDAOList implements ProductDAOInterface{
	private List<Product> products;
	public ProductDAOList() {
		products = new ArrayList<>();
	}
	
	@Override
	//target - 이 annotation이 선언될 수 있는 위치
	//retention - 컴파일러에게 영향을 미치는 어노테이션 
	public void insert(Product product) throws AddException {
		for (Product p : products) { //저장소에 있는 제품을 보면서 no가 이미 있으면 저장 불가
//			if (p.getProdNo().equals(product.getProdNo())) {
			if (p.equals(product)) {
				throw new AddException("이미 존재하는 상품입니다");
			}
		}
		products.add(product);
	}

	@Override
	public Product selectByProdNo(String no) throws FindException {
		for (Product p : products) {
			if (p.getProdNo().equals(no)) {
				return p;
			}
		}
		throw new FindException("상품이 없습니다");
	}

	@Override
	public Object selectAll() throws FindException {
		if (products.size() == 0) {
			throw new FindException("상품이 한개도 없습니다");
		}
		return products;
	}

	@Override
	public void update(Product p) throws ModifyException {
		for (Product savedP : products) {  //p는 입력받아온 값, savedP는 기존에 있던 값
			if (savedP.getProdNo().equals(p.getProdNo())) { //받아온 값이 기존에 있던 no와 같으면
				if (p.getProdName() != null) {  //no가 null이 아니면
					savedP.setProdName(p.getProdName()); //기존값을 받아온 값으로 바꾸기			
				}
				if(p.getProdPrice() != 0) {  //price가 0이 아니면
					savedP.setProdPrice(p.getProdPrice()); //기존값을 받아온 값으로 바꾸기
				}
				return;
			}
		}
		throw new ModifyException("상품이 없습니다");
	}

	@Override
	public void delete(String prodNo) throws RemoveException {
		for (Product savedP : products) {
			if (savedP.getProdNo().equals(prodNo)) {
				products.remove(savedP);
				return;
			}
		}
		throw new RemoveException("상품이 없습니다");
	}

}
