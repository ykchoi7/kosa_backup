package com.my.board.dao;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.my.board.dto.BoardDTO;
import com.my.board.entity.Board;
import com.my.board.entity.Reply;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest //Spring Boot용 테스트 어노테이션
@Slf4j
class BoardRepositoryTest {
	@Autowired
	BoardRepository brepository;
	
	@Autowired
	ReplyRepository rrepository;
	
	@Test
	@DisplayName("게시글 저장 테스트")
	void test1B_Insert() {
		Board b = 
				Board
				.builder()
				.boardTitle("제목2")
				.boardContent("내용2")
				.boardId("작성자1")
				.boardDt(new java.util.Date())
				.build();
		log.error("INSERT용 Board 객체 entity boardTitle:{}, boardContent:{}, boardId:{}, boardDt:{}",
				b.getBoardTitle(),
				b.getBoardContent(),
				b.getBoardId(),
				b.getBoardDt()
				);
		brepository.save(b);
	}
	
	@Test
	@DisplayName("답글 저장 테스트")
	void test2R_Insert() {
		Integer boardNo = 1;
		Integer parentNo = 1;
		Reply r = 
				Reply
				.builder()
				.replyBoardNo(boardNo)
				.replyParentNo(parentNo)
				.replyContent("답글1")
				.replyId("작성자3")
				.replyDt(new java.util.Date())
				.build();
		log.error("INSERT용 Reply 객체 entity replyNo:{}, replyBoardNo:{}, replyParentNo:{}, "
				+ "replyContent:{}, replyId:{}, replyDt: {}",
				r.getReplyNo(),
				r.getReplyBoardNo(),
				r.getReplyParentNo(),
				r.getReplyContent(),
				r.getReplyId(),
				r.getReplyDt()
				);
		rrepository.save(r);
	}
	
	@Test
	@DisplayName("DTO->VO 변환")
	void test3DtoToVo_ModelMapper() {
		BoardDTO dto = BoardDTO
				.builder()
				.boardTitle("게시글1")
				.boardContent("게시글1 내용")
				.boardId("작성자1")
				.boardDt(new java.util.Date())
				.build();

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STANDARD)
				.setFieldAccessLevel(AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true);
		
		Object source = dto;
		Class<Board> destinationType = Board.class;
		Board entity = mapper.map(source, destinationType);
		log.error("entity boardTitle:{}, boardContent:{}, boardId:{}, boardDt:{}",
				entity.getBoardTitle(),
				entity.getBoardContent(),
				entity.getBoardId(),
				entity.getBoardDt()
		);
	}
	
	@Test
	@DisplayName("VO->DTO 변환")
	void test4VoToDto_ModelMapper() {
		Integer boardNo = 1;
		Optional<Board> optB = brepository.findById(boardNo);
		Board entity = optB.get();
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STANDARD)
				.setFieldAccessLevel(AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true);
		
		Object source = entity;
		Class<BoardDTO> destinationType = BoardDTO.class;
		BoardDTO dto = mapper.map(source, destinationType);
		log.error("entity boardTitle:{}, boardContent:{}, boardId:{}, boardDt:{}",
				dto.getBoardTitle(),
				dto.getBoardContent(),
				dto.getBoardId(),
				dto.getBoardDt()
				);
	}
	
	@Test
	@DisplayName("게시물 삭제")
	void test5B_Delete() {
		int boardNo = 1;
		brepository.deleteById(boardNo);
	}
	
}
