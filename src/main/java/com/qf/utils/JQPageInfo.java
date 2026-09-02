package com.qf.utils;

public class JQPageInfo {
	private Integer page;
	
	private Integer limit;
	
	private String sidx;
	
	private String order;
	
	private  Integer offset;

/**
 * 获取当前页码的方法
 * @return 返回当前页码的整数值
 */
	public Integer getPage() {
	// 返回page属性值
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

/**
 * 获取限制值的方法
 * @return 返回限制值的Integer对象
 */
	public Integer getLimit() {
	// 返回limit成员变量的值
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
}
