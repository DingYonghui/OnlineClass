package com.mori.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;



import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mori.domain.AdminUser;
import com.mori.domain.StudentUser;

import cn.itcast.jdbc.TxQueryRunner;

public class AdminUserDao {
	private QueryRunner qr = new TxQueryRunner();
	/*
	 * 添加一个ADMIN
	 */
	public void add(AdminUser form) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into adminuser values(?,?,?,?,?)";
			Object[] params = { form.getPk_ANumber(), form.getAKey(),
					form.getAName(), form.getPower(),form.getUrl()

			};
			System.out.print(form.getPower());
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	/*
	 * 
	 */
	public void  delete()
	{
		
	}
	/*
	 * 
	 */
	public AdminUser checkAdmin(AdminUser form)
	{
		try {
			String sql = "select * from adminuser where pk_ANumber=? and AKey=?";
			Object[] params = { form.getPk_ANumber(), form.getAKey()
					
			};
			return qr.query(sql,new BeanHandler<AdminUser>(AdminUser.class),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	
}
