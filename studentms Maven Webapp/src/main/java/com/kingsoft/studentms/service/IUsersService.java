package com.kingsoft.studentms.service;

import com.kingsoft.studentms.model.Users;

public interface IUsersService {

	
	public String doLogin(String username,String usertype);
	
	
	
	
	
	
	
	
	//杨超
	public Users login(String username,String password);
	public boolean cheackRegister(String username);
	public String register(String username,String password);
	
}
