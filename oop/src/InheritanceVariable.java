//부모와 자식클래스 내부의 변수명은 동일하나, 변수 타입과 메서드명이 다를 때

class PVariable1{
	int a;
	void pm1() {
		System.out.println("PVariable1의 a=" + a);
	}
}

class PVariable2 extends PVariable1 {
	void pm2() {
		System.out.println("PVariable2의 a=" + a);
	}
}

class CVariable extends PVariable2 {
	String a;
	void cm() {
		System.out.println("CVariable의 a=" + a + ", PVariable1의 a=" + super.a);
	}
}

public class InheritanceVariable {

	public static void main(String[] args) {
		PVariable1 pv1 = new PVariable1();
		pv1.a = 9;
		PVariable2 pv2 = new PVariable2();
		pv2.a = 99;
		CVariable cv = new CVariable();
		cv.a = "hello"; //동일한 변수명을 가지고 있을때 부모쪽의 변수가 자식쪽의 변수로 덮어씌워짐
		
		cv.cm(); //CVariable의 a=hello, PVariable1의 a=0
		//자기 내부에 있는 메서드 출력 => hello 출력, hello와 부모클래스의 a를 모두 출력하고 싶을때 => super.a
		cv.pm2(); //PVariable2의 a=0
		//부모 클래스에 있는 메서드로 출력 가능, 자기가 가지고 있는 a가 없기 때문에 부모의 a를 가져옴 -> 초기화된 a의 값 0 출력
		cv.pm1(); //PVariable1의 a=0
		//조상 클래스에 있는 메서드로 출력 가능, 자기가 가지고 있는 a의 초기화된 int값 0이 출력됨
		
		pv2.pm2(); //PVariable2의 a=99
		pv2.pm1(); //PVariable1의 a=99
		
		pv1.pm1(); //PVariable1의 a=9
	}

}
