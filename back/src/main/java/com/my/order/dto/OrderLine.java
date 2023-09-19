package com.my.order.dto;

import com.my.product.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrderLine {
	private Integer orderLineNo; //확장성을 고려하면 Integer가 더 적합
//	private String orderProdNo;
	private Product orderP;
	private Integer orderQuantity;
//	private OrderInfo info;
}
