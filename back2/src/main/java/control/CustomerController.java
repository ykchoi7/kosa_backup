package control;

import com.my.customer.service.CustomerService;

public abstract class CustomerController implements Controller {
	protected CustomerService service;
	public CustomerController() {
		service = CustomerService.getInstance();
	}
}
