package com.mori.service;

import com.mori.Exception.UserException;
import com.mori.dao.AdminUserDao;
import com.mori.domain.AdminUser;

public class AdminService {
	private AdminUserDao aUD=new AdminUserDao();
	
	public AdminUser login(AdminUser form) throws UserException
	{
		AdminUser admin=aUD.checkAdmin(form);
		if(admin==null)
			throw new UserException("帐号密码错误或者身份不符合权限");
		else
		return admin;
	}
}
