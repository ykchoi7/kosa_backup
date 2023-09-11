package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Cross-origin 문제 해결을 위한 코드 - 특정 port url에서 접근하는 것을 허용
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter(); //응답 출력 stream
		out.print("로그인성공");
	}

}
