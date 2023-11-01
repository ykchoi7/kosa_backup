package com.my.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable

//복합키를 설정하기 위해 하나의 클래스로 묶기
public class LineEmbedded implements Serializable{
	@Column(name="line_no")
	private Long lineNo;
	
	@Column(name ="line_pno")
	private String pNo;
}