package com.my.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.jpa.entity.B;

public interface BRepository extends JpaRepository<B, Long> {

}
