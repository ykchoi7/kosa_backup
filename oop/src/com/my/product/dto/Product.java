package com.my.product.dto;

import java.util.Objects;

public class Product {
	
	private String prodNo;	//이 변수를 private로 선언하여 dao나 user에서 사용이 불가하므로 오류 발생
	private String prodName;
	private int prodPrice;
	
	//생성자는 클래스명과 동일해야한다!! 
	//메서드와 구분 가능한 포인트 - return타입, void 등이 없음!!
	public Product(){}  //사용자정의 생성자 - 매개변수 없는 생성자
	
	public Product(String prodNo, String prodName) { //매개변수 2개
//		this.prodNo = prodNo;
//		this.prodName = prodName;
		
		//아래와 동일한 변수를 생성해야할 때 => this생성자 호출 (*반드시* 생성자의 첫 줄에 와야한다!! 안 그러면 컴파일 오류 발생)
		this(prodNo, prodName, 0);
	}
	
	public Product(String prodNo, String prodName, int prodPrice) { //매개변수 3개
		this.prodNo = prodNo;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	}
	//생성자가 아무것도 없어야 default 생성자 생성 (위에 3개 모두 없어야함)
	//생성자 Product() -> 오버로드 : 같은 이름 but 매개변수가 다른 타입이나 다른 갯수
	
	public void setProdNo(String prodNo) {
		//제약조건을 걸 때 setProdNo 내에서 설정해줌
		if (prodNo.length() != 5) {
			System.out.println("상품번호는 5자리여야 합니다.");
			return; //종료-> 5자리가 아니면 null이 입력됨
			//여기서 등록을 안하게 하고 싶으면 ProductUser의 add() 메서드에서 조건을 걸어야함
		}
		this.prodNo = prodNo;
	}
	
	public String getProdNo() {
		//return "prodNo의 암호화";
		return prodNo;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public String getProdName() {
		return prodName;
	}
	
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	
	public int getProdPrice() {
		return prodPrice;
	}
	
	
//	if (p.getProdNo().equals(product.getProdNo()))
//	-> if (p.equals(product)) 로 만들기 위한 코드 작성해보기

	//내가 짠 코드
//	public Object Product() {
//		return this.prodNo;
//	}
//
//	public void equals(String product) {
//		product = this.prodNo;
//	}
	
	public boolean equals(Product p) {
		return this.prodNo.equals(p.prodNo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(prodNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		//위 두줄을 풀어써보면
//		Class currentClass = this.getClass();
//		Class paramClass = obj.getClass();
//		if (currentClass != obj.getClass()) {
//			return false;
//		}
		Product other = (Product) obj;
		return Objects.equals(prodNo, other.prodNo);
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		if(obj == null) {
//			return false;
//		}
//		if(obj instanceof Product) {
//			Product product = (Product)obj;
//			if(this.prodNo.equals(product.prodNo)) {
//				return true;
//			}
//		}
//		return false;
//	}
	
}
