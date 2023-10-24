package com.my.order.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter  @Getter
@NoArgsConstructor  @AllArgsConstructor
@Builder
public class OrderInfo {
	private Integer orderNo;
	private String orderId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date orderDt;
	private List<OrderLine> lines;

}
