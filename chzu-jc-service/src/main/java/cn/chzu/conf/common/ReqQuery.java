package cn.chzu.conf.common;

import java.io.Serializable;



/**
 * 分页请求对象
 */

public class ReqQuery<T> implements Serializable {
	private static final long serialVersionUID = 201703190101009002L;
	public static final long totalCount = 10000;

	// 分页信息，pageRow>0时分页取数据
	// 页大小
	
	private int pageRow = 0;

	// 起始页
	
	private int startPage = 0;

	// 总记录数
	
	private long totalRow = 0;

	// 排序字段
	
	private String orderBy = null;

	// 查询条件
	
	private T object;

	public ReqQuery() {
	}

	public ReqQuery(T object) {
		this.object = object;
	}

	public int getPageRow() {
		return pageRow;
	}

	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public long getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(long totalRow) {
		this.totalRow = totalRow;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

}
