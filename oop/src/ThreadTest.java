
class Sound extends Thread { //Thread클래스는 상속받는 경우, 상속받은 클래스 자체를 스레드로 사용 가능
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.print(Thread.currentThread().getName());
			System.out.println(" sound" + i);
		}
	}
}

class Caption implements Runnable { //인터페이스를 구현하는 경우, 해당 클래스를 인스턴스화해서 Thread생성자에 argument로 넘겨줘야한다
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.print(Thread.currentThread().getName());
			System.out.println(" caption" + i);
		}
	}
}

public class ThreadTest {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()); //현재 운영되는 스레드 알아내기 (스레드 이름)
		
		Sound s = new Sound(); //Sound타입의 객체 생성
//		s.run(); //run메소드 직접 호출 -> 아무 의미 없다!
		s.start();
		
		//재사용성이 높은 스레드일 경우 클래스이름을 만든다
//		Caption c = new Caption();
//		//c.start();
//		Thread t = new Thread(c);
//		t.start();
		
		/*
		 * 재사용성없는 스레드인 경우 클래스 이름을 만들지 않는다.익명클래스로 객체 생성한다
		 */
//		Thread t = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				for (int i = 0; i < 100; i++) {
//					System.out.print(Thread.currentThread().getName());
//					System.out.println(" caption" + i);
//				}
//			}
//		});
//		t.start();
		
		
		/*
		 * 람다식 표현 (재사용성 떨어질때 사용)
		 */
		Thread t = new Thread(() -> { //new Runnable() {@Override public void run() { 까지 표현됨
			for (int i = 0; i < 100; i++) {
				System.out.print(Thread.currentThread().getName());
				System.out.println(" caption" + i); //중간중간 이상한 값이 있어도 위에 thread까지만 실행되고 동영상 Thread가 cpu를 점유하게 된것
			}
		});
		t.start();
		
		for (int i = 0; i < 100; i++) {
			System.out.println(" 동영상" + i);
		}
		System.out.println("THE END");
		
	}
}
