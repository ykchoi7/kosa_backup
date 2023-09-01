package solid;

//public class Employee {
//	private String employeeId;
//    private String name;
//    private int age;
//
//    void getEmployeeDetails() {
//        // 데이터베이스에서 직원 정보를 가져오는 코드
//    }
//
//    void calculatePay() {
//        // 직원별 임금 계산
//    }
//}
//
////하나의 클래스에서 Employee 정보를 가져오는 기능과 임금을 계산하는 기능을 동시에 하고 있다
////이를 분리하여 한 클래스에서 한 가지의 기능을 하도록 만들어야 한다


//올바른 사용
public class Employee {
	private String employeeId;
    private String name;
    private int age;

    void getEmployeeDetails() {
        // 데이터베이스에서 직원 정보를 가져오는 코드
    }
}


