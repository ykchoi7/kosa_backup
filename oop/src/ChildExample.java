//p.334 문제풀이

class Parent {
	public String nation;
	
	public Parent() {
		this("대한민국");
		System.out.println("Parent() call");
	}
	
	public Parent(String nation) {
		this.nation = nation;
		System.out.println("Parent(String nation) call");
	}
}

class Child extends Parent {
	public String name;
	
	public Child() {
		this("홍길동");
		System.out.println("Child() call");
	}
	
	public Child(String name) {
		//super(); 생략된 것 -> Parent() 실행
		this.name = name;
		System.out.println("Child(String name) call");
	}
}


public class ChildExample {
	public static void main(String[] args) {
		Child child = new Child();
	}
}
