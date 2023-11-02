package com.my.board.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.board.dao.BoardRepository;
import com.my.board.dao.ReplyRepository;
import com.my.board.dto.BoardDTO;
import com.my.board.entity.Board;
import com.my.board.entity.Reply;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {
	@Autowired
	BoardRepository br;
	
	@Autowired
	ReplyRepository rr;

	//DTO->VO 변환
	public void DtoToVo_ModelMapper() {
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
	
	//VO->DTO 변환
	public void VoToDto_ModelMapper() {
		Integer boardNo = 1;
		Optional<Board> optB = br.findById(boardNo);
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
	
	
	public List<Board> findAll() throws FindException {
		br.findAll();
		List<Board> list = br.findAll();
		return list;
	}
	
	public Board findByBoardNo(int boardNo) throws FindException {
		Optional<Board> optB = br.findById(boardNo);
		Board board = optB.get();
		return board;
	}
	
	public void write(Board board) throws AddException {
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
		br.save(b);
	}
	
	public void modify(Board board) throws ModifyException {
		Integer boardNo = board.getBoardNo();
		Optional<Board> optB = br.findById(boardNo);
		Board b = optB.get();
		b.modifyContent("게시글 수정");
		br.save(b);
	}
	
	public void remove(int boardNo) throws RemoveException {
		br.deleteById(boardNo);
	}
	
	public void writeReply(Reply reply) throws AddException {
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
		rr.save(r);
	}
	
	public void modifyReply(Reply reply) throws ModifyException {
		Integer replyNo = reply.getReplyNo();
		Optional<Reply> optR = rr.findById(replyNo);
		Reply r = optR.get();
		r.modifyContent("댓글 수정");
		rr.save(r);
	}
	
	public void removeReply(int replyNo) throws RemoveException {
		rr.deleteById(replyNo);
	}
	
}
