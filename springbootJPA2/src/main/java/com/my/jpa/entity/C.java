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

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@ToString

@Entity
@Table(name="c_tbl") 
/**
 * 게시글 작성자 (회원)
 */
public class C {
	@Id
	@Column(name="c_id")
	private String cId;      //아이디
	
	@Column(name="c_name")
	private String cName;	//이름
}
