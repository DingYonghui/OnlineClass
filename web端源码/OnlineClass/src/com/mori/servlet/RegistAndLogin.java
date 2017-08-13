package com.mori.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mori.Exception.UserException;
import com.mori.domain.AdminUser;
import com.mori.domain.Lesson;
import com.mori.domain.PageFormBean;
import com.mori.domain.StudentUser;
import com.mori.domain.StudentUserInfo;
import com.mori.domain.Student_Lesson;
import com.mori.domain.TeacherUser;
import com.mori.service.AdminService;
import com.mori.service.LessonService;
import com.mori.service.StudentUserService;
import com.mori.service.TeacherService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.itcast.vcode.utils.VerifyCode;
import developPage.CheckKeyForm;
import developPage.CheckNickNameLength;
import developPage.CheckPhoneRegex;

public class RegistAndLogin extends BaseServlet {
	private StudentUserService studentUserService = new StudentUserService();
	private TeacherService teacherService = new TeacherService();
	private AdminService adminService = new AdminService();
	private LessonService ls=new LessonService();
	HashMap<String, String> errors = new HashMap<String, String>();
//loginSuccess
	public String loginAdminSuccess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		//找pass==0的List
		List<TeacherUser> teacherList=teacherService.findUnpass();
		request.setAttribute("unPassTeacherList", teacherList);
		return "f:/manager/manager.jsp";
	}
	public String loginSuccess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		return "f:/teacher/logined.jsp";
	}
	public String loginStudentSuccess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		//查找studentInfo 设置session 
		StudentUser form=(StudentUser)request.getSession().getAttribute("studentUser");
		try {
			StudentUserInfo form1=studentUserService.find(form);
			if(form1==null)
			{
				request.setAttribute("msg", "学生用户个人信息不完整");
				return "f:/index.jsp";
			}
			request.getSession().setAttribute("studentUserInfo", form1);
			String pid=(String)request.getSession().getAttribute("nowPid");
			if(pid==null||pid.trim().isEmpty())
			request.getSession().setAttribute("nowPid", "C00000_1_1");//默认视频
			String lid=(String)request.getSession().getAttribute("nowLid");
			if(lid==null)
				request.getSession().setAttribute("nowLid", "C00000");//默认视频
			String vPath=(String)request.getSession().getAttribute("nowVPath");
			if(vPath==null)
				request.getSession().setAttribute("nowVPath", "");//默认视频

		} catch (UserException e) {
			// TODO Auto-generated catch block
		throw new RuntimeException();
		}
		//查找student_lesson  设置订阅的课程list
		String SId=form.getPk_SPhone();
		List<Student_Lesson> listSL=ls.querySL(SId);
		List<String> listLId=new ArrayList<String>();
		List<Lesson> lessonList=new ArrayList<Lesson>();
		Iterator<Student_Lesson> it=listSL.iterator();
		int i=0;
		while(it.hasNext())
		{
			
			listLId.add(i, it.next().getLId());
			i++;
		}
		i=0;
		Iterator<String> itS=listLId.iterator();
		while(itS.hasNext())
		{
			lessonList.add(i, ls.getLessonById(itS.next()));
			i++;
			//
		}
		request.setAttribute("MyLesson", lessonList);
		//z找回一堆SL  得到一堆LId 查找  返回  封装
		//显示树状
		return "f:/PartExchangeControl?method=loadExchange";
	}
	public String logoutSuccess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		//TeacherUser a=new  TeacherUser();
		//StudentUser b=new StudentUser();
		request.getSession().setAttribute("teacherUser", null);
		request.getSession().setAttribute("studentUser", null);
		  request.getSession().setAttribute("managerUser", null);
		return "f:/index.jsp";
	}
	public String logoutSuccess1(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.getSession().setAttribute("studentUser", null);
		request.getSession().setAttribute("teacherUser", null);
		return "f:/index.jsp";
	}
