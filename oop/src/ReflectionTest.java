import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Scanner;

public class ReflectionTest {
	
	//Reflection 실행되면
	//1) ReflectionTest.class 찾기
	//2) JVM으로 코딩 -> Loadtime Dynamic Load
	//3) 바이트코드 검색
	//4) 0과1로 재해석, Method영역에 기억
	//5) static 변수 자동초기화
	//6) main() 호출
	
	public static void test(String className) throws ClassNotFoundException {
		Class c = Class.forName(className); //인자값을 활용해서 Runtime Dynamic Load 진행 (클래스의 정보를 담고 있는 클래스)
		
		//각 정보를 알 수 있음
		Field[] fields = c.getDeclaredFields();
		for (Field f : fields) {
			System.out.println(f.getName());
		}
		System.out.println("-----------------------");
		
		Method[] methods = c.getDeclaredMethods(); //메서드는 어떤 것이 있는지 알아보기 위함 (메서드 호출)
		for (Method m : methods) {
			System.out.println(m.getName());
		}
		
		try {
			//deprecated - 
			Object obj = c.getDeclaredConstructor().newInstance(); //객체생성
			System.out.println(obj); //obj.toString() 자동호출됨
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		String s;
		Date d;
		Class.forName("java.util.Date"); //실행시에 작동하는 클래스 = Runtime Dynamic Load
		Class.forName("java.util.ArrayList");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("클래스이름을 입력하세요 ex)java.util.Date");
		String className = sc.nextLine();
		test(className);
	}
	
	
}



