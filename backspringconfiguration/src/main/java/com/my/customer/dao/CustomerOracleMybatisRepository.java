package com.my.customer.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.my.customer.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
@Repository("customerDAO")
public class CustomerOracleMybatisRepository implements CustomerRepository {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public Customer selectById(String id) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Customer c = 
					(Customer)session.selectOne("com.my.customer.CustomerMapper.selectById",  id);
			if(c != null) { 
				return c;
			}else {
				throw new FindException("고객이 없습니다");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
		
	}
	
	@Override
	@Transactional(rollbackFor = AddException.class) //commit, rollback을 따로 기입해주지 않기 위해서
	public void insert(Customer c) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("com.my.customer.CustomerMapper.insert", c);
//			session.commit(); //Spring에서는 commit()메소드를 할 필요가 없다, AutoCommit으로 진행!!
		}catch(Exception e) {
//			session.rollback();
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	
}
