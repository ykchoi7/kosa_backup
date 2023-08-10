package com.my.tcp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

	public static void main(String[] args) {
		//사용자정의 포트번호는 1025~ 부터 사용하기
		int port = 5432;
		ServerSocket ss = null; //지역변수 초기값 null
		Socket s = null;
		InputStream is = null; 
		DataInputStream dis = null;
		//echo 프로그램
		OutputStream oos = null;
		DataOutputStream dos = null;
		
		try {
			ss = new ServerSocket(port);
			System.out.println("클라이언트 접속 기다리기");
			s = ss.accept(); //클라이언트 접속 기다린다는 코드
							 //클라이언트가 접속을 하면 소켓을 만들고 할 일이 없기 때문에 프로그램 종료
			
			//한 글자 읽어오기
			is = s.getInputStream();
//			int readValue = is.read();

			//문자열 읽어오기
			dis = new DataInputStream(is);
//			String readValue = dis.readUTF();
			
			//클라이언트에서 받은 메시지를 서버에서 내보내기
			oos = s.getOutputStream();
			dos = new DataOutputStream(oos);

			//키보드로 입력받은 메시지 출력하기 -> 받은 메시지 출력하기
			String receiveMsg;
			while(!(receiveMsg=dis.readUTF()).equals("quit")) { 
				//받아놓은걸 넣는게 아니라 받아올때마다 while문을 실행해야되기 때문에 이렇게 코드를 작성해야한다!
				System.out.println("클라이언트가 보낸 메시지 " + receiveMsg);
				//받은 메시지 쓰기 -> 클라이언트로 다시 보낼 예정
				dos.writeUTF(receiveMsg);
			}
			
//			System.out.println("클라이언트가 보낸 메시지: " + (char)readValue); //문자 하나
//			System.out.println("클라이언트가 보낸 메시지: " + readValue); //문자열 출력
			
		} catch (EOFException e) {
			//따로 출력하는 내용 없으면 비워두기
		} catch (BindException e) { //문제의 경우의 수를 미리 처리해두는게 좋다
			System.out.println(port + "포트가 이미 사용중입니다");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("클라이언트와의 연결이 종료되었습니다.");
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
