package com.my.product.dao;
import com.my.product.dto.Product;

public class ProductDAOArray implements ProductDAOInterface{

	private Product[] products = new Product[5]; //상품저장소
	private int totalCnt; //저장된 상품수, 처음 0으로 초기화
	
	//사용설명서를 인터페이스에 적어두어도 여기에서도 마우스 올려놓았을 때 설명이 보인다
	public void insert(Product product) {
		
		if (totalCnt == products.length) {
			System.out.println("저장소가 꽉 찼습니다.");
		} else {
			products[totalCnt] = product; //[]에 넣을 때는 그냥 각 인덱스 지정해주고 값 넣어주면 됨
			totalCnt++;
		}
		
//		while (totalCnt < 5) {
//			ArrayList<Product> plist = new ArrayList<>();
//			int prodLen = products.length;
//			
//			for (int i = 0; i < prodLen; i++) {
//				plist.add(product);
//				totalCnt++;
//			}
//		} System.out.println("저장소가 꽉 찼습니다.");
		
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
