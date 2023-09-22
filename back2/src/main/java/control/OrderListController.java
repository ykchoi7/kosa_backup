package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;

public class OrderListController extends OrderController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.13:5500"); //http://127.0.0.1:5500
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setContentType("application/json; charset=utf-8");

		//응답 출력 스트림 얻기
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		//요청 전달 데이터 얻기
		HttpSession session = request.getSession();
		String loginedId = (String)session.getAttribute("loginedId");
		Map<String, Object> map = new HashMap<>();
		
		if (loginedId == null) { //로그인아이디 값이 없을 때
			map.put("status", 0);
			map.put("msg", "로그인하세요");
		} else {
			try {
				List<OrderInfo> info = service.findById(loginedId);
				map.put("status", 1);
				map.put("list", info);
			} catch (FindException e) {
				e.printStackTrace();
				map.put("status", 0);
				map.put("msg", e.getMessage());
			}
		}
		String jsonStr = mapper.writeValueAsString(map);
		out.print(jsonStr);
		return null;
	}

}
