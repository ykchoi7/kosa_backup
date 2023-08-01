package com.my.customer.dto;

public class Person {
	
	protected String name;
	protected String address;
	
	//부모클래스에서는 생략해도 됨
	public Person() {
		super(); //Object
	}
	
	//Person생성자 오버로드
	public Person(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	//getter/setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "name은 " + name + ", address는 " + address;
	}
	
}
