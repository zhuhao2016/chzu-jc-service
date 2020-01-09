package cn.chzu.conf.util.session;

import cn.chzu.conf.util.session.CompUserApp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户信息", resource="compUser")
 * @author zyn
 *
 */
public class SessionCompUser implements Serializable
{
	private static final long serialVersionUID = 201704801824L;

   //所属公司
    private String corpUuid;

    //所属机构
    private String deptUuid;

    //员工编号
    private String userCode;

   //用户类型：员工，外协，实习，兼职，访客
    private String userType;

    //授权应用
    private List<CompUserApp> appAuthList = null;
    
    public SessionCompUser()
    {
        
    }
    
	public List<CompUserApp> getAppAuthList()
	{
		return appAuthList;
	}

	public void setAppAuthList(List<CompUserApp> appAuthList) 
	{
		this.appAuthList = appAuthList;
	}
	
	public synchronized void addAppAuth( CompUserApp appAuth )
	{
		if( appAuthList == null ){
			appAuthList = new ArrayList<>();
		}
		
		appAuthList.add( appAuth );
	}

    public String getCorpUuid() 
    {
        return corpUuid;
    }

    public void setCorpUuid(String corpUuid) 
    {
        this.corpUuid = corpUuid == null ? null : corpUuid.trim();
    }

    public String getDeptUuid() 
    {
        return deptUuid;
    }

    public void setDeptUuid(String deptUuid) 
    {
        this.deptUuid = deptUuid == null ? null : deptUuid.trim();
    }

    public String getUserCode() 
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserType() 
    {
        return userType;
    }

    public void setUserType(String userType) 
    {
        this.userType = userType == null ? null : userType.trim();
    }

}
