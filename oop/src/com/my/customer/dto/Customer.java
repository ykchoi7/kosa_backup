package com.my.customer.dto;

public class Customer extends Person {
	private String id;
	private String pwd;
	
	//매개변수없는 생성자
	public Customer() {
		super();
	}
	
	//매개변수있는 생성자
	public Customer(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	
	public Customer(String id, String pwd, String name, String address) {
//		this.id = id;
//		this.pwd = pwd;
		this(id, pwd); //중복되는 것은 this생성자로 축약 가능
		this.name = name;
		super.address = address; //this, super 둘다 상관없음
//		super(name, address); //this와 super 생성자 동시에 사용할 수 없음->이 생성자들은 맨 앞줄에서만 유효하기 때문
	}

	//getter/setter
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

	@Override
	public String toString() {
		return "id는 " + id + ", pwd는 " + pwd + ", " + super.toString();
	}
	
}
