//부모와 자식클래스 내부의 메서드명이 동일할 때

class ParentMethod1 {
	void pm1() {
		System.out.println("ParentMethod1의 pm1()입니다");
	}
	
	void m() {
		System.out.println("ParentMethod1의 m()입니다");
	}
}

class ParentMethod2 extends ParentMethod1 {
	void pm2() {
		System.out.println("ParentMethod2의 pm2()입니다");
	}
}

class ChildMethod extends ParentMethod2 {
	void cm() {
		System.out.println("ChildMethod의 cm()입니다");
	}
	
	void m() {
		System.out.println("ChildMethod의 m()입니다");
		super.m();
	}
}


public class InheritanceMethod {

	public static void main(String[] args) {
		ChildMethod cm = new ChildMethod();
		cm.m(); //부모와 자식 메서드명이 동일할 때 자식 메서드로 덮어씌워짐
		
		ParentMethod1 pm = new ChildMethod();
		pm.m(); //pm의 타입이 ChildMethod로 새로 생성이 되었기 때문에 최종적으로 ChildMethod의 메소드로 덮어씌워짐
		
		ChildMethod cm1 = (ChildMethod) new ParentMethod1();
		cm1.m();
		
		//자식의 객체에서 부모로부터 받은 모든 메서드를 불러올 수 있다
		cm.cm();
		cm.pm2();
		cm.pm1();
		
		ParentMethod2 pm2 = new ParentMethod2();
		pm2.pm2();
		pm2.pm1();
//		pm2.cm(); //부모클래스 입장에서 자식클래스의 메서드를 가져올 수는 없다
		pm2.m();  //여기서는 자식 메서드의 m()이 아니라 pm2보다 부모인 pm1의 메서드 m()을 가져와서 출력 가능
	}

}
