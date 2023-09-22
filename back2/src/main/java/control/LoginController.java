package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;

public class LoginController extends CustomerController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Cross-origin 문제 해결을 위한 코드 - 특정 port url에서 접근하는 것을 허용
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.13:5500"); // http://127.0.0.1:5500
		response.setContentType("application/json; charset=utf-8"); // text/html
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		PrintWriter out = response.getWriter(); //응답 출력 stream
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println(id + " : " + pwd);
		
//				CustomerService cs = new CustomerService();
//				String msg = "";
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		
		//세션 정보 중에 loginedId 어트리뷰트가 있으면 초기화 시켜주기
		HttpSession session = request.getSession();
		session.removeAttribute("loginedId"); //초기화
		
		try { //로그인 성공한 경우
			service.login(id, pwd);
			map.put("status", 1);
			map.put("msg", "로그인 성공");
			session.setAttribute("loginedId", id);
			//cs.login(request.getParameter("id"), request.getParameter("pwd"));
			//msg = "로그인 성공";
		} catch (FindException e) { //로그인 실패한 경우
			map.put("status", 0);
			map.put("msg", "로그인 실패");
			// e.printStackTrace();
			//msg = "로그인 실패";
		}
		
		String jsonStr = mapper.writeValueAsString(map);
		out.print(jsonStr);
		return null;
	}

}
