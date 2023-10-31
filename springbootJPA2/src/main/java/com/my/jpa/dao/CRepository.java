package com.my.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.jpa.entity.C;

public interface CRepository extends JpaRepository<C, String> {

}
