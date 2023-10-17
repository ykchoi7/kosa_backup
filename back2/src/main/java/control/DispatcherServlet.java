package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/") //DispatcherServlet -> 여기서 /만 설정하면 뒤에 호출하는 uri로 출력이 된다
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String envFileName = "control.properties";
	private Properties env;

    public DispatcherServlet() {
    	super();
    }
    
	@Override
	public void init() throws ServletException {
		super.init(); // init() 메소드는 자동호출되기 때문에 이걸 오버라이딩해야한다
		env = new Properties();
		ServletContext sc = this.getServletContext();
		String realPath = sc.getRealPath("WEB-INF\\classes\\com\\my\\env\\" + envFileName);
    	System.out.println("in DispatcherServlet의 init(): realPath=" + realPath);
		//load() 메서드 작성
    	try {
			env.load(new FileInputStream(realPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//모든 Controller에서 반복되는 접근 허용 부분을 한번에 처리하기 위해서 이 부분에 추가해둔다
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.13:5500"); // http://127.0.0.1:5500
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		System.out.println("request.getServletPath()=" + request.getServletPath()); //uri값 반환 (/DispatcherServlet)
		
		//각 controller를 호출하는 방식
//		if (request.getServletPath().equals("/productjson")) {
//			ProductJsonController control = new ProductJsonController();
//			control.execute(request, response);
//		} else if (request.getServletPath().equals("/productlistjson")) {
//			ProductListJsonController control = new ProductListJsonController();
//			control.execute(request, response);
//		}
		
		//위 경우를 사용하면 메뉴가 추가될 때마다 리로드, 재컴파일 등이 필요하다 -> 오랜 시간이 걸림 
		//=> reflection기술을 활용해서 여러가지 서블릿 호출을 한번에 할 수 있다
		//control.properties 파일 사용
		String className = env.getProperty(request.getServletPath());
		try {
//			Class clazz = Class.forName(className); //클래스 이름에 해당하는 .class파일 찾아서 JVM으로 로드
//			Method m = clazz.getMethod("getInstance");
//			Controller controller = (Controller)clazz.getDeclaredConstructor().newInstance(); //객체 생성해서 Controller타입으로 다운캐스팅
//			controller.execute(request, response); //호출한 controller의 execute() 메소드를 호출
			
			Class<?> clazz = Class.forName(className); //클래스이름에 해당하는 .class파일 찾아서 JVM으로 로드
			Controller controller;
			try {
				Method method = clazz.getMethod("getInstance");
				controller = (Controller)method.invoke(null); //static인 getInstance()메서드호출, 일반 controller로 다운캐스팅
			}catch(NoSuchMethodException e) {
				controller = (Controller)clazz.getDeclaredConstructor().newInstance();
			}
			String path = controller.execute(request, response);
			if (path != null) {
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
