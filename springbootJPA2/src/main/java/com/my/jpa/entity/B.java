package com.my.jpa.entity;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString

@Entity
@Table(name = "b_tbl") // 게시글
@SequenceGenerator(
		   name = 
		       "B_SEQ_GENERATOR", // 사용할 sequence 이름
		   sequenceName =
		         "b_seq", // 실제 데이터베이스 sequence 이름
		   initialValue = 1, allocationSize = 1
		)

public class B {

	@Id
	@Column(name = "b_no")
    @GeneratedValue(
       strategy = GenerationType.SEQUENCE,
       generator =
             "B_SEQ_GENERATOR" // 위의 sequence 이름
    )
	private Long bNo;
	
	//@Column 어노테이션을 설정하지 않으면 멤버변수와 같은 이름의 컬럼이 자동 만들어진다
	
	@ManyToOne
	@JoinColumn(name="b_id", nullable = false) //어떤 컬럼을 기준으로 Join할지 결정, null 허용x
	private C bC; //게시글을 쓴 작성자 정보

	@OneToMany(
			fetch = FetchType.EAGER //fetch란? SQL저장소에서 SELECT를 한 후 스냅샷에 객체 생성 (영속성 유지)
			,
			cascade = CascadeType.REMOVE //B entity가 removed 상태가 될 때 cascade를 진행한다 라는 것을 의미
//			cascade = CascadeType.ALL   //모든 상태전이를 같이 하고 싶을 때(B entity가 persist상태가 되면 r entity도 모두 persist 상태가 되도록) .ALL
//			cascade = CascadeType.MERGE
			)
	@JoinColumn(name="rb_no")
	private List<R> rs;

}