class Single {
	static private Single s = new Single(); //main메서드 호출되기 전에 초기화가 된다 (명시적 초기화)

	private Single() {
		
	}
	
	static Single getInstance() { //static 내부에서는 this 사용 불가
//		return new Single();
		return s;
	}
}


public class SingletonTest {

	public static void main(String[] args) {
		Single s1, s2;
		//s1 = new Single(); //생성자 호출 못하도록-> single class에서 생성자를 private으로 생성하면 된다
		s1 = Single.getInstance(); //ok-> single. 으로 호출하기 위해서는 static으로 메서드 만들어야 한다
		s2 = Single.getInstance();
		System.out.println(s1 == s2);
	}

}
