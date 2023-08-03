
class ParentConstructor {
	ParentConstructor() { //매개변수없는 생성자
		System.out.println("ParentConstructor() 생성자가 호출됨");
	}
	
	ParentConstructor(String name) {
		System.out.println("ParentConstructor("+name+") 생성자가 호출됨");
	}
}

class ChildConstructor extends ParentConstructor {
	ChildConstructor() {
		//super(); 이 부분이 생략된것!!!
		System.out.println("ChildConstructor() 생성자가 호출됨");
	}
	
	ChildConstructor(int age) {
		super("최윤경"); //부모클래스에서 특정 메서드를 호출하고 싶을때는 그것에 맞는 인자값을 부여해주기 
		System.out.println("ChildConstructor("+age+") 생성자가 호출됨");
	}
}


public class InheritanceConstructor {

	public static void main(String[] args) {
		ParentConstructor p = new ParentConstructor();
		ChildConstructor c = new ChildConstructor(); //**자식 생성자 호출될 때 부모 생성자를 먼저 호출
		ChildConstructor c1 = new ChildConstructor(10); //부모의 매개변수없는 생성자가 먼저 호출됨
														//다음으로 자식의 매개변수 int가 있는 생성자가 호출됨

		ParentConstructor p1 = new ChildConstructor();  //upcasting
//		ChildConstructor c2 = (ChildConstructor) new ParentConstructor(); 
		//ClassCastException -> new로 하면 기존 parent에는 자식이 없기 때문에 다운캐스팅이 안됨
		ChildConstructor c2 = (ChildConstructor) p1;
	}

}
