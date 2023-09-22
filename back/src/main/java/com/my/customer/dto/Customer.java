package com.my.customer.dto;

import java.io.File;

public class Customer extends Person{
	/**
	 * 
	 */
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

	public Customer(String id, String pwd, String name) {
		this(id, pwd);
		this.name = name;
	}


	//setter/getter
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String toString() {
		return "id는" + id + ", pwd는" + pwd + ", name은" + name + "address는" + address;
	}
	
}
