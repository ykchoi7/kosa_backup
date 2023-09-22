package control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.customer.dto.Customer;
import com.my.customer.service.CustomerService;
import com.my.util.Attach;

//파일 업로드 포함 signup
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService service;
	public SignupServlet() {
		service = new CustomerService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.13:5500"); // http://127.0.0.1:5500
		response.setContentType("application/json; charset=utf-8"); // text/html
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper(); //JSON문자열을 만드는 ADI
		Map<String, Object> map = new HashMap<>(); //응답내용
		
		//요청전달데이터 얻기
//		String id = request.getParameter("id");
//		String pwd = request.getParameter("pwd");
//		String name = request.getParameter("name");

//		//데이터를 Customer 객체에 넣어주기
//		Customer c = new Customer(id, pwd, name);
		
		try {
			Attach attach = new Attach(request);
			String id = attach.getParameter("id");
			String pwd = attach.getParameter("pwd");
			String name = attach.getParameter("name");
			Customer c = new Customer(id, pwd, name);
			service.signup(c);
		
			//프로필 첨부파일 저장
			try {
				String originProfileFileName = attach.getFile("f1").get(0).getName();
				attach.upload("f1", id + "_profile_" + originProfileFileName);
				//여기서 썸네일용 라이브러리(Thumbnailator) 사용해서 profile의 100px 버전을 저장 -> 용량 과부하 방지 가능
			} catch (Exception e) {
			}
			
			//자기소개서 첨부파일 저장
			try {
				String originIntroFileName = attach.getFile("f2").get(0).getName();
				attach.upload("f2", id + "_intro_" + originIntroFileName);
			} catch (Exception e) {	
			}
			map.put("status", 1);
			map.put("msg", "가입성공");
		} catch(Exception e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", e.getMessage());
		}
		out.print(mapper.writeValueAsString(map));
	}

}
