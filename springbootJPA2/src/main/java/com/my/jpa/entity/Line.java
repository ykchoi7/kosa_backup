package com.my.jpa.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor

@Entity
@Table(name="line_tbl")
public class Line{
	@EmbeddedId 
	private LineEmbedded id = new LineEmbedded();
	
	@ManyToOne
	@JoinColumn(name="line_no")
	@MapsId("lineNo") //복합키일 때 어떤 멤버변수를 연결하는지 결정하는 용도
	private Info info;
	
	@ManyToOne
	@JoinColumn(name="line_pno")
	@MapsId("pNo")
	private P lineP;
	
	@Column(name="line_q")
	private int lineQuantity;
}
