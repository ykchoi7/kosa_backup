package com.my.order.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;

public interface OrderRepository {
	/**
	 * 주문기본정보와 상세정보들을 추가한다
	 * @param info
	 * @throws AddException
	 */
	void insert(OrderInfo info) throws AddException;
	
	/**
	 * 주문자아이디에 해당하는 주문목록을 조회한다
	 * @param orderId 주문자아이디
	 * @return 주문들
	 * @throws FindException
	 */
	List<OrderInfo> selectById(String orderId) throws FindException;
}
