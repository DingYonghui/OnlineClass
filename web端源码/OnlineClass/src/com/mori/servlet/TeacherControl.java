package com.mori.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;

import com.mori.Exception.UserException;
import com.mori.domain.PageFormBean;
import com.mori.domain.TeacherUser;
import com.mori.domain.Teacher_Department;
import com.mori.service.TeacherService;

public class TeacherControl extends HttpServlet {
	TeacherService ts=new TeacherService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Base设置了编码 这里要手动设置
				response.setContentType("text/html;charset=UTF-8");
				request.setCharacterEncoding("UTF-8");
				String method = (String) request.getParameter("method");
				if ("editTeacherInfo".equals(method)) {
					this.editTeacherInfo(request, response);
				}
				else if("loadMyInfo".equals(method))
				{
					this.loadMyInfo(request,response);
				}
				else if("changeDep".equals(method))
				{
					this.changeDep(request,response);
				}
				else if("pass".equals(method))
				{
					this.pass(request,response);
				}
				else if("noPass".equals(method))
				{
					this.noPass(request,response);
				}
			
	}
	
	

	private void noPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tid=(String)request.getParameter("t_id");
		ts.delete(tid);
		request.setAttribute("msg", "审核删除一位用户");
		request.getRequestDispatcher("/RegistAndLogin?method=loginAdminSuccess").forward(request, response);
		
	}


	private void pass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tid=(String)request.getParameter("t_id");
	ts.setIsPass(tid);
	request.setAttribute("msg", "审核成功一位教师");
	request.getRequestDispatcher("/RegistAndLogin?method=loginAdminSuccess").forward(request, response);
	}


	private void changeDep(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置Dep
		Teacher_Department td=new Teacher_Department();
		TeacherUser tu=(TeacherUser)request.getSession().getAttribute("teacherUser");
		String Did=(String)request.getParameter("Did");
		System.out.println(Did);
		td.setDId(Did);
		td.setTPhone(tu.getPk_TPhone());
		ts.updata(td);
		request.setAttribute("msg", "修改成功");
		this.loadMyInfo(request, response);
	}


	private void loadMyInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TeacherUser tu=(TeacherUser)request.getSession().getAttribute("teacherUser");
		String Did=ts.queryTD(tu.getPk_TPhone()).getDId();
		request.setAttribute("Did", Did);
		request.getRequestDispatcher("/teacher/myInfo.jsp").forward(request, response);
	}


	private void editTeacherInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TeacherUser form = CommonUtils.toBean(request.getParameterMap(),
				TeacherUser.class);
		System.out.print(form.toString());
		try {
			ts.updata(form);
			request.getSession().setAttribute("teacherUser", form);
			request.setAttribute("msg", "修改成功");
			request.getRequestDispatcher("/teacher/myInfo.jsp").forward(request, response);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/teacher/myInfo.jsp").forward(request, response);
		}
	}

}
