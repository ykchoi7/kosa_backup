
public class OverloadTest { //오버로드 예체
	
	//비슷한 기능을 수행하는 메서드만 같은 이름 사용
	public static void plus(int a, int b) {
		System.out.println("합은 " + (a+b));
	}
	
	public static void plus(int a, int b, int c) {
		System.out.println("합은 " + (a+b+c));
	}
	
	public static void plus(double a, double b) {
		System.out.println("합은 " + (a+b));
	}
	//println이 대표적으로 오버로드된 케이스 - () 안에 다양한 타입이 들어갈 수 있음
	
	public static void main(String[] args) {
		plus(1,2); //3
		plus(1,2,3); 
		plus(1, 2.0); //3.0 plus() 안에 double타입으로 넣었기 때문에 1이 자동 형변환 -> 1.0
	}

}
