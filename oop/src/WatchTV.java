//제공자 코드
/**
 * Document Comment 문서용 주석
 * 클래스 코드 사용 설명서
 * 
 * TV용 클래스이다
 * @author 최윤경 -- 작성자 누구인지 알려주기
 * @version 1.0 -- 버전작성
 */
class TV {
	boolean power; //전원-아래에서 tv.power로 활용했기 때문에 non-static변수(인스턴스 변수)
				   //자동 초기화 false
	int channel;
	int volume;

	/**
	 * 각 메서드 설명
	 * 전원을 켠다
	 */
	public void powerOn() {
		power = true;	//power에 true를 대입하라
						//**현재 사용중인 TV타입의 객체**의 power 에 값을 대입해야한다!!
	}
	
	/**
	 * 전원상태를 반환한다
	 * @return 전원이 켜져있으면 true 반환, 꺼져있으면 false 반환 (어떤 경우에 값을 반환하는지 설명)
	 */
	public boolean isPower() {
		return power;	//isPower 호출되면 power값을 반환한다
	}
	
	/**
	 * 채널을 설정한다
	 * @param ch 설정할 채널값
	 */
	public void setChannel(int ch) {
		channel = ch;	//setChannel 호출되면 매개변수 ch를 받아와서 channel에 대입
	}
	
	/**
	 * 현재 채널을 반환한다
	 * @return 현재 채널값
	 */
	public int getChannel() {
		return channel;	//getChannel 호출되면 channel값을 반환한다
	}	
	
	/**
	 * 채널값을 1 증가한다
	 */
	public void channelUp() {
		channel++;		//channelUp 호출되면 channel값에 1을 더한다
	}

	/**
	 * 음량을 1 증가한다
	 */
	public void volumeUp() {
		volume++;
	}
	
	/**
	 * 음량을 1 감소한다 
	 * 단, 최소 음량은 0이다
	 */
	public void volumeDown() {
		if (volume == 0) {
			return; //반환값이 없을때 void를 사용하는데 메서드를 끝내고 싶을 때 return 사용할 수 있다
					//여기서는 return = 메서드 종료
		}
		volume--;
	}
	
	/**
	 * 현재 음량을 반환한다
	 * @return 음량
	 */
	public int getVolume() {
		return volume;
	}
}


//사용자 코드
public class WatchTV {
	
	public static void main(String[] args) {
		TV tv;			//참조형 지역변수 선언
		tv = new TV();	//객체 생성, 힙 영역에 메모리 할당
		System.out.println(tv.power); 	//false (기본값)
		System.out.println(tv.channel); //0
		System.out.println(tv.volume);  //0
		tv.powerOn(); //위의 powerOn() 메서드가 실행되어 tv.power에 true 대입
		
		TV tv1 = new TV(); //tv1 생성됨 -> TV클래스의 변수들 자동 초기화
 		tv1.powerOn(); //위의 powerOn() 메서드가 실행되어 tv1.power에 true 대입
 		
 		boolean flag = tv.isPower();
 		if (flag) {
 			System.out.println("전원이 켜졌습니다");
 			
 			//채널 세팅
 			tv.setChannel(11);
 			
 			//채널 번호 get
 			int ch = tv.getChannel();
 			System.out.println("현재 채널은 " + ch);
 			
 			//채널 위로 이동
 			tv.channelUp();
 			System.out.println("현재 채널은 " + tv.getChannel()); //12
 			
 			//볼륨 20 올리기
 			for (int i = 0; i < 20; i++) {
				tv.volumeUp();
			}
 			
 			//볼륨 50 줄이기
 			for (int i = 0; i < 50; i++) {
				tv.volumeDown();
			}
 			System.out.println("현재 볼륨은 " + tv.getVolume()); //50까지 줄여도 음수가 나오지 않음
 			
 		} else {
 			System.out.println("전원이 꺼졌습니다");
 		}
	}

}
