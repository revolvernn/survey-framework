package com.revolver.survey.guest.model;

import java.util.List;
/**
 * 
 * @author REVOLVER
 *
 * @param <T>
 */
public class Page<T> {
	// 当前页码
	private Integer pageNo;

	// 当前页显示条数
	private int pageSize;

	// 总记录数
	private int totalRecordNo;

	// 总页数
	private int totalPageNo;

	private List<T> list;

	public Page(String pageNoStr, int totalRecordNo, int pageSize) {
		// 设置总记录数
		this.totalRecordNo = totalRecordNo;
		this.pageSize = pageSize;

		// 计算总页数
		this.totalPageNo = this.totalRecordNo / this.pageSize
				+ ((this.totalRecordNo % this.pageSize == 0) ? 0 : 1);

		// 设置默认值
		this.pageNo = 1;

		try {
			this.pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		
		if(pageNo > totalPageNo){
			pageNo = totalPageNo;
		}
		if(pageNo < 1){
			pageNo = 1;
		}
	}
	
	public boolean isHasPrev(){
		return pageNo>1;
	}
	public boolean isHasNext(){
		return pageNo < totalPageNo;
	}
	public int getPrev(){
		return pageNo - 1;
	}
	public int getNext(){
		return pageNo + 1;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalRecordNo() {
		return totalRecordNo;
	}

	public int getTotalPageNo() {
		return totalPageNo;
	}

}
