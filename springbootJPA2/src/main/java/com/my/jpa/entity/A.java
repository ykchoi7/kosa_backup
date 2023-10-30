package com.my.jpa.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@ToString

@Entity //엔터티객체
@Table(name = "a_tbl")

/*For inserting, 
 *    should this entity use dynamic sql generation 
 *    where only non-null columns get referenced in the prepared sql statement?
    ex) 
    insert 
    into  a_tbl
        (a_2,      a_1) 
    values
        (?,        ?)
 */
@DynamicInsert

/*
 * For updating, 
 *     should this entity use dynamic sql generation 
 *     where only changed columns get referenced in theprepared sql statement? 
    ex)
    update
        a_tbl 
    set
        a_2=? 
    where
        a_1=?
 */
@DynamicUpdate

public class A {
	@Id
	@Column(length = 5) //문자형의 자릿수 선정
	private String a_1;
	
	@Column(nullable=false, precision = 5, scale = 2)
	@ColumnDefault(value = "1")
//	private Integer a_2; //Integer로 선언하면 자릿수 보장 못함 -> number(10,0)
	private BigDecimal a_2; //이걸 지정해줘야 자릿수도 보장됨 -> number(5,3)
	
//	@Column
//	@JsonFormat(pattern = "yy/MM/dd", timezone = "Asia/Seoul")
//	@CreationTimestamp //INSERT 쿼리가 발생할 때, 현재 시간을 값으로 채워서 쿼리를 생성
	@ColumnDefault(value = "SYSDATE")
	private Date a_3; //java.sql.Date
	
	private String a4;
	@Transient  //컬럼과 관계없는 일반 멤버변수임을 알려주는 어노테이션
	private String test;
}
