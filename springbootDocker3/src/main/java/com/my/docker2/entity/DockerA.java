package com.my.docker2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @NoArgsConstructor @AllArgsConstructor
@Builder
@ToString

@Entity
@Table(name="docker_a_tbl")
public class DockerA {
	@Id
	@Column(name="a_1")
	private String a1;
	
	@Column(name="a_2")
	private String a2;
}
