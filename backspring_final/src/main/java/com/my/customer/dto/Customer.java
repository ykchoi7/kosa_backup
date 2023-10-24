package com.my.customer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter  @Getter @ToString
public class Customer extends Person  {
	private static final long serialVersionUID = 1L;
	private String id;
	transient private String pwd;
	//매개변수없는 생성자
	public Customer() {
		super();
	}
	
	public Customer(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	
	public Customer(String id, String pwd, String name, String address) {
//		this.id = id;
//		this.pwd = pwd;
		this(id, pwd);
		this.name = name;
		super.address = address;
//		super(name, address);
	}

	
}
