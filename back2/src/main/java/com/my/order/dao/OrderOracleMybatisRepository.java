package com.my.order.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;
import com.my.order.dto.OrderLine;
import com.my.product.dto.Product;
import com.my.sql.MyConnection;

public class OrderOracleMybatisRepository implements OrderRepository {
	private SqlSessionFactory sqlSessionFactory;
	public OrderOracleMybatisRepository() {
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
	public List<OrderInfo> selectById(String orderId) throws FindException {
		SqlSession session = null;
		
		try {
			List<OrderInfo> list = new ArrayList<>();
			session = sqlSessionFactory.openSession();
			list = session.selectList("com.my.order.OrderMapper.selectById", orderId);
			System.out.println("주문기본(OrderInfo)객체 수 : " + list.size()); //3
			for (OrderInfo info: list) {				
				System.out.println(info.getOrderNo() + ":" + info.getOrderDt());
				for (OrderLine line: info.getLines()) {
					System.out.println("주문상세: 상품번호-"+line.getOrderP().getProdNo() + "수량-"+line.getOrderQuantity());
				}
				System.out.println("--------------------------");
			}
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
	public void insert(OrderInfo info) throws AddException {
		SqlSession session = null;

		//DB와의 연결이 성공된 경우
		try {
			session = sqlSessionFactory.openSession();
//			insertInfo(conn, info.getOrderId());
//			insertLine(conn, info.getLines());
			insertInfo(session, info.getOrderId());
			insertLine(session, info.getLines());
			session.commit();
		} catch(Exception e) {
			session.rollback();
			throw new AddException(e.getMessage());
		} finally {
//			MyConnection.close(conn, null, null);
			//Connection은 다른 메소드에서 닫지 않고 여기서만 닫는다
			if (session != null) {
				session.close();
			}
		}
		
	}
	
	public void insertInfo(SqlSession session, String id) throws AddException {
		try {
			//메소드 호출
			session.insert("com.my.order.OrderMapper.insertInfo", id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}
	}
	

	public void insertLine(SqlSession session, List<OrderLine> lines) throws AddException {
		try {
			//메소드 호출
			for (OrderLine line: lines) {
				session.insert("com.my.order.OrderMapper.insertLine", line);				
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}
		//sqlSession은 AutoCommit이 아니기 때문에 commit() 필수! 돌아가고싶으면 동일하게 rollback()
	}

	
}
