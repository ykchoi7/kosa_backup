package com.my.board.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Builder

public class ReplyDTO {
	private Integer replyNo;
	private Integer replyBoardNo;
	private Integer replyParentNo;
	private String replyContent;
	private String replyId;
	private Date replyDt;
	private Integer level;
}
