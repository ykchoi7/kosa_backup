package com.my.product.dto;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private String prodNo;
	private String prodName;
	private int prodPrice;

	@Override
	public int hashCode() {
		return Objects.hash(prodNo);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj instanceof Product) {
			Product product = (Product)obj;
			if(this.prodNo.equals(product.prodNo)) {
				return true;
			}
		}
		return false;
	}
	
}
