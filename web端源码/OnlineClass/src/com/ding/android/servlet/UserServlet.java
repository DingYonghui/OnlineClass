package com.ding.android.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mori.dao.StudentUserInfoDao;
import com.mori.domain.StudentUserInfo;
import com.mori.service.StudentUserService;

public class UserServlet extends HttpServlet {

	StudentUserInfoDao dao = new StudentUserInfoDao();
	StudentUserService service = new StudentUserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String status = request.getParameter("status");
		String pk_SPhone = request.getParameter("pk_SPhone");
		
		System.out.println("发送过来的指令："+status);
		System.out.println("用户手机号为："+pk_SPhone);
		
		if(status.equals("getStudentInfo")){//获得学生信息
			
			StudentUserInfo userInfo = dao.findById(pk_SPhone);
			
			/**
			 * 用map封装，为了以后方便修改、维护
			 */
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "userInfo");
			map.put("boolean", "1");
			map.put("data", userInfo);
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			out.write(jsonObject.toString());
			
			System.out.print(jsonObject.toString());
		}
		else if(status.equals("completeUserInformation")){//修改学生信息
			
			System.out.println("-----修改完善用户信息请求-----");
			
			StudentUserInfo form = new StudentUserInfo();

			
			if(request.getParameter("pk_SPhone")!=null)
			  form.setPk_SPhone(request.getParameter("pk_SPhone"));
			
//			if(request.getParameter("SNo")!=null)
//			  form.setSNo(request.getParameter("SNo"));
			
			
			if(request.getParameter("SName")!=null)
			  form.setSName(request.getParameter("SName"));
			System.out.println(form);
			
//			if(request.getParameter("SHeadIcon")!=null)
//			form.setSHeadIcon(request.getParameter("SHeadIcon"));
			//form.setSAge(Integer.parseInt(request.getParameter("SAge")));
			//form.setSGender(Integer.parseInt(request.getParameter("SGender")));
			
//			if(request.getParameter("SNickName")!=null)
//			form.setSNickName(request.getParameter("SNickName"));
			
			if(request.getParameter("SSchool")!=null)
			   form.setSSchool(request.getParameter("SSchool"));
			
			if(request.getParameter("SDepartment")!=null)
			   form.setSDepartment(request.getParameter("SDepartment"));
			
			if(request.getParameter("SMajor")!=null)
			   form.setSMajor(request.getParameter("SMajor"));
			
			if(request.getParameter("SClass")!=null)
			   form.setSClass(request.getParameter("SClass"));
			
			if(request.getParameter("SGender")!=null)
				   form.setSGender(Integer.parseInt(request.getParameter("SGender")));
//			
//			if(request.getParameter("SDefaultPhone")!=null)
//			form.setSDefaultPhone(request.getParameter("SDefaultPhone"));
//			
//			if(request.getParameter("SEmail")!=null)
//			form.setSEmail(request.getParameter("SEmail"));
//			
//			if(request.getParameter("SRegistTime")!=null)
//			form.setSRegistTime(request.getParameter("SRegistTime"));
			
			
			System.out.print("-----form完成初始化-----");
			System.out.println(form);
			
			
			
			try{
				dao.update(form);
				out.write("1");
			}catch (Exception e) {
				// TODO: handle exception
				out.write("0");
			}
			
		}
		else if(status.equals("getMyLessons")){//获得学生信息
			
            JSONArray studentLessons = service.studentLessonList(pk_SPhone);
			
//			/**
//			 * 用map封装，为了以后方便修改、维护
//			 */
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("name", "studentLessons");
//			map.put("boolean", "1");
//			map.put("data", studentLessons.toString());
//			
//			JSONObject jsonObject = JSONObject.fromObject(map);
//			out.write(jsonObject.toString());
            out.write(studentLessons.toString());
			System.out.print("我的课程为："+studentLessons.toString());
			
		}
	
		out.flush();
		out.close();
		
	}

	/**
     * 通过is解析读取网络数据
     *
     * @param is
     * @return
     */
    private String readStream(InputStream is) {
        InputStreamReader isr;
        String result = "";
        String line = "";
        try {
            //字节流转化为字符流
            isr = new InputStreamReader(is, "utf-8");
            //缓冲流
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result = result + line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
	
}
