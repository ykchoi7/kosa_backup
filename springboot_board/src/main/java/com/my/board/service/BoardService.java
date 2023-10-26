package com.my.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.my.board.dao.BoardOracleRepository;
import com.my.board.dto.Board;
import com.my.board.dto.Reply;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@Service
public class BoardService {
	@Autowired
	private BoardOracleRepository repository;
	
	public List<Board> findAll() throws FindException {
		return repository.selectAll();
	}
	
	public Board findByBoardNo(int boardNo) throws FindException {
		return repository.selectByBoardNo(boardNo);
	}
	
	public void write(Board board) throws AddException {
		repository.insert(board);
	}
	
	public void modify(Board board) throws ModifyException {
		repository.update(board);
	}
	
	public void remove(int boardNo) throws RemoveException {
		repository.delete(boardNo);
	}
	
	public void writeReply(Reply reply) throws AddException {
		repository.insertReply(reply);
	}
	
	public void modifyReply(Reply reply) throws ModifyException {
		repository.updateReply(reply);
	}
	
	public void removeReply(int replyNo) throws RemoveException {
		repository.deleteReply(replyNo);
	}
	
}
