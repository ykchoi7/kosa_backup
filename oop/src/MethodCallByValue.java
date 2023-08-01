
public class MethodCallByValue {

	//메서드 만들기
	public static void m(int i) { //i = 매개변수 //(3) m메서드 실행
		i = 99;		//(4) 1이 있던 i 대신 99를 i에 대입
	}				//(5) 그러나 m 메서드가 종료되면서 i에 99 들어갔던것도 종료됨

	//메서드 만들기 2
	public static void m(int[] arr) {
		arr[0] = 99; //arr[0]에 99 대입
	}				 //종료되면서 stack에 있던 m메서드도 없어짐
	
	public static void main(String[] args) {
		int i = 1;	//(1) 지역변수 i -> 메인에서 실행된 변수가 heap영역에 저장
		m(i);		//(2) m() 메서드 호출, i는 m메서드의 인자 -> 호출했으니 m메서드의 매개변수i에 인자 i값이 복붙 
		System.out.println(i); //main메서드나 m메서드나 heap영역에 있는 i값을 참조하기 때문에 지역변수 i값이 출력됨
		
		int[] arr = {1,2,3}; //배열 초기화
		m(arr);				 //arr = {99, 2, 3};
		System.out.println(arr[0]); //배열은 주소값이 변화하면 배열도 같이 변하기 때문에 m이 실행됨에 따라 heap영역의 값도 변경
	}
	//여기까지 Method영역에 저장
	//main 메소드의 지역변수 i와 매개변수 args는 Stack영역에 저장

}
