
public class ObjectTest {

	public static void main(String[] args) {
		Object o1;
		Object o2;
		Object o3;
		
		o1 = new Object();
		o2 = new Object();
		o3 = o1;
		
		//object
		System.out.println(o1.hashCode());
		System.out.println(o2.hashCode());
		System.out.println(o3.hashCode()); //o1과 동일
		
		//== 주소값 비교
		System.out.println(o1 == o2); //false
		System.out.println(o1 == o3); //true
		
		//.equals() toString값 비교 
		System.out.println(o1.equals(o2)); //false
		System.out.println(o1.equals(o3)); //true
		
		System.out.println(o1.toString()); //클래스명+@+주소값
		
		Circle c1, c2;
		c1 = new Circle(5);
		c2 = new Circle(5);
		System.out.println(c1.toString()); //클래스명+@+해시코드값16진수 Circle@73a28541
		System.out.println(c2.toString()); //Circle@6f75e721
		System.out.println(c1 == c2); //false
		System.out.println(c1.equals(c2)); //false
		//equals는 String으로 변환한 값을 비교하는데 String 타입이 아닌 값들은 모두 주소값이 반환된다 
		
		System.out.println("--String--");
		String s1, s2;
		s1 = new String("Hi");
		s2 = new String("Hi");
		System.out.println(s1.toString()); //Hi
		System.out.println(s2.toString()); //Hi
		System.out.println(s1 == s2); //false
		System.out.println(s1.equals(s2)); //true
		//반면 String 타입은 반환값도 String으로 반환되기 때문에 equals를 했을 때 같은 값이라고 인식한다
	}

}
