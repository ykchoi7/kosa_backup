//제공자 코드
class Employee{
	String no; //인스턴스변수 선언 - String타입은 null로 자동 초기화
	String name;
	int salary;
	
	//this를 사용하는 이유? 재사용성을 높이기 위해서! 
	void setNo(String no) {
		this.no = no; //no = no; 라고 하면 매개변수를 매개변수에 넣는 것 
					  //이 메서드에서 선언된 멤버변수 no에 값이 들어가지 않음 -> 그래서 받은 값이 출력되지 않는다!
					  //현재 사용중인 객체의 no에 넣기 위해 this.no로 작성 
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	void setSalary(int salary) {
		this.salary = salary;
	}

	String getNo() {
		return no;
	}
	
	String getName() {
		return name;
	}
	
	int getSalary() {
		return salary;
	}
	
	void print() {
		System.out.println("사번:"+ no + ", 사원명:" + name + ", 기본급:" + salary);
	}
	
}


//사용자 코드
public class HR { //사원관리

	public static void main(String[] args) {
		Employee e1 = new Employee();
		String no = e1.no;
		System.out.println(no); //null

		e1.setNo("2301");	//사번설정
		e1.setName("최윤경");	//사원명설정
		e1.setSalary(1000);	//기본급설정
		System.out.println("사원의 사번- " + e1.getNo() + ", 사원명-" + e1.getName() + ", 기본급- " + e1.getSalary());
		e1.print(); //사번:사번값, 사원명:사원명값, 기본급:기본급값 이 출력되도록 한다
	
		Employee eTemp;
		eTemp = null;
		eTemp = new Employee(); //새로운 변수를 만들어서 초기화시킬 경우
								//new - 힙 메모리 공간 확보
		eTemp = e1;
		System.out.println(e1 == eTemp); //주소값까지 동일한지 확인 => true
		eTemp.setSalary(2000);
		System.out.println(e1.getSalary()); //값은 주소를 참고하고 있기 때문에 eTemp를 바꿨는데도 e1값도 바뀜
		eTemp = null; //여기서 null을 넣겠다는 것은 '아무것도 참조하게 하지 않겠다'는 의미 = heap영역과의 연결을 끊겠다
		System.out.println(eTemp); //null
		System.out.println(e1.getSalary()); //2000
//		System.out.println(eTemp.getSalary()); //NullPointerException 발생 후 프로그램 종료
		System.out.println("THE END"); //위 NullPointerException으로 이 부분은 실행되지 않음 
	
	
		//배열 활용
		Employee[] employees; //선언 - 참조형 배열 변수(초기화되기 전에는 출력불가)
		//최대 20명이 저장될 수 있는 배열 생성
		employees = new Employee[20];
		employees[0] = e1;
		
//		Employee e2 = new Employee();
//		e2.setNo("2302");
//		e2.setName("name2");
//		e2.setSalary(1000);
//		employees[1] = e2;
//		
//		Employee e3 = new Employee();
//		e3.setNo("2303");
//		e3.setName("name3");
//		e3.setSalary(1000);
//		employees[2] = e3;
//		
//		Employee e4 = new Employee();
//		e4.setNo("2304");
//		e4.setName("name4");
//		e4.setSalary(1000);
//		employees[3] = e4;
//		
//		Employee e5 = new Employee();
//		e5.setNo("2305");
//		e5.setName("name5");
//		e5.setSalary(1000);
//		employees[4] = e5;
		
		//객체 생성 반복문으로 실행
		for (int i = 2; i <= 5; i++) {
			Employee e = new Employee();
			e.setNo("230"+i);
			e.setName("name"+i);
			e.setSalary(1000);
			employees[i-1] = e;
		}
		
		for (int i = 0; i < 5; i++) { //배열의 길이만큼 반복하면 나머지는 모두 null이기 때문에 NullPointerException 발생
			employees[i].print();
		}
	}
	
}
