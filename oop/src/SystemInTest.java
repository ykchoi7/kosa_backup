import java.io.IOException;
import java.io.InputStream;

public class SystemInTest {

	public static void main(String[] args) {
		InputStream is = System.in;
		try {
			//int readValue = is.read();
			//System.out.println(readValue);
			int readValue = -1; //초기화
			while((readValue = is.read()) != -1) {
				System.out.print(readValue);
			} //windows의 스트림종료 ctrl+z 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
