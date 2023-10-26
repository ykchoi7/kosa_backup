package com.my.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.my.board.dto.Board;
import com.my.board.dto.Reply;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@Repository
public class BoardOracleRepository {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	/**
	 * 게시글 목록을 검색한다
	 * @return 게시글 목록
	 * @throws FindException
	 */
	public List<Board> selectAll() throws FindException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			List<Board> list = session.selectList("com.my.board.BoardMapper.selectAll");
			return list;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * 게시글을 상세 조회한다
	 * @param boardNo 게시글번호
	 * @return Board 객체
	 * @throws FindException
	 */
	public Board selectByBoardNo(int boardNo) throws FindException {
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			Board board = session.selectOne("com.my.board.BoardMapper.selectByBoardNo", boardNo);
			return board;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	//Optional<> 클래스는 null이 올 수 있는 값을 감싸는 Wrapper 클래스로, 
	//참조하더라도 NullPointerException이 발생하지 않도록 도와준다.
	public Optional<Board> selectByBoardNoOptional(int boardNo) throws FindException{
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Board b =  session.selectOne("com.my.board.BoardMapper.selectByBoardNo", boardNo);
			Optional<Board> optB = Optional.of(b); //Optional.of(b) - b객체를 Optional형태로 변환
												   //b가 null이 아니라면 Optional.of()로 생성 가능, null이면 catch문으로 이동
			return optB;
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	/**
	 * 게시글을 작성한다
	 * @param board Board객체
	 * @return 
	 * @throws AddException
	 */
	public void insert(Board board) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("com.my.board.BoardMapper.insertBoard", board);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			throw new AddException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	/**
	 * 게시글을 수정한다
	 * @param board Board객체
	 * @return
	 * @throws ModifyException
	 */
	public void update(Board board) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.update("com.my.board.BoardMapper.updateBoard", board);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			throw new ModifyException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	/**
	 * 게시글을 삭제한다
	 * @param boardNo 게시글번호
	 * @return
	 * @throws RemoveException
	 */
	public void delete(int boardNo) throws RemoveException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.delete("com.my.board.BoardMapper.deleteBoard", boardNo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			throw new RemoveException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	/**
	 * 답글을 작성한다
	 * @param reply Reply객체
	 * @throws AddException
	 */
	@Transactional(rollbackFor = AddException.class)
	public void insertReply(Reply reply) throws AddException {
		SqlSession session = null;
		try {
			session= sqlSessionFactory.openSession();
			session.insert("com.my.board.BoardMapper.insertReply", reply);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	/**
	 * 답글을 수정한다
	 * @param reply Reply객체
	 * @throws ModifyException
	 */
	@Transactional(rollbackFor = ModifyException.class)
	public void updateReply(Reply reply) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.update("com.my.board.BoardMapper.updateReply", reply);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	/**
	 * 답글을 삭제한다
	 * @param replyNo 답글번호
	 * @throws RemoveException
	 */
	@Transactional(rollbackFor = RemoveException.class)
	public void deleteReply(int replyNo) throws RemoveException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.delete("com.my.board.BoardMapper.deleteReply", replyNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
}
