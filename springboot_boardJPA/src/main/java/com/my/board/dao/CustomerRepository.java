package com.my.board.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.board.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
