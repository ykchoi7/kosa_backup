package control;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.13:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		//post방식의 요청인 경우 요청 바디의 형식은 application/x-www-form-urlencoded 입니다
		//x-www-form-urlencoded 그럼 왜 이걸 사용하지 않았나? -> input 태그를 활용하여 파일 첨부를 하고 싶으면 
		//enctype="multipart/form-data" 이런 형식을 사용해야 한다
//		String tValue = request.getParameter("t");
//		String f1Value = request.getParameter("f1");
		
		//post방식의 요청인 경우 요청 바디의 형식은 multi-part/form-data인 경우 -> String으로 읽어오기
//		ServletInputStream sis = request.getInputStream();
//		Scanner sc = new Scanner(sis);
//		while(sc.hasNextLine()) {
//			System.out.println(sc.nextLine());			
//		}
		
		String tempDir = "D:\\KOSA202307\\temp"; //temp파일 저장할 경로 따로 설정 (용량이 클 때 생성되는 임시파일이 저장될 경로)
		String attachesDir = "D:\\KOSA202307\\attaches"; //첨부경로
		
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		File repository = new File(tempDir);
		
		//tmp폴더에 생성된 파일 객체 - repository가 존재하지 않을 때
		if (!repository.exists()) {
			if (repository.mkdir()) {
				System.out.println(tempDir + "폴더 생성");
			} else {
				System.out.println(tempDir + "폴더 생성안됨");
				return;
			};
		}
		
		//attaches폴더 생성 여부 확인
		if (!new File(attachesDir).exists()) {
			if (new File(attachesDir).mkdir()) {
				System.out.println(attachesDir + "폴더 생성");
			} else {
				System.out.println(attachesDir + "폴더 생성안됨");
				return;
			}
		}
		
		fileItemFactory.setRepository(repository);
		fileItemFactory.setSizeThreshold(10*1024); //10*1024byte 이상인 경우 임시파일(temp파일)이 만들어짐
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory); //가장 기억해야할 포인트

		try {
			List<FileItem> items = fileUpload.parseRequest(request); //첨부된 파일 한줄씩 읽어와서 파싱하기
			for (FileItem item: items) {
				if (item.isFormField()) { //요청전달데이터인 경우
					System.out.println(item.getFieldName() + ":" + item.getString());
				} else { //첨부파일인 경우
					System.out.println(item.getName() +":"+ item.getSize());
					if (item.getSize() > 0) {
						UUID uuid = UUID.randomUUID(); //universal unique id 생성 -> 중복되지않는 값을 넣어주기 위해
						System.out.println(uuid);
						File attacheFile = new File(attachesDir, uuid +"_"+ item.getName());
						//한 서버에 여러가지 파일을 업로드 할 때 파일명이 같으면 덮어씌워지는 위험이 있음 => 고유한 이름을 넣어주는게 안전 (uuid 사용하는 이유)
						try {
							item.write(attacheFile); //첨부파일 서버경로에 저장
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

}



