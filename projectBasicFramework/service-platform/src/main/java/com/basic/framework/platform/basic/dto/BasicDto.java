/**
 * 
 */
package com.basic.framework.platform.basic.dto;

import java.io.Serializable;

/**
 * @author gmc
 * @see 基础数据传递对象
 */
public class BasicDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查询条件
	 */
	protected String searchKey;
	
	/**
	 * 当前页
	 */
	protected Integer pageNumber;
	
	/**
	 * 分页记录条数
	 */
	protected Integer pageSize;

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
