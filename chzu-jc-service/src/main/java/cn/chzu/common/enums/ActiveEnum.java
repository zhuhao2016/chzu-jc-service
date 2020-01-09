package cn.chzu.common.enums;

/**
 * active枚举
 */
public enum ActiveEnum {
	ACTIVE_NOT_EXIST(0, "失效"), 
	ACTIVE_EXIST(1, "有效");

	private Integer active;

	private String msg;

	public Integer getActive() {
		return active;
	}

	public void setAcitve(Integer active) {
		this.active = active;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private ActiveEnum(Integer active, String msg) {
		this.active = active;
		this.msg = msg;
	}
}
