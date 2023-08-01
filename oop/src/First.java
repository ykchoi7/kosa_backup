
public class First {
	
	static int smf; //static field
	int mf;			//non-static field, 인스턴스 변수
	
	public static void main(String[] args) {
		int lv; //local variable 지역변수
		//System.out.println(lv);  //lv 지역변수는 초기화를 하지 않았으므로 출력하려고하면 오류 발생
	
		System.out.println(smf);   //smf static변수는 생성만 해도 자동초기화가 되기 때문에 오류 없음 (0으로 초기화) 

		//System.out.println(mf);    //static 메서드 내부에서는 non-static 변수를 참조할 수 없음
	
		//인스턴스화 (객체 만들기)
		First one; 		   //참조형 지역변수 one (클래스 First 타입을 가지고 있기 때문)
		one = new First(); //인스턴스화 (객체 만들기)
		//생성되면 HEAP 영역에 자리가 생성됨 
		//smf와 mf는 one이 초기화되어 Heap영역에 저장된다
		System.out.println(one.mf); //객체를 찾아가서 그 객체의 필드로 사용 -> 0
		
	}

}
