import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//JDBC 연결
public class JDBCTest {
	
	public static void selectTest() {
		//1. JDBC드라이버설치
		//2. 드라이버클래스들 JVM에 로드
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("JDBC드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		//3. DB와 연결
		Connection conn = null;
		//oracle에서 접속할 때 기입하는 기본정보와 동일
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB와 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*
		//4-1. SQL문 DB에 송신
		Statement stmt = null;
		//5. SQL문 결과 수신하기
		ResultSet rs = null; //송신된 결과를 받기 위해 생성하는 변수 (결과집합)
		try {
			stmt = conn.createStatement(); //SQL문을 송신하기 위한 장치
			int dId = 60; //부서번호
			String fn = "D"; //글자찾기
			String selectSQL = "SELECT employee_id, first_name, hire_date, salary\r\n"
					+ "FROM employees\r\n"
					+ "WHERE department_id=" + dId
					+ "AND SUBSTR(first_name, 1, 1) = '" + fn + "'";
			rs = stmt.executeQuery(selectSQL); //송신한것을 결과집합이 수신
			while (rs.next()) { //한 행씩 돌면서 다음 행이 없을때까지 테이블 끝까지 반복
				int eId = rs.getInt("employee_id"); //db에서 employee_id값을 받음
				String eName = rs.getString("first_name");
				//인덱스 표현 방식 - rs.getString(2); sql문에서 두번째 요소에 있는 요소 사용
				Date eHdt = rs.getDate("hire_date"); //
				int eSal = rs.getInt("salary");
				System.out.println(eId + ":" + eName + ":" + eHdt + ":"+ eSal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		//*6. DB연결끊기 꼭 하기!!!*
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null)  {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}*/
	
		//4-2. SQL문 DB에 송신하기
		PreparedStatement pstmt = null;
		//5. SQL문 결과 수신하기
		ResultSet rs = null;
		
		String selectSQL = "SELECT employee_id, first_name, hire_date, salary\r\n"
				+ "FROM employees\r\n"
				+ "WHERE department_id=?\r\n"
				+ "AND SUBSTR(first_name, 1, 1) = ?";
		try {
			pstmt = conn.prepareStatement(selectSQL); //위에 ?까지 있는 sql 구문을 DB에 먼저 보냄
			pstmt.setInt(1, 60); //pstmt에 ?에 넣을 값을 set해준다
			pstmt.setString(2, "D");
			rs = pstmt.executeQuery(); //?값만 송신 (인자 따로 필요없음)
			while (rs.next()) { //한 행씩 돌면서 다음 행이 없을때까지 테이블 끝까지 반복
				int eId = rs.getInt("employee_id"); //db에서 employee_id값을 받음
				String eName = rs.getString("first_name");
				Date eHdt = rs.getDate("hire_date");
				int eSal = rs.getInt("salary");
				System.out.println(eId + ":" + eName + ":" + eHdt + ":"+ eSal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null)  {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void insertTest() {
		//2. JDBC드라이버 로드
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		//3. DB와 연결
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		try {
			conn = DriverManager.getConnection(url, user, password);
//			conn.setAutoCommit(false); //자동커밋 실행x -> 수동커밋 만들기
			System.out.println("DB와 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//4. SQL구문 송신
		PreparedStatement pstmt = null;
		String insertSQL = "INSERT INTO customer(id, pwd, name) VALUES (?,?,?)";
		try {
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, "id13");
			pstmt.setString(2, "pw13");
			pstmt.setString(3, "최yk");
			int rowcnt = pstmt.executeUpdate(); //DML, DDL처리할 때는 executeUpdate()
			System.out.println(rowcnt + "건 추가 성공");
//			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
//			conn.rollback();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public static void main(String[] args) {
//		selectTest();
//		insertTest();
	}

}



