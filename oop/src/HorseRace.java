import java.awt.Canvas;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;

class Horse extends Canvas implements Runnable{
	private String name;
	private int x;
	private int y;
	Horse(String name){
		this.name = name;
		x = 50;
		y = 10;
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawString(name, x, y);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	@Override
	public void run() { //달려나가게 x 포지션 변경
		for (int i = 0; i < 20; i++) { //각 말이 CPU를 번갈아서 할당하면서 달리는 동작을 한다
			System.out.println(name);
			this.setX(this.getX()+10);
			this.repaint();
			long millis = (long)(Math.random()*1000+1); //1.0<= <1001.0
			try {
				Thread.sleep(millis); //random값으로 일시정지할 수 있게 설정
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class HorseRace {
	private JFrame f; //창,액자
	private JButton btReady, btStart; //준비, 달려 버튼
	private Horse[] horses;
	public HorseRace(){ //생성자
		f = new JFrame("달려");
		f.setLayout(new GridLayout(4,1));
		Container c = f.getContentPane(); //프레임뒷판
		horses = new Horse[3]; //말 3마리 배열로 선언
		for(int i=0; i<horses.length; i++) {
			horses[i]= new Horse((i+1)+"번 말"); //각 배열 방에 말 생성
			c.add(horses[i]); //프레임뒷판에 각 말을 넣어주기 -> 추가된 Horse의 paint()메소드가 자동호출됨
		}
		Panel p = new Panel();
		btReady = new JButton("준비");
		btStart = new JButton("달려");
		p.add(btReady);p.add(btStart); //패널에 버튼 추가
		c.add(p); //패널이 프레임뒷판에 추가
		
		btReady.addActionListener((e)->{
			for (Horse h:horses) {
				h.setX(0); //준비버튼 누르면 x를 0픽셀로 이동
				h.repaint(); //Horse객체의 drawing이 클리어되고 paint()메소드 호출됨
			}
		});
		
		btStart.addActionListener((e)->{
			for (Horse h:horses) {
				//반복문만 활용하면 한마리씩 일정한 거리만큼 이동하므로 한 마리만 이기게 되어있음
//				for (int i=0; i<20; i++) {
//					h.setX(h.getX()+10);
//					h.repaint(); //Horse객체의 drawing이 클리어되고 paint()메소드 호출됨
//				}
				
				//스레드로 말들이 달리기 다투게 만들기
				Thread t = new Thread(h);
				t.start(); //시작하려면 꼭 start()메소드 활용하기
			}
		});
		
		f.setSize(500, 200); //프레임 가로,세로 크기 설정
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new HorseRace();
	}

}
