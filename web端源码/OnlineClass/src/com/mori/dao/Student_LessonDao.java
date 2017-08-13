package com.mori.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mori.Exception.StudentException;
import com.mori.domain.Student_Lesson;

import cn.itcast.jdbc.TxQueryRunner;

import com.mori.domain.StudentUser;

public class Student_LessonDao {
	/*
	 * 课程老是去导入学生或者学生学习课程就需要增加一次对应关系
	 */
	private QueryRunner qr = new TxQueryRunner();
	/*
	 * 
	 */
	public List<Student_Lesson> queryBySId(String Sphone)
	{
		try {
			String sql="select * from student_lesson where SPhone=?";
			return qr.query(sql, new BeanListHandler<Student_Lesson>(Student_Lesson.class),Sphone);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/*
	 * 订阅（学习）课程则调用添加 check
	 */
	public void add(Student_Lesson form) throws StudentException {
		try {
			String sql = "insert into student_lesson values(?,?)";
			Object[] params = { form.getLId(), form.getSPhone() };
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new StudentException("订阅异常");
		}
	}

	/*
	 * 删除 check
	 */
	public void delete(Student_Lesson form) {
		try {
			String sql = "delete from student_lesson where LId=? and SPhone=?";
			Object[] params = { form.getLId(), form.getSPhone() };
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 批量前获取查找到的重复的帐号   之后添加lesson_student关联
	 */
	public void batchAdd(String LId,List<StudentUser> studentForm)
	{

		try {
			String sql = "insert into student_lesson values(?,?)";
			
			Object[][] params=new Object[studentForm.size()][2];
			for(int i=0;i<studentForm.size();i++)
			{
				params[i][0]=LId;
				params[i][1]=studentForm.get(i).getPk_SPhone();
				
				
			}
			qr.batch(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public void batchLink(List<Student_Lesson> formList) throws StudentException {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into student_lesson values(?,?)";

			Object[][] params = new Object[formList.size()][2];
			for (int i = 0; i < formList.size(); i++) {
				params[i][0] = formList.get(i).getLId();
				params[i][1] = formList.get(i).getSPhone();
				
				for(int j=0;j<params[i].length;j++)
				{
					System.out.print(params[i][j]);
				}
			}
			
			//System.out.print("zzzzzzzzzzzz");
			qr.batch(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new StudentException("BATCH异常");
		}
	}
	/*
	 * 
	 */
	public List<Student_Lesson> querySId(String LId)
	{
		try {
			String sql="select * from student_lesson where LId=?";
			return qr.query(sql, new BeanListHandler<Student_Lesson>(Student_Lesson.class),LId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
