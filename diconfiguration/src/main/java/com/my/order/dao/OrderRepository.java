package com.my.order.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;

public interface OrderRepository {
	/**
	 * 주문기본정보와 상세 정보들을 추가한다
	 * @param info
	 * @throws AddException
	 */
	void insert(OrderInfo info) throws AddException;
	
	/**
	 * orderId로 주문 정보를 조회한다
	 * @param orderId
	 * @return 
	 * @throws FindException
	 */
	List<OrderInfo> selectById(String orderId) throws FindException;
}
