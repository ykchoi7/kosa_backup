import world.asia.Japan;
import world.asia.Korea;
import world.europe.France;
//import world.asia.*; //이건 권장하지 않는 방향 (패키지 어디에 속하는지 알 수 없기 때문)

public class CountryTest {

	public static void main(String[] args) {
//		Korea k; //컴파일 에러 - default package내에 Korea.java가 없기 때문
//		world.asia.Korea k;
//		k = new world.asia.Korea();
//		
//		world.asia.Japan j;
//		j = new world.asia.Japan();
		
		//매번 패키지명.클래스명을 사용하기 번거롭기 때문에 간단하게 작성 & import
		Korea k;
		k = new Korea();
		
		Japan j;
		j = new Japan();

		France f = new France();
		
//		k.capital = "베이징";
//		k.language = "일본어";
//		k.population = "-1"; //마이너스도 넣어질 수 없음
		
	}

}
