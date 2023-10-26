package com.my.board.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.my.board.dto.Board;
import com.my.board.dto.Reply;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@SpringBootTest //Spring Boot용 테스트 어노테이션
class BoardRepositoryTest {
	@Autowired
	BoardOracleRepository repository;
	
	@Test
	void testSelectAll() throws FindException {
		List<Board> list = repository.selectAll();
		int expectedSize = 3;
		Assertions.assertEquals(expectedSize, list.size());
	}
	
	@Test
	@DisplayName("게시글번호1의 상세조회") //성공테스트
	void testSelectByBoardNo() throws FindException {
		int boardNo = 1;
		Board board = repository.selectByBoardNo(boardNo);
		String expectedTitle = "제목1";
		int expectedReplySize = 6;
		Assertions.assertEquals(expectedTitle, board.getBoardTitle()); //같은 것으로 단정
		Assertions.assertEquals(expectedReplySize, board.getReplies().size());
	}
	
	@Test
	@DisplayName("존재하지 않는 게시글번호로 상세조회") //실패테스트
	void testSelectByBoardNo2() throws FindException {
		int boardNo = 0;
		Board board = repository.selectByBoardNo(boardNo);
		Assertions.assertNull(board); //board 인자가 null일 것으로 단정 -> true/false
		
		//*없는 데이터인 경우도 테스트 필수!
		Assertions.assertThrows(FindException.class, () -> { //FindException 발생할 것으로 단정
			repository.selectByBoardNoOptional(boardNo);
		});
	}
	
	@Test
	@DisplayName("게시글 작성")
	@Transactional //자동 rollback()까지해서 test할때는 실제로 데이터값이 들어가지 않게 설정하는 어노테이션
	void testInsert() throws AddException, FindException {
		Board board = new Board();
		board.setBoardTitle("제목5");
		board.setBoardContent("내용5");
		board.setBoardId("C");
		repository.insert(board);

		//내용이 동일한지 확인
		String expectedTitle = "제목5";
		String expectedContent = "내용5";
		String expectedId = "C";
		Assertions.assertEquals(expectedTitle, board.getBoardTitle());
		Assertions.assertEquals(expectedContent, board.getBoardContent());
		Assertions.assertEquals(expectedId, board.getBoardId());
		
		//추가한 boardNo에 해당하는 board객체가 존재하는지 확인
		int boardNo = 5;
		Board board2 = repository.selectByBoardNo(boardNo);
		Assertions.assertNotNull(board2); // 값이 있을것이라 단정 (NOT NULL)
		
		//AddException이 발생했는지 확인
//		Assertions.assertThrows(AddException.class, () -> { //AddException 발생할 것으로 단정
//			repository.insert(board);
//		});
	}
	
	@Test
	@DisplayName("게시글 수정")
	@Transactional
	void testUpdate() throws ModifyException, FindException {
		int boardNo = 1;
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setBoardContent("내용수정");
		repository.update(board);
		Board board2 = repository.selectByBoardNo(boardNo);
		
		//수정된 게시글의 내용이 일치하는지 확인
		String expectedContent = "내용수정";
		Assertions.assertEquals(expectedContent, board2.getBoardContent()); //boardNo에 해당하는 내용을 수정하고 expected값이랑 비교
	}
	
	@Test
	@DisplayName("게시글 삭제")
	@Transactional
	void testDelete() throws RemoveException, FindException {
		int boardNo = 4;
		repository.delete(boardNo);
		
		//3번 게시글 없어졌는지 확인
		Board board = repository.selectByBoardNo(boardNo);
		Assertions.assertNull(board); //boardNo=4인 행이 없어져서 null일것이라 단정
	}
	
	
	@Test
	@DisplayName("답글 작성")
	@Transactional
	void testInsertReply() throws AddException {
		int replyBoardNo = 1;
		int replyParentNo = 1;
		Reply reply = new Reply();
		reply.setReplyBoardNo(replyBoardNo);
		reply.setReplyParentNo(replyParentNo);
		reply.setReplyContent("내용수정하기");
		reply.setReplyId("C");
		repository.insertReply(reply);
	}
	
	@Test
	@DisplayName("존재하지 않는 id로 답글 작성하기")
	@Transactional
	void testInsertReply2() throws AddException {
		int replyBoardNo = 1;
		int replyParentNo = 2;
		Reply reply = new Reply();
		reply.setReplyBoardNo(replyBoardNo);
		reply.setReplyParentNo(replyParentNo);
		reply.setReplyContent("답글답글");
		reply.setReplyId("ABB");
		repository.insertReply(reply);
		
		Assertions.assertThrows(AddException.class, () -> {
			System.out.println("존재하지 않는 id입니다");
		});
	}
	
	@Test
	@DisplayName("답글 수정")
	@Transactional
	void testUpdateReply() throws ModifyException {
		int replyNo = 3;
		String replyContent = "내용수정222";
		Reply reply = new Reply();
		reply.setReplyNo(replyNo);
		reply.setReplyContent(replyContent);
		repository.updateReply(reply);
	}
	
	@Test
	@DisplayName("존재하지 않는 replyNo로 수정 시도")
	@Transactional
	void testUpdateReply2() throws ModifyException {
		int replyNo = 9;
		String replyContent = "내용삽입";
		Reply reply = new Reply();
		reply.setReplyNo(replyNo);
		reply.setReplyContent(replyContent);
		repository.updateReply(reply);
		
		Assertions.assertThrows(ModifyException.class, () -> {
			
		});
	}
	
	@Test
	@DisplayName("답글 삭제")
	@Transactional
	void testDeleteReply() throws RemoveException {
		int replyNo = 3;
		repository.deleteReply(replyNo);
	}
	
	@Test
	@DisplayName("존재하지 않는 replyNo로 삭제 시도")
	@Transactional
	void testDeleteReply2() throws RemoveException {
		int replyNo = 10;
		repository.deleteReply(replyNo);
		
		Assertions.assertThrows(RemoveException.class, () -> {
			
		});
	}
	
}
