
class Pop extends Thread {
	private Share s;
	
	Pop(Share s) {
		this.s = s;
	};
	
	@Override
	public void run() {
		this.s.pop();
	}
}

class Push extends Thread {
	private Share s;
	
	Push(Share s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		this.s.push();
	}
}

class Share {
	private int i;
	
	public void push() {
		for (int i = 0; i < 100; i++) {
			synchronized(this) { //동기화처리(lock) synchronized(공유객체){} -> segment 짧게 설정하기
				this.notify(); //이 공유객체를 사용하는 wait된 스레드를 깨운다, wait스레드가 여러개라면 가장 먼저 wait된 스레드 하나만 깨울 수 있다
							   //모든 wait를 다 깨우고 싶다? => notifyAll() 사용
				System.out.println("Before push : i=" + this.i);
				this.i++;
				System.out.println("After push :  i=" + this.i);
			}
		}
	}
	
	public void pop() {
		for (int i = 0; i < 100; i++) {
			synchronized (this) { //잠금장치
				if (this.i == 0) {
					try {
						this.wait(); //wait() 메소드 - 이 공유객체를 사용하는 현재 스레드를 일시중지한다
									//이 wait()를 깨우려면 같은 것을 공유하는 다른 스레드
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Before pop : i=" + this.i);
				this.i--;
				System.out.println("After pop : i=" + this.i);
			}
		}
	}
	
}

public class ThreadShareTest {
	
	public static void main(String[] args) {
		Share s;
		s = new Share(); //객체생성
		
//		Push push = new Push();
//		push.s = s;
		Push push = new Push(s);
		Pop pop = new Pop(s);
		
		push.start();
		pop.start();
		//share, push, pop 세가지 thread가 하나씩 돌아가면서 cpu 점유
		//push 내에서도 결과 하나 출력하다가 다른 걸로 넘어갈 수 있음
		//서로 공유되었을 때 위험한 경우(i++하는 동안 cpu를 뺏기면 안됨
		//=> 동기화 처리 (임계영역 설정) -> 공유 객체를 사용하는 다른 스레드에게 cpu를 뺏기지 마라!
	}

}
