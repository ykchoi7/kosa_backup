import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void write() {
		/*
		 * 스트림 : 바이트 단위 출력스트림
		 * 필터스트림 : 데이터타입단위 출력스트림
		 * 목적지 : 파일
		 */
		String fileName = "a.dat"; // 현재 실행되고 있는 경로에 파일이 생성됨 
								   // oop > java -cp bin DataIOTest
		FileOutputStream fos = null; //초기화 먼저
		DataOutputStream dos = null;
		
		try {
			fos = new FileOutputStream(fileName);
			dos = new DataOutputStream(fos);
			dos.writeInt(1); //int타입 쓰기 (byte타입으로)
			dos.writeFloat(2.3F); //float타입
			dos.writeBoolean(false); //boolean타입
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void read() {
		/*
		 * 스트림: 바이트단위 입력스트림
		 * 필터스트림: 데이터타입단위 출력스트림
		 * 자원: 파일
		 * 
		 */
		String fileName = "a.dat";
		
		FileInputStream fis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			//꼭 쓰기한 순서대로 데이터를 읽어와야한다!!
			int i = dis.readInt();
			float f = dis.readFloat();
			boolean b = dis.readBoolean();
			System.out.println(i + ":" + f +":" + b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dis != null)
				try {
					dis.close(); //나중에 생성된 걸 먼저 close
				} catch (IOException e) {
					e.printStackTrace();
				} 
//			fis.close(); //dis, fis 중에 하나만 close()해도된다
		}
		
	}
	
	public static void main(String[] args) {
//		write();
		read();
	}

}
