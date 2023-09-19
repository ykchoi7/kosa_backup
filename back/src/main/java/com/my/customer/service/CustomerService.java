package com.my.customer.service;

import com.my.customer.dao.CustomerOracleMybatisRepository;
import com.my.customer.dao.CustomerRepository;
import com.my.customer.dto.Customer;
import com.my.exception.FindException;

public class CustomerService {
	
	private CustomerRepository repository;
	public CustomerService() {
//		repository = new CustomerOracleRepository();
		repository = new CustomerOracleMybatisRepository();
	}
	
	public void login(String id, String pwd) throws FindException {
		try {
			Customer customer = repository.selectById(id);
			if(!customer.getPwd().equals(pwd)) throw new FindException();
			//System.out.println("로그인 성공");
		} catch (FindException e) {
			//e.printStackTrace();
			throw new FindException("로그인 실패");
		}
	}
}
