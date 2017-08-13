package com.blackdog.android.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mori.service.LessonService;

public class GetSectionAndPart extends HttpServlet {

private LessonService service = new LessonService();
private final static String IP="120.24.5.239";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			String lessonId = request.getParameter("lessonId");
			JSONArray array = service.returnSectionByLesson(lessonId);
			if(array == null){
				return;
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			JSONArray a = new JSONArray();
			for(int i = 0;i<array.size();i++){
				String fk_id = ((JSONObject)array.get(i)).getString("pk_SId");
				String sectionName = ((JSONObject)array.get(i)).getString("SName");
				JSONArray array2 = service.returnPartBySection(fk_id);
				JSONObject obj = new JSONObject();
				obj.put("pk_SId", fk_id);
				obj.put("SName", sectionName);
				
				JSONArray arr = new JSONArray();
				for(int j=0;j<array2.size();j++){
					JSONObject obj1 = new JSONObject();
					JSONObject obj2 = array2.getJSONObject(j);
					String PName = obj2.getString("PName");
					String PVideoPath = "http://"+IP+":8080/ClassOnline"+obj2.getString("PVideoPath");
					String fk_SId = obj2.getString("fk_SId");
					String pk_PId = obj2.getString("pk_PId");
					obj1.put("pk_PId", pk_PId);
					obj1.put("PName", PName);
					obj1.put("PVideoPath", PVideoPath);
					obj1.put("fk_SId", fk_SId);
					arr.add(obj1);
				}
				obj.put("parts", arr.toString());
				System.out.println(arr.toString());
				a.add(obj.toString());
			}
			out.append(a.toString());
		
	}
}
