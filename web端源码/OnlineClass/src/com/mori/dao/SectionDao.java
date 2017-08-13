package com.mori.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mori.domain.Lesson;
import com.mori.domain.Section;

import cn.itcast.jdbc.TxQueryRunner;


public class SectionDao {
	private QueryRunner qr = new TxQueryRunner();

	/*
	 * 添加章 check
	 */
	public void add(Section form) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into section values(?,?,?,?,?,?,?)";
			Object[] params = { form.getPk_SId(), form.getFk_LId(),
					form.getSName(), form.getSInfo(), form.getSCount(),
					form.getSIcon(),form.getSTime()

			};

			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * 删除章 check
	 */
	public void delete(String id) {

		try {
			String sql = "delete from section where pk_SId=?";
			Object[] params = { id };
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

//	/*
//	 * 搜索章 
//	 */
//	public List<Section> query(String criteriaLessonName) {
//
//		try {
//			String sql = "select * from lesson where LName like ?";
//			return qr.query(sql, new BeanListHandler<Section>(Section.class),
//					"%"+criteriaLessonName+"%");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			throw new RuntimeException();
//		}
//
//	}
	/*
	 * 更新课程  相应记得更新章节什么鬼的  check*
	 */
	public void update(Section form)
	{
		try {
			String sql = "update section set pk_SId=?,fk_LId=?,SName=?,SInfo=?"
					+ ",SCount=?,SIcon=?,STime=?";
			Object[] params = {form.getPk_SId(),form.getFk_LId(),form.getSName(),form.getSInfo()
					,form.getSCount(),form.getSIcon(),form.getSTime()};
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过id找Section check?
	 */
	public Section findSectionById(String SId)
	{
		try{
			String sql="select * from section where pk_SId=?";
			return qr.query(sql, new BeanHandler<Section>(Section.class),SId);
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过Lid 找一堆Section CHECK*
	 */
	public List<Section> findSectionByLessonId(String LId)
	{
		try {
			String sql="select * from section where fk_LId=?";
			return qr.query(sql, new BeanListHandler<Section>(Section.class),LId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 批处理
	 */


	
}
