package com.my.board.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor
@Builder

@Entity
@Table(name="reply_tbl")
@SequenceGenerator(
		name = "REPLYJPA_SEQ_GENERATOR",
		sequenceName = "replyjpa_seq",
		initialValue = 1,
		allocationSize = 1
)
public class Reply {
	
	@Id
	@Column(name="reply_no")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "REPLYJPA_SEQ_GENERATOR"
	)
	private Integer replyNo;
	
	@Column(name="reply_board_no")
	private Integer replyBoardNo;
	
	@Column(name="reply_parent_no")
	private Integer replyParentNo;
	
	@Column(name="reply_content")
	private String replyContent;
	
	@Column(name="reply_id")
	private String replyId;
	
	@Column(name="reply_dt")
	private Date replyDt;
	
	/**
	 * 답글 내용을 변경한다
	 * @param name
	 */
	public void modifyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
//	private Integer level;
	
}
