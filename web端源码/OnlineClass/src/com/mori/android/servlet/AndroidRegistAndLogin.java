package com.mori.android.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mori.Exception.UserException;
import com.mori.domain.StudentUser;
import com.mori.service.StudentUserService;

public class AndroidRegistAndLogin extends HttpServlet {
	private StudentUserService studentUserService=new StudentUserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doPost(request, response);
	}
	//决定执行哪些方法
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Base设置了编码 这里要手动设置
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String method = (String) request.getParameter("method");
		
		if ("regist".equals(method)) {
			this.regist(request, response);
		}
		else if("login".equals(method))
		{
			this.login(request, response);
		}
		else if("".equals(method))
		{
			
		}
		
	
		
	}
	/*
	 * 安卓注册 获取安卓request的参数 封装bean 调用service 再用json 处理
	 */
	public void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		/*
		 * 1.手动封装bean
		 * 2.调用注册  
		 * 3.异常则再处理
		 */
	}
	/*
	 * 安卓登录 获取安卓request的参数 封装bean 调用service 再用json 处理
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		
		try {//最好在service 里返回json
			StudentUser form=new StudentUser();
			form.setPk_SPhone(request.getParameter("pk_SPhone"));
			form.setSKey(request.getParameter("SKey"));
			form=studentUserService.login(form);
			JSONObject json=JSONObject.fromObject(form);
			response.getWriter().write(json.toString());//给APP这个用户对象 用json带
		} catch (UserException e) {
			// TODO Auto-generated catch block
			response.getWriter().write("false");//用户名密码错误  APP获取空JSON  在APP解析 有异常则不跳转  在登录界面提示msg
		}
	}
}
