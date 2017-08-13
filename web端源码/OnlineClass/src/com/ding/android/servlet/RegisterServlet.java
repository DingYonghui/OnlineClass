package com.ding.android.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mori.dao.StudentUserDao;
import com.mori.dao.StudentUserInfoDao;
import com.mori.domain.StudentUser;

public class RegisterServlet extends HttpServlet {

	
	
	/**
	* 用户注册
	*返回值：0：代表注册失败，
	*     -1：代表已经注册过了，
	*     1：代表成功注册
	*/
	private StudentUserDao userDao = new StudentUserDao();
	private StudentUserInfoDao userInfoDao = new StudentUserInfoDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("-----注册请求-----");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		StudentUser user = new StudentUser();
		String pk_SPhone = request.getParameter("pk_SPhone");
		String sKey = request.getParameter("sKey");
		String SDepartment = request.getParameter("SDepartment");
		int power = 0;
		user.setPk_SPhone(pk_SPhone);
		user.setSKey(sKey);
		user.setPower(power);
		System.out.println("账号："+pk_SPhone+"密码："+sKey);
		
		StudentUser student = userDao.findByPk_sPhone(pk_SPhone);
		System.out.println(student);
		if(student != null){
			System.out.println("用户已注册");
			out.write("-1");
		}else{
			try{
				userDao.add(user);
				out.write("1");
				//userInfoDao.add(, pk_SPhone);
			}catch(Exception e){
				out.write("0");
				return;
			}
		}
		
		out.flush();
		out.close();
		
	}

}
