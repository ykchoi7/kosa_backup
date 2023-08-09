import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.dao.ProductDAOArray;
import com.my.product.dao.ProductDAOInterface;
import com.my.product.dao.ProductDAOList;
import com.my.product.dto.Product;

public class ProductUser {
	Scanner sc = new Scanner(System.in); //sc=nonstatic 변수 (객체가 생성될 때 메모리에 할당)
//	ProductDAOArray dao = new ProductDAOArray();  //dao는 한번 선언하고 각 메서드가 같이 공유해야한다
//	ProductDAOInterface dao = new ProductDAOArray(); //interface 활용, array 저장소 사용하는 경우
//	ProductDAOInterface dao = new ProductDAOList(); //저장소를 다르게 사용 가능 - list 저장소로 쓰는 경우
	ProductDAOInterface dao;
	
	ProductUser() {
//		dao = new ProductDAOList();
		//이거 대신 properties 파일을 활용할 예정
		Properties env = new Properties();
		try {
			env.load(ProductUser.class.getResourceAsStream("my.properties")); 
			//class가 있는 경로에서 my.properties파일을 읽어와라
			String className = env.getProperty("product.dao"); 
			//property값을 가져오는 product.dao 라는 key에 해당하는 value를 가져와서 className에 넣기
			Class clazz = Class.forName(className); //className을 받는다 & 클래스 로드
			Object obj = clazz.getDeclaredConstructor().newInstance(); //객체 생성
			dao = (ProductDAOInterface) obj; //객체화된 것을 interface로 변환해서 dao에 저장
			System.out.println("DAO에서 실제 사용된 클래스: " + dao.getClass());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remove() throws RemoveException {
		System.out.println(">>상품 삭제<<");
		Product p1 = new Product();
		System.out.println("상품번호를 입력하세요: ");
		String prodNo = sc.nextLine();
					
		try {
			dao.delete(prodNo);
		} catch(RemoveException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void modify() throws ModifyException, FindException {
		System.out.println(">>상품 수정<<");
		System.out.println("상품번호를 입력하세요: ");
		String prodNo = sc.nextLine();
		
		//no로 제품 찾기
		dao.selectByProdNo(prodNo);
		
		//바꿀 상품이름과 가격 입력받기
		System.out.println("바꿀 상품 이름을 입력해주세요. 변경하지 않으려면 엔터를 누르세요");
		String pName = sc.nextLine();
		if (pName.equals("")) {
			pName = null;
		}
		System.out.println("바꿀 상품 가격을 입력해주세요. 변경하지 않으려면 0을 입력해주세요");
		String pPrice = sc.nextLine();
		
		Product p = new Product(prodNo, pName, Integer.parseInt(pPrice));
		dao.update(p);
		
		//내가 처음 짠 코드,, 이렇게 하나하나 할 추가할 필요 없음
//		if (opt == "1") {
//			String pname = p.getProdName();
//			System.out.println("바꿀 상품 이름을 입력해주세요 ");
//			String rname = sc.nextLine();
//			if (p.getProdName().equals(null)) {
//				throw new ModifyException("상품 이름이 없어서 수정할 수 없습니다");
//			} else {
//				dao.update(p);
//			}
//		}
//		if (opt == "2") {
//			int pprice = p.getProdPrice();
//			System.out.println("바꿀 상품 가격을 입력해주세요 ");
//			int rprice = sc.nextInt();
//			if (p.getProdPrice() == 0) {
//				throw new ModifyException("상품 가격이 0원이라서 수정할 수 없습니다");
//			} else {
//				dao.update(p);
//			}
//		}
	}
	
	public void findAll() throws FindException {
		System.out.println(">>상품 전체목록<<");
		try {
//			Product[] all1 = dao.selectAll(); //자료구조 배우기 전 배열로 생성했을때
			Object obj = dao.selectAll();
			if (obj instanceof Product[]) {
				Product[] all1 = (Product[]) obj;
				for (int i = 0; i < all1.length; i++) {
					Product p = all1[i];
					System.out.println(p.getProdNo() + ":" + p.getProdName() + ":" + p.getProdPrice());
				}
			} else if (obj instanceof List) {
				List<Product> list = (List) obj;
				for (int i = 0; i < list.size(); i++) {
					Product p = list.get(i);
					System.out.println(p.getProdNo() + ":" + p.getProdName() + ":" + p.getProdPrice());
				}
			}
		} catch (FindException e) {
			System.out.println(e.getMessage());
		}
		
		//자료구조 배우기 전 배열만 사용할 때의 코드
//		if(all1 == null) {
//			System.out.println("상품이 없습니다"); //출력됨
//		}else {
//			for(Product p: all1) {
//				System.out.println(p.getProdNo() + ":" + p.getProdName() + ":" + p.getProdPrice());
//			}
//		}
		System.out.println("----------------");		
	}
	
	public void findByProdNo() throws FindException {
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
		String prodPrice = sc.nextLine(); 
		//입력받는 값을 String으로 받아야 두번씩 호출이 안된다 (int로 받으면 두번 호출됨)
		
//		p1.prodNo = prodNo;
//		p1.prodName = prodName;
//		p1.prodPrice = prodPrice;
		p1.setProdNo(prodNo);
		p1.setProdName(prodName);
		p1.setProdPrice(Integer.parseInt(prodPrice));
		
		//위처럼 하나씩 세팅하지 않는 방법으로 생성자 사용하기
		Product p = new Product(prodNo, prodName, Integer.parseInt(prodPrice));
		//p1.prodPrice와 prodPrice의 데이터 타입이 다를 때,
			//String->int : int = Integer.parseInt(String);
			//String->float : float = Float.parseFloat(String);
		
		//exception 처리방식 1)throw declaration 2)직접 try~catch
		try {
			dao.insert(p1);
		} catch (AddException e) {
//			e.printStackTrace(); --이 부분이 있었기 때문에 오류메세지가 빨간색까지 다 뜨는 것
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void main(String[] args) throws AddException, FindException, ModifyException, RemoveException {
		ProductUser user = new ProductUser();
		
		//반복의 횟수 혹은 반복 최대 값이 결정되어있을 때 -> for 반복문
		//반복 횟수 정해져있지 않을 때 -> while 반복문
		while(true) {
			System.out.println("작업을 선택하세요: 1-상품전체목록, 2-상품번호로검색, 3-상품추가, 4-상품수정, 5-상품삭제, 9-종료");
			String opt = user.sc.nextLine(); //줄단위로 입력된 값 반환
			if (opt.equals("1")) {
				user.findAll();
			} else if (opt.equals("2")) {
				user.findByProdNo();
			} else if (opt.equals("3")) {
				user.add();
			} else if (opt.equals("4")) {
				user.modify();
			} else if (opt.equals("5")) {
				user.remove();
			} else if (opt.equals("9")) {
				break;
			}
		}
				
	}

}
