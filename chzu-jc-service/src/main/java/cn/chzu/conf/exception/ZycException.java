package cn.chzu.conf.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义卓越城exception
 */
public class ZycException extends RuntimeException {

	private static final long serialVersionUID = 200901080101009010L;
	
	 protected List<String> detailMessage = null;

	private static final Logger log = LoggerFactory.getLogger(ZycException.class.getName());

	protected String errCode;
	protected String message;

	public ZycException() {
		super("N/A");
		errCode = "999999";
		message = "未知错误";
	}

	public ZycException(String message) {
		super(message);
		errCode = "";
		this.message = message;
	}

	public ZycException(String errCode, String message) {
		super(message);
		this.errCode = errCode;
		this.message = message;
	}

	public ZycException(String errCode, String message, Throwable cause) {
		super(message, cause);
		this.errCode = errCode;
		this.message = message;
	}

	public ZycException(String message, Throwable cause) {
		super(message, cause);
		errCode = "";
		this.message = message;
	}

	public String getErrCode() {
		return this.errCode;
	}
	
   public void addMessage(String message) 
   {
       if(detailMessage==null){
           detailMessage = new ArrayList<String>();
       }

       detailMessage.add(message);    
   }


}
