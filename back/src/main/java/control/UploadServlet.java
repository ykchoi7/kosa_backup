package control;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post방식의 요청인 경우 요청 바디의 형식은 application/x-www-form-urlencoded 입니다
		//x-www-form-urlencoded 그럼 왜 이걸 사용하지 않았나? -> input 태그를 활용하여 파일 첨부를 하고 싶으면 
		//enctype="multipart/form-data" 이런 형식을 사용해야 한다
//		String tValue = request.getParameter("t");
//		String f1Value = request.getParameter("f1");
		
		//post방식의 요청인 경우 요청 바디의 형식은 multi-part/form-data인 경우 -> String으로 읽어오기
		ServletInputStream sis = request.getInputStream();
		Scanner sc = new Scanner(sis);
		while(sc.hasNextLine()) {
			System.out.println(sc.nextLine());			
		}
	}

}



