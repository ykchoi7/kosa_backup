package com.my.board.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
