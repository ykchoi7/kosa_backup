package com.my.order.dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;
import com.my.order.dto.OrderLine;
@Repository(value="orderDAO")
public class OrderOracleMybatisRepository implements OrderRepository {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
//	public OrderOracleMybatisRepository() {
//		String resource = "com/my/sql/mybatis-config.xml";
//		InputStream inputStream;
//		try {
//			inputStream = Resources.getResourceAsStream(resource);
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@Override
	@Transactional(rollbackFor = AddException.class)
	public void insert(OrderInfo info) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			insertInfo(session, info.getOrderId());
			insertLine(session, info.getLines());			
//			session.commit();
		}catch(Exception e) {
//			session.rollback();
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	public void insertInfo(SqlSession session, String id) throws AddException{
		try {
			//메소드 호출
			session.insert("com.my.order.OrderMapper.insertInfo", id);
		}catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}
	}
	
	public void insertLine(SqlSession session, List<OrderLine> lines) throws AddException{
		try {
			//메소드 호출
			for(OrderLine line: lines) {			
				session.insert("com.my.order.OrderMapper.insertLine", line);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}
		//sqlSession은 AutoCommit이 아니기 때문에 commit() 필수! 돌아가고싶으면 동일하게 rollback()
	}

	public List<OrderInfo> selectById(String orderId) throws FindException{
		SqlSession session = null;
		try {
			List<OrderInfo> list = new ArrayList<>();
			session = sqlSessionFactory.openSession();
			list = session.selectList("com.my.order.OrderMapper.selectById", orderId);
			System.out.println("주문기본(OrderInfo)객체 수: " + list.size()); //3
			for(OrderInfo info: list) {
				System.out.println(info.getOrderNo() + ":" + info.getOrderDt());
				for(OrderLine line: info.getLines()) {
					System.out.println("주문상세: 상품번호-" + line.getOrderP().getProdNo() +", 수량-" + line.getOrderQuantity());
				}
				System.out.println("------------");
			}
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

}
