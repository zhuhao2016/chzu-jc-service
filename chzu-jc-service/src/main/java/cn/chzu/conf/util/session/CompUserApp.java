package cn.chzu.conf.util.session;

import java.io.Serializable;

/**
 * 应用权限", resource="appAuth")
 * @author zyn
 *
 */
public class CompUserApp  implements Serializable
{
	private static final long serialVersionUID = 201704801826L;

    //应用UUID
    private String appUuid;

   //授权用户
    private String privUser;

    //角色列表
    private String roleList;

    //角色组列表
    private String rolesList;

    //应用编号
    private String appCode;
    
    public CompUserApp()
    {
    	
    }

    public String getAppUuid() 
    {
        return appUuid;
    }

    public void setAppUuid(String appUuid)
    {
        this.appUuid = appUuid == null ? null : appUuid.trim();
    }

    public String getPrivUser() 
    {
        return privUser;
    }

    public void setPrivUser(String privUser) 
    {
        this.privUser = privUser == null ? null : privUser.trim();
    }

	public String getAppCode() 
	{
		return appCode;
	}

	public void setAppCode(String app_code) 
	{
		this.appCode = app_code;
	}
	
	public String getRoleList()
	{
		return roleList;
	}

	public void setRoleList(String roleList)
	{
		this.roleList = roleList;
	}

	public String getRolesList()
	{
		return rolesList;
	}

	public void setRolesList(String rolesList)
	{
		this.rolesList = rolesList;
	}

}
