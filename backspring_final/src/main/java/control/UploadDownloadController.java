package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class UploadDownloadController {
	@PostMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile f1, List<MultipartFile> f2) throws IOException {
		System.out.println(f1.getOriginalFilename() + ":" + f1.getSize() );
		if(f1 != null &&  f1.getSize() > 0) {
			File targetFile = new File("D:\\kosa202307\\attaches" ,  f1.getOriginalFilename());
			FileCopyUtils.copy(f1.getBytes(), targetFile);
			
			//----섬네일파일 만들기 START----
			int width=100;
			int height=100;				

			String thumbFileName = "t_" + f1.getOriginalFilename(); //섬네일파일명
			File thumbFile = new File("D:\\kosa202307\\attaches" , thumbFileName);
			FileOutputStream thumbnailOS = new FileOutputStream(thumbFile);//출력스트림
			InputStream thumbnailIS = f1.getInputStream(); //첨부파일 입력스트림				
			Thumbnailator.createThumbnail(thumbnailIS, thumbnailOS, width, height);
			//-----섬네일파일 만들기 END------			
			
			for(MultipartFile mf: f2) {
				if(mf != null && mf.getSize() > 0) {
					File targetFile2 = new File("D:\\kosa202307\\attaches" ,  mf.getOriginalFilename());
					FileCopyUtils.copy(f1.getBytes(), targetFile2);
				}
			}			
			return "upload OK";
		}else {
			return "upload FAIL";
		}	
	}
	
	
	@GetMapping("/downloadtest")
	@ResponseBody
	public ResponseEntity<?> downloadtest() throws IOException {
		String existFileName = "D:\\KOSA202307\\attaches\\t_badge.png";
		HttpStatus status = HttpStatus.OK;
//		HttpStatus status = HttpStatus.NOT_FOUND;
//		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION,
				    "attachment;filename=" + URLEncoder.encode(existFileName, "UTF-8"));
		
		File file = new File(existFileName);
		String contentType = Files.probeContentType(file.toPath());//파일의 형식		
		headers.add(HttpHeaders.CONTENT_TYPE, contentType); //응답형식		
		headers.add(HttpHeaders.CONTENT_LENGTH, ""+file.length());//응답길이
		
		byte[]bArr = FileCopyUtils.copyToByteArray(file);
		ResponseEntity<?> entity = new ResponseEntity<>(bArr, headers, status);//응답상태코드
		return entity;
	}
	
	
	@GetMapping("/download")
	@ResponseBody
	public ResponseEntity<?> download(String id, String opt) throws IOException{
												//opt - profile인지 intro인지 종류 구분
		String attachesDir = "D:\\KOSA202307\\attaches";
		File dir = new File(attachesDir);
		
		if(opt.equals("profile")) {
			opt+="_t"; //썸네일 파일
		}
		String fileName = id + "_"+ opt +"_";
		
//		String existFileName = id + "_"+ opt +"_";
		for(File f: dir.listFiles()) {
			String existFileName = f.getName();
			if(existFileName.startsWith(fileName)) { //파일을 찾으면
		
				HttpStatus status = HttpStatus.OK; //상태 good
				HttpHeaders headers = new HttpHeaders();
				headers.add(HttpHeaders.CONTENT_DISPOSITION, //HTTP Response Body에 오는 컨텐츠의 성향을 알려주는 속성
						    "attachment;filename=" + URLEncoder.encode(existFileName, "UTF-8"));
				
				System.out.println("in download file: " + f + ", file size:" + f.length());
				String contentType = Files.probeContentType(f.toPath());//파일의 형식		
				headers.add(HttpHeaders.CONTENT_TYPE, contentType); //응답형식		
				headers.add(HttpHeaders.CONTENT_LENGTH, ""+f.length());//응답길이
				
				byte[]bArr = FileCopyUtils.copyToByteArray(f);
				ResponseEntity<?> entity = new ResponseEntity<>(bArr, headers, status);//응답상태코드
				return entity;
			}
		}
		HttpStatus status = HttpStatus.NOT_FOUND; //파일 못 찾으면 not found status 띄우기
		ResponseEntity<?> entity = new ResponseEntity<>("프로필썸네일파일이 없습니다", status);
		return entity;
		
	}
	
}
