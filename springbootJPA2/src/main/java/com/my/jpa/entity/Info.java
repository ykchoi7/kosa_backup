package com.my.jpa.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder

@Entity
@Table(name="info_tbl") //사용할 테이블명 : info_tbl
@SequenceGenerator(
			name = "INFO_SEQ_GENERATOR",
			sequenceName = "info_seq", 
			initialValue=1, allocationSize=1
		)

public class Info {
	//pk 역할용 컬럼명: info_no
	@Id //pk역할
	@Column(name="info_no")
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "INFO_SEQ_GENERATOR"
	)
	private Long infoNo;	//주문기본번호 : 일련번호용 객체 - info_seq 시퀀스객체
	
	//컬럼명: info_id
	@Column(name="info_id")
	private String infoId;
	
	//컬럼명: info_dt
	@Column(name="info_dt")
	private Date infoDt;
	
	@OneToMany( //(mappedBy="id.lineNo")--EmbeddedId에서 타고들어가서 lineNo를 찾음
			fetch = FetchType.EAGER
			,
			cascade = CascadeType.ALL
			,
			mappedBy="id.lineNo"
			)
//	@JoinColumn(name="line_no")
	private List<Line> lines; //주문 상세정보 전체
}
