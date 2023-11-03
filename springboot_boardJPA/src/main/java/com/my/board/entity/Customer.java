package com.my.board.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder

@Entity
@Table(name="customer_tbl")
public class Customer {
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="pwd")
	private String pwd;
	
	@Column(name="name")
	private String name;
	
	@OneToMany
	@JoinColumn(name="board_id")
	private List<Board> b;
	
	@OneToMany
	@JoinColumn(name="reply_id")
	private List<Reply> r;

}
