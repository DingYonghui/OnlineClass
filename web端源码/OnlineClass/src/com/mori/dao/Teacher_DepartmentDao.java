package com.mori.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;






import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mori.Exception.TeacherException;
import com.mori.domain.StudentUser;
import com.mori.domain.Teacher_Department;

import cn.itcast.jdbc.TxQueryRunner;

public class Teacher_DepartmentDao {
	private QueryRunner qr = new TxQueryRunner();
	//注册默认添加设置为互联网系
	public void add(Teacher_Department form)  {
		try {
			String sql = "insert into teacher_department values(?,?)";
			Object[] params = { form.getTPhone() ,form.getDId() };
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	//更改
	public void updata(Teacher_Department form)
	{
		try {
			String sql="update teacher_department set DId=? where TPhone=?";
			Object[] params={form.getDId(),form.getTPhone()};
			qr.update(sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	//查找
	public Teacher_Department query(String TPhone)
	{
		try {
			String sql="select * from teacher_department where TPhone=?";
			return qr.query(sql,
					new BeanHandler<Teacher_Department>(Teacher_Department.class), TPhone);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	
	}
}
