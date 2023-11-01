package com.my.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor

@Entity
@Table(name="pd_tbl")
public class PD {
	@Id
	@Column(name="pd_no")
	private String pdNo; //상품번호
	
	@Column(name="pd_cal")
	private int cal; //상품칼로리
	
	@Column(name="pd_na")
	private int na; //나트륨
	
	//자식쪽 : @ManyToOne 역할
	@OneToOne
	@JoinColumn(name="pd_no") //fk역할
	private P p;
}
