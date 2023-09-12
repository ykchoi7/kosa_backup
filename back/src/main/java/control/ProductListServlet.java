package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.FindException;
import com.my.product.dto.PageGroup;
import com.my.product.dto.Product;
import com.my.product.service.ProductService;


@WebServlet("/productlist")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService service;
	public ProductListServlet() {
		service = new ProductService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청전달데이터 currentPage 얻기
		String currentPage = request.getParameter("currentPage");
		int cp = 1;
		if (currentPage != null && !currentPage.equals("")) {
			cp = Integer.parseInt(currentPage);
		}
		
		String path = "productlistresult.jsp"; //정상처리될 때의 경로
		try {
//			List<Product> list = service.findAll(cp);
//			request.setAttribute("list", list); //list라는 이름으로 값을 구한 리스트 저장해두기
			PageGroup<Product> pb = service.findAll(cp);
			request.setAttribute("pb", pb);
		} catch (FindException e) {
			e.printStackTrace();
			path = "fail.jsp"; //처리 실패했을 때의 경로
			request.setAttribute("msg", e.getMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
