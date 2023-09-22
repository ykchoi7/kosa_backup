package control;

import com.my.order.service.OrderService;
import com.my.product.service.ProductService;

public abstract class OrderController implements Controller {
	protected OrderService service;
	public OrderController() {
		service = OrderService.getInstance();
	}

}
