package com.my.customer.dao;

import com.my.customer.dto.Customer;

public class CustomerDao {
	public static void main(String[] args) {
		Customer c = new Customer("id1", "p1", "n1", "a1");
		System.out.println(c);
	}
}
