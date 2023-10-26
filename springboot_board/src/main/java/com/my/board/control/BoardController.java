package com.my.board.control;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.board.dto.Board;
import com.my.board.dto.Reply;
import com.my.board.service.BoardService;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@RestController
@RequestMapping("/board") //요청방식이 get, post이든 상관없이 모든 것을 포함 
						  //(모든 링크에 공통적으로 들어가는 uri라면 requestmapping을 활용하는 것이 좋음)
public class BoardController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BoardService service;
	
	@GetMapping("/list") //게시글 전체보기용
	public List<Board> list() throws FindException {
		return service.findAll();
	}
	
	@GetMapping("/{boardNo}") //1번 게시글 상세
	public Board info(@PathVariable int boardNo) throws FindException {
		return service.findByBoardNo(boardNo);
	}
	
	//POST 작성 /board 
	@PostMapping(value="", produces="application/json;charset=UTF-8") //게시글 쓰기
	public ResponseEntity<?> write(@RequestBody Board board) throws AddException {
		try {
			service.write(board);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
			return new ResponseEntity<>("등록되었습니다", headers, HttpStatus.OK);
		} catch (AddException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
//		return "글쓰기" + board;
	}
	
	//PUT 수정 /board/1
	@PutMapping(value="/{boardNo}", produces = "application/json;charset=UTF-8") //게시글 수정
	public ResponseEntity<?> modify(@PathVariable int boardNo,
						 			@RequestBody Board board) throws ModifyException {
		board.setBoardNo(boardNo);
		service.modify(board);
		return new ResponseEntity<>(HttpStatus.OK);
//		return "글수정" + board;
	}
	
	//DELETE 삭제 /board/1
	@DeleteMapping(value="/{boardNo}", produces = "application/json;charset=UTF-8") //게시글 삭제
	public ResponseEntity<?> remove(@PathVariable int boardNo) throws RemoveException {
		service.remove(boardNo);
		return new ResponseEntity<>(HttpStatus.OK);
//		return "글삭제" + boardNo;
	}
	
	
	//답글쓰기 POST/board/reply/1
	//      POST/board/reply/1/9
	@PostMapping(value={"/reply/{boardNo}/{parentNo}", "reply/{boardNo}"}) //문자열 배열로 설정
	public ResponseEntity<?> writeReply(@PathVariable int boardNo,
			@PathVariable(name="parentNo") Optional<Integer> optParentNo, //@PathVariable int parentNo 도 가능하지만 매개변수를 전달하지 않았을 때 NPE이 발생한다
			@RequestBody Reply reply) throws AddException { //쓰기에서 작성한 정보들을 한 번에 reply객체로 전달해줘야하기 때문에 @RequestBody를 사용한다
			// 요청전달데이터 @RequestParam(required=false, defaultValue="0") int sal(){ } 이런식으로 써줄 수도 있다
		if(!optParentNo.isPresent()) { // 있는지, 없는지에 따라 true/false --> 일반 답글쓰기
			reply.setReplyBoardNo(boardNo);
			service.writeReply(reply);
		} else { //parentNo가 있는 경우 --> 답글의 답글쓰기
			Integer parentNo = optParentNo.get();
			reply.setReplyParentNo(parentNo);
			reply.setReplyBoardNo(boardNo);
			service.writeReply(reply);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//답글 수정 PUT/board/reply/13
	@PutMapping(value="/reply/{replyNo}")
	public ResponseEntity<?> modifyReply(@PathVariable int replyNo,
										@RequestBody Reply reply) throws ModifyException {
		System.out.println(replyNo);
		reply.setReplyNo(replyNo);
		service.modifyReply(reply);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//답글 삭제 DELETE/board/reply/13
	@DeleteMapping(value="/reply/{replyNo}")
	public ResponseEntity<?> removeReply(@PathVariable int replyNo) throws RemoveException {
		System.out.println(replyNo);
		log.debug("DEBUG 메시지");
		log.info("INTO 메시지");
		log.warn("WARN 메시지");
		log.error("ERROR 메시지");
		//root level보다 높은 단계에 있는 메시지만 출력됨
		service.removeReply(replyNo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}


