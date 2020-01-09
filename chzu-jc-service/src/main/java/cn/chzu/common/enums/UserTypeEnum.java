package cn.chzu.common.enums;

/**
 * 用户类型枚举
 */
public enum UserTypeEnum {
	USER_TYPE_MANAGER(0, "管理平台人员"), 
	USER_TYPE_SYNERGY_USER(1, "平台用户");

	private Integer userType;

	private String value;

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private UserTypeEnum(Integer userType, String value) {
		this.userType = userType;
		this.value = value;
	}
}
