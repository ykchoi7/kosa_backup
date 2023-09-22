package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.AddException;
import com.my.order.dto.OrderInfo;
import com.my.order.dto.OrderLine;
import com.my.product.dto.Product;

public class AddOrderController extends OrderController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.13:5500"); // http://127.0.0.1:5500
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		// 출력스트림 얻기
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 잭슨 활용
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr;
		
		// 세션에서 loginedId값 받기
		HttpSession session = request.getSession();
		String loginedId = (String)session.getAttribute("loginedId");
		System.out.println(loginedId);
		
		// 응답내용을 담을 map
		Map<String, Object> map = new HashMap<>();
		
		if (loginedId == null) {
			map.put("status", 0);
			map.put("msg", "로그인하세요");
		} else {
			//장바구니 가져오기
			Map<String, Integer> cart = (Map)session.getAttribute("cart");
			
			if (cart == null) { //장바구니가 비어있는 경우
				map.put("status", 0);
				map.put("msg", "장바구니가 비었습니다");
			} else { //장바구니가 비어있지 않은 경우
				//info 객체 생성 과정
				OrderInfo info = new OrderInfo();
				info.setOrderId(loginedId); //주문자 id 세팅
				
				List<OrderLine> lines = new ArrayList<>();
				info.setLines(lines); //주문자 주문 정보 세팅
				
				for (String prodNo : cart.keySet()) {
					int quantity = cart.get(prodNo);
					OrderLine line = new OrderLine();
					Product p = new Product();
					p.setProdNo(prodNo);
					line.setOrderP(p);
					line.setOrderQuantity(quantity);
					lines.add(line);
				}

				try { //추가 성공하면
					service.add(info);
					session.removeAttribute("cart"); //성공하면 장바구니 비우기
					map.put("status", 1);
				} catch (AddException e) { //추가 실패하면
					// e.printStackTrace();
					map.put("status", 0);
					map.put("msg", e.getMessage());
				}
			}
		}
		out.print(mapper.writeValueAsString(map));
		return null;
	}

}
