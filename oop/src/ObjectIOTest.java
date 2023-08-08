import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.my.customer.dto.Customer;

public class ObjectIOTest {

	public static void write() {
		
		/*
		 * 스트림 : 파일용 바이트단위 출력스트림
		 * 필터스트림 : 객체단위 출력스트림
		 * 목적지 : 파일
		 */
		String fileName = "a.ser";
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(new Date());
			Customer c = new Customer("id1", "p1" ,"n1", "a1");
			oos.writeObject(c); //customer파일에 얼려져있음
			//customer에만 Serializable 하면 id, pwd만 직렬화되기 때문에 부모인 Person에 implements하기
			//그래야 name, address까지 모두 serializable 가능
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void read() {
		String fileName = "a.ser";
		ObjectInputStream ois = null;
		
		
		try {
			ois = new ObjectInputStream(new FileInputStream(fileName));
			Object obj1 = ois.readObject();
			System.out.println(obj1.toString());
			
			Object obj2 = ois.readObject();
			System.out.println(obj2); //obj2.toString() 자동 호출됨
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		write();
		read();
	} 

}
