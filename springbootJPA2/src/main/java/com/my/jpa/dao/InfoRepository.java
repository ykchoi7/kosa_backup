package com.my.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.jpa.entity.Info;

public interface InfoRepository extends JpaRepository<Info, Long>{

}
