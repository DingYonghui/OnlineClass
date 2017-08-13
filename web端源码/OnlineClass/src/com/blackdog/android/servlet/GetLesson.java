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

import com.mori.dao.LessonDao;
import com.mori.domain.Lesson;

public class GetLesson extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			LessonDao lessonDao = new LessonDao();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String fk_DId = request.getParameter("fk_DId");
			List<Lesson> lessons = lessonDao.findLessonByDId(fk_DId); 
			if(lessons == null){
				System.out.println("lesson is null");
			}else{
				JSONArray arr = new JSONArray();
				for(Lesson lesson:lessons){
					
					String id = lesson.getPk_LId();
					String imagePath = "http://120.24.5.239:8080/ClassOnline/"+lesson.getLIcon();
					System.out.println("图片url:"+imagePath);
					String teacher = lesson.getLName();
					String content = lesson.getLInfo();
					JSONObject o1 = new JSONObject();
					o1.put("id", id);
					o1.put("imagePath", imagePath);
					o1.put("teacher", teacher);
					o1.put("content", content);
					arr.add(o1.toString());
				}
				System.out.println(arr.toString());
				PrintWriter out= response.getWriter();
				out.print(arr.toString());
			}
	}
}
