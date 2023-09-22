package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/res")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답형식 설정 (한글 깨질 때) : MIME방식 "application/json" , "text/plain"
		response.setContentType("text/html;charset=utf-8"); //text/plain 같은 형식을 넣으면 html코드 못 읽어들임
															//그러나, 웹프로그램에서만 요청이 들어오는 것이 아니기 때문에 text/html 권장하지 않음
		// 응답출력스트림얻기
		PrintWriter out = response.getWriter();					//Writer 출력단위 문자
		//ServletOutputStream sos = response.getOutputStream(); //OutputStream 출력단위 바이트
		
		//서블릿이 직접 응답하는 아래 형태 권장하지 않음!!! 
		//ResponseServlet(controller)은 요청을 받기만 해서 service 호출 -> dao 호출 -> dao가 DB와 직접 연동하도록 하기 -> 응답은 jsp(viewer)가 하도록 하기
		out.print("<h1>WELCOME</h1> <div>최윤경's 서블릿입니다</div>");
		out.print("<table>");
		out.print("<tr>");
		for (int i = 1; i <= 5; i++) {
			out.print("<td>"); out.print(i); out.print("</td>");
		}
		out.print("</tr>");
		out.print("</table>");
	}

}
