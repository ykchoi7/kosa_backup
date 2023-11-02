package com.my.jpa.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class StoreDTO {
	private String corNo;
	private String name;
	@JsonFormat(pattern="yy/MM/dd", timezone="Asia/Seoul")
	private Date dt;
}
