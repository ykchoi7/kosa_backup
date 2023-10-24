package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.customer.dto.Customer;
import com.my.customer.service.CustomerService;
import com.my.exception.FindException;

import net.coobird.thumbnailator.Thumbnailator;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService service;

	@GetMapping("/iddupchk")
	public Map<String, Object> iddupchk(String id) {
		Map<String, Object> map = new HashMap<>();		
		try {
			service.idDupChk(id);
			map.put("status", 0);
		} catch (FindException e) {
			map.put("status", 1);
		}
		return map;
	}

	@PostMapping("/signup")
	public Map<String, Object>signup(Customer c, //c에 id, pwd, name 매개변수로 담긴다
			MultipartFile f1, 					//첨부파일 MultipartFile 형태로 받기
			List<MultipartFile> f2) throws Exception{ 
		Map<String, Object> map = new HashMap<>(); //응답내용
//		try {			
			service.signup(c);
			String attachesDir = "D:\\KOSA202307\\attaches"; //첨부경로

			//프로필 첨부파일 
			if(f1 != null && f1.getSize() > 0) {
				String originProfileFileName = f1.getOriginalFilename();
				String targetFileName = c.getId() + "_profile_" + originProfileFileName;
				File targetFile = new File(attachesDir, targetFileName);
				FileCopyUtils.copy(f1.getBytes(), targetFile);

				//----섬네일파일 만들기 START----
				int width=100;
				int height=100;				

				String thumbFileName = c.getId() + "_profile_t_" + originProfileFileName; //섬네일파일명
				File thumbFile = new File(attachesDir, thumbFileName);
				FileOutputStream thumbnailOS = new FileOutputStream(thumbFile);//출력스트림
				InputStream thumbnailIS = f1.getInputStream(); //첨부파일 입력스트림				
				Thumbnailator.createThumbnail(thumbnailIS, thumbnailOS, width, height);
				//-----섬네일파일 만들기 END------
			}else {
				throw new Exception("프로필 파일이 없습니다");
			}

			//자기소개서 첨부파일 
			for(MultipartFile mf: f2) {
				if(mf != null && mf.getSize() > 0) {
					String originProfileFileName = mf.getOriginalFilename();
					String targetFileName = c.getId() + "_intro_" + originProfileFileName;
					File targetFile = new File(attachesDir, targetFileName);
					FileCopyUtils.copy(mf.getBytes(), targetFile);
				}else {
					throw new Exception("자소서 파일이 없습니다");
				}
			}			
			map.put("status", 1);
			map.put("msg", "가입성공");			
//		} catch (Exception e) {			
//			e.printStackTrace();
//			map.put("status", 0);
//			map.put("msg", e.getMessage());		
//		}
		return map;
	}
	
	@PostMapping("/login")
	public Map<String, Object> login(String id, 
			                         String pwd,
			                         HttpSession session){
		Map<String, Object> map = new HashMap<>();
		//세션 정보 중에 loginedId 어트리뷰트가 있으면 초기화 시켜주기
		session.removeAttribute("loginedId"); //초기화
		try {
			service.login(id, pwd);
			map.put("status", 1);
			map.put("msg", "로그인 성공");
			session.setAttribute("loginedId", id);
		} catch (FindException e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "로그인 실패");			
		}
		return map;
	}
	
	@GetMapping("/logout")
	public void logout(HttpSession session) {
		//세션 정보 중에 loginedId 어트리뷰트가 있으면 초기화 시켜주기
		session.removeAttribute("loginedId");
		session.invalidate();
	}

}
