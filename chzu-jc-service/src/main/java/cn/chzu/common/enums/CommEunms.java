
 /**
 *
 * @(#) Eunms.java
 * @Package cn.lz.management.common.enums
 * 
 * Copyright © cerno Corporation. All rights reserved.
 *
 */

package cn.chzu.common.enums;

/**
 *  Description : 公共常量类
 * 
 *  @author:  xukuanwei
 *
 * Create Date：   2019年11月20日 下午4:54:20
 * History:  2019年11月20日 下午4:54:20   xukuanwei   Created.
 *           
 */
public class CommEunms {
	
	private CommEunms() {
		
	}
	
	// 是否失效枚举
	public enum ActiveEnum{
		ACTIVE_NOT_EXIST(0,"失效"),
		ACTIVE_EXIST(1,"有效");
		
		private int code;
		
		private String msg;

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		private ActiveEnum(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}
	}
}

