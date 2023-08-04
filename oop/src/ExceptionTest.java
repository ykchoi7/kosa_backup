import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionTest {
	public static void m(Object obj) {
		try {
			String str = obj.toString();
			System.out.println("객체정보: " + str);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage()); //null
		} finally {
			System.out.println("finally"); //try구문이든, catch구문이든 실행되는 코드
		}
	}
	
	public static void m(int num) {
		System.out.println("99를 " + num + "로 나눈 나머지값은 " + 99%num);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("0이 아닌 숫자를 입력해주세요");
		int num;
		try {
			num = sc.nextInt(); //num이 try블럭 안에 있으면 그 안에서만 사용 가능하다 
			m(num);
			//try블럭에서 if(num==0)이라는 조건으로 예외를 미리 막는 것도 GOOD CODE!
		} catch (InputMismatchException e) {
			//예외가 발생하면 한번더 알려주기
			System.out.println("숫자값을 입력하지 않았습니다. 1로 자동설정합니다.");
//			m(1);
			num = 1;
			m(num);
		} catch (ArithmeticException e) {
//			e.printStackTrace(); //exception 내용을 전부 stack처럼 차곡차곡 쌓아서 출력 -> 보기 좋지 않으므로 디버깅용으로만 사용!!
			String msg = e.getMessage();
			System.out.println(msg); //예외메시지만 출력함
			System.out.println("0을 입력했습니다");
			num = 1;
		}
		//m메서드에서 catch하는 방법도 있음!
		
		Object o;
		o = new Object();
		m(o); //객체의 toString값 반환
		o = null;
		m(o); //NullPointerException발생
	}
}
