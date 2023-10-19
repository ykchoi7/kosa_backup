package com.my.util;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageGroup<T> {
	
	private List<T> list; //제너릭 타입으로 생성
	private int totalCnt; //총 상품수		11
	private int totalPage; //총 페이지수	4
	private int currentPage; //현재 페이지	1 2 3 4 5 6  1 2 3 4 7(3일때)
	private int startPage;  //시작 페이지	1 1 3 3 5 5  1 1 1 4 7
	private int endPage; //끝 페이지		2 2 4 4 6 6  3 3 3 6 9
	
	//상수 선언
	public static final int CNT_PER_PAGE = 3;
	public static final int CNT_PER_PAGEGROUP = 2;
	
	//생성자 직접 생성
	public PageGroup(List<T> list, int currentPage, int totalCnt) {
		this.list = list;
		this.currentPage = currentPage;
		this.totalCnt = totalCnt;
		
		//총 페이지 수 계산
		totalPage = (int)Math.ceil((double)totalCnt/CNT_PER_PAGE);
		
		//시작페이지, 끝페이지 계산
		if (endPage > totalPage) {
			endPage = totalPage;
		} else {
			if (currentPage%CNT_PER_PAGEGROUP == 0) {
				endPage = currentPage;
				startPage = (currentPage-CNT_PER_PAGEGROUP)+1;
			} else {
				startPage = (currentPage/CNT_PER_PAGEGROUP)*CNT_PER_PAGEGROUP+1;
				endPage = startPage + CNT_PER_PAGEGROUP -1;
			}
		}
	}
	
	public static void main(String[] args) {
		int cp = 2;	 //현재 보려는 페이지
		int tc = 11; //총 상품수
		PageGroup pg = new PageGroup(null, cp, tc);
		System.out.println(pg.getTotalPage()); //4
		System.out.println(pg.getStartPage()); //1
		System.out.println(pg.getEndPage());   //2
	}
}
