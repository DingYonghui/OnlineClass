package com.ding.android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.ding.android.consts.CONSTS;
import com.mori.dao.StudentUserDao;
import com.mori.domain.Lesson;
import com.mori.domain.StudentUser;

public class LoginServlet extends HttpServlet {
	
	StudentUserDao dao = new StudentUserDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("-----登录请求-----");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		StudentUser user = new StudentUser();
		String pk_SPhone = request.getParameter("pk_SPhone");
		String SKey = request.getParameter("sKey");
		user.setPk_SPhone(pk_SPhone);
		user.setSKey(SKey);
		System.out.println("账号："+pk_SPhone+"密码："+SKey);
		
		StudentUser user1 = dao.checkStudentUser(user);

		if(user1 == null){
			out.write("0");
		}else{
			out.write("1");
		}

		out.flush();
		out.close();
		
	}

}
