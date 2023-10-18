package com.my.product.service;

import java.util.List;

import com.my.exception.FindException;
import com.my.product.dao.ProductOracleMybatisRepository;
import com.my.product.dao.ProductOracleRepository;
import com.my.product.dao.ProductRepository;
import com.my.product.dto.PageGroup;
import com.my.product.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class ProductService {
	private ProductRepository repository;
	
//	public ProductService() {
//		repository = new ProductOracleRepository();
//		repository = new ProductOracleMybatisRepository();
//	}
	
	public PageGroup<Product> findAll(int currentPage) throws FindException {
		if (currentPage < 1) {
			currentPage = 1;
		}
		
		int cntPerPage = 3; //한 페이지당 보여줄 목록 수
		//currentPage						//1 2 3 4
		int startRow = (currentPage*3)-2;	//1 4 7 10
		int endRow = startRow+2;			//3 6 9 12
//		return repository.selectAll(startRow, endRow);
		
		List<Product> list = repository.selectAll(startRow, endRow);
		int totalCnt = repository.selectCount();
		
		/* PageGroup 클래스로 따로 빼기
		int cntPerPageGroup = 2; //페이지그룹에 보여줄 페이지 목록 수
		double totalPage = Math.ceil((double)(totalCnt/cntPerPageGroup)); //총 페이지 수 계산
		//currentPage		//1  2  3  4  5
		int startPage;		//1  1  3  3  5
		int endPage;		//2  2  4  4  6
		*/
		
		PageGroup pg = new PageGroup(list, currentPage, totalCnt);
		return pg;
		
	}
	
	public Product findByProdNo(String prodNo) throws FindException {
		return repository.selectByProdNo(prodNo);
	}
	
	public static void main(String[] args) {
		ProductService ps = new ProductService();
		try {
//			List<Product> list = ps.findAll(1);
//			System.out.println(list);
			PageGroup<Product> pg = ps.findAll(1);
			System.out.println(pg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
