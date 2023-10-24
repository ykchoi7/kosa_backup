package control;


import java.util.HashMap;
import java.util.Map;

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
	public PageGroup<Product> list(@RequestParam(name = "currentPage",
	                                             required = false,
	                                             defaultValue = "1")
									int cp){
		try {
			PageGroup<Product> pg = service.findAll(cp);
			return pg;
		}catch(FindException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/productjson")
	public Object product(@RequestParam(name = "prodno") String prodNo) { 
		//prodNo를 매개변수로 받는데 front에서 데이터를 prodno라는 변수로 전달해주었기 때문에 @RequestParam(name="prodno")로 가져와서 넣어주기
		try {
			Product p = service.findByProdNo(prodNo);
			return p;
		} catch (FindException e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<>();
			map.put("msg", e.getMessage());
			return map;
		}
	}
}
