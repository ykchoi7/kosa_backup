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
import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.product.service.ProductService;

public class CartListController implements Controller {
	protected ProductService service;
	public CartListController() { //생성자
		service = ProductService.getInstance();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.13:5500"); //http://127.0.0.1:5500
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		HttpSession session = request.getSession();
		List cartlist = new ArrayList<>();
		Map<String, Object> map = (Map)session.getAttribute("cart");
		
		try {
			for (String key : map.keySet()) {
				Product p = service.findByProdNo(key);
				Map<String, Object> carts = new HashMap<>();
				carts.put("product", p);
				carts.put("quantity", map.get(key));
				cartlist.add(carts);
			}
		} catch (FindException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(cartlist);
		out.print(jsonStr);
		return null;
	}

}
