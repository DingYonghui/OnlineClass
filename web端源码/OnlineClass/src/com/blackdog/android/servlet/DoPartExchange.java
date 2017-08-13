package com.blackdog.android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mori.dao.PartExchangeDao;
import com.mori.domain.PartExchange;

public class DoPartExchange extends HttpServlet {

	private PartExchangeDao dao = new PartExchangeDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

            request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String name = request.getParameter("name");
			String content = request.getParameter("content");
			String fk_PId = request.getParameter("fk_PId");
			
			System.out.print("数据库需要保存的讨论："+content);
			
			PartExchange partExchange = new PartExchange();
			partExchange.setFk_PId(fk_PId);
			partExchange.setPEContent(content);
			partExchange.setPEByWho(name);
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat();
			String id = format.format(date);
			partExchange.setPk_PEId(id);
			dao.add(partExchange);
	}

}
