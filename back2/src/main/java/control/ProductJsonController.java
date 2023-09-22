package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.product.dto.Product;

public class ProductJsonController extends ProductController {
	//부모인 ProductController의 service 사용 가능
	//그리고 조상 클래스인 Controller 인터페이스의 메소드는 반드시 재정의해야한다
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		return null;
	}

}
