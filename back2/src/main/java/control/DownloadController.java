package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.13:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		//text/html, application/json
		response.setContentType("application/octet-stream; charset=utf-8"); //octet-stream 바이너리 단위 형식
//		PrintWriter out = response.getWriter();
		//위에걸 사용 안하는 이유? PrintWriter는 문자단위의 출력 스트림인데, 여기서는 바이너리 형태로 파일 내용을 출력해야한다
		ServletOutputStream sos = response.getOutputStream(); //바이트 단위 출력 스트림
		String id = request.getParameter("id");
		String opt = request.getParameter("opt"); //profile인지 intro인지 종류 구분
		String attachesDir = "D:\\KOSA202307\\attaches";
		String fileName = id + "_" + opt + "_";
		File dir = new File(attachesDir);
		for (File file: dir.listFiles()) {
			String existFileName = file.getName();
			if (existFileName.startsWith(fileName)) {
				System.out.println(existFileName + "파일입니다. 파일크기는 " + file.length());
				response.setHeader("Content-Disposition",
						"attachment;filename=" + URLEncoder.encode(existFileName, "UTF-8"));
				FileInputStream fis = new FileInputStream(file);
				int readValue = -1;
				while ((readValue = fis.read()) != -1) {
					sos.write(readValue); //바이트 단위로 쓰기
				}
				sos.close();
			}
		}
		System.out.println(id+"의 프로필 파일이 없습니다");
		return null;
	}

}
