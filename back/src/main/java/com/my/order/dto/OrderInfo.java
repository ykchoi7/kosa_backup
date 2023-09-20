package com.my.order.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class OrderInfo {
	private Integer orderNo;
	private String orderId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") //날짜 깨지지 않게 표시하기 위한 설정
	private Date orderDt;
	private List<OrderLine> lines;
}
