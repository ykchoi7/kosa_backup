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
	private String tempDir = "D:\\KOSA202307\\temp"; //임시파일 저장경로
	private String attachesDir = "D:\\KOSA202307\\attaches"; //첨부경로
	private ServletFileUpload fileUpload;	
	private Map<String, List<FileItem>> requestMap;
	public Attach(HttpServletRequest request) throws FileUploadException {
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		File repository = new File(tempDir);
		if(!repository.exists()) {
			if(!repository.mkdir()) {				
				throw new FileUploadException(tempDir+"폴더생성 안됨");
			}
		}
		
		if(!new File(attachesDir).exists()) {
			if(!new File(attachesDir).mkdir()) {
				System.out.println(attachesDir+"폴더 생성");
				throw new FileUploadException(attachesDir+"폴더생성안됨");
			}
		}
		
		fileItemFactory.setRepository(repository); //업로드경로설정
		fileItemFactory.setSizeThreshold(10*1024); //10*1024byte이상인 경우 임시파일이 만들어짐
		fileUpload = new ServletFileUpload(fileItemFactory);
		requestMap =  fileUpload.parseParameterMap(request);
	}
	/**
	 * 요청전달데이터에 해당하는 값을 반환한다
	 * @param name 요청전달데이터이름
	 * @return 요청전달데이터값, 이름에 해당하는 값이 없으면 null을 반환한다
	 */
	public String getParameter(String name) {
		List<FileItem> items = requestMap.get(name);
		if(items == null) {
			return null;
		}
		FileItem item = items.get(0);
		String value = item.getString();
		return value;
	}
	
	/**
	 * 요청전달데이터에 해당하는 값들을 모두 반환한다
	 * @param name 요청전달데이터 이름
	 * @return 요청전달데이터값들, 이름에 해당하는 값이 없으면 null을 반환한다 
	 */
	public String[] getParameterValues(String name){
		List<FileItem> list = requestMap.get(name);
		if(list== null) {
			return null;
		}
		String[] arr = new String[list.size()];
		int i=0;
		for(FileItem item: requestMap.get(name)) {
			arr[i] = item.getString();
			i++;
		}		
		return arr;
	}
	/**
	 * 요청전달데이터이름에 해당하는 첨부된 파일들의 정보를 반환한다
	 * @param name 요청전달데이터이름
	 * @return 첨부된 파일들의 정보
	 * @throws Exception
	 */
	public List<FileItem> getFile(String name) throws Exception{
		return requestMap.get(name);
	}
	
	/**
	 * 첨부파일을 서버에 저장한다
	 * @param name 요청전달데이터 이름
	 * @throws Exception
	 */
	public void upload(String name) throws Exception{
		FileItem fileItem = requestMap.get(name).get(0);
		if(fileItem == null ||  fileItem.getSize() == 0){
			throw new Exception("첨부할 파일이 없습니다");
		}
		String fileName = fileItem.getName();			
		File profileFile = new File(attachesDir, fileName); 
		fileItem.write(profileFile);
	}
	
	/**
	 * 첨부파일을 서버에 저장한다
	 * @param name  요청전달데이터 이름
	 * @param fileName 서버에 저장될 파일이름
	 * @throws Exception
	 */
	public void upload(String name, String fileName) throws Exception {
		FileItem fileItem = requestMap.get(name).get(0);
		System.out.println("fileItem=" + fileItem);
		if(fileItem == null ||  fileItem.getSize() == 0){
			throw new Exception("첨부할 파일이 없습니다");
		}
		//String fileName = fileItem.getName();
		File file = new File(attachesDir, fileName); 
		fileItem.write(file);
		
	}
}
