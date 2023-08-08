package com.my.product.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.dto.Product;

public class ProductDAOFile implements ProductDAOInterface {
	private String fileName = "D:\\products.txt";
	public ProductDAOFile() {
		createFile();
	}
	
	//공통적으로 사용되는 부분은 따로 메소드 만들기
	private void createFile() {
		File file = new File(fileName);
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(fileName + "파일이 생성되었습니다");
		} else {
			System.out.println(fileName + "파일이 이미 존재합니다");
		}		
	}

	@Override
	public void insert(Product product) throws AddException {
		try {
			selectByProdNo(product.getProdNo());
			throw new AddException("이미 존재하는 상품입니다");
		} catch (FindException e) {
			e.printStackTrace();
			FileWriter fw = null;
			try {
				fw = new FileWriter(fileName, true);
				String prodStr = product.getProdNo() + ":" + product.getProdName() + ":" + product.getProdPrice()+"\n";
				fw.write(prodStr);
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (fw != null) {
					try {
						fw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public Product selectByProdNo(String no) throws FindException {
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(fileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] arr = line.split(":");
				String prodNo = arr[0];
				if (prodNo.equals(no)) {
					return new Product(prodNo, arr[1], Integer.parseInt(arr[2]));
				}
			}
			throw new FindException("번호에 해당하는 상품이 없습니다");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			createFile();
			throw new FindException("번호에 해당하는 상품이 없습니다");
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

	@Override
	public Object selectAll() throws FindException {
		List<Product> all = new ArrayList<>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(fileName));
			while (sc.hasNextLine()) { //다음줄이 있으면 true 반환
				String line = sc.nextLine(); //줄단위로 파일 내용 불러오기
				String[] arr = line.split(":");
				String prodNo = arr[0];
				String prodName = arr[1];
				int prodPrice = Integer.parseInt(arr[2]);
				Product p = new Product(prodNo, prodName, prodPrice); //한줄씩 읽어온걸 한 상품에 대한 정보로 인식
				all.add(p);
			}
			if (all.size() == 0) {
				throw new FindException("상품이 한개도 없습니다");
			}
			return all; //try블럭에 return이 있어도 finally블럭 실행 후 return이 실행된다 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			createFile();
			throw new FindException("상품이 한개도 없습니다");
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

	@Override
	public void update(Product p) throws ModifyException {
		FileWriter fw = null;
		
		try {
			List<Product> all = (List)selectAll();
			boolean updated = false;
			for(Product savedP : all) {				
				if(savedP.equals(p)) {//if(savedP.getProdNo().equals(p.getProdNo())) {
					if(p.getProdName() != null) {
						savedP.setProdName(p.getProdName());
						updated = true;
					}
					if(p.getProdPrice() != 0) {
						savedP.setProdPrice(p.getProdPrice());
						updated = true;
					}
					break;
				}
			}
			if(updated) {
				fw = new FileWriter(fileName);
				for(Product savedP: all) {
					String pStr = savedP.getProdNo() 
							+ ":" + savedP.getProdName() 
							+ ":" + savedP.getProdPrice() 
							+"\n";
					fw.write(pStr);
				}
			}
		} catch (FindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(String prodNo) throws RemoveException {
		FileWriter fw = null;
		try {
			List<Product> all = (List)selectAll();
//			Product savedP = new Product();
//			savedP.setProdNo(prodNo);
//			all.remove(savedP);			
			fw = new FileWriter(fileName); //fileName - true
			for(Product p: all) {
				if(!p.getProdNo().equals(prodNo)) {
					String pStr = p.getProdNo() 
						+ ":" + p.getProdName() 
						+ ":" + p.getProdPrice() 
						+"\n";
					fw.write(pStr);
				}
			}
		} catch (FindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
