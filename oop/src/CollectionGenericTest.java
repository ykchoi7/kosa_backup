import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class CollectionGenericTest {
	
	public static void test(Collection<String> c) {
		c.add("one");
		c.add("two");
		c.add("one");
		c.add("four");
		c.add("five");
		//다른 데이터 타입을 컴파일에러 발생
//		c.add(Integer.valueOf(3));
//		c.add(Boolean.valueOf(false));
		
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
	
	public static void main(String[] args) {
		Collection<String> c;
		c = new ArrayList<>(); //element generic
		test(c); //순서대로, 중복 허용한 채로 값이 들어감
		
		System.out.println("-------------");
		test(new HashSet<>());
	}
}
