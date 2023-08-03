package com.my.product.dao;
import com.my.exception.AddException;
import com.my.product.dto.Product;

public class ProductDAOArray implements ProductDAOInterface{

	private Product[] products = new Product[5]; //상품저장소
	private int totalCnt; //저장된 상품수, 처음 0으로 초기화
	
	//사용설명서를 인터페이스에 적어두어도 여기에서도 마우스 올려놓았을 때 설명이 보인다
	public void insert(Product product) throws AddException { 
		//**부모에도 예외 throws 되어있어야 자식에서도 예외 선언 가능!!!
		//return을 하는 것은 좋은 객체지향언어가 아니다!
		
//		if (totalCnt == products.length) {
//			System.out.println("저장소가 꽉 찼습니다.");
//			return;
//		} else {
//			products[totalCnt] = product; //[]에 넣을 때는 그냥 각 인덱스 지정해주고 값 넣어주면 됨
//			totalCnt++;
//		}
		
		for (int i = 0; i < totalCnt; i++) {
			if (products[i].getProdNo().equals(product.getProdNo())) {
				throw new AddException("이미 존재하는 상품입니다"); 
				//그냥 예외만 알려주고 넘어가면 (return을 활용하는 형태) 재사용성이 떨어지기 때문에 예외 떠넘기기!
			}
		}
		
		//if 구문 대신 exception으로 처리해보기
		try {
			products[totalCnt] = product;
			totalCnt++; //GOOD CODE
			//products[totalCnt++] = product; //이렇게 작성하는 코드는 BAD CODE
		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("저장소가 꽉 찼습니다. 저장된 상품 수: " + totalCnt); //저장소가 꽉 찼습니다. 저장된 상품 수: 5
			throw new AddException("저장소가 꽉 찼습니다. 저장된 상품 수: " + totalCnt); //예외를 잡는데 AddException이라는 강제 예외를 발생시킨다
		}
	}
	
	
	public Product selectByProdNo(String prodNo) {
		for (int i = 0; i < totalCnt; i++) {
			Product savedP = products[i]; //이미 저장된 상품
			if (savedP.getProdNo().equals(prodNo)){ //String의 같음은 꼭 .equals 로 표현하기***
				return savedP;
			}
		}
		return null;
	}

	
	public Product[] selectAll() {  //여러개가 조회되어야하기 때문에 배열타입으로 생성
		
		Product[] all = new Product[totalCnt];
		
		//저장소에 저장된 상품 0개 일 때
		if (totalCnt == 0) {
			return null;
		}
//		if (products == null) { //products는 명시 초기화를 했기 때문에 null이 아님 -> 그래서 이렇게 설정하면 안됨
//			return null;
//		}
		
		//저장소에 저장된 상품이 있을 때
		for (int i = 0; i < totalCnt; i++) {
			all[i] = products[i];
		}
		return all;
		
	}
}
