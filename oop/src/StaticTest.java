class A {
	static int si;	//static
	int i;	//non-static
	
	static void sm() {
//		System.out.println(this.i); //static 메서드 내에서는 this도 사용불가
		System.out.println(si); //static변수는 사용 가능
	}
	
	void m() { //객체 생성 후에만 사용가능
		System.out.println(si);
		System.out.println(this.i);
	}
}

public class StaticTest {

	public static void main(String[] args) {
		System.out.println(A.si); //A클래스도 bin에서 찾아서 사용
//		System.out.println(A, i); //non-static 변수는 초기화가 되지 않아서 사용할 수 없다
		
		A a1, a2;
		a1 = new A();
		a2 = new A(); //초기값을 각각 인스턴스 변수인 a1, a1에 넣음
		
		System.out.println(a1.i);	//0
		System.out.println(a2.i);	//0
		System.out.println(a1.si);	//a1이 참조하는 A의 si변수를 찾아감 
		System.out.println(a2.si);	//a2가 참조하는 A의 si변수를 찾아감
		
		a1.i++; a1.si++;
		
		System.out.println(a1.i);	//1
		System.out.println(a1.si);	//1
		System.out.println(A.si);	//1
		
		System.out.println(a2.i);	//0
		System.out.println(a2.si);	//1
		//a1=null이면 쓰레기로 구분되어 Garbage Collector
		
		a1.m();
//		A.m(); //static이 아닌 메서드일 경우 객체 생성을 new키워드로 해줘야 사용이 가능하고, 클래스에 직접 접근은 불가하다!
		
		A.sm();
		a1.sm();
	}
}
