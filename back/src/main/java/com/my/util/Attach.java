package com.my.util;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Attach {
	
	//파일 저장경로 설정
	private String tempDir = "D:\\KOSA202307\\temp"; //temp파일 저장할 경로 따로 설정 (용량이 클 때 생성되는 임시파일이 저장될 경로)
	private String attachesDir = "D:\\KOSA202307\\attaches"; //첨부경로	
	
	private ServletFileUpload fileUpload;
	private Map<String, List<FileItem>> requestMap;
	
	public Attach(HttpServletRequest request) throws FileUploadException {
		//파일 업로드 경로 설정
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
		requestMap = fileUpload.parseParameterMap(request);
	}
	
	/**
	 * 요청전달데이터에 해당하는 값을 반환한다
	 * @param name 요청 전달 데이터 이름
	 * @return 요청전달데이터값, 이름에 해당하는 값이 없으면 null을 반납한다
	 */
	public String getParameter(String name) {
		List<FileItem> items = requestMap.get(name);
		if (items == null) {
			return null;
		}
		FileItem item = items.get(0);
		String value = item.getString();
		return value;
		//return requestMap.get(name).get(0).getString();
	}
	
	/**
	 * 요청전달데이터에 해당하는 값을 모두 반환한다
	 * @param name 요청 전달 데이터 이름
	 * @return 요청전달데이터값 반환한다
	 */
	public String[] getParameterValues(String name){
		List<FileItem> list = requestMap.get(name);
		
		String[] arr = new String[list.size()];
		int i=0;
		for(FileItem item: requestMap.get(name)) {
			arr[i] = item.getString();
			i++;
		}		
		return arr;
	}
	
	//요청전달데이터 c=c111&c=c222&c=c333
	/**
	 * 요청전달데이터 이름에 해당하는 첨부파일들의 정보를 반환한다
	 * @param name 요청 전달 데이터 이름
	 * @return 첨부된 파일들의 정보
	 * @throws Exception 
	 */
	public List<FileItem> getFile(String name) throws Exception {
		return requestMap.get(name);
	}
	
	/**
	 * 첨부파일을 서버에 저장한다
	 * @param name 요청전달데이터 이름
	 * @throws Exception
	 */
	public void upload(String name) throws Exception{
		FileItem fileItem = requestMap.get(name).get(0);
		if(fileItem != null &&  fileItem.getSize() > 0) {
			String fileName = fileItem.getName();			
			File profileFile = new File(fileName); 
			fileItem.write(profileFile);
		}
	}
	
	/**
	 * 첨부파일을 서버에 저장
	 * @param name 요청전달데이터 이름
	 * @param fileName 서버에 저장될 파일 이름
	 * @throws Exception
	 */
	public void upload(String name, String fileName) throws Exception {
		FileItem fileItem = requestMap.get(name).get(0);
		System.out.println("fileItem=" + fileItem);
		if(fileItem == null || fileItem.getSize() == 0){
			throw new Exception("첨부할 파일이 없습니다");
		}
		//String fileName = fileItem.getName();
		File file = new File(attachesDir, fileName); 
		fileItem.write(file);
	}
}
