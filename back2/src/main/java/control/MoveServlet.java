package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/move")
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//방법1 - 클라이언트 차원에서의 이동
//		response.sendRedirect("http://www.naver.com");	
		
		//방법2 - 서블릿(서버) 차원에서의 이동
//		RequestDispatcher rd = request.getRequestDispatcher("req"); //같은 웹컨텍스트 내에서만 이동 가능
//		rd.forward(request, response);
//		rd.include(request, response);
		
//		RequestDispatcher rd = request.getRequestDispatcher("req");
//		rd.forward(request, response);
		
		response.setContentType("text/html; charset=UTF-8"); //형식 지정해줘야 깨짐없이 잘 출력됨
		PrintWriter out = response.getWriter();
//		out.print("BEFORE FORWARD");
//		RequestDispatcher rd = request.getRequestDispatcher("res");
//		rd.forward(request, response);
//		out.print("AFTER FORWARD");
		//이렇게 진행할 경우 before, after 어떤 문구도 나타나지 않는다
		//특히, forward 후에는 다른 작업을 진행하지 않는게 좋다, 돌아와서 다음 작업하고 이런 구조로 만들지 말기!
		
		out.print("BEFORE INCLUDE");
		RequestDispatcher rd = request.getRequestDispatcher("res");
		rd.include(request, response);
		out.print("AFTER INCLUDE");
		
	}

}
