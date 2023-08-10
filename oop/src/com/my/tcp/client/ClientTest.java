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

public class ClientTest {

	//네트워크 테스트는 혼자 먼저 해보고 다른 것과 연결해야함
	public static void main(String[] args) {
		Socket s = null;
		String serverIP = "127.0.0.1"; //"192.168.1.12  127.0.0.1";  //인터넷이 없어도 사용할 수 있는 IP 127.0.0.1 = localhost
		int serverPort = 5432;
		OutputStream oos = null;
		DataOutputStream dos = null; //여러개의 값을 전달하기 위함
		//echo프로그램
		InputStream is = null;
		DataInputStream dis = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			s = new Socket(serverIP, serverPort); //서버와 연결
			System.out.println("서버와 연결 성공");
			oos = s.getOutputStream();
			dos = new DataOutputStream(oos); //oos를 객체로 가짐
//			oos.write(65); //유니코드 쓰기
//			dos.writeUTF("안녕하세요, 최윤경입니다"); //문자열 쓰기
			
			//서버에서 보낸 메시지 출력하기 (echo프로그램)
			is = s.getInputStream();
			dis = new DataInputStream(is);
			
			//키보드로 메시지 입력 받아서 출력하기 -> 메시지 입력
			String sendMsg;
			do { //일단 한번은 내보내야하기 때문에 do~while문을 사용
				System.out.println("서버로 보낼 메시지 (종료하려면 quit을 입력하세요)");
				sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
				//서버로 보낸 메시지를 다시 받음
				String receiveMsg = dis.readUTF();
				System.out.println("서버에서 되돌려준 메시지: " + receiveMsg);
			} while (!sendMsg.equals("quit"));
		
		} catch (EOFException e) {
			
		} catch (UnknownHostException e) {
			System.out.println(serverIP + "서버는 존재하지 않습니다. 서버IP를 확인하세요");
		} catch (ConnectException e) { //자식예외 - 접근은 했는데 connect가 안될 때 발생하는 예외
			System.out.println("서버가 실행되지 않았습니다. 서버 실행 여부를 확인하세요");
		} catch (SocketException e) { //부모예외 - 소켓에 접근하려고 했는데 망가졌을 때 발생하는 예외
			System.out.println("서버가 강제종료되었습니다. 서버 확인하세요");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//나중에 생성된걸 먼저 close (dos -> oos -> s)
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
