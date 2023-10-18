package com.my.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;
import com.my.order.dto.OrderLine;
import com.my.product.dto.Product;
import com.my.sql.MyConnection;

public class OrderOracleRepository implements OrderRepository {

	
	@Override
	public List<OrderInfo> selectById(String orderId) throws FindException {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
		String selectByIdSQL = "SELECT order_no, order_id, order_dt,\r\n"
				+ "          order_quantity,\r\n"
				+ "          p.prod_no, prod_name,prod_price\r\n"
				+ "FROM order_info info JOIN order_line line ON ( info.order_no = line.order_line_no)\r\n"
				+ "                              JOIN product p ON (line.order_prod_no = p.prod_no)\r\n"
				+ "WHERE order_id = ?\r\n"
				+ "ORDER BY order_dt DESC";
		
		List<OrderLine> lines = new ArrayList<>();
		List<OrderInfo> list = new ArrayList<>();
		int oldOrderNo = 0;
		try {
			pstmt = conn.prepareStatement(selectByIdSQL);
			pstmt.setString(1, orderId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Integer orderNo = rs.getInt("order_no");
				if (oldOrderNo != orderNo) { //첫 행이거나 주문번호가 바뀔 때
					OrderInfo info = new OrderInfo();
					info.setOrderNo(orderNo);
					info.setOrderId(orderId);
					info.setOrderDt(rs.getDate("order_dt"));
					
					//lines에는 아직 값이 안 들어간 상태. 그 상태로 일단 껍데기만 info에 넣어둠
					lines = new ArrayList<>();
					info.setLines(lines);
					list.add(info);
					
					//orderNo 바꿔놓기
					oldOrderNo = orderNo;
				}
				//lines에 값을 넣어주기
				int orderQuantity = rs.getInt("order_quantity");
				String prodNo = rs.getString("prod_no");
				String prodName = rs.getString("prod_name");
				int prodPrice = rs.getInt("prod_price");
				Product orderP = new Product(prodNo, prodName, prodPrice);
				OrderLine line = new OrderLine(orderNo, orderP, orderQuantity);
				lines.add(line);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException();
		} finally {
			MyConnection.close(conn, pstmt, rs);
		}
		
	}
	
	
	@Override
	public void insert(OrderInfo info) throws AddException {
		Connection conn = null;
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}

		//DB와의 연결이 성공된 경우
		try {
			insertInfo(conn, info.getOrderId());
			insertLine(conn, info.getLines());
		} finally {
			MyConnection.close(conn, null, null);
			//Connection은 다른 메소드에서 닫지 않고 여기서만 닫는다
		}
		
	}
	

	public void insertInfo(Connection conn, String id) throws AddException {
		PreparedStatement pstmt = null;
		String insertInfoSQL = "INSERT INTO order_info(order_no, order_id, order_dt)\r\n"
					+ "VALUES (order_seq.NEXTVAL, ?, SYSDATE)";
		
		try {
			pstmt = conn.prepareStatement(insertInfoSQL);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			MyConnection.close(null, pstmt, null);
		}
	}

	
	private void insertLine(Connection conn, List<OrderLine> lines) throws AddException {
		PreparedStatement pstmt = null;
		String insertLineSQL = "INSERT INTO order_line(order_line_no, order_prod_no, order_quantity)\r\n"
				+ "VALUES (order_seq.CURRVAL, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(insertLineSQL);
			for (OrderLine line: lines) {
				String prodNo = line.getOrderP().getProdNo();
				int quantity = line.getOrderQuantity();
				pstmt.setString(1, prodNo);
				pstmt.setInt(2, quantity);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			MyConnection.close(null, pstmt, null);
		}
	}

	
}
