package com.my.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.jpa.entity.P;

public interface PRepository extends JpaRepository<P, String>{
	
}
