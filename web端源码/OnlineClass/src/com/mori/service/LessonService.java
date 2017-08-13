package com.mori.service;

import java.util.List;









import org.w3c.dom.ls.LSException;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mori.Exception.LessonException;
import com.mori.Exception.StudentException;
import com.mori.dao.LessonDao;
import com.mori.dao.PartDao;
import com.mori.dao.SectionDao;
import com.mori.dao.Student_LessonDao;
import com.mori.domain.Lesson;
import com.mori.domain.Part;
import com.mori.domain.Section;
import com.mori.domain.Student_Lesson;

public class LessonService {
/*
 * 1.建设课程 增加课程
 * 2.增加章
 * 3。增加节
 * 4.删除，及相应把删除的章  节  关联的东西清理干净  包括存储路径中存储的资源 关联的学生选课关系置空
 * 5.订阅课程 学习课程 建立学生课程关系
 * 
 */
	private Student_LessonDao slD=new Student_LessonDao();
	private SectionDao sd=new SectionDao();
	private LessonDao ld=new LessonDao();
	private PartDao pd=new PartDao();

	/*
	 * 1.添加课程
	 * 		判断课程id是否重复
	 * 		
	 */
	public void addLesson(Lesson form) throws LessonException
	{
		if(ld.findLessonById(form.getPk_LId())!=null)
		{
			throw new LessonException("ID重复,同一个老师又要开同一个课程?老师好精力-0-");//ID用老师的id和课本的id（老师填的）组合起来 这样还重复我也就没话说了
		}
		ld.add(form);
	}
	/*
	 * 
	 */
	public Part getPart(String Pid)
	{
		return pd.findPartById(Pid);
	}
	/*
	 * 2.增加章
	 * 
	 */
	public void addSection(Section form) throws LessonException{
		if(sd.findSectionById(form.getPk_SId())!=null)
		{
			throw new LessonException("同一章什么鬼不要重复把");
		}
		sd.add(form);
	}
	/*
	 * 3.增加节
	 */
	public void addPart(Part form) throws LessonException{
		if(pd.findPartById(form.getPk_PId())!=null)
		{
			throw new LessonException("同一章同一节什么鬼不要重复把");
		}
		pd.add(form);
	}
	/*
	 * 编辑课
	 */
	public void editLesson(Lesson form)
	{
		ld.update(form);
	}
	/*
	 * 删除课  级联 所以一删  全删了 包括学生选课表
	 */
	public void deleteLesson(Lesson form) throws LessonException
	{
		if(ld.findLessonById(form.getPk_LId())==null)throw new LessonException("删除的课程不存在");
		ld.delete(form.getPk_LId());
		//
		
	}
	/*
	 * 编辑章
	 */
	public void editSection(Section form)
	{
		sd.update(form);
	}
	/*
	 * 删除章
	 */
	public void deleteSection(Section form) throws LessonException
	{
		if(sd.findSectionById(form.getPk_SId())==null)throw new LessonException("删除的章不存在");
		sd.delete(form.getPk_SId());
	}
	/*
	 * 编辑节
	 */
	public void editPart(Part form)
	{
		pd.update(form);
	}
	/*
	 * 删除节
	 */
	public void deletePart(Part form) throws LessonException
	{
		if(pd.findPartById(form.getPk_PId())==null)throw new LessonException("删除的节不存在");
		pd.delete(form.getPk_PId());
	}
	/*
	 * 找学生s l
	 */
	public List<Student_Lesson> querySL(String Phone)
	{
		return slD.queryBySId(Phone);
	}
	/*
	 * 找学生s l
	 */
	public JSONArray querySLJSON(String Phone)
	{
		return JSONArray.fromObject(this.querySL(Phone));
	}
	/*
	 * 获取课程基本信息
	 */
	public Lesson getLessonById(String LId)
	{
		
		return ld.findLessonById(LId);
	}
	/*
	 * 订阅课程
	 */
	public void studyOne(Student_Lesson form) throws StudentException
	{
		slD.add(form);
	}
	/*
	 * 取消订阅
	 */
	public void resetStudeny(Student_Lesson form)
	{
		slD.delete(form);
	}
	/*
	 * 获取教师相关课程
	 */
	public List<Lesson> getLessonByTId(String TId)
	{
		List<Lesson> list=ld.findLessonByTId(TId);
		return list;
	}
	/*
	 * 获取系相关课程记录
	 */
	public JSONArray returnLessonByDepartment(String DId)
	{
		List<Lesson> list=ld.findLessonByDId(DId);
		JSONArray json=JSONArray.fromObject(list);
		return json;
	}
	/*
	 * 获取课程相关章
	 */
	public JSONArray returnSectionByLesson(String LId)
	{
		List<Section> list=sd.findSectionByLessonId(LId);
		JSONArray json=JSONArray.fromObject(list);
		return json;
	}
	/*
	 * 获取章相关节
	 */
	public JSONArray returnPartBySection(String SId)
	{
		List<Part> list=pd.findPartBySectionId(SId);
		JSONArray json=JSONArray.fromObject(list);
		return json;
	}
	/*
	 * 封装所有章节课程为一个JSON   2015/11/5 测试成功
	 */
	public JSONObject returnDetailLesson(String LId) throws LessonException
	{
		List<Section> sectionList=sd.findSectionByLessonId(LId);
		if(sectionList==null)
		{
			throw new LessonException("LId不存在");

		}
		if(sectionList.size()<=0)
			throw new LessonException("该课程建设不完整,请编辑建设");

		String [] sId=new String[sectionList.size()];
		//批量查询
		for(int i=0;i<sectionList.size();i++)
		{
			sId[i]=sectionList.get(i).getPk_SId();
			sectionList.get(i).setPartList(pd.findPartBySectionId(sId[i]));
		}
		
		Lesson lesson=ld.findLessonById(LId);
		lesson.setSd(sectionList);
		JSONObject json=JSONObject.fromObject(lesson);
		
		return json;
	}
	/*
	 * 封装所有章节课程为一个JSON   2015/11/5 测试成功
	 */
	public  Lesson returnDetailLesson1(String LId) throws LessonException
	{
		List<Section> sectionList=sd.findSectionByLessonId(LId);
		if(sectionList==null)
		{
			throw new LessonException("LId不存在");

		}
		if(sectionList.size()<=0)
			throw new LessonException("该课程建设不完整");

		String [] sId=new String[sectionList.size()];
		//批量查询
		for(int i=0;i<sectionList.size();i++)
		{
			sId[i]=sectionList.get(i).getPk_SId();
			sectionList.get(i).setPartList(pd.findPartBySectionId(sId[i]));
		}
		
		Lesson lesson=ld.findLessonById(LId);
		lesson.setSd(sectionList);
		
		
		return lesson;
	}	
	public void updatePart(Part form)
	{
		pd.update(form);
	}
	
	
}
