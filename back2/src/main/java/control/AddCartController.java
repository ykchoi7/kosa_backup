package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.product.service.ProductService;

public class AddCartController implements Controller {
	protected ProductService service;
	public AddCartController() { //생성자
		service = ProductService.getInstance();
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Access Allow
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.13:5500"); //http://127.0.0.1:5500
		response.setHeader("Access-Control-Allow-Credentials", "true");
		//쿠키 정보에 대한 인증서 -> 백엔드에서도 허용되어야 함
		
		//1.요청전달데이터 얻기
		String prodNo = request.getParameter("prodno");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		System.out.println(prodNo + ":" + quantity);
		
		//2.HttpSession객체 얻기
		HttpSession session = request.getSession();
		
		//3.session객체의 어트리뷰트값 얻기 (이름:"cart", String상품번호, Integer상품갯수)
		Map<String, Integer> cart = (Map)session.getAttribute("cart"); //session에 저장된 속성값은 받아온다
		
		//4.어트리뷰트가 없으면 객체 생성 후 어트리뷰트로 추가
		if (cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}
	
		//5.요청전달데이터 상품번호를 key, 수량을 value로 어트리뷰트의 요소로 추가한다
		if (cart.containsKey(prodNo)) { //기존에 prodNo가 있으면
			int prevQuantity = cart.get(prodNo);
			cart.replace(prodNo, prevQuantity + quantity);
		} else { //prodNo가 없으면 새로 넣어주기
			cart.put(prodNo, quantity);			
		}
		
		//6.어트리뷰트 요소들을 모두 출력한다
		System.out.println(cart);
		return null;
	}

}
