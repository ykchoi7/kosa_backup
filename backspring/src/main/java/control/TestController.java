package control;

import java.util.stream.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.my.product.dto.Product;

@Controller
public class TestController {
	
	@GetMapping("/a")
	public ModelAndView a(@RequestParam(name="n") String name, //요청전달데이터명:n -> ?n=kosa
						  @RequestParam(required=false, defaultValue = "0") int sal) {
		System.out.println("a() 호출됨 name: "+ name + ", sal: " + sal);
		//String 타입은 매개변수 값이 전달되지 않으면 null로 출력되지만 int타입은 String 타입으로 자동형변환을 못해서 예외가 발생한다
		//전달되지 않아도 오류 발생하지 않게 하기 위해서는 위처럼 설정 필요! -> default 0 이 출력된다
		return null;
	}
	
	@GetMapping("/b")
	// /b?cb=one&cb=two&cb=three 요청전달데이터값이 여러개인 경우 => String배열로 한번에 받기
	public ModelAndView b(String[] cb) {
		for (String e : cb) {
			System.out.println(e);
		}
		
		Stream<String> st = Stream.of(cb);
		System.out.println(st);
		return null;
	}
	
	@GetMapping("/c")
	public String c(Model model) {
		model.addAttribute("msg", "안녕하세요");
		String viewName = "c.jsp";
		return viewName;
	}
	
	@GetMapping("/d")
	// /d?prodNo=C0001&prodName=아메리카노&prodPrice=1000
	public ModelAndView d(Product p) { //Command객체 : 요청전달데이터를 property로 갖는 객체
		System.out.println(p);
		return null;
	}
	
	//반환형
	@GetMapping("/e")
	public ModelAndView e() {
		ModelAndView mnv = new ModelAndView();
		//mnv.setViewName("e.jsp");		//View의 view이름 설정
		mnv.setViewName("e");
		mnv.addObject("msg", "WELCOME"); //Model의 속성 설정 - WELCOME 화면에 출력
		return mnv;
	}
	
	@GetMapping("/f")
	public String f() {
//		String viewName = "f.jsp"; //설정해준 msg가 없기 때문에 null 출력
		String viewName = "f";
		return viewName;
	}
	
	@GetMapping("/g")
	public void g() { //viewName "g.jsp"로 자동 찾게됨
		
	}
	
	
}
