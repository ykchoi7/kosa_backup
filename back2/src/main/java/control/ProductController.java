package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.product.service.ProductService;

//추상클래스로 만듦으로써 Controller 인터페이스의 메소드를 오버라이딩하지 않고도 사용할 수 있다
public abstract class ProductController implements Controller {
	protected ProductService service;
	public ProductController() {
		service = ProductService.getInstance();
	}

}
