package com.my.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.sql.MyConnection;

public class ProductOracleRepository implements ProductRepository {

	@Override
	public List<Product> selectAll(int startRow, int endRow) throws FindException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
//			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
		String selectAllSQL = "SELECT *\r\n"
				+ "		FROM ( SELECT rownum rn, a.*\r\n"
				+ "		           FROM (SELECT *\r\n"
				+ "		                 FROM product\r\n"
				+ "		                 ORDER BY prod_no\r\n"
				+ "		                ) a\r\n"
				+ "		      )\r\n"
				+ "		WHERE rn BETWEEN ? AND ?";
		
		List<Product> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(selectAllSQL);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery(); //송신해준 값을 resultset에 넣어주기
			while(rs.next()) { //rs 한 줄씩 읽으면서 값을 list에 저장
				String prodNo = rs.getString("PROD_NO");
				String prodName = rs.getString("PROD_NAME");
				int prodPrice = rs.getInt("PROD_PRICE");
				Product p = new Product(prodNo, prodName, prodPrice);
				list.add(p);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(conn, pstmt, rs);
		}
	}
	
	@Override
	public int selectCount() throws FindException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			throw new FindException(e.getMessage());
//			e.printStackTrace();
		}
		
		String selectCountSQL = "SELECT COUNT(*)\r\n"
								+ "FROM product";
		try {
			pstmt = conn.prepareStatement(selectCountSQL);
			rs = pstmt.executeQuery();
			rs.next(); //결과행이 하나뿐이기 때문에 while 돌 필요가 없고 한 행만 읽어오면 된다
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(conn, pstmt, rs);
		}
	}

	
	public static void main(String[] args) {
		ProductOracleRepository repository = new ProductOracleRepository();
		
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
		try {
			System.out.println(repository.selectCount());
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

}
