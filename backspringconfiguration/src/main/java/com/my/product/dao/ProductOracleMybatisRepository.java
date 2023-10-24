package com.my.product.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.my.exception.FindException;
import com.my.product.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Repository(value = "productDAO")
@Setter @Getter @NoArgsConstructor  @AllArgsConstructor
public class ProductOracleMybatisRepository implements ProductRepository {
	
	@Autowired
	@Qualifier(value = "sqlSessionFactory") 
	private SqlSessionFactory sqlSessionFactory;
	
	
	
	@Override
	public List<Product> selectAll(int startRow, int endRow) throws FindException {	
		SqlSession session = null;
		List<Product> list = new ArrayList<>();	
		try {			
			session = sqlSessionFactory.openSession();
			//session.selectOne();
			Map<String, Integer> map = new HashMap<>();
			map.put("start", startRow);
			map.put("end", endRow);
			list = session.selectList("com.my.product.ProductMapper.selectAll",  map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	public int selectCount() throws FindException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int count = session.selectOne("com.my.product.ProductMapper.selectCount");
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
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
			if(p != null) {
				return p;
			}else {
				throw new FindException("상품이 없습니다");
			}
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = MyConnection.getConnection();
//		} catch (Exception e) {
//			throw new FindException(e.getMessage());
//		}
//		String selectByProdNoSQL = "SELECT *\r\n"
//				+ "		 FROM product\r\n"
//				+ "		 WHERE prod_no=?";
//		try {
//			pstmt = conn.prepareStatement(selectByProdNoSQL);
//			pstmt.setString(1, prodNo);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				return new Product(rs.getString("prod_no"),
//						           rs.getString("prod_name"),
//						           rs.getInt("prod_price")
//						);
//			}else {
//				throw new FindException("상품이 없습니다");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new FindException(e.getMessage());
//		} finally {
//			MyConnection.close(conn, pstmt, rs);
//		}
//		
	}

	public static void main(String[] args) {
		ProductOracleMybatisRepository repository = new ProductOracleMybatisRepository();
//		int startRow = 2;
//		int endRow = 4;
//		try {
//			List<Product> list = repository.selectAll(startRow, endRow);
//			System.out.println(list);
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
		
//		try {
//			System.out.println(repository.selectCount());
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
		try {
			Product p = repository.selectByProdNo("XXX"); //"C0001");
			System.out.println(p);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
	
	
}
