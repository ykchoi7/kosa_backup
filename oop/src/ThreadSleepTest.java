import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadSleepTest {

	public static void main(String[] args) {
	
		System.out.println(Thread.currentThread().getName()); //main thread
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //Date타입 형식 설정
		
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				Date dt = new Date();
				System.out.println(sdf.format(dt));
				try {
					Thread.sleep(1000); //1초간 일시중지
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();  //재사용할 일이 없다면 지역변수 안만들고 동작시킬수도 있음
	
	}

}
