package com.mori.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import com.mori.domain.Lesson;
import com.mori.domain.Part;
import com.mori.domain.Section;
import com.mori.domain.TeacherUser;
import com.mori.service.LessonService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class LessonControl extends HttpServlet {

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
		System.out.print(method);
		if ("getLessonTree".equals(method)) {
			this.getLessonTree(request, response);
		} else if ("buildBase".equals(method)) {
			this.buildBase(request, response);
		}
		else if("loadBuildClass".equals(method))
		{
			this.loadBuildClass(request, response);
		}
		else if("LoadMyClass".equals(method))
		{
			this.LoadMyClass(request, response);
		}
		else if("returnSectionArray".equals(method))
		{
			this.returnSectionArray(request,response);
		}
		else if("addSection".equals(method))
		{
			this.addSection(request,response);
		}
		else if("LoadPart".equals(method))
		{
			this.LoadPart(request,response);
		}
		else if("loadMyClass".equals(method))
		{
			this.loadMyClass(request, response);
		}
		else if("delectLesson".equals(method))
		{
			this.delectLesson(request,response);
		}
		else if("delectPart".equals(method))
		{
			this.delectPart(request,response);
		}
		else if("delectSection".equals(method))
		{
			this.delectSection(request,response);
		}
		else if("addPart".equals(method))
		{
			this.addPart(request, response);
		}
		else if("loadStudentMyClass".equals(method))
		{
			this.loadStudentMyClass(request, response);
		}
		else if("changeLid".equals(method))
		{
			this.changeLid(request,response);
		}
		else if("changePid".equals(method))
		{
			this.changePid(request,response);
		}
		else if("uploadVideo".equals(method))
		{
			this.uploadVideo(request,response);
		}
			// 决定执行哪些方法
	}
	
	

	private void uploadVideo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DiskFileItemFactory factory = new DiskFileItemFactory();// 工厂
		ServletFileUpload sfu = new ServletFileUpload(factory);// 解析器
		sfu.setFileSizeMax(100 * 1024 * 1024);
		sfu.setSizeMax(100 * 1024 * 1024);
		Part form = (Part)request.getSession().getAttribute("nowPart") ;
		File destFile = null;
		String fileType = null;
		String path = this.getServletContext().getContextPath();
		try {
			List<FileItem> fileItemList =sfu.parseRequest(request);
			FileItem fileItem;
			fileItem=fileItemList.get(0);
			String fileName=fileItem.getName();
			System.out.print(fileName);
			int index=fileName.lastIndexOf(".");
			
			if(index!=-1)
				fileType=fileName.substring(index);
			//文件根路径
			String root=this.getServletContext().getRealPath("/video");//WEB-INF下 防止恶意上传---------------------- 
			//System.out.print(fileName);
			
			
			/*
			 * 处理绝对路径问题
			 */
			int index1=fileName.lastIndexOf("\\");
			if(index1!=-1)
			{
				fileName=fileName.substring(index1+1);
			}

			/*
			 *文件同名
			 */
			String savename=CommonUtils.uuid()+"_"+fileName;
			/*
			 * 哈希
			 */
			int hCode=fileName.hashCode();
			String hex=Integer.toHexString(hCode);
			/*
			 * 前两个字符目录
			 */
			File dirFile;
			try{
			dirFile=new File(root,hex.charAt(0)+"/"+hex.charAt(1));
			/*
			 * c创建目录连
			 */
			dirFile.mkdirs();
			/*
			 * 创建目录
			 */
			destFile=new File(dirFile,savename);
			
			}
			catch(java.lang.StringIndexOutOfBoundsException e)
			{
				request.setAttribute("msg", "什么都不传?");
				request.getRequestDispatcher("/teacher/part.jsp").forward(request, response);
				
				return;//---------------------------调试中遇到不上传东西  就会遇到这个异常 所以处理
			}
			//form.setS_photo(destFile.getPath());//绝对路径 需要转义 还是保存 photos/...dir...savename.. 然后调用${pageContext.request.contextPath}拼接
			/*
			 * 操作保存的相对路径
			 * 拼接
			 */
			StringBuilder relatively_path=new StringBuilder("video");
			
			relatively_path.append("/"+hex.charAt(0)+"/");
			relatively_path.append(hex.charAt(1)+"/"+savename);
			
			form.setPVideoPath("/"+relatively_path.toString());
			//---
			
	
		try {
			fileItem.write(destFile);
		} catch (Exception e) {
			System.out.print("#￥%@%￥#");
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		ls.updatePart(form);
	
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", "上传异常");
			request.getRequestDispatcher("/teacher/part.jsp").forward(request, response);
			return;
		}
		request.setAttribute("msg", "上传 成功啦");
		request.getRequestDispatcher("/teacher/part.jsp").forward(request, response);
	}

	private void changePid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Pid=(String)request.getParameter("nPid");
		//String Path=(String)request.getParameter("nPath");
		//System.out.print("--------------"+Pid+"-------------"+Path);
		request.getSession().setAttribute("nowPid", Pid);
		Part nowPart=ls.getPart(Pid);
		request.getSession().setAttribute("nowPart", nowPart);
		
		//request.getSession().setAttribute("nowVPath", Path);
		request.getRequestDispatcher("/teacher/part.jsp").forward(request, response);
	}

	private void changeLid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Lid=(String)request.getParameter("nLid");
		//System.out.print("aaa"+Lid);
		request.getSession().setAttribute("nowLid", Lid);	
		request.getRequestDispatcher("/RegistAndLogin?method=loginStudentSuccess").forward(request, response);
	}

	/*
	 * 加载MYSTUDENT 的myclass
	 */
	/*
	 * 加载建设课程页面  读取TID相应的课程信息
	 */
	public void loadStudentMyClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//根据session的USER 获取ID 查找所有LESSON记录 返回JAVABEAN 带给request
		TeacherUser tU=(TeacherUser)request.getSession().getAttribute("teacherUser");
		String TId=tU.getPk_TPhone();

		List<Lesson> lessonList=ls.getLessonByTId(TId);
		if(lessonList.size()>=0){
		
			request.setAttribute("MyLesson", lessonList);
			request.getRequestDispatcher("/teacher/myStudent.jsp").forward(request, response);
		}
		else
		request.getRequestDispatcher("/teacher/myStudent.jsp").forward(request, response);
		
	}
	/*
	 * 加载建设课程页面  读取TID相应的课程信息
	 */
	public void loadMyClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//根据session的USER 获取ID 查找所有LESSON记录 返回JAVABEAN 带给request
		TeacherUser tU=(TeacherUser)request.getSession().getAttribute("teacherUser");
		String TId=tU.getPk_TPhone();
		List<Lesson> lessonList=ls.getLessonByTId(TId);
		if(lessonList.size()>=0){
		
			request.setAttribute("MyLesson", lessonList);
			request.getRequestDispatcher("/teacher/test3.jsp").forward(request, response);
		}
		else
		request.getRequestDispatcher("/teacher/test3.jsp").forward(request, response);
		
	}



	

	

	/*
	 * 加载建设课程页面  读取TID相应的课程信息
	 */
	public void loadBuildClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//根据session的USER 获取ID 查找所有LESSON记录 返回JAVABEAN 带给request
		TeacherUser tU=(TeacherUser)request.getSession().getAttribute("teacherUser");
		String TId=tU.getPk_TPhone();
		List<Lesson> lessonList=ls.getLessonByTId(TId);
		if(lessonList.size()>=0)
		{
		request.setAttribute("MyLesson", lessonList);
		//System.out.print(lessonList.get(0).toString());
		request.getRequestDispatcher("/teacher/testclassBuild.jsp").forward(request, response);
	}
		else{
			request.getRequestDispatcher("/teacher/testclassBuild.jsp").forward(request, response);
		}
	}

	/*
	 * 基本课程建设
	 */

	public void buildBase(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.工厂 解析器 解析表单
		 * 
		 * 2.手动补充属性值到bean
		 * 
		 * 3.图片项 上传并保存 文件名 手动添加icon
		 * 
		 * 3.打散目录 保存 顺便将路径保存到bean
		 * 
		 * 4.手动添加各项其他项目到bean
		 * 
		 * 5.表单校验 6.传bean 调用service 7.添加成功 留在本页面 显示msg 成功添加一名学生
		 */
//		
//	String textArea=request.getParameter("textArea")+"";
//	System.out.print(textArea+"------===========");
		DiskFileItemFactory factory = new DiskFileItemFactory();// 工厂
		ServletFileUpload sfu = new ServletFileUpload(factory);// 解析器
		sfu.setFileSizeMax(130 * 1024);
		sfu.setSizeMax(1024 * 1024);
		Lesson form = new Lesson();
		File destFile = null;
		String fileType = null;
		String path = this.getServletContext().getContextPath();
		try {
			List<FileItem> fileItemList =sfu.parseRequest(request);
			FileItem fileItem;
			int  _index=0;
	if(fileItemList.size()==6)
	{
		_index=0;
	}
	else if(fileItemList.size()==5){
		_index=1;
	}
			fileItem=fileItemList.get(5-_index);//问题 就是  选择了lesson id 就可以上传(4123) 不选择(3012) 直接输入id 就不可以
			form.setFk_L_TPhone(fileItem.getString("UTF-8"));//--------------------------
			fileItem=fileItemList.get(1-_index);
			
			
			form.setPk_LId(fileItem.getString("UTF-8"));
			fileItem=fileItemList.get(2-_index);
			
			
			form.setLName(fileItem.getString("UTF-8"));
			fileItem=fileItemList.get(3-_index);
			form.setLInfo(fileItem.getString("UTF-8"));
			fileItem=fileItemList.get(4-_index);
			
			//System.out.print(fileItem.toString()+"2");
			//----------
			//上传文件的后缀
			
			String fileName=fileItem.getName();
			System.out.print(fileName);
			int index=fileName.lastIndexOf(".");
			
			if(index!=-1)
				fileType=fileName.substring(index);
			//文件根路径
			String root=this.getServletContext().getRealPath("/photos");//WEB-INF下 防止恶意上传---------------------- 
			//System.out.print(fileName);
			
			//生成2层目录
				//1.文件名
				//2.hashcode
				//3.16进制
				//4.前两字符当路径
			/*
			 * 处理绝对路径问题
			 */
			int index1=fileName.lastIndexOf("\\");
			if(index1!=-1)
			{
				fileName=fileName.substring(index1+1);
			}
//			File dirFile=new File(root);
//			StringBuilder file_name=new StringBuilder();
//			file_name.append(s_id_record);
//			file_name.append(fileType);
//			destFile=new File(dirFile,file_name.toString());
			//System.out.print(file_name.toString());
			/*
			 *文件同名
			 */
			String savename=CommonUtils.uuid()+"_"+fileName;
			/*
			 * 哈希
			 */
			int hCode=fileName.hashCode();
			String hex=Integer.toHexString(hCode);
			/*
			 * 前两个字符目录
			 */
			File dirFile;
			try{
			dirFile=new File(root,hex.charAt(0)+"/"+hex.charAt(1));
			/*
			 * c创建目录连
			 */
			dirFile.mkdirs();
			/*
			 * 创建目录
			 */
			destFile=new File(dirFile,savename);
			
			}
			catch(java.lang.StringIndexOutOfBoundsException e)
			{
				//request.setAttribute("msg", "什么都不传?");
				//request.getRequestDispatcher("PreServlet?method=preStudentAdd").forward(request, response);
				response.getWriter().print("<script>alert('什么都不传？');</script>");
				return;//---------------------------调试中遇到不上传东西  就会遇到这个异常 所以处理
			}
			//form.setS_photo(destFile.getPath());//绝对路径 需要转义 还是保存 photos/...dir...savename.. 然后调用${pageContext.request.contextPath}拼接
			/*
			 * 操作保存的相对路径
			 * 拼接
			 */
			StringBuilder relatively_path=new StringBuilder("photos");
			
			relatively_path.append("/"+hex.charAt(0)+"/");
			relatively_path.append(hex.charAt(1)+"/"+savename);
			form.setLIcon(relatively_path.toString());
			//---
			
		//查找TPhone对应的Did并设置
		form.setFk_DId("1");//---------------
		System.out.print(form.toString());
		try {
			fileItem.write(destFile);
		} catch (Exception e) {
			System.out.print("#￥%@%￥#");
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	//	form.setFk_DId(fk_DId);
		//form.setLIcon(fileItem);	
			try {
				ls.addLesson(form);
				response.getWriter().print("<script>alert('成功添加一个课程');</script>");
			} catch (LessonException e) {
				// TODO Auto-generated catch block
				response.getWriter().print("<script>alert('"+e.toString()+"');</script>");//
				return;
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			System.out.print("??");
			e.printStackTrace();
		}
	//	request.setAttribute("LessonBuildMsg", "成功添加一个课程");
		//request.getRequestDispatcher("/testclassBuild.jsp").forward(request, response);
		
	}

	
	
	/*
	 * AJAX START-------------------
	 */
	/*
	 * 删除章
	 */
	private void delectSection(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		String LId=(String)request.getParameter("LId");
		
		String selectIndexS=(String)request.getParameter("selectIndexS");
		int inS=Integer.parseInt(selectIndexS);
	Section form =new Section();
	String SId=LId+"_"+Integer.toString(inS);
		form.setPk_SId(SId);
		try {
			ls.deleteSection(form);
			json.put("success", true);
			json.put("msg", "删除成功");
			response.getWriter().print(json.toString());
		} catch (LessonException e) {
			// TODO Auto-generated catch block
			json.put("success", false);
			json.put("msg", e.getMessage());
			response.getWriter().print(json.toString());
		}
	}
	/*
	 * 删除节
	 */
	private void delectPart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		String LId=(String)request.getParameter("LId");
		String selectIndexP=(String)request.getParameter("selectIndexP");
		int inP=Integer.parseInt(selectIndexP);
		String selectIndexS=(String)request.getParameter("selectIndexS");
		int inS=Integer.parseInt(selectIndexS)+1;
	Part form =new Part();
	String PId=LId+"_"+Integer.toString(inS)+"_"+Integer.toString(inP);
		form.setPk_PId(PId);
		//System.out.print("222222222222222222222:"+PId);
		try {
			ls.deletePart(form);
			json.put("success", true);
			json.put("msg", "删除成功");
			response.getWriter().print(json.toString());
		} catch (LessonException e) {
			// TODO Auto-generated catch block
			json.put("success", false);
			json.put("msg", e.getMessage());
			response.getWriter().print(json.toString());
		}
	}
	/*
	 * 删除课程
	 */
		private void delectLesson(HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			// TODO Auto-generated method stub
			JSONObject json = new JSONObject();
			String LId=(String)request.getParameter("LId");
			Lesson form =new Lesson();
			form.setPk_LId(LId);
			try {
				ls.deleteLesson(form);
				json.put("success", true);
				json.put("msg", "删除成功");
				response.getWriter().print(json.toString());
			} catch (LessonException e) {
				// TODO Auto-generated catch block
				json.put("success", false);
				json.put("msg", e.getMessage());
				response.getWriter().print(json.toString());
			}
		}
//
//	/*
//	 * 基本课程建设
//	 */
//
//	public void buildBase(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		/*
//		 * 1.工厂 解析器 解析表单
//		 * 
//		 * 2.手动补充属性值到bean
//		 * 
//		 * 3.图片项 上传并保存 文件名 手动添加icon
//		 * 
//		 * 3.打散目录 保存 顺便将路径保存到bean
//		 * 
//		 * 4.手动添加各项其他项目到bean
//		 * 
//		 * 5.表单校验 6.传bean 调用service 7.添加成功 留在本页面 显示msg 成功添加一名学生
//		 */
//		
//		JSONObject json = new JSONObject();
//		System.out.print("1222");
//		DiskFileItemFactory factory = new DiskFileItemFactory();// 工厂
//		ServletFileUpload sfu = new ServletFileUpload(factory);// 解析器
//		sfu.setFileSizeMax(130 * 1024);
//		sfu.setSizeMax(1024 * 1024);
//		Lesson form = new Lesson();
//		File destFile = null;
//		String fileType = null;
//		String path = this.getServletContext().getContextPath();
//		try {
//			List<FileItem> fileItemList =sfu.parseRequest(request);
//			FileItem fileItem;
//			fileItem=fileItemList.get(0);
//			
//			fileItem=fileItemList.get(2);
//			System.out.print(fileItem.toString()+"2");
//		} catch (FileUploadException e) {
//			// TODO Auto-generated catch block
//			System.out.print("??");
//			e.printStackTrace();
//		}
//		json.put("success", true);
//		json.put("msg","生成课程记录完成");
//		response.getWriter().print(json.toString());
//	}
//

	/*
	 * 树状
	 */

	public void getLessonTree(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 最后返回JSON 包含课程 课程里面包含LIST 章 章又包含节
		// 由于JS写了JSON DATA 接收服务器print的数据 所以必须print json！！！

		JSONObject json = new JSONObject();
		// Lesson lesson = new Lesson();
		// 调用服务 获取tree
		try {
			String LId=(String)request.getParameter("selectClass");
			System.out.print(LId);
			json = ls.returnDetailLesson(LId);
			System.out.print(json.toString());
			// lesson = ls.returnDetailLesson1("C3069");
			json.put("msg", "课程框架如下");
			json.put("success", true);
			response.getWriter().print(json.toString());
			System.out.print(json.toString());
			// request.setAttribute("lesson", lesson);//AJAX不能带着request 转发
			// 所以就别用el 和jstl了 单纯AJAX传输数据然后用JS就行了！！！！！！

		} catch (LessonException e) {
			// TODO Auto-generated catch block
			json.put("success", false);
			json.put("msg", e.getMessage());
			response.getWriter().print(json.toString());
			System.out.print(json.toString());
			return;
		}
		// request.setAttribute("", o);

		// System.out.print("{"+"\""+"name"+"\""+":1,"+"\""+"age"+"\""+":2}");
	}
	
	//
	private void returnSectionArray(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		JSONObject json=new JSONObject();
		String LId=(String)request.getParameter("LId");
		List<Section> sectionList=ls.returnSectionByLesson(LId);
		JSONArray sectionJson=JSONArray.fromObject(sectionList);
		System.out.println(sectionList.toString());
		if(sectionList.size()<=0)
		{
			json.put("success", false);
			json.put("msg", "该课程建设不完整");
			response.getWriter().print(json.toString());	
		}
		else
		{
			
			json.put("success", true);
			json.put("sectionList", sectionList);
			response.getWriter().print(json.toString());
		}
//		json.put("success", true);
//		json.put("msg", "0.0");
//		response.getWriter().print(json.toString());
	}
	//
	private void LoadMyClass(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
	
		JSONObject json=new JSONObject();
		String LId=(String)request.getParameter("LId");
		Lesson lesson=ls.getLessonById(LId);
		//JSONObject jsonlesson=JSONObject.fromObject(lesson);
		if(lesson==null)
		{
			json.put("success", false);
			json.put("msg", "课程还未建设,请新建");
			response.getWriter().print(json.toString());
			System.out.print(json.toString());
			return;
		}
		else{
			json.put("success", true);
			json.put("lessonLoaded", lesson);
			response.getWriter().print(json.toString());
			
			System.out.println(json.toString());
		//	System.out.print(jsonlesson.toString());
			return;
		}
	}
	/*
	 * LoadPart
	 */
	private void LoadPart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		JSONObject json=new JSONObject();
		String selectIndex=(String)request.getParameter("selectIndex");
		int index=Integer.parseInt(selectIndex)+1;
		String LId=(String)request.getParameter("LId");
		String SId=LId+"_"+Integer.toString(index);
		List<Part>partList=ls.returnPartBySection(SId);
		if(partList.size()<=0)
		{
			json.put("success", false);
			json.put("msg", "章节建设不完整");
			response.getWriter().print(json.toString());
		}
		else{
			json.put("success", true);
			json.put("partList", partList);
			response.getWriter().print(json.toString());
			
		}
	}
	/*
	 * 更新章
	 */
	private void changeSection(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		JSONObject json=new JSONObject();
		String selectIndex=(String)request.getParameter("selectIndex");
		int index=Integer.parseInt(selectIndex)+1;
		String LId=(String)request.getParameter("LId");
		String SId=LId+"_"+Integer.toString(index);
		//找到ＳＩd相关的记录　更改具体的项目　然后updata
		
			}
	/*
	 *添加jie
	 */
	private void addPart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		JSONObject json=new JSONObject();
		String PName=(String)request.getParameter("PName");
		String PId=(String)request.getParameter("PId");
		String SId=(String)request.getParameter("SId");
		Part form=new Part();
		form.setPk_PId(PId);
		form.setPName(PName);
		form.setFk_SId(SId);
		System.out.print(form.toString());
		try {
			ls.addPart(form);
			json.put("success", true);
			json.put("msg", form);
			response.getWriter().print(json.toString());

		} catch (LessonException e) {
			// TODO Auto-generated catch block
			json.put("success", false);
			json.put("msg", e.getMessage());
			response.getWriter().print(json.toString());

		}
			}
	/*
	 *添加课章
	 */
	private void addSection(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		JSONObject json=new JSONObject();
		String SName=(String)request.getParameter("SName");
		String SId=(String)request.getParameter("SId");
		String LId=(String)request.getParameter("LId");
		Section form=new Section();
		form.setPk_SId(SId);
		form.setSName(SName);
		form.setFk_LId(LId);
		System.out.print(form.toString());
		try {
			ls.addSection(form);
			json.put("success", true);
			json.put("msg", form);
			response.getWriter().print(json.toString());

		} catch (LessonException e) {
			// TODO Auto-generated catch block
			json.put("success", false);
			json.put("msg", e.getMessage());
			response.getWriter().print(json.toString());

		}
			}
	
	
	/*
	 * AJAX END
	 */
}
