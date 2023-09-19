package com.my.order.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class OrderInfo {
	private Integer orderNo;
	private String orderId;
	private Date orderDate;
	private List<OrderLine> lines;
}
