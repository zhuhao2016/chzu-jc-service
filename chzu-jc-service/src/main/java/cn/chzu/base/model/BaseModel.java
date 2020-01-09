package cn.chzu.base.model;

import java.io.Serializable;
import java.util.Date;

/** 
 * 基础model
 */
//@PojoNote(desc="基础model")

public abstract class BaseModel implements Serializable, Comparable<BaseModel> {
	private static final long serialVersionUID = 1L;
	
//	@FieldNote(desc="主键", notNull=true)
	
	private Integer id;
	
//	@FieldNote(desc="版本号", notNull=true)
	
	private int version;
	
//	@FieldNote(desc="创建时间",notNull=true)
	
	private Date createTime;
	
//	@FieldNote(desc="修改时间", notNull=true)
	//@ApiModelProperty(value = "修改时间",dataType="Date",name="editTime",allowEmptyValue=true,example="2019-12-12")
	private Date editTime;
	
//	@FieldNote(desc="创建人", length=50, notNull=true)
	//@ApiModelProperty(value = "创建人id",dataType="Integer",name="createUser",allowEmptyValue=true,example="1")
	private Integer createUser;
	
//	@FieldNote(desc="修改人", length=50, notNull=true)
	//@ApiModelProperty(value = "修改人id",dataType="Integer",name="editUser",allowEmptyValue=true,example="1")
	private Integer editUser;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Integer getEditUser() {
		return editUser;
	}
	public void setEditUser(Integer editUser) {
		this.editUser = editUser;
	}
	/**
	 * 
	 */
    @Override
	public int hashCode() {
 		return id.hashCode();
	}

	@Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object && this.hashCode() == object.hashCode()) {
            return true;
        }
        if (!(object instanceof BaseModel)) {
            return false;
        }
        final BaseModel other = (BaseModel) object;
        Serializable id = this.getId();
        Serializable otherId = other.getId();
        if ((id == null && otherId != null) || (id != null && !id.equals(otherId))) {
            return false;
        }
        return true;
    } 

	@Override
	public int compareTo(BaseModel another){
		if(this.getId()>another.getId()){
			return -1;
		}else if(this.getId()<another.getId()){
			return 1;
		}
		return 0;
	}
	
	public String getChineseName(){
		return this.getClass().getSimpleName();
	}
}
