package com.my.tcp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class ServerThread extends Thread {
	
	private Socket s;
	private InputStream is = null; 
	private DataInputStream dis = null;
	private OutputStream oos = null;
	private DataOutputStream dos = null;
//	private ArrayList<ServerThread> list;
//	private Vector<ServerThread> list;
	private List<ServerThread> list; //일반화된 인터페이스 타입으로 선언해야 하위 구체화된 클래스타입을 변경하더라도 큰 변동 사항이 없다
	private String clientIP;
	
	//생성자
	ServerThread(Socket s, List<ServerThread> list) throws IOException {
		this.s = s;
		this.list = list;
		is = s.getInputStream();
		dis = new DataInputStream(is);
		oos = s.getOutputStream();
		dos = new DataOutputStream(oos);
		InetAddress ip = s.getInetAddress(); //로컬컴퓨터의 IP주소 가져오기
		clientIP = ip.getHostAddress(); //객체의 IP주소 얻기, HostName은 클라이언트명
		System.out.println(clientIP + "클라이언트가 접속했습니다");
//		for (ServerThread t : list) {
//			try {
//				t.dos.writeUTF(clientIP + "클라이언트가 접속했습니다");
//			} catch (IOException e) {
//			}
//		}
		broadcast(clientIP + "님이 접속했습니다");
	}
	
	//데이터 송수신 작업
	@Override
	public void run() {
		try {
			String receiveMsg;
			while(!(receiveMsg=dis.readUTF()).equals("quit")) { 
//				System.out.println("클라이언트가 보낸 메시지 " + receiveMsg);
//				dos.writeUTF(receiveMsg);
				//throw Exception 자체가 옵션에 없는 이유 - Override 규칙 때문! 부모에서 throw를 안했기 때문에 자식에서도 throw를 설정할 수 없음
				//while반복문 안에서 try~catch 안하는 이유 - 내부에서 하면 예외 발생할 때 catch를 하더라도 반복은 계속 진행됨 / 예외 발생했을 때 반복문을 빠져나와서 예외처리해야 반복문이 종료됨
				
				//자료구조에 담은 서버 정보값 가져오기
//				for (ServerThread t : list) {
//					try {
//						t.dos.writeUTF(clientIP + ">" + receiveMsg);
//					} catch (IOException e) {
//					}
//				}
				broadcast(clientIP + "> " + receiveMsg);
			}
		} catch (EOFException e) {

		} catch (IOException e) {
			
		} finally { //소켓과의 연결 종료는 입출력을 완료하면 진행되어야하므로 thread에서 진행
			list.remove(this);
			System.out.println("클라이언트와의 연결이 종료되었습니다.");
//			for (ServerThread t : list) {
//				try {
//					t.dos.writeUTF(clientIP + "님이 나갔습니다");
//				} catch (IOException e) {
//				}
//			} //try~catch 내부에서 하는 이유: 한 클라이언트가 나가도 다른 클라이언트는 이어나가야하기 때문에 반복 실행 => 반복문 내부에서 예외처리
			broadcast(clientIP + "님이 나갔습니다");
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	//반복되는 부분은 생성자를 만들어서 간단한 코드로 사용할 수 있게 만든다
	//broadcast() 기능 - 클라이언트들에게 보여줄 메시지를 쓴다
	private void broadcast(String msg) {
		for (ServerThread t : list) {
			try {
				t.dos.writeUTF(msg);
			} catch (IOException e) {
			}
		}
	}
}

public class ServerMultiThread {
	
	public static void main(String[] args) {
		int port = 5432;
		ServerSocket ss = null; //지역변수 초기값 null
		Socket s = null;
//		ArrayList<ServerThread> list = new ArrayList<>();
		//list에 add하는 동안은 cpu가 다른거에 뺏기지 않도록 처리를 해야하는데 이를 위해서는 synchronized 설정과 같은 귀찮은 일을 해야한다
		//이를 덜기 위해 자료형 자체를 ArrayList 대신 Vector를 사용하면 따로 synchronized 처리 필요없다
//		Vector<ServerThread> list = new Vector<>();
		List<ServerThread> list = new Vector<>(); //생성만 구체적인 클래스타입으로 하면 된다
		
		try {
			ss = new ServerSocket(port);
			System.out.println("클라이언트 접속 기다리기");
			
			while(true) { //클라이언트접속기다리기부터 반복
				s = ss.accept(); //클라이언트 접속 기다린다는 코드
				ServerThread t = new ServerThread(s, list);
				list.add(t); //자료구조에 담기
				t.start(); //스레드 시작
//				new ServerThread(s).start(); //새로운 스레드 시작, 객체 인자로 소켓정보 전달
			}	
		} catch (BindException e) {
			System.out.println(port + "포트가 이미 사용중입니다");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
