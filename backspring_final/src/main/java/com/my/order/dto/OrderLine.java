package com.my.order.dto;

import com.my.product.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor  @AllArgsConstructor

public class OrderLine {
	private Integer orderLineNo;
	//private String orderProdNo;
	private Product orderP;
	private Integer orderQuantity;
//	private OrderInfo info;
	
}