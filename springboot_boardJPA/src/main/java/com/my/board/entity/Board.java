package com.my.board.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.my.board.dto.ReplyDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
@ToString

@Entity
@Table(name="board_tbl")
@DynamicInsert
@SequenceGenerator(
			name = "BOARDJPA_SEQ_GENERATOR",
			sequenceName = "boardjpa_seq",
			initialValue = 1,
			allocationSize = 1
		)
public class Board {
	
	@Id
	@Column(name="board_no")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "BOARDJPA_SEQ_GENERATOR"
	)
	private Integer boardNo; //게시글 번호
	
	@Column(name="board_title")
	private String boardTitle; //게시글 제목
	
	@Column(name="board_content")
	private String boardContent; //게시글 내용
	
	@Column(name="board_id")
	private String boardId; //게시글 작성자
	
	@Column(name="board_dt")
	@ColumnDefault(value="SYSDATE")
	private Date boardDt; //게시글 작성일자
	
	@OneToMany(
			fetch = FetchType.EAGER
			,
			cascade = CascadeType.ALL
	)
	@JoinColumn(name="reply_board_no")
	private List<Reply> replies; //답글 목록
	
	/**
	 * 게시글 내용을 변경한다
	 * @param name
	 */
	public void modifyContent(String boardContent) {
		this.boardContent = boardContent;
	}	
	
	public void countReply(Integer boardNo) {
		this.boardNo = boardNo;
	}
//	private Integer replycnt; //답글 개수
	
}
