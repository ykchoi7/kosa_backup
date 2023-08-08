import java.util.StringTokenizer;

public class StringTest {
	public static void main(String[] args) {
		String s = "안녕하세요";
		char c = s.charAt(0); //'안' - 0번째 인덱스에 있는 문자 하나
		int size = s.length(); //5 - 문자열 길이
		
		s = "가나다라다나가";
		//System.out.println("Palindrome문자열입니다");
		//System.out.println("Palindrome문자열이 아닙니다");
		
		/*if(s.charAt(0) != s.charAt(size-1)) {
			System.out.println("Palindrome문자열이 아닙니다");
		}else if(s.charAt(1) != s.charAt(size-1-1)) {
			System.out.println("Palindrome문자열이 아닙니다");
		}else if(s.charAt(2) != s.charAt(size-1-2)) {
			System.out.println("Palindrome문자열이 아닙니다");
		}else {
			System.out.println("Palindrome문자열입니다");
		}*/
		size = s.length();
		int i=0;
		int max = size/2;
		for(; i<max; i++) {
			if(s.charAt(i) != s.charAt(size-1-i)) {
				break;
			}
		}
		if(i == max) {
			System.out.println("Palindrome문자열입니다");
		}else {
			System.out.println("Palindrome문자열이 아닙니다");
		}
		
		s = "100:70:65";
		s = "100::65";
		String delim=":";
		String[]arr = s.split(delim); //: 기준으로 split
		for(String str:arr) {//"100", "", "65"
			if(str.equals("")) { //공백이 있으면
				System.out.println("미응시"); //미응시로 출력
			}else {
				System.out.println(Integer.parseInt(str));
			}
		}
		
		System.out.println("-----------------");
		StringTokenizer st = new StringTokenizer(s, delim);
		while(st.hasMoreTokens()) {
			String str = st.nextToken(); //"100", "65"
			System.out.println(str);
		}
			
	}
}
