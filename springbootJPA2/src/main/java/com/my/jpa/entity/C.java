package com.my.jpa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Builder
//@ToString

@Entity
@Table(name="c_tbl") 
/**
 * 게시글 작성자 (회원)
 */
public class C {
	@Id
	@Column(name="c_id")
	private String cId;      //아이디
	
	@Column(name="c_name")
	private String cName;	//이름
	
	@OneToMany//(mappedBy = "bC") //C가 강제삭제되지 않게 하는 법 1) mappedBy
	@JoinColumn(name="b_id")    //						2) @JoinColumn - *권장*더 강력한 제어
	private List<B> bs; //회원 입장에서 작성한 게시글들 (default:Lazy상태)
}
