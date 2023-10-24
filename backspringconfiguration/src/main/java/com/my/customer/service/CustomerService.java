package com.my.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.customer.dao.CustomerOracleMybatisRepository;
import com.my.customer.dao.CustomerRepository;
import com.my.customer.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
@Service(value="customerService")
public class CustomerService {
	@Autowired
	private CustomerRepository repository;

	/**
	 * 아이디와 비밀번호에 일치하는 고객정보가 존재한다면 반환값이 없고
	 *                               존재하지 않으면 FindException발생한다  
	 * @param id 아이디
	 * @param pwd 비밀번호
	 * @throws FindException 
	 */
	public void login(String id, String pwd) throws FindException{
		//try {
			Customer c = repository.selectById(id);
			if(!c.getPwd().equals(pwd)) {
				throw new FindException();
			}
		//}catch(FindException e) {
		//	throw new FindException("로그인 실패");
		//}
	}
	
	/**
	 * 아이디에 해당하는 고객이 존재하지 않으면 FindException발생한다 
	 * @param id
	 * @throws FindException
	 */
	public void idDupChk(String id) throws FindException{
		repository.selectById(id);
	}
	
	public void signup(Customer c) throws AddException{
		repository.insert(c);
	}
}






