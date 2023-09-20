package com.my.customer.service;

import com.my.customer.dao.CustomerOracleMybatisRepository;
import com.my.customer.dao.CustomerRepository;
import com.my.customer.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;

public class CustomerService {
	
	private CustomerRepository repository;
	public CustomerService() {
//		repository = new CustomerOracleRepository();
		repository = new CustomerOracleMybatisRepository();
	}
	
	/**
	 * 아이디와 비밀번호에 일치하는 고객정보가 존재한다면 반환값이 없고
	 * @param id
	 * @param pwd
	 * @throws FindException
	 */
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
	
	/**
	 * 아이디에 해당하는 고객이 존재하지 않으면 FindException 발생한다
	 * @param id
	 * @throws FindException
	 */
	public void idDupChk(String id) throws FindException {
		repository.selectById(id);
	}
	
	/**
	 * 고객이 가입되지 않으면 AddException 발생한다
	 * @param c
	 * @throws AddException
	 */
	public void signup(Customer c) throws AddException {
		repository.insert(c);
	}
}
