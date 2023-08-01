import java.util.Scanner;

import com.my.product.dao.ProductDAO;
import com.my.product.dto.Product;

public class ProductUser {
	Scanner sc = new Scanner(System.in); //sc=nonstatic 변수 (객체가 생성될 때 메모리에 할당)
	ProductDAO dao = new ProductDAO();	 //dao는 한번 선언하고 각 메서드가 같이 공유해야한다
	
	public void findAll() {
		System.out.println(">>상품 전체목록<<");
		Product[] all1 = dao.selectAll();
		if(all1 == null) {
			System.out.println("상품이 없습니다"); //출력됨
		}else {
			for(Product p: all1) {
				System.out.println(p.getProdNo() + ":" + p.getProdName() + ":" + p.getProdPrice());
			}
		}
		System.out.println("----------------");		
	}
	
	public void findByProdNo() {
		System.out.println(">>상품 번호로 검색<<");
		System.out.println("상품번호를 입력하세요: ");
		String prodNo = sc.nextLine(); //sc.next() 앞에는 this.이 생략된 것

		Product p;
		p = dao.selectByProdNo(prodNo);
		if(p == null) {
			System.out.println("상품이 없습니다"); 
		}else{
			//출력됨
			System.out.println(p.getProdNo() +"번호 상품의 상품명:" + p.getProdName() + ", 가격:" + p.getProdPrice());
		}
	}
	
	public void add() { //상품 상세 정보 입력받기
		System.out.println(">>상품 추가<<");
		Product p1 = new Product();
		System.out.println("상품번호를 입력하세요: ");
		String prodNo = sc.nextLine();
		System.out.println("상품명을 입력하세요: ");
		String prodName = sc.nextLine();
		System.out.println("상품가격을 입력하세요: ");
		int prodPrice = sc.nextInt();
		
//		p1.prodNo = prodNo;
//		p1.prodName = prodName;
//		p1.prodPrice = prodPrice;
		p1.setProdNo(prodNo);
		p1.setProdName(prodName);
		p1.setProdPrice(prodPrice);
		
		//위처럼 하나씩 세팅하지 않는 방법으로 생성자 사용하기
		Product p = new Product(prodNo, prodName, prodPrice);
		//p1.prodPrice와 prodPrice의 데이터 타입이 다를 때,
			//String->int : int = Integer.parseInt(String);
			//String->float : float = Float.parseFloat(String);
		dao.insert(p1);
	}
	
	
	public static void main(String[] args) {
		ProductUser user = new ProductUser();
		
		//반복의 횟수 혹은 반복 최대 값이 결정되어있을 때 -> for 반복문
		//반복 횟수 정해져있지 않을 때 -> while 반복문
		while(true) {
			System.out.println("작업을 선택하세요: 1-상품전체목록, 2-상품번호로검색, 3-상품추가, 9-종료");
			String opt = user.sc.nextLine(); //줄단위로 입력된 값 반환
			if (opt.equals("1")) {
				user.findAll();
			} else if (opt.equals("2")) {
				user.findByProdNo();
			} else if (opt.equals("3")) {
				user.add();
			} else if (opt.equals("9")) {
				break;
			}
		}
				
	}
}
