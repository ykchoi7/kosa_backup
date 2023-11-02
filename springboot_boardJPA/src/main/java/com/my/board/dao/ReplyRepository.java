package com.my.board.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.board.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

}
