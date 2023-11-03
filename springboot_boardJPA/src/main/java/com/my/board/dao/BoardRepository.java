package com.my.board.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.my.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	//JpaRepository에서 지원하지 않는 메소드만 생성해주기
	@Query(value="SELECT b.*, \r\n"
			+ "    	(SELECT COUNT(*) FROM board_reply WHERE reply_board_no=b.board_no) replycnt\r\n"
			+ "	FROM board  b\r\n"
			+ "	ORDER BY board_no DESC",
			nativeQuery = true)
	public List<Board> findAll();
	
	@Query(value="SELECT * FROM board_tbl b LEFT JOIN\r\n"
			+ "    (SELECT level,r.* FROM reply_tbl r START WITH reply_parent_no IS NULL CONNECT BY PRIOR reply_no = reply_parent_no\r\n"
			+ "    ORDER SIBLINGS BY reply_no DESC)\r\n"
			+ "ON b.board_no = reply_board_no\r\n"
			+ "WHERE b.board_no = :boardNo",
			nativeQuery = true)
	public List<Object[]> findByBoardNo(int boardNo);
	
}
