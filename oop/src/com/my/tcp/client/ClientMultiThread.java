package com.my.tcp.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

//서버로 내보낸 내용을 다시 읽어들이는 기능을 수행
class ClientThread extends Thread {
	private Socket s;
	private InputStream is = null;
	private DataInputStream dis = null;
	
	ClientThread(Socket s) throws IOException {
		this.s = s;
		is = s.getInputStream();
		dis = new DataInputStream(is);
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				//서버로 보낸 메시지를 다시 받음
				String receiveMsg;
				receiveMsg = dis.readUTF();
				System.out.println("서버에서 되돌려준 메시지: " + receiveMsg);
			}
			//내부에서 try~catch를 하지 않은 이유 - 메시지를 받지 못해도 다른 메시지는 계속 출력을 해야하기 때문
		} catch (SocketException e) {
			
		} catch (EOFException e) { //강제종료시 발생하는 예외
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

public class ClientMultiThread { //여러 thread가 나누어서 일을 하게 만들기

	//메인에서는 키보드 입력 내용을 송신하는 기능 수행
	public static void main(String[] args) {
		Socket s = null;
		String serverIP = "127.0.0.1"; //"192.168.1.12  127.0.0.1";  //인터넷이 없어도 사용할 수 있는 IP 127.0.0.1 = localhost
		int serverPort = 5432;
		OutputStream oos = null;
		DataOutputStream dos = null; //여러개의 값을 전달하기 위함
		Scanner sc = new Scanner(System.in);
		
		try {
			s = new Socket(serverIP, serverPort); //서버와 연결
			System.out.println("서버와 연결 성공");
			
			new ClientThread(s).start(); //새로운 스레드 시작, Socket을 공유객체가 되도록 인자로 전달
			
			oos = s.getOutputStream();
			dos = new DataOutputStream(oos);
			
			//키보드로 메시지 입력 받아서 출력하기 -> 메시지 입력 역할
			String sendMsg;
			do {
				System.out.println("서버로 보낼 메시지 (종료하려면 quit을 입력하세요)");
				sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
			} while (!sendMsg.equals("quit"));
		
		} catch (UnknownHostException e) {
			System.out.println(serverIP + "서버는 존재하지 않습니다. 서버IP를 확인하세요");
		} catch (ConnectException e) { //자식예외 - 접근은 했는데 connect가 안될 때 발생하는 예외
			System.out.println("서버가 실행되지 않았습니다. 서버 실행 여부를 확인하세요");
		} catch (SocketException e) { //부모예외 - 소켓에 접근하려고 했는데 망가졌을 때 발생하는 예외
			System.out.println("서버가 강제종료되었습니다. 서버 확인하세요");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
