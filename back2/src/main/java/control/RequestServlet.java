package control;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/req")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tValue = request.getParameter("t");
		// 요청전달 데이터 중 t 데이터 자체가 전송이 안됐을 때 => null : c1
		// t 요청전달 데이터는 있지만 값이 없을 경우 =>  : c1
		String cValue = request.getParameter("c");
		// c 요청전달 데이터가 여러개인 경우 => aaa : c1 첫번째 값만 들어옴
		System.out.println(tValue + " : " + cValue);
		
		// c 요청전달 데이터가 여러개일 때는 .getParameterValues를 사용해야한다!
		// 단 이 경우 c 데이터 자체가 전송이 안되면, NullPointerException이 발생 
		String[] cValues = request.getParameterValues("c");
		if (cValues != null) {
			for (int i = 0; i < cValues.length; i++) {
				System.out.println(cValues[i]);
			}
		}
		
		System.out.println("request.getContextPath() : " + request.getContextPath()); // 지금 사용하는 경로를 가져오기 /back
		System.out.println("request.getRequestURL() : " + request.getRequestURL());	  // http://localhost:8888/back/req
		System.out.println("request.getRequestURI() : " + request.getRequestURI());	  // /back/req
		System.out.println("request.getServletPath() : " + request.getServletPath()); // /req
		
		Enumeration<String> em = request.getHeaderNames();
		while (em.hasMoreElements()) { //찾아갈 헤더 이름이 있는지 확인
			String name = em.nextElement(); //있는 이름들을 하나씩 찾아감
			String value = request.getHeader(name);
			System.out.println(name + " : " + value);
			// 요소들 중에 user-agent를 확인하면 어느 환경에서 요청을 해왔는지 알 수 있다 (Windows / Postman 등)
		}
		
		//.setAttribute는 어떤 데이터타입이건 설정 가능하다
		request.setAttribute("msg", "요청속성1");
		request.setAttribute("cnt", 1); //AutoBoxing: (int->Integer) -> upcasting:Object
		request.setAttribute("now", new Date());
		
		//강제 형변환도 가능
		String msg = (String)request.getAttribute("msg");
		int cnt = (Integer)request.getAttribute("cnt"); //최종 Object타입으로 저장되었기 때문에 Wrapper자료형으로 변환해야한다
		request.removeAttribute("now");
	}
}
