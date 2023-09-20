package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.customer.dto.Customer;
import com.my.customer.service.CustomerService;
import com.my.exception.AddException;


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
		ObjectMapper mapper = new ObjectMapper();
		
		//요청전달데이터 얻기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		
		Map<String, Object> map = new HashMap<>();

		try {
			//데이터를 Customer 객체에 넣어주기
			Customer c = new Customer(id, pwd, name);
			service.signup(c);
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
