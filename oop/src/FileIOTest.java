import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOTest {
	public static void write() {
		/*
		 * 스트림종류 : 바이트출력스트림
		 * 목적지 : 파일
		 */
		/*String fileName = "D:\\b.txt";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
			//fos.write(65);
			byte[] bytes = "ABCDEFG".getBytes();
			fos.write(bytes);			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		/*
		 * 스트림종류 : 문자출력스트림
		 * 목적지 : 파일
		 */
		String fileName = "D:\\c.txt";
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName, true);
			fw.write("가나다라마바사");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public static void read() {
		/*
		 *스트림종류 : 바이트 입력스트림
		 *자원 : 파일 
		 */
		String fileName = "D:\\a.txt"; //   \n  \t
		/*FileInputStream fis;
		try {
			fis = new FileInputStream(fileName);
			int readValue = -1;
			while( (readValue = fis.read()) != -1) {
				System.out.print((char)readValue);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		/*
		 *스트림종류 : 문자입력스트림FileReader
		 *자원 : 파일 
		 */
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);//자원과의 연결
			int readValue = -1;
			while( (readValue = fr.read() ) != -1) {
				System.out.print( (char)readValue);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}//자원과의 연결해제
			}
		}
	}
	public static void main(String[] args) {
		//read();
		write();
	}

}
