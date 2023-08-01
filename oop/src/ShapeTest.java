
abstract class Shape { //컴파일시 class Shape extends Object{}로 바뀐다 (object가 가장 상위)
//	private double area;
	protected double area;
	
	double getArea() {
		return area;
	}
	
//	void makeArea() {
//		
//	}
	
	abstract void makeArea(); //*하위클래스에서 재정의할 것이기 때문에 {} 없이 선언
	
	public String toString() {
		return (" 도형의 면적은 " + area + "입니다");
	}
}

//Shape에서 makeArea()를 abstract 메서드로 만든 경우 => 반드시 abstract method를 재정의해줘야 한다!!
class Triangle extends Shape {
	void makeArea() {
		
	}
}

class Circle extends Shape {  //자식쪽에서 부모를 결정, 부모클래스로 인해 자식클래스가 간결해짐
	private int radius;
	
	public Circle(int radius) {
		this.radius = radius;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void makeArea() {
		area = 3.14*radius*radius;
		//변수 area가 private이라고 선언되었으면 상속은 되지만, 자식은 직접 접근이 어렵다 -> 자식클래스 내부에서 호출할 수 없음
	}
	
	public String toString() {
		return("반지름이 " + radius + "인 원" + super.toString());
	}
}

class Rectangle extends Shape {
	private int width;
	private int height;
	private double area;
	
	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void makeArea() {
		area = width*height;
	}
	
	double getArea() {
		return area;
	}
	
	public String toString() {
		return("가로" + width + " 세로" + height + "인 사각형 도형의 면적은 " + area + "입니다");
	}
}


public class ShapeTest {  //public class ShapeTest extends Object{}
	public static void main(String[] args) {
		Circle c = new Circle(5); //반지름이 5인 원객체
		System.out.println(c.getRadius()); //5 -> 반환값이 없는 메서드일 경우 출력이 불가하다
		c.makeArea(); //원의 면적을 계산한다
		System.out.println(c.getArea()); //면적값(소수점이하까지) 
		
		Rectangle r = new Rectangle(3,4); //가로3, 세로4인 사각형 객체
		r.makeArea(); //사각형의 면적을 계산한다
		System.out.println(r.getArea()); //면적값 12.0
		
		
		//Upcasting & Downcasting으로 만들기
		Shape[] shapes = new Shape[5];
		shapes[0] = new Circle(5);		//upcasting ->같은 메서드가 있을 때 자식의 메서드로 덮어씌워진다
		shapes[1] = new Rectangle(3,4);	//upcasting
		
		for (int i = 0; i < 2; i++) {
//			shapes[i].makeArea(); //이걸 하고 싶으면 자식클래스로 downcasting해야한다
			if (shapes[i] instanceof Circle) {  //instanceof - 해당 요소인지 확인
				Circle c1 = (Circle) shapes[i]; //downcasting - upcasting된 것만 downcasting이 가능하다
				c1.makeArea();
			} else if (shapes[i] instanceof Rectangle) {
				Rectangle r1 = (Rectangle) shapes[i]; //downcasting
				r1.makeArea();
			}
			System.out.println(shapes[i].getArea());
		}
		
//		Circle c1 = (Circle) shapes[i]; //어느 자식클래스에 속해있는지 구분하지 않았기 때문에 ClassCastException 발생
//		c1.makeArea();
		
		
		//사용자 입장에서 간단하게 사용하고 싶을때 - Shape에 makeArea() 메서드 만들기
		for (int i = 0; i < 2; i++) {
			shapes[i].makeArea(); //Shape에 makeArea() 메서드 만들어도 다른 자식클래스의 메서드로 덮어씌워짐
			System.out.println(shapes[i].getArea());
			System.out.println(shapes[i].toString()); //클래스명@주소값
			//도형의 면적은 78.5입니다 와 같은 형식으로 출력하고 싶을 때 => Shape클래스에서 toString() 재정의
			//반지름이 5인 원 도형의 면적은 78.5입니다 => 도형마다 다르게 toString() 출력하고 싶으면 각 도형클래스에서 toString() 재정의
			System.out.println(shapes[i]); //shape[i].toString()이 자동 호출됨
		}
		
	}
}
