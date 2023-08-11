
public class WrapperTest {

	public static void main(String[] args) {
		int i=100;
		Object obj;
		//Boxing : 기본자료형을 참조자료형으로 만드는 것
		//{기본형(int)->참조형(Integer)}->Object로 업캐스팅		
		obj = Integer.valueOf(i); 
		
		obj = i; //AutoBoxing됨
		         //컴파일시에 Integer.valueOf(i);로 코드가 바뀜
		System.out.println(obj);
		
		int j;
		//Object를 다운캐스팅   UnBoxing :{Integer(참조형) -> int(기본형}
		j = ((Integer)obj).intValue();
		
		j = (Integer)obj; //AutoUnboxing됨
		                  //컴파일시에 ((Integer)obj).intValue();로 코드가 바뀜
		
	}

}
