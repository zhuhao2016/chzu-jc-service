package cn.chzu.conf.util.session;

import java.io.Serializable;

/**
 * 应用权限信息", resource="appAuth")
 * @author zyn
 *
 */
public class AuthUserApp  implements Serializable
{
	private static final long serialVersionUID = 201704801825L;

    //应用UUID
    private String appUuid;

    //订阅级别
    private String userLevel;

    //应用编号
    private String appCode;
    
    public AuthUserApp()
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

    public String getUserLevel() 
    {
        return userLevel;
    }

    public void setUserLevel(String userLevel)
    {
        this.userLevel = userLevel == null ? null : userLevel.trim();
    }

	public String getAppCode() 
	{
		return appCode;
	}

	public void setAppCode(String app_code) 
	{
		this.appCode = app_code;
	}
	
}
