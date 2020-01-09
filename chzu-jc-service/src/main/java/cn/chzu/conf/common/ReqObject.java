package cn.chzu.conf.common;

import cn.chzu.conf.exception.ZycException;

import java.io.Serializable;



/**
 * 请求对象
 */

public class ReqObject<T> implements Serializable {
	private static final long serialVersionUID = 201703190101009001L;

	// 客户端流水号
	
	private String flowNo = "";

	// 终端类型
	
	private String term = "";

	// 企业编号
	
	private String corp = "";

	// 请求数据
	
	private T object = null;

	public ReqObject() {

	}

	public ReqObject(T object) {
		this.object = object;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getCorp() {
		return corp;
	}

	public void setCorp(String corp) {
		this.corp = corp;
	}

	public T getObject() {
		if (object == null) {
			throw new ZycException("888888", "输入参数错误");
		}

		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

}
