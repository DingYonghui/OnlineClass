package com.blackdog.android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mori.dao.PartExchangeDao;
import com.mori.domain.PartExchange;

/**
 * 这个类是用于得到课程的所有评论
 * @author blackdog
 *
 */
public class GetPartExchange extends HttpServlet {

	private PartExchangeDao dao = new PartExchangeDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String fk_PId = request.getParameter("fk_PId");
            request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out= response.getWriter();
		
			List<PartExchange> list = dao.findBackListById(fk_PId);
			if(list == null){
				out.print("");
				return;
			}
			JSONObject obj = null;
			JSONArray arr = new JSONArray();
			for(int i = 0;i<list.size();i++){
				 obj = new JSONObject();
				 PartExchange part = list.get(i);
				 String name = part.getPEByWho();
				 String content = part.getPEContent();
				 obj.put("name",name);
				 obj.put("content", content);
				 arr.add(obj);
			}
			out.print(arr.toString());
	}

}
