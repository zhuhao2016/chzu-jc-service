package cn.chzu.conf.util.appinfo;

import java.util.List;

/**
 * 字段信息", resource="devService")
 * @author zyn
 *
 */
public class FieldInfo 
   {
	//字段名称
	private String fieldName;
	
	//字段说明
	private String fieldDesc;
	
	//数据类型
	private String dataType;
	
	//字段类型
	private String fieldType;
	
	//长度
	private String fieldLength;
	
	//非空
	private String notNull;
	
	private List<FieldInfo> fields;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldDesc() {
		return fieldDesc;
	}
	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldLength() {
		return fieldLength;
	}
	public void setFieldLength(String fieldLength) {
		this.fieldLength = fieldLength;
	}
	public String getNotNull() {
		return notNull;
	}
	public void setNotNull(String notNull) {
		this.notNull = notNull;
	}
	public List<FieldInfo> getFields() {
		return fields;
	}
	public void setFields(List<FieldInfo> fields) {
		this.fields = fields;
	}
	@Override
	public String toString() {
		return "FileldInfo [fieldName=" + fieldName + ", fieldDesc=" + fieldDesc + ", dataType=" + dataType
				+ ", fieldType=" + fieldType + ", fieldLength=" + fieldLength + ", notNull=" + notNull + ", fields="
				+ fields + "]";
	}
	
}
