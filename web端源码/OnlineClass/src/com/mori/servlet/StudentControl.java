package com.mori.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mori.Exception.LessonException;
import com.mori.Exception.StudentException;
import com.mori.domain.Lesson;
import com.mori.domain.StudentUser;
import com.mori.domain.StudentUserInfo;
import com.mori.domain.Student_Lesson;
import com.mori.domain.TeacherUser;
import com.mori.service.LessonService;
import com.mori.service.StudentUserService;
import com.mori.util.MoriExcelStudentUtil;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class StudentControl extends HttpServlet {

	private StudentUserService ss = new StudentUserService();
	private LessonService ls = new LessonService();
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Base设置了编码 这里要手动设置
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 解析JSON
//		System.out.print("A");

		String method = (String) request.getParameter("method");
	//	System.out.print(method);
		if ("addStudent".equals(method)) {
			//this.getLessonTree(request, response);
		} else if ("buildBase".equals(method)) {
			//this.buildBase(request, response);
		}else if("putStudent".equals(method))
		{
			this.putStudent(request,response);
		}
		else if("OutStudent".equals(method))
		{
			this.OutStudent(request,response);
		}
		// 决定执行哪些方法

	}

	
	

	private void OutStudent(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		JSONObject json=new JSONObject();
		MoriExcelStudentUtil mESU=new  MoriExcelStudentUtil();
		String LId=(String)request.getParameter("LId");
		System.out.println("OutStudent:"+LId);
		String path="D://testExcel//"+LId+"_mystudent.xls";
		try {
			mESU.outExcel(LId, path);
			json.put("success", true);
			json.put("msg", "已经将excel导出到"+path);
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("success", false);
			json.put("msg", "空表");
			response.getWriter().print(json.toString());
		};
	}

	/*
	 * 基本
	 */




	
	/*
	 * AJAX START
	 */

	private void putStudent(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		JSONObject json=new JSONObject();
		String[] allData=request.getParameterValues("allData");
		JSONArray data=JSONArray.fromObject(allData);
		//System.out.print(data.toString());
		List<StudentUser> listStudent=new ArrayList<StudentUser>();
		List<Student_Lesson> listSL=new ArrayList<Student_Lesson>();
		List<StudentUserInfo> listSUI=new ArrayList<StudentUserInfo>();
		int len=data.size();
		//System.out.print(len);
		//int listFlag=len/2;
		//学生课程关系---
		String nowClass=(String)request.getParameter("nowClass");//ID
		String LId=(String)request.getParameter("LId");
		String Did=ls.getLessonById(LId).getFk_DId();;
		for(int i=0;i<len;i+=3)
		{
			StudentUser student=new StudentUser();
			//try {
				Student_Lesson sl=new Student_Lesson();
				StudentUserInfo suI=new StudentUserInfo();
				sl.setLId(nowClass);
				sl.setSPhone(data.get(i)+"");
				listSL.add(i/3,sl);
//				ss.linkLesson(sl);
//				System.out.print(sl.toString());
//			} catch (StudentException e) {
//				// TODO Auto-generated catch block
//				json.put("success", false);
//				json.put("msg", e.toString());
//				response.getWriter().print(json.toString());
//				return;
//			}
			student.setPk_SPhone(data.get(i)+"");
			student.setSKey(data.get(i+1)+"");
			student.setPower(0);
			suI.setPk_SPhone(data.get(i)+"");
			suI.setSName(data.get(i+2)+"");
			suI.setSDepartment(Did);
			System.out.println(suI.toString());
			listSUI.add(i/3,suI);
			//System.out.println(student.toString()+"."+i);
			
			listStudent.add(i/3, student);
		}
		//
		ss.batchAdd(listStudent);
		ss.batchLink(listSL);
		ss.batchInfo(listSUI);
		json.put("success", true);
		json.put("msg", "成功啦添加啦");
		response.getWriter().print(json.toString());
	}
	
		
		
	/*
	 * AJAX END
	 */
}
