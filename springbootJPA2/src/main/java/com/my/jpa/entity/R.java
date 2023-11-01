package com.my.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor

@Entity
@Table(name="r_tbl") // 게시글댓글
public class R {
	@Id
	@Column(name = "r_no")
	private Long rNo; //댓글번호
	
	@Column(name = "rb_no")
	private Long bNo; //글번호
	
	@Column(name="r_content")
	private String rContent;//글내용
}
