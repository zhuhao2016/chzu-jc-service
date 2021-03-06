package cn.chzu.conf.common;

import java.io.Serializable;
import java.util.List;


/**
 * 分页返回对象
 */

public class ResList<T> implements Serializable {
	private static final long serialVersionUID = 201703160101009002L;

	// 分页信息，结果数据
	// 页大小
	
	private int pageRow = 0;
	
	// 起始页
	
	private int startPage = 0;
	
	// 总记录数
	
	private long totalRow = 0;
	
	// 返回对象
	
	private List<T> list;

	public ResList(List<T> list) {
		this.list = list;
		totalRow = (list == null) ? 0 : list.size();
	}

	public ResList() {

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

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
