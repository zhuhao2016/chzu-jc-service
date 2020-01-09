package cn.chzu.conf.util.session;

import cn.chzu.conf.util.session.AuthUserApp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/**
 * 用户信息", resource="authUser")
 * @author zyn
 *
 */
public class SessionAuthUser implements Serializable
{
	private static final long serialVersionUID = 201708201823L;

	//用户标识"
	    private String userId;

	    //用户名
	    private String userName;

	    //用户姓名
	    private String perName;

	    //电子邮箱
	    private String email;

	    //用户类型：内部用户，互联网用户
	    private String regType;

	    //授权应用
	    private List<AuthUserApp> appAuthList = null;
	    
	    public SessionAuthUser()
	    {
	    	
	    }
	    
		public String getUserId() 
		{
			return userId;
		}

		public void setUserId(String userId) 
		{
			this.userId = userId;
		}

		public String getUserName() 
		{
			return userName;
		}

		public void setUserName(String userName) 
		{
			this.userName = userName;
		}

		public String getPerName() 
		{
			return perName;
		}

		public void setPerName(String perName) 
		{
			this.perName = perName;
		}

		public String getEmail() 
		{
			return email;
		}

		public void setEmail(String email) 
		{
			this.email = email;
		}

		public String getRegType() 
		{
			return regType;
		}

		public void setRegType(String regType) 
		{
			this.regType = regType;
		}

		public List<AuthUserApp> getAppAuthList()
		{
			return appAuthList;
		}

		public void setAppAuthList(List<AuthUserApp> appAuthList)
		{
			this.appAuthList = appAuthList;
		}

		public synchronized void addAppAuth( AuthUserApp appAuth )
		{
			if( appAuthList == null ){
				appAuthList = new ArrayList<>();
			}
			
			appAuthList.add( appAuth );
		}
	    
	    
}