//	public String unLogin(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException{
//		request.getSession().setAttribute("teacherUser", "");
//		return "f:/index.jsp";
//	}
	public String regist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*
		 * 在点击SUBMIT之前异步校验过了 这里也可以做二次校验 确保健壮性 但是我就不！！ 主要还是把注册信息加入数据库 调用service
		 */

		// 获取表单数据然后封装成bean调用service传入
		PageFormBean Pform = CommonUtils.toBean(request.getParameterMap(),
				PageFormBean.class);// **toBean原理要再去学
		//System.out.print(Pform.toString());// 断点测试
		if ("学生".equals(Pform.getFifthPut() + "")) {
			StudentUser form = new StudentUser();
			form.setPk_SPhone(Pform.getSecondPut() + "");
			form.setSKey(Pform.getFourthPut() + "");
			form.setRegistNickName(Pform.getFirstPut() + "");
			 try {
					 System.out.print(form.toString());
					 studentUserService.regist(form);
					 request.setAttribute("msg", "注册成功");
						return "f:/login.jsp";
					
					 } catch (UserException e) {
					 // TODO Auto-generated catch block
					 request.setAttribute("msg", e.getMessage());
					 request.setAttribute("Pform", Pform);
					 request.setAttribute("rOl", "1");
					 return "f:/login.jsp";
					 }
			
		} else if ("老师".equals(Pform.getFifthPut() + "")) {
				TeacherUser form=new TeacherUser();
				form.setPk_TPhone(Pform.getSecondPut()+"");
				form.setTKey(Pform.getFourthPut()+"");
				form.setTName(Pform.getFirstPut()+"");
				form.setTIsPass(0);//未通过审核 属于审核状态
				try {
					teacherService.regist(form);
					request.setAttribute("msg", "注册审核提交,请留意短信息");
					return "f:/login.jsp";
				} catch (UserException e) {
					// TODO Auto-generated catch block
					 request.setAttribute("msg", e.getMessage());
					 request.setAttribute("Pform", Pform);
					 request.setAttribute("rOl", "1");
					 return "f:/login.jsp";
					
				}
		}

		// //判断权限进行区别处理
		// if("学生".equals(Pform.getFifthPut()+""))
		// {
		//
		// StudentUser form=new StudentUser();
		// form.setPk_SPhone(Pform.getSecondPut()+"");
		// form.setSKey(Pform.getFourthPut()+"");
		// form.setRegistNickName(Pform.getFirstPut()+"");
		//
		// if(errors!=null)
		// {
		// if(errors.size()>0)
		// {
		// request.setAttribute("msg", "请正确填写注册信息");
		// request.setAttribute("Pform", Pform);
		// return "f:/login.jsp";
		// }
		// }
		// try {
		// System.out.print(form.toString());
		// studentUserService.regist(form);
		// request.setAttribute("msg", "注册成功");
		//
		// } catch (UserException e) {
		// // TODO Auto-generated catch block
		// request.setAttribute("msg", e.getMessage());
		// request.setAttribute("Pform", Pform);
		// return "f:/login.jsp";
		// }
		// }
		// else if("教师".equals(Pform.getFifthPut()+""))
		// {
		// //提交直接添加到数据库 但中间要审核 审核过了才允许登陆
		// TeacherUser form=new TeacherUser();
		// form.setPk_TPhone(Pform.getSecondPut()+"");
		// form.setTKey(Pform.getFourthPut()+"");
		// form.setTName(Pform.getFirstPut()+"");
		//
		// }
		return "f:/login.jsp";

		// 获取errors 若有内容 就转发回原页面 带着errors 然return 否则就执行注册 (避免AJAX
		// error标签显示了还能全局访问服务器 )
		// errors = (HashMap<String, String>) request.getAttribute("errors");
		// if (errors == null) {
		// try {
		// studentUserService.regist(form);
		// } catch (UserException e) {
		// // TODO Auto-generated catch block
		// errors.put("sPhoneError", e.getMessage());
		// request.setAttribute("errors", errors);
		// request.setAttribute("form", form);
		// return "f:/regist.jsp";
		// }
		// return "f:/login.jsp";
		// // 成功无异常 跳转到登录
		// } else {
		// if (errors.size() != 0) {
		// // 传入errors form 显示
		// return "f:/regist.jsp";
		// } else {
		// try {
		// studentUserService.regist(form);
		// } catch (UserException e) {
		// // TODO Auto-generated catch block
		// errors.put("u_username", e.getMessage());
		// request.setAttribute("errors", errors);
		// request.setAttribute("form", form);
		// return "f:/regist.jsp";
		// }
		// request.setAttribute("msg", "注册成功 请登陆");
		// return "f:/login.jsp";
		// return "f:/MyJsp1.jsp";
		// // 成功无异常 跳转到登录
		// }

		// }
	}

	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 表单交给AJAX 这里类似regist 负责service 调用login check
		 */
		// System.out.print("loginS");
		// 获取表单数据然后封装成bean调用service传入
		// errors = new HashMap<String, String>();

		PageFormBean Pform = CommonUtils.toBean(request.getParameterMap(),
				PageFormBean.class);// **toBean原理要再去学
		/*
		 * 手动获取字段封装 根据选取的Power 学生 0 1 教师 2 3 admin 4来相应封装不同的form
		 */
		// System.out.print(Pform.toString());
		if ("学生".equals(Pform.getThirdPut().toString()))// 校验学生登录
		{
			StudentUser form = new StudentUser();
			form.setPk_SPhone(Pform.getFirstPut() + "");
			form.setSKey(Pform.getSecondPut() + "");
			try {
				studentUserService.login(form);
			} catch (UserException e) {
				// TODO Auto-generated catch block
				System.out.print("USE E:" + e.getMessage());

				request.setAttribute("msg", e.getMessage());
				request.setAttribute("Pform", Pform);
				return "f:/login.jsp";

				// e.printStackTrace();//用户名密码错误 或者权限不对 e.message
			}

		} else if ("老师".equals(Pform.getThirdPut().toString()))// 校验教师登录
		{

			TeacherUser form = new TeacherUser();
			form.setPk_TPhone(Pform.getFirstPut() + "");
			form.setTKey(Pform.getSecondPut() + "");
			try {
				teacherService.login(form);
			} catch (UserException e) {
				// TODO Auto-generated catch block
				request.setAttribute("msg", e.getMessage());
				request.setAttribute("Pform", Pform);
				return "f:/login.jsp";
			}

		} else if ("管理员".equals(Pform.getThirdPut().toString()))// 校验ADMIN登录
		{
			AdminUser form = new AdminUser();
			form.setPk_ANumber(Pform.getFirstPut() + "");
			form.setAKey(Pform.getSecondPut() + "");
			try {
				adminService.login(form);
			} catch (UserException e) {
				// TODO Auto-generated catch block
				request.setAttribute("msg", e.getMessage());
				request.setAttribute("Pform", Pform);
				return "f:/login.jsp";
			}

		} else {// 选择的权限
			// msg 请选择权限
			request.setAttribute("msg", "请选择权限");
			request.setAttribute("Pform", Pform);
			return "f:/login.jsp";

		}
		//
		return "f:/index.jsp";

		// //手动封装 因为每个用户的用户名字段密码字段不一样 在表单上显示的就不一样 不能像以前直接就一键封装！！！！！！！！！！！！！！！
		// System.out.print(form.toString());
		//
		// HashMap<String,String>
		// errors=(HashMap<String,String>)request.getAttribute("errors");
		// if(errors==null)
		// {
		// try {
		// studentUserService.login(form);
		// } catch (UserException e) {
		// // TODO Auto-generated catch block
		//
		// request.setAttribute("msg", e.getMessage());
		// request.setAttribute("errors", errors);
		// request.setAttribute("form", form);
		// // request.setAttribute("u_sRand", u_sRand);
		//
		// return "f:/login.jsp";
		// }
		// return "f:/MyJsp2.jsp";
		// //成功无异常 跳转到登录
		// }
		// else{
		// if(errors.size()!=0)
		// {
		// //传入errors form 显示
		// return "f:/login.jsp";
		// }
		// else
		// {
		// try {
		// studentUserService.login(form);
		// } catch (UserException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// //成功无异常 跳转到登录
		// }
		//
		// }
		// //根据errors 决定

	}

	/*
	 * 验证码
	 */
	public void loginVCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 
		 */
		System.out.print("加载验证码");
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();
		request.getSession().setAttribute("session_vcode", vc.getText());
		VerifyCode.output(image, response.getOutputStream());
		// System.out.print("loginVCode");

	}

	/*
	 * AJAX异步访问方法---------------------------------------------START
	 * 局部访问无法携带request response转发 所以不要设置errors 直接给json 用js innerHtml
	 */
	public void loginCheckPhone(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 登录
		 */

		// 或者phone表单项参数 sPhone
		String phone = (String) request.getParameter("firstPut_AJAX");
		// System.out.print(phone);
		CheckPhoneRegex cPR = new CheckPhoneRegex();// 自定义检验手机号正则
		boolean checkResult = cPR.check(phone);
		JSONObject json = new JSONObject();
		if (!checkResult) {
			json.put("msg", "手机号格式错误");
			response.getWriter().print(json.toString());
		} else {
			StudentUser stu = studentUserService.checkIsUsed(phone);
			TeacherUser t = teacherService.checkIsUsed(phone);

			if (stu == null && t == null) {
				json.put("msg", "账号不存在");
				System.out.print(json.toString());
				response.getWriter().print(json.toString());

			} else {
				json.put("msg", "√");
				response.getWriter().print(json.toString());
			}

		}

	}

	public void registCheckPhone(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 注册流程 获取表单信息 校验输入账号是否为11位数字 利用session或者attritube存放判断的结果error信息
		 */

		// 或者phone表单项参数 sPhone
		String phone = (String) request.getParameter("Phone_AJAX");
		// System.out.print(phone);
		JSONObject json = new JSONObject();
		if (phone == null || phone.trim().isEmpty()) {
			json.put("msg", "手机号为空？");
			response.getWriter().print(json.toString());
			return;
		} else {
			CheckPhoneRegex cPR = new CheckPhoneRegex();// 自定义检验手机号正则
			boolean checkResult = cPR.check(phone);
			if (!checkResult) {
				json.put("msg", "手机号格式错误");
				response.getWriter().print(json.toString());
			} else {
				StudentUser stu = studentUserService.checkIsUsed(phone);
				TeacherUser t = teacherService.checkIsUsed(phone);

				if (stu == null && t == null) {
					json.put("msg", "√");
					System.out.print(json.toString());
					response.getWriter().print(json.toString());
				} else {
					json.put("msg", "该帐号已被注册过");
					response.getWriter().print(json.toString());
				}

			}
		}

		// //session errors
		// //暂时还没添加Map<String,String>errors =new HashMap<String,String>();
		//
		// if(!checkResult)
		// {
		// HashMap<String,String> errors=(HashMap<String,
		// String>)request.getSession().getAttribute("errors");
		// errors.put("sPhoneError", "请填写正确的手机号格式");
		// request.setAttribute("errors", errors);
		// }
		// else{
		// HashMap<String,String>
		// errors=(HashMap<String,String>)request.getSession().getAttribute("errors");
		// errors.put("sPhoneError", "");
		// request.setAttribute("errors", errors);
		//
		// }
		// 判断是否被注册 调用service 设置session errors

	}

	public void loginCheckKey(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// /*
		// * 注册流程 获取表单信息 校验输入 密码是否符合规则
		// */
		// // 或者key表单项参数 sKey
		String key = (String) request.getParameter("secondPut_AJAX");
		System.out.print(key);
		CheckKeyForm cKF = new CheckKeyForm();// 自定义检验密码号正则
		int result = cKF.check(key);
		JSONObject json = new JSONObject();
		if (result == -1) {

			json.put("msg", "密码格式错误");
			response.getWriter().print(json.toString());
		} else {
			json.put("msg", "格式√");
			response.getWriter().print(json.toString());
		}
		// // 判断result -1则设置error 密码格式不对 0 1 2则显示安全级别
		// if (result == -1) {
		// HashMap<String, String> errors = (HashMap<String, String>) request
		// .getSession().getAttribute("errors");
		// errors.put("sKeyError", "请填写正确的密码格式");
		// request.setAttribute("errors", errors);
		// } else {
		// // 根据格式判定密码强弱 设置新的errors
		// }

	}

	public void registCheckKey(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// /*
		// * 注册流程 获取表单信息 校验输入 密码是否符合规则
		// */
		// // 或者key表单项参数 sKey
		String key = (String) request.getParameter("Key_AJAX");
		String reKey = (String) request.getParameter("ReKey_AJAX");
		JSONObject json = new JSONObject();
		CheckKeyForm cKF = new CheckKeyForm();// 自定义检验密码号正则
		int result;
		if (reKey == null || reKey.trim().isEmpty()) {

			if (key == null || key.trim().isEmpty()) {
				json.put("msg", "密码不能空哟");
				response.getWriter().print(json.toString());
				return;

			}
			result = cKF.check(key);
				if (result == -1) {
	
					json.put("msg", "密码格式错误");
					json.put("twoKey",false );
					response.getWriter().print(json.toString());
					return;
				} else {
					if (result == 0) {
						json.put("msg", "√密码格式等级1");
						//json.put("twoKey",true );
						response.getWriter().print(json.toString());
					} else if (result == 1) {
						json.put("msg", "√密码格式等级2");
						//json.put("twoKey",true );
						response.getWriter().print(json.toString());
					} else {
						json.put("msg", "√密码棒棒的");
						json.put("twoKey",true );
						//response.getWriter().print(json.toString());
	
					}
			}
			
		}
		//
		// // 判断result -1则设置error 密码格式不对 0 1 2则显示安全级别
		// if (result == -1) {
		// HashMap<String, String> errors = (HashMap<String, String>) request
		// .getSession().getAttribute("errors");
		// errors.put("sKeyError", "请填写正确的密码格式");
		// request.setAttribute("errors", errors);
		// } else {
		// // 根据格式判定密码强弱 设置新的errors
		// }
		else {
			if (!key.equals(reKey)) {
				json.put("msg", "密码跟下面的要一样哦");
				json.put("twoKey",false );
				response.getWriter().print(json.toString());

			} else {
				result = cKF.check(key);
				if(result == -1)
				{
					json.put("msg", "密码格式错误");
					json.put("twoKey",false );
					response.getWriter().print(json.toString());
					return;
				}else if (result == 1) {
					json.put("msg", "√密码格式等级2");
					json.put("twoKey",true );
					response.getWriter().print(json.toString());
				} else {
					json.put("msg", "√密码棒棒的");
					json.put("twoKey",true );
					response.getWriter().print(json.toString());

				}
				

			}
			return;

		}
	}

	public void registReCheckKey(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// /*
		// * 注册流程 获取表单信息 校验输入 密码是否符合规则
		// */
		// // 或者key表单项参数 sKey
		String key = (String) request.getParameter("Key_AJAX");
		String reKey = (String) request.getParameter("ReKey_AJAX");
		JSONObject json = new JSONObject();
		if (!key.equals(reKey)) {
			json.put("msg", "密码要跟上面的一样哟");
			json.put("twoKey",false);
			response.getWriter().print(json.toString());
			return;
		}
		//
		if (reKey == null || reKey.trim().isEmpty()) {
			json.put("msg", "密码不能空哟");
			response.getWriter().print(json.toString());
			return;

		}
		CheckKeyForm cKF = new CheckKeyForm();// 自定义检验密码号正则
		int result = cKF.check(reKey);
		if (result == -1) {

			json.put("msg", "密码格式错误");
			response.getWriter().print(json.toString());
			return;
		} else {
			json.put("msg", "√");
			json.put("twoKey",true );
			response.getWriter().print(json.toString());

		}
		// // 判断result -1则设置error 密码格式不对 0 1 2则显示安全级别
		// if (result == -1) {
		// HashMap<String, String> errors = (HashMap<String, String>) request
		// .getSession().getAttribute("errors");
		// errors.put("sKeyError", "请填写正确的密码格式");
		// request.setAttribute("errors", errors);
		// } else {
		// // 根据格式判定密码强弱 设置新的errors
		// }

	}

	public void registCheckMessageCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*
		 * 注册流程
		 */
	}

	public void loginCheckVCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*
		 * 登陆流程 获取表单信息 校验输入 验证码是否符合规则
		 */
		String vcode = (String) request.getSession().getAttribute(
				"session_vcode");
		String fourthPut = request.getParameter("fourthPut_AJAX");
		JSONObject json = new JSONObject();

		if (!vcode.equalsIgnoreCase(fourthPut)) {
			json.put("msg", "验证码错误");
			response.getWriter().print(json.toString());
		} else {
			json.put("msg", "√");
			response.getWriter().print(json.toString());

		}
	}

	public void registCheckName(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		/*
		 * 注册流程 获取表单信息 校验输入 验证码是否符合规则
		 */
		String rName = (String) request.getParameter("Name_AJAX");// 链接关系用parameter
																	// 转发关系用attribute
		JSONObject json = new JSONObject();
		if (rName == null || rName.trim().isEmpty()) {
			json.put("msg", "昵称为空？");
			response.getWriter().print(json.toString());
			return;
		}
		System.out.print(rName);
		CheckNickNameLength cnnL = new CheckNickNameLength();
		boolean result = cnnL.check(rName);
		if (result) {
			json.put("msg", "√");
			response.getWriter().print(json.toString());
		} else {
			json.put("msg", "请输入合理长度的信息");
			response.getWriter().print(json.toString());
		}
	}
	/*
	 * ajax 注册
	 */
	public void registSubmit(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PageFormBean Pform = CommonUtils.toBean(request.getParameterMap(),
				PageFormBean.class);// **toBean原理要再去学
		//System.out.print(Pform.toString()+"??");
		 JSONObject json = new JSONObject();
		 
		 
		 String power=Pform.getFifthPut()+"";
		 if("0".equals(power)){
			 StudentUser form = new StudentUser();
				form.setPk_SPhone(Pform.getSecondPut() + "");
				form.setSKey(Pform.getFourthPut() + "");
				form.setRegistNickName(Pform.getFirstPut() + "");
				
						 System.out.print(form.toString());
						 try {
							studentUserService.regist(form);
							json.put("msg", "注册成功啦");
							  json.put("success", true);
							  response.getWriter().print(json.toString());
								return;
						 } catch (UserException e) {
							// TODO Auto-generated catch block
							 json.put("msg", e.getMessage());
							  json.put("success", false);
							  response.getWriter().print(json.toString());
							  return;
						}
		 }
		 else if("1".equals(power))
		 {
			 TeacherUser form=new TeacherUser();
				form.setPk_TPhone(Pform.getSecondPut()+"");
				form.setTKey(Pform.getFourthPut()+"");
				form.setTName(Pform.getFirstPut()+"");
				form.setTIsPass(0);//未通过审核 属于审核状态
				
					try {
						teacherService.regist(form);
						json.put("msg", "注册完成请等待审核");
						  json.put("success", true);
						  response.getWriter().print(json.toString());
							return;
					} catch (UserException e) {
						// TODO Auto-generated catch block
						 json.put("msg", e.getMessage());
						  json.put("success", false);
						  response.getWriter().print(json.toString());
						  return;
					}
		 }
		 else{
			 json.put("msg","请选择注册类型");
			  json.put("success", false);
			  response.getWriter().print(json.toString());
		 }
		
	}
	/*
	 * 登录AJAX
	 */
	public void loginSubmit(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PageFormBean Pform = CommonUtils.toBean(request.getParameterMap(),
				PageFormBean.class);// **toBean原理要再去学
	
		/*
		 *AJAX要 手动
		 */
		//StudentUser form=new StudentUser();
		String power=Pform.getThirdPut()+"";
		// System.out.print(power);//-1 0 1 2
		 JSONObject json = new JSONObject();
		/*
		 * 登陆校验验证码
		 * 
		 */
			String vcode = (String) request.getSession().getAttribute(
					"session_vcode");
			
			String fourthPut =Pform.getFourthPut()+"";
			System.out.println(fourthPut);
			if (!vcode.equalsIgnoreCase(fourthPut)) {
				json.put("success", false);
				json.put("msg", "验证码错误");
				response.getWriter().print(json.toString());
				return;
			}
		
		 
		 if("0".equals(power))
		 {
			 StudentUser form = new StudentUser();
				form.setPk_SPhone(Pform.getFirstPut() + "");
				form.setSKey(Pform.getSecondPut() + "");
				try {
					studentUserService.login(form);
					  json.put("msg", "登陆成功啦");
					  json.put("success", true);
					  json.put("level", 0);
					  //System.out.print(form.toString());
					  request.getSession().setAttribute("studentUser", form);
					  request.getSession().setAttribute("teacherUser", null);
					  request.getSession().setAttribute("managerUser", null);
					  request.getSession().setAttribute("nowPid", null);//默认视频
					  request.getSession().setAttribute("nowLid", null);//默认视频
					  request.getSession().setAttribute("nowVPath", null);//默认视频
					response.getWriter().print(json.toString());
				} catch (UserException e) {
					// TODO Auto-generated catch block
					json.put("msg", e.getMessage());
					json.put("success", false);
					response.getWriter().print(json.toString());
				}
		 }
		 else if("1".equals(power))
		 {
			 TeacherUser form = new TeacherUser();
				form.setPk_TPhone(Pform.getFirstPut() + "");
				form.setTKey(Pform.getSecondPut() + "");
				
					try {
						form=teacherService.login(form);
						  json.put("msg", "登陆成功啦");
						  json.put("success", true);
						  json.put("level", 1);
						  request.getSession().setAttribute("teacherUser", form);
						  request.getSession().setAttribute("studentUser", null);
						  request.getSession().setAttribute("managerUser", null);
							response.getWriter().print(json.toString());
						  System.out.print(form.toString());
					} catch (UserException e) {
						// TODO Auto-generated catch block
						json.put("msg", e.getMessage());
						json.put("success", false);
						
						response.getWriter().print(json.toString());
					}
		 }
		 else if("2".equals(power))
		 {
			 AdminUser form=new AdminUser();
			 form.setPk_ANumber(Pform.getFirstPut() + "");
			 form.setAKey(Pform.getSecondPut() + "");
			 try {
				form=adminService.login(form);
				json.put("msg", "登陆成功啦");
				  json.put("success", true);
				  json.put("level", 2);
				  request.getSession().setAttribute("managerUser", form);
				  request.getSession().setAttribute("studentUser", null);
				  request.getSession().setAttribute("teacherUser", null);
					response.getWriter().print(json.toString());
			} catch (UserException e) {
				// TODO Auto-generated catch block
				json.put("msg", e.getMessage());
				json.put("success", false);
				
				response.getWriter().print(json.toString());
			}
		 }
		 
//		if ("学生".equals(Pform.getThirdPut().toString()))// 校验学生登录
//		{
//			StudentUser form = new StudentUser();
//			form.setPk_SPhone(Pform.getFirstPut() + "");
//			form.setSKey(Pform.getSecondPut() + "");
//			try {
//				studentUserService.login(form);
//			} catch (UserException e) {
//				// TODO Auto-generated catch block
//				System.out.print("USE E:" + e.getMessage());
//
//				request.setAttribute("msg", e.getMessage());
//				request.setAttribute("Pform", Pform);
//			
//
//				// e.printStackTrace();//用户名密码错误 或者权限不对 e.message
//			}
//
//		} else if ("老师".equals(Pform.getThirdPut().toString()))// 校验教师登录
//		{
//
//			TeacherUser form = new TeacherUser();
//			form.setPk_TPhone(Pform.getFirstPut() + "");
//			form.setTKey(Pform.getSecondPut() + "");
//			try {
//				teacherService.login(form);
//			} catch (UserException e) {
//				// TODO Auto-generated catch block
//				request.setAttribute("msg", e.getMessage());
//				request.setAttribute("Pform", Pform);
//			
//			}
//
//		} else if ("管理员".equals(Pform.getThirdPut().toString()))// 校验ADMIN登录
//		{
//			AdminUser form = new AdminUser();
//			form.setPk_ANumber(Pform.getFirstPut() + "");
//			form.setAKey(Pform.getSecondPut() + "");
//			try {
//				adminService.login(form);
//			} catch (UserException e) {
//				// TODO Auto-generated catch block
//				request.setAttribute("msg", e.getMessage());
//				request.setAttribute("Pform", Pform);
//			
//			}
//
//		} else {// 选择的权限
//			// msg 请选择权限
//			request.setAttribute("msg", "请选择权限");
//			request.setAttribute("Pform", Pform);
//		
//
//		}
//		//
	
	}
	

	/*
	 * AJAX异步访问方法---------------------------------------------END
	 */

}
