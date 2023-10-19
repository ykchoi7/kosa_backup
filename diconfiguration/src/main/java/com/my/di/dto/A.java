
package com.my.di.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component(value="a")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class A {
	private String msg;
}
