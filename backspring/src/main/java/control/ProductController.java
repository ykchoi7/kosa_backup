package control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.product.service.ProductService;
import com.my.util.PageGroup;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/productlistjson")
	public PageGroup<Product> list(@RequestParam(name="currentPage", 
												 required = false,
												 defaultValue = "1") int cp) {
		try {
			PageGroup<Product> pg = service.findAll(cp);
			return pg;
		} catch (FindException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
