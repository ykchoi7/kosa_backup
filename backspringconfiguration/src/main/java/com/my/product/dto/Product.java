package com.my.product.dto;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor  @AllArgsConstructor
@Getter  @Setter @ToString
public class Product {
	private String prodNo;
	private String prodName;
	private int prodPrice;


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
	@Override
	public int hashCode() {
		return Objects.hash(prodNo);
	}

}
