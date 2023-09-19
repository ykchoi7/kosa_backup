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
import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.product.service.ProductService;

@WebServlet("/productjson")
public class ProductJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService service;
	
	public ProductJsonServlet() {
		service = new ProductService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답형식
		response.setContentType("application/json;charset=utf-8");
		
		//응답 헤더
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.13:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		//응답출력스트림얻기
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		//요청전달데이터 얻기
		String prodNo = request.getParameter("prodno");
		try {
			Product p = service.findByProdNo(prodNo);
			String jsonStr = mapper.writeValueAsString(p);
			out.print(jsonStr);
		} catch (FindException e) {
			e.printStackTrace();
			//정상 처리되지 못했을 때 메세지 띄우는 방법
			Map<String, String> map = new HashMap<>();
			map.put("msg", e.getMessage());
			String jsonStr = mapper.writeValueAsString(map);
			out.print(jsonStr);
		}
	}

}
