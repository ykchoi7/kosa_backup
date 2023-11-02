package com.my.board.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.board.dao.BoardRepository;
import com.my.board.dto.BoardDTO;
import com.my.board.entity.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {
	@Autowired
	private BoardRepository br;

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
	
}
