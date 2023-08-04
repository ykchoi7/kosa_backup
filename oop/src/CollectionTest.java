import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.my.customer.dto.Customer;
import com.my.product.dto.Product;

public class CollectionTest {
	
	public static void test(Collection c) {
		c.add("one");
		c.add(Integer.valueOf(2)); //deprecated->현재 버전에서 사용하지 않음
		c.add(Float.valueOf(3.0F));
		c.add("one");
		c.add(Boolean.valueOf(false));
		c.add(new Product("C0001", "아메리카노", 1000));
		c.add(new Product("C0001", "라떼", 2000));
		
		System.out.println("요소갯수= " + c.size());
		System.out.println(c); //c.toString메소드 자동호출
	
		c.remove("one"); //중복된 값이 있을 때 첫번째 값을 제거
		System.out.println("one객체 삭제됨");
		
		//한 요소씩 출력하는 방법
//		Iterator it = c.iterator();
//		while(it.hasNext()) { //방문할 요소가 있는가 
//			Object e = it.next(); //요소 방문
//			System.out.println("저장된 요소: " + e);
//		}; 
		
		//향상된 for문으로 구현하기
		for (Object e:c) {
			System.out.println("저장된 요소: " + e);
		}
	}
	
	public static void test(Map m) {
		m.put("one", new Date()); //값 입력할 때 put메소드 활용 //1
		m.put("two", new String("second")); //2
		m.put("one", Integer.valueOf(3)); //**key가 중복되면 value부분이 덮어씌워진다!!
										  //Date() 대신 one=3 출력
		m.put("four", Float.valueOf(4.5F)); //3
		m.put("five", Boolean.valueOf(false)); //4
		m.put(new Product("C0001", "아메리카노", 1000), "커피1"); //5
		m.put(new Product("C0001", "카페라떼", 1000), "커피2");
		//Product에는 hashCode 오버라이딩되어있어서 같은 key값이면 같은 객체로 인식한다
		m.put(new Customer("id1", "홍길동"), "고객1"); //6
		m.put(new Customer("id1", "장길동"), "고객2"); //7
		System.out.println("요소갯수: " + m.size());
		System.out.println(m);
		
		//product 정보 불러오기 위해서 생성자 새로 만들고 no를 특정 상품번호로 설정
		Product p = new Product();
		p.setProdNo("C0001");
		System.out.println("C0001 상품키의 값은 " + m.get(p)); //커피2로 덮어씌워짐
	}
	
	
	public static void main(String[] args) {
		Collection c;
		c = new ArrayList();
		test(c); //순서대로, 중복 허용한 채로 값이 들어감
		
		System.out.println("-------------");
		test(new HashSet()); //순서상관없이, 중복없이 값이 들어감
		
		System.out.println("-------------");
		test(new HashMap());
	}
}
