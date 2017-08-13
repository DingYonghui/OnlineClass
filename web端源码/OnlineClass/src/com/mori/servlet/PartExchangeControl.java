package com.mori.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
import com.mori.domain.Lesson;
import com.mori.domain.PartExchange;
import com.mori.domain.PartResponse;
import com.mori.domain.StudentUser;
import com.mori.domain.StudentUserInfo;
import com.mori.domain.TeacherUser;
import com.mori.service.ExchangeService;
import com.mori.service.LessonService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class PartExchangeControl extends HttpServlet {

	private ExchangeService es = new ExchangeService();
	
	private LessonService ls=new LessonService();

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
		System.out.print(method);
		if ("addExchange".equals(method)) {
			this.addExchange(request, response);
		} else if ("loadExchange".equals(method)) {
			this.loadExchange(request, response);
		}else if("changePid".equals(method))
		{
			this.changePid(request,response);
		}else if("loadResponse".equals(method))
		{
			this.loadResponse(request,response);
		}else if("addResponse".equals(method))
		{
			this.addResponse(request,response);
		}
		// 决定执行哪些方法
			//returnExchangeByPart
		
	}
	
	private void addResponse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取评论框内容
		String content=(String)request.getParameter("content");
		//处理url编码
		content=new String(content.getBytes("iso-8859-1"),"utf-8");
		String studentName=((StudentUserInfo)request.getSession().getAttribute("studentUserInfo")).getSName()+"";
		PartResponse partR=new PartResponse();
		String PEId=request.getSession().getAttribute("nowPEId")+"";
		partR.setFk_PEId(PEId);
		
		String len=request.getParameter("len")+"";
		int i=Integer.parseInt(len);
		partR.setPk_PRId(PEId+"_"+(i+1));
		partR.setPR_ByWho_Name(studentName);
		String who=request.getParameter("bywho")+"";
		who=new String(who.getBytes("iso-8859-1"),"utf-8");
		partR.setPR_ToWho(who);
		partR.setPRContent(content);
		//获取评论时间
		SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss     ");       
		Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间       
		String   time   =   formatter.format(curDate);  
		
		partR.setPRTime(time);
		System.out.println(partR.toString()+"-------------------");
		try {
			es.addResponse(partR);
		} catch (LessonException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/PartExchangeControl?method=loadResponse&PEId="+PEId).forward(request, response);
			return;
		}
		request.getRequestDispatcher("/PartExchangeControl?method=loadResponse&PEId="+PEId).forward(request, response);
		
	}

	private void loadResponse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//student/discuss.jsp
		String PEId=request.getParameter("PEId")+"";//
		request.getSession().setAttribute("nowPEId", PEId);
		List<PartResponse> list=es.returnResponseByPEIdNoJson(PEId);//回复LIST
		
			request.setAttribute("ResponseNum", list.size());
			request.setAttribute("responseList", list);
		
		String Pid=(String)request.getSession().getAttribute("nowPid");
		//
		String partName=ls.getPart(Pid).getPName();
		request.setAttribute("partName", partName);
		PartExchange pe=es.getExchangeByPEId(PEId);
		request.setAttribute("PE", pe);
		request.getRequestDispatcher("/student/discuss.jsp").forward(request, response);
		
	}

	private void changePid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Pid=(String)request.getParameter("nPid");
		String Path=(String)request.getParameter("nPath");
		System.out.print("--------------"+Pid+"-------------"+Path);
		request.getSession().setAttribute("nowPid", Pid);
		request.getSession().setAttribute("nowVPath", Path);
		request.getRequestDispatcher("/RegistAndLogin?method=loginStudentSuccess").forward(request, response);
	}

	private void addExchange(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取评论框内容
		String content=(String)request.getParameter("content");
		//处理url编码
		content=new String(content.getBytes("iso-8859-1"),"utf-8");
		//获取session中的EName 设置Time  Pid...
		String studentName=((StudentUserInfo)request.getSession().getAttribute("studentUserInfo")).getSName()+"";
		

			//获取评论时间
			SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss     ");       
			Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间       
			String   time   =   formatter.format(curDate);   
			//获取Pid
			String fk_PId=(String)request.getSession().getAttribute("nowPid");
			PartExchange  partE=new PartExchange();
			partE.setFk_PId(fk_PId);
			partE.setPEByWho(studentName);
			partE.setPEContent(content);
			partE.setPETime(time);
			//ExchangeNum
			String len=request.getParameter("len")+"";
			int i=Integer.parseInt(len);
			String Pid=(String)request.getSession().getAttribute("nowPid");
			//PEID
			partE.setPk_PEId(Pid+(i+1));
			try {
				es.addExchange(partE);
			} catch (LessonException e) {
				// TODO Auto-generated catch block
				request.setAttribute("msg", e.getMessage());
				request.getRequestDispatcher("/RegistAndLogin?method=loginStudentSuccess").forward(request, response);
				return;
			}
			request.getRequestDispatcher("/RegistAndLogin?method=loginStudentSuccess").forward(request, response);
		
		
	}

	public void loadExchange(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//PId
//		TeacherUser tU=(TeacherUser)request.getSession().getAttribute("teacherUser");
//		String TId=tU.getPk_TPhone();
		String Pid=(String)request.getSession().getAttribute("nowPid");//由session拿来 当前PId
		System.out.print("Pid:"+Pid);
		List<PartExchange>exchangeList=es.returnExchangeByPartNoJson(Pid);
		
		
		if(exchangeList.size()>=0){
			request.setAttribute("ExchangeNum", exchangeList.size());
			request.setAttribute("partExchangeList", exchangeList);
			request.getRequestDispatcher("/student/mp4.jsp").forward(request, response);
		}
		else
		request.getRequestDispatcher("/student/mp4.jsp").forward(request, response);
		
	}

	
	
	
	

	/*
	 * 基本
	 */

	public void buildBase(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
	}


	
	/*
	 * AJAX START
	 */

	
		
		
	/*
	 * AJAX END
	 */
}
