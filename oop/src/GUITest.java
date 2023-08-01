import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * GUI프로그램 순서
 * 1. 이벤트소스(bt)와 이벤트종류(ActionEvent)를 결정한다
 * 2. 이벤트처리용 클래스(이벤틀 핸들러)를 작성한다
 * class MyHandler implements ActionListener {}
 * 3. 이벤트소스와 이벤트핸들러를 연결한다
 *    bt.addActionListener( new MyHandler() ); -> 
 */
class MyHandler implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) { //action event 발생했을 때 자동으로 호출되는 메서드
		System.out.println("클릭되었습니다");
	}
	
}

public class GUITest {
	private JFrame f; //창
	private JButton bt; //버튼
	private JTextField t; //한줄입력란
	
	public GUITest() {
		f = new JFrame("프레임");
		bt = new JButton("클릭");
		t = new JTextField("입력하세요");
		
		Container c = f.getContentPane(); //프레임 뒷판
		c.setLayout(new FlowLayout()); //순서대로 배치
		c.add(bt);
		c.add(t);
		
		bt.addActionListener(new MyHandler()); //이벤트소스와 이벤트핸들러 연결
		
		f.setSize(300, 200);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		new GUITest(); //생성자 호출
	}

}
