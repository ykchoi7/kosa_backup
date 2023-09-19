package com.my.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.my.customer.dto.Customer;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

public class CustomerOracleRepository implements CustomerRepository {

	@Override
	public Customer selectById(String id) throws FindException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}
		
		String selectByIdSQL = "SELECT *\r\n"
				+ "		 FROM customer\r\n"
				+ "		 WHERE id=?";
		
		try {
			pstmt = conn.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Customer(rs.getString("id"),
						           rs.getString("pwd")
						);
			}else {
				throw new FindException("id가 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(conn, pstmt, rs);
		}
	}

}
