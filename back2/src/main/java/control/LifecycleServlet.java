package control;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifecycleServlet
 */
@WebServlet("/lifecycle")
public class LifecycleServlet extends HttpServlet { //HttpServlet의 상속을 받는다
	private static final long serialVersionUID = 1L;
       
    public LifecycleServlet() {
        super();
        System.out.println("LifeCycleServlet 생성자가 호출됨"); //servlet/jsp엔진이 요청받아서 자동호출
        //여기에서 서블릿컨텍스트 연결해도 null값만 있을 뿐
//        ServletContext sc = this.getServletContext();
//		System.out.println(sc.getRealPath("a.jpg"));
    }

    //초록색 표시 - 오버라이드된 메소드
	public void init(ServletConfig config) throws ServletException {
		System.out.println("LifeCycleServlet init메서드 호출됨"); //자동호출
		super.init(config); //부모쪽의 init메소드 호출
		ServletContext sc = this.getServletContext(); //서블릿컨텍스트 타입의 값으로 반환
		System.out.println(sc.getRealPath("a.jpg")); //배포된 실제경로의 a.jpg 경로 확인
	}

	public void destroy() {
		System.out.println("LifeCycleServlet destroy메서드 호출됨"); //기존 서블릿 객체가 죽기 직전에 호출됨
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet service메서드 호출됨"); //자동호출, 요청할 때마다 service 메소드 호출
		String idValue = request.getParameter("id");
		String pwdValue = request.getParameter("pwd");
		System.out.println("요청전달데이터 id="+ idValue + ", pwd=" + pwdValue);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet doGet메서드 호출됨");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet doPost메서드 호출됨");
	}

}
