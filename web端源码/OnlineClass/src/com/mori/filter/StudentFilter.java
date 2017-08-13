package com.mori.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.mori.domain.StudentUser;
import com.mori.domain.TeacherUser;







public class StudentFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//1，session_user 判断是否为空
		//2.放行
		HttpServletRequest req=(HttpServletRequest)request;
		
		StudentUser student=(StudentUser)req.getSession().getAttribute("studentUser");
		TeacherUser teacher=(TeacherUser)req.getSession().getAttribute("teacherUser");
		if(student!=null||teacher!=null)
		{
			chain.doFilter(request, response);
			return;
		}
		else{
			req.setAttribute("msg", "登陆完再行动吧");
			req.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {
	}

	
	

}
