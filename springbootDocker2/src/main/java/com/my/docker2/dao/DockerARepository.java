package com.my.docker2.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.docker2.entity.DockerA;

public interface DockerARepository extends JpaRepository<DockerA, String> {

}
