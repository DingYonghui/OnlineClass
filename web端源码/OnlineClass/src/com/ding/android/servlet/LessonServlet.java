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
import com.mori.dao.LessonDao;
import com.mori.domain.Lesson;


/**
 * 根据系别获取所有课程
 * @author jack
 *
 */
public class LessonServlet extends HttpServlet {

	LessonDao lessonDao = new LessonDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("-----doPost-----");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		PrintWriter out = response.getWriter();
		
		String fk_DId = request.getParameter("pk_DId");
		System.out.println("发送过来的系别id为"+fk_DId);
		List<Lesson> temp  = lessonDao.findLessonByDId(fk_DId); 
		List<Lesson> lessons  = new ArrayList<Lesson>();
		
		for(int i = 0 ;i<temp.size();i++){
			Lesson lesson  = new Lesson();
			lesson.setPk_LId(temp.get(i).getPk_LId());
			lesson.setFk_DId(temp.get(i).getFk_DId());
			lesson.setLName(temp.get(i).getLName());
			lesson.setLCount(temp.get(i).getLCount());
			lesson.setLIcon(temp.get(i).getLIcon());
		    lessons.add(lesson);
		}
		
		System.out.println(lessons);
	
		/**
		 * 用map封装，为了以后方便修改、维护
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "lessons");
		map.put("boolean", "1");
		map.put("data", lessons);
		 
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		out.write(jsonObject.toString());
		
		System.out.print(jsonObject.toString());
		
		out.flush();
		out.close();
	}
}
