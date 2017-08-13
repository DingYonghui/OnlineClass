package com.mori.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mori.Exception.StudentException;
import com.mori.Exception.UserException;
import com.mori.dao.LessonDao;
import com.mori.dao.StudentUserDao;
import com.mori.dao.StudentUserInfoDao;
import com.mori.dao.Student_LessonDao;
import com.mori.domain.ExcelStudent;
import com.mori.domain.Lesson;
import com.mori.domain.StudentUser;
import com.mori.domain.StudentUserInfo;
import com.mori.domain.Student_Lesson;

import developPage.CheckKeyForm;
import developPage.CheckNickNameLength;

public class StudentUserService {
	StudentUserDao sUD = new StudentUserDao();
	StudentUserInfoDao sUID = new StudentUserInfoDao();
	Student_LessonDao sl=new Student_LessonDao();
	private LessonDao ld=new LessonDao();
	/*
	 *1.注册
	 *2.登录
	 *3.批量增加 控制器中 提供input  要添加几个人  然后就foreach给这么多数量的input  然后全部添加的form list 传进来
	 *4.添加个人信息 （最好还是检验sno 和姓名是否真实相符  借助教务系统 内部访问 获取  **这里待最后完善）
	 */
	
	/*
	 * 注册 1.添加账户 2.添加用户信息
	 */
	public void regist(StudentUser form) throws UserException{
		if(sUD.findByPk_sPhone(form.getPk_SPhone())!=null)throw new UserException("帐号被注册");
		
		CheckKeyForm ckF=new CheckKeyForm();
		if(ckF.check(form.getSKey())==-1)throw new UserException("密码格式哇？");
		sUD.add(form);
		
		CheckNickNameLength cnnL=new CheckNickNameLength();
		if(!cnnL.check(form.getRegistNickName()))throw new UserException("死活要乱起名啊");
		sUID.add(form.getRegistNickName(), form.getPk_SPhone());
	}
	/*
	 * 查找相关个人信息
	 */
	public StudentUserInfo find(StudentUser form) throws UserException
	{
		if(form==null)throw new UserException("空的STUDENT");
		
		return sUID.findById(form.getPk_SPhone());
	}
	/*
	 * 登陆
	 */
	public StudentUser login(StudentUser form) throws UserException {
		// TODO Auto-generated method stub
		StudentUser student = sUD.checkStudentUser(form);
		if (student == null)

			throw new UserException("帐号密码错误或者身份不符合权限");
		else {
	//	if(student.getPower()!=1&&student.getPower()!=0)
				//throw new UserException("身份不符合权限");
//			else  都在这个学生表里查了 = =  不可能不符合权限
			return student;
		}
			}
	/*
	 * 
	 */
	public void batchAdd(List<StudentUser> list) 
	{
		//批处理
		try {
			sUD.batchAdd(list);
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//出错则再循环查询 把已注册的用户集合起来 更新订阅字段  继续批处理  备注  去详细信息加一个订阅字段 外键 课程
		}
		
		
		//
	}
	public void linkLesson(Student_Lesson form) throws StudentException//addLS
	{
		sl.add(form);
	}
	/*
	 * 用户名 密码 姓名 拼接成LIST
	 */ 
	public List<ExcelStudent> returnExcel(String LId)
	{
		List<Student_Lesson> sL=sl.querySId(LId);
		StudentUser sU;
		StudentUserInfo sI;
		ExcelStudent ES;
		List<ExcelStudent> eS=new ArrayList<ExcelStudent>();
		String SId;
		for(int i=0;i<sL.size();i++)
		{
			sI=new StudentUserInfo();
			sU=new StudentUser();
			ES=new ExcelStudent();
			SId= sL.get(i).getSPhone();
			sU=sUD.findByPk_sPhone(SId)	;
			sI=sUID.findById(SId);
			ES.setPk_SPhone(sU.getPk_SPhone());
			ES.setSKey(sU.getSKey());
			if(sI!=null)
			ES.setSName(sI.getSName());
			else
				ES.setSName("默认姓名");
			System.out.print(ES.toString());
			eS.add(i, ES);
		}
		return eS;
		
	}
	/*
	 * AJAX
	 */
	public StudentUser checkIsUsed(String SPhone)
	{
		return sUD.findByPk_sPhone(SPhone);
	}

	public void batchLink(List<Student_Lesson> listSL) {
		// TODO Auto-generated method stub
		try {
			sl.batchLink(listSL);
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}

	public void batchInfo(List<StudentUserInfo> listSUI) {
		// TODO Auto-generated method stub
		try {
			sUID.batchAdd(listSUI);
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
	//-------------!!!!!!!!!!!!!!!!!!!!!!!!!
	public JSONArray studentLessonList(String SId)
	{
		//1找到sl中的一堆 返回一堆LID
		List<Student_Lesson> LidList=sl.queryBySId(SId);
		//System.out.print(b);
		Iterator<Student_Lesson> it=LidList.iterator();
		
		List<Lesson> lessonList=new ArrayList<Lesson>();
		int i=0;
		while(it.hasNext())
		{
			//System.out.println(ld.findLessonById(it.next().getLId()).toString());
			lessonList.add(i, ld.findLessonById(it.next().getLId()));//id
			//System.out.print("---"+i);
		i++;
		}
		JSONArray json=JSONArray.fromObject(lessonList);
		
		return json;
	}
}
