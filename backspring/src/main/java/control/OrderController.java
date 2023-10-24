package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;
import com.my.order.dto.OrderLine;
import com.my.order.service.OrderService;
import com.my.product.dto.Product;

@RestController
public class OrderController {
	@Autowired
	private OrderService service;
	@GetMapping("/addorder")
	public  Map<String, Object> add(HttpSession session) {
		String loginedId = (String)session.getAttribute("loginedId");

		Map<String, Object> map = new HashMap<>();
		if(loginedId == null) { //로그인 안된 경우
			map.put("status", 0);
			map.put("msg", "로그인하세요");
		}else {
			Map<String, Integer> cart = (Map)session.getAttribute("cart");
			if(cart == null) {
				map.put("status", 0);
				map.put("msg", "장바구니가 비었습니다");
			}else {
				OrderInfo info = new OrderInfo();
				info.setOrderId(loginedId);
				List<OrderLine> lines = new ArrayList<>();
				info.setLines(lines);
				for(String prodNo : cart.keySet()) {
					int quantity = cart.get(prodNo);

					OrderLine line = new OrderLine();
					Product p = new Product();
					p.setProdNo(prodNo);
					line.setOrderP(p);
					line.setOrderQuantity(quantity);
					lines.add(line);
				}
				try {
					service.add(info);
					session.removeAttribute("cart"); //장바구니 비우기					
					map.put("status", 1);					
				} catch (AddException e) {
					e.printStackTrace();
					map.put("status", 0);
					map.put("msg", e.getMessage());
				}
			}
		}
		return map;
	}
	
	@GetMapping("/orderlist")
	public  Map<String, Object> list(HttpSession session) {
		String loginedId = (String)session.getAttribute("loginedId");
		Map<String, Object> map = new HashMap<>();
		if(loginedId == null) { //로그인 안된 경우
			map.put("status", 0);
			map.put("msg", "로그인하세요");
		}else {
			try {
				List<OrderInfo> list = service.findById(loginedId);
				map.put("status", 1);
				map.put("list", list);				
			} catch (FindException e) {
				e.printStackTrace();
				map.put("status", 0);
				map.put("msg", e.getMessage());
			}			
		}
		return map;
	}
	
}
