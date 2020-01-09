package cn.chzu.base.model;

import java.io.Serializable;


/** 
 * 基础model
 */
//基础model
public abstract class BaseModelVo implements Serializable, Comparable<BaseModelVo> {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	
	//版本号
	private int version;
	
	//创建时间
	private String createTime;
	
	//修改时间
	private String editTime;
	
	//创建人
	private int createUser;
	
	//修改人
	private int editUser;
	
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
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public int getCreateUser() {
		return createUser;
	}
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}
	public int getEditUser() {
		return editUser;
	}
	public void setEditUser(int editUser) {
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
        if (!(object instanceof BaseModelVo)) {
            return false;
        }
        final BaseModelVo other = (BaseModelVo) object;
        Serializable id = this.getId();
        Serializable otherId = other.getId();
        if ((id == null && otherId != null) || (id != null && !id.equals(otherId))) {
            return false;
        }
        return true;
    } 

	@Override
	public int compareTo(BaseModelVo another){
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
