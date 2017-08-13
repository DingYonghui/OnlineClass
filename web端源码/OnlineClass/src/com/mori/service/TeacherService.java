package com.mori.service;

import java.util.List;

import com.mori.Exception.UserException;
import com.mori.dao.TeacherUserDao;
import com.mori.dao.Teacher_DepartmentDao;
import com.mori.domain.StudentUser;
import com.mori.domain.TeacherUser;
import com.mori.domain.Teacher_Department;

public class TeacherService {
	/*
	 * 这些和学生 ADMIN一起放在USER服务1.注册2.登录3.批量增加
	 */
	TeacherUserDao tUD = new TeacherUserDao();
	Teacher_DepartmentDao tdD=new Teacher_DepartmentDao();

	/*
	 * 登陆
	 */
	public TeacherUser login(TeacherUser form) throws UserException {
		// TODO Auto-generated method stub
		TeacherUser teacher = tUD.checkTeacherUser(form);
		if (teacher == null)

			throw new UserException("帐号密码错误或者身份不符合权限");

		else {
			// if(teacher.getPower()!=2&&teacher.getPower()!=3)
			// throw new UserException("身份不符合权限");
			// else
			if(teacher.getTIsPass()!=1)
			{
				System.out.print(teacher.toString()+"");

				throw new UserException("该用户正在审核中，请联系管理员");
			}
			else
			return teacher;
		}
	}
	/*
	 * 注册审核
	 */
	public void regist(TeacherUser form) throws UserException
	{
		//注册 添加到数据库 然而审核状态为0  登录的时候还要检验是否为1  在管理员系统里审核
		TeacherUser t=tUD.findByPk_sPhone(form.getPk_TPhone());
		if(t!=null)throw new UserException("帐号被注册");
		tUD.add(form);
		 Teacher_Department td=new Teacher_Department();
		 td.setTPhone(form.getPk_TPhone());
		 td.setDId("0");//默认互联网
		tdD.add(td);
	}
	public void updata(TeacherUser form) throws UserException{
		TeacherUser t=tUD.checkTeacherUser(form);
		if(t==null)throw new UserException("请确认是你的账号？");
		
	}
	public void updata(Teacher_Department form)
	{
		
		tdD.updata(form);
	}
	public Teacher_Department queryTD(String TPhone)
	{
		return tdD.query(TPhone);
	}
	/*
	 * AJAX
	 */
	public TeacherUser checkIsUsed(String TPhone)
	{
		return tUD.findByPk_sPhone(TPhone);
	}
	public List<TeacherUser> findUnpass() {
		// TODO Auto-generated method stub
		return tUD.findUnPass();
	}
	public void setIsPass(String TPhone)
	{
		tUD.Pass(TPhone);
	}
	public void delete(String TPhone)
	{
		tUD.delete(TPhone);
	}

	
}
