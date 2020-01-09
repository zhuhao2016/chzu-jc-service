package cn.chzu.conf.util.session;

import cn.chzu.conf.util.session.SessionAuthUser;
import cn.chzu.conf.util.session.SessionCompUser;

import java.io.Serializable;
import java.util.List;

public class UserSessionData implements Serializable
{
	private static final long serialVersionUID = 201704101111l;
	
	private SessionAuthUser authUser;
	private List<SessionCompUser> compUser;
	
	public SessionAuthUser getAuthUser() 
	{
		return authUser;
	}
	public void setAuthUser(SessionAuthUser authUser) 
	{
		this.authUser = authUser;
	}
	public List<SessionCompUser> getCompUser() 
	{
		return compUser;
	}
	public void setCompUser(List<SessionCompUser> compUser) 
	{
		this.compUser = compUser;
	}

	@Override
	public String toString() {
		return "UserData [authUser=" + authUser + ", compUser=" + compUser + "]";
	}
	
}
