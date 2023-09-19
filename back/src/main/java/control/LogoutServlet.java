package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.13:5500"); // http://127.0.0.1:5500
//		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		//세션 정보 중에 loginedId 어트리뷰트가 있으면 초기화 시켜주기
		HttpSession session = request.getSession();
		System.out.println("in logout: " + session.getId());
		session.removeAttribute("loginedId"); //초기화
		session.invalidate();
		
	}

}
