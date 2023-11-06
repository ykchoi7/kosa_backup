package com.my.board.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.board.dao.BoardRepository;
import com.my.board.dao.ReplyRepository;
import com.my.board.dto.BoardDTO;
import com.my.board.dto.ReplyDTO;
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

	//DTO->VO 변환 (Board)
	public Board DtoToVo_ModelMapper(BoardDTO dto) {
		
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
		return entity;
	}
	
	//DTO->VO 변환 (Reply)
	public Reply RDtoToVo_ModelMapper(ReplyDTO rdto) {
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STANDARD)
				.setFieldAccessLevel(AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true);
		
		Object source = rdto;
		Class<Reply> destinationType = Reply.class;
		Reply entity = mapper.map(source, destinationType);
		return entity;
	}
	
	//VO->DTO 변환
	public BoardDTO VoToDto_ModelMapper(Board entity) {
//		Integer boardNo = 1;
//		Optional<Board> optB = br.findById(boardNo);
//		Board entity = optB.get();
		
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
		return dto;
	}
	
	
	//VoToDto
	public List<BoardDTO> findAll() throws FindException {
		List<Board> board = br.findAll();
		List<BoardDTO> list = new ArrayList();
		
		for (Board entity : board) {
			BoardDTO dto = VoToDto_ModelMapper(entity);
			dto.setReplycnt(dto.getReplies().size());
			list.add(dto);	
		}
		return list;
	}
	
	//VoToDto
	public BoardDTO findByBoardNo(int boardNo) throws FindException {
//	    SimpleDateFormat format = new SimpleDateFormat();

		List<ReplyDTO> rlist;
		BoardDTO bdto;
		try {
			List<Object[]> list = br.findByBoardNo(boardNo);
			Object[] obj = list.get(0);
			rlist = new ArrayList();
			
			bdto = BoardDTO
							.builder()
//							.boardNo(Integer.parseInt((String)obj[0]))
							.boardNo(((BigDecimal)obj[0]).intValue())
							.boardContent((String)obj[1])
							.boardDt((java.util.Date)obj[2])
							.boardId((String)obj[3])
							.boardTitle((String)obj[4])
							.build();
			for (Object[] replies : list) {
				ReplyDTO rdto = ReplyDTO
						.builder()
						.level(((BigDecimal)obj[5]).intValue())
						.replyNo(((BigDecimal)obj[6]).intValue())
						.replyBoardNo(((BigDecimal)obj[7]).intValue())
						.replyContent((String)obj[8])
						.replyDt((java.util.Date)obj[9])
						.replyId((String)obj[10])
						.build();
				if (obj[11] != null) {
					rdto.setReplyParentNo(((BigDecimal)obj[11]).intValue());
				}
				rlist.add(rdto);

				bdto.setReplies(rlist);
				bdto.setReplycnt(rlist.size());
			}
			return bdto;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//DtoToVo
	public void write(BoardDTO bdto) throws AddException {
		Board board = DtoToVo_ModelMapper(bdto);
		br.save(board);
		
		//util패키지를 따로 만들어서 mapper를 생성한 경우 -> INSTANCE로 가져오기
//		ModelMapper mapper = MapperUtil.INSTANCE.get();
//		Board board = mapper.map(bdto, Board.class);
//		br.save(board);
	}
	
	public void modify(BoardDTO bdto) throws ModifyException {
		Integer boardNo = bdto.getBoardNo();
		Optional<Board> optB = br.findById(boardNo);
		//예외처리 중간에 한번 해주기 (게시글번호에 해당하는 게시글이 없는 경우)
		optB.orElseThrow(() -> 
			new ModifyException("게시글이 없어서 수정 실패")
		);
		Board b = optB.get();
		b.modifyContent(bdto.getBoardContent()); //내용 수정
		br.save(b);
	}
	
	public void remove(int boardNo) throws RemoveException {
		try {
			br.deleteById(boardNo);
		} catch (Exception e) {
			throw new RemoveException(e.getMessage());
		}
	}
	
	public void writeReply(ReplyDTO rdto) throws AddException {
		Reply rentity = RDtoToVo_ModelMapper(rdto);
		rr.save(rentity);
//		rdto = 
//				ReplyDTO
//				.builder()
//				.replyBoardNo(rdto.getReplyBoardNo())
//				.replyParentNo(rdto.getReplyParentNo())
//				.replyContent(rdto.getReplyContent())
//				.replyId(rdto.getReplyId())
//				.replyDt(new java.util.Date())
//				.build();
//		log.error("INSERT용 Reply 객체 entity replyNo:{}, replyBoardNo:{}, replyParentNo:{}, "
//				+ "replyContent:{}, replyId:{}, replyDt: {}",
//				rdto.getReplyNo(),
//				rdto.getReplyBoardNo(),
//				rdto.getReplyParentNo(),
//				rdto.getReplyContent(),
//				rdto.getReplyId(),
//				rdto.getReplyDt()
//				);
	}
	
	public void modifyReply(ReplyDTO rdto) throws ModifyException {
		Integer replyNo = rdto.getReplyNo();
		Optional<Reply> optR = rr.findById(replyNo);
		Reply r = optR.get();
		r.modifyContent(rdto.getReplyContent());
		rr.save(r);
	}
	
	public void removeReply(int replyNo) throws RemoveException {
		rr.deleteById(replyNo);
	}
	
}
