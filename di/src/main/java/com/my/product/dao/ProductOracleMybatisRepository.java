package com.my.product.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.sql.MyConnection;

public class ProductOracleMybatisRepository implements ProductRepository {
	
	private SqlSessionFactory sqlSessionFactory;

	//DB를 mybatis로 연동
	public ProductOracleMybatisRepository() {
		String resource = "com/my/sql/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Product> selectAll(int startRow, int endRow) throws FindException {
		
		SqlSession session = null;
		List<Product> list = new ArrayList<>();
		try {
			session = sqlSessionFactory.openSession();
//			session.selectOne(); //return 자료형이 String -> SQL실행결과가 최대 행의 개수 1개일 때 selectOne() 사용
			Map<String, Integer> map = new HashMap<>();
			map.put("start", startRow);
			map.put("end", endRow);
			list = session.selectList("com.my.product.ProductMapper.selectAll", map); //return 자료형이 리스트 -> SQL실행결과가 여러행일 경우 selectList() 사용
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	@Override
	public Product selectByProdNo(String prodNo) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession(); //Connection
			Product p = session.selectOne("com.my.product.ProductMapper.selectByProdNo", prodNo);
			if (p != null) {
				return p;
			} else {
				throw new FindException("상품이 없습니다");
			}
		} catch (FindException e) {
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	@Override
	public int selectCount() throws FindException {
		SqlSession session = null;
		
		String selectCountSQL = "SELECT COUNT(*)\r\n"
								+ "FROM product";
		try {
			session = sqlSessionFactory.openSession();
			int count = session.selectOne("com.my.product.ProductMapper.selectCount");
			return count; 
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();				
			}
		}
	}

	
	public static void main(String[] args) {
		ProductOracleMybatisRepository repository = new ProductOracleMybatisRepository();
		
		//행 start부터 end까지의 product 불러오기 test
//		int startrow = 2;
//		int endrow = 3;
//		
//		try {
//			List<Product> list = repository.selectAll(startrow, endrow);
//			System.out.println(list);
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
		
		//전체 상품 수 불러오기 test
//		try {
//			System.out.println(repository.selectCount());
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
		
		//prodno로 상세정보 불러오기 test
		try {
			System.out.println(repository.selectByProdNo("C0001"));
		} catch (FindException e) {
			e.printStackTrace(); //상품번호가 없으면 '상품이 없습니다' 출력
		}
	}

}
