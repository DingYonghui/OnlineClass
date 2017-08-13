package com.mori.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mori.domain.Lesson;
import com.mori.domain.PageBean;





import cn.itcast.jdbc.TxQueryRunner;

public class LessonDao {
	//以后还有课程历史记录表  加一个字段 修改或者添加时间 每修改一次就在历史记录加一次******
	private QueryRunner qr = new TxQueryRunner();

	/*
	 * 添加课程  check*
	 */
	public void add(Lesson form) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into lesson values(?,?,?,?,?,?,?)";
			Object[] params = { form.getPk_LId(), form.getLName(),
					form.getLInfo(), form.getLIcon(), form.getFk_L_TPhone(),
					form.getLCount(),form.getFk_DId()

			};

			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * 删除课程 check 
	 */
	public void delete(String id) {

		try {
			String sql = "delete from lesson where pk_LId=?";
			Object[] params = { id };
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

	/*
	 * 搜索课程 条件就是课程名 不翻页版本   *check
	 */
	public List<Lesson> query(String criteriaLessonName) {

		try {
			String sql = "select * from lesson where LName like ?";
			return qr.query(sql, new BeanListHandler<Lesson>(Lesson.class),
					"%"+criteriaLessonName+"%");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}

	}
	/*
	 * 翻页版本  check 
	 */
	public PageBean<Lesson> findBackPgByName(int pc, int ps,String LName)
	{
		try {
			PageBean<Lesson> pb = new PageBean<Lesson>();
			pb.setPc(pc);
			pb.setPs(ps);
			/*
			 * tr
			 */
			String sql = "select count(*) from lesson";
			Number num;
			num = (Number) qr.query(sql, new ScalarHandler());
			int tr = num.intValue();
			pb.setTr(tr);
			/*
			 * beanList
			 */
			sql="select * from lesson where LName like ? limit ?,?";
			List<Lesson> beanList = qr.query(sql,
					new BeanListHandler<Lesson>(Lesson.class),
					"%"+LName+"%",(pc - 1) * ps, ps);
			pb.setBeanList(beanList);
			return pb;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		throw new RuntimeException(e);
		}
		
	}
	/*
	 * 更新课程  相应记得更新章节什么鬼的  check* 
	 */
	public void update(Lesson form)
	{
		try {
			String sql = "update lesson set pk_LId=?,LName=?,LInfo=?,LIcon=?"
					+ ",fk_L_TPhone=?,LCount=?,fk_DId=? where pk_LId=? ";
			Object[] params = { form.getPk_LId(), form.getLName(),
					form.getLInfo(),form.getLIcon(),form.getFk_L_TPhone(),form.getLCount(),form.getFk_DId(),form.getPk_LId() };
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过id找Lesson check*
	 */
	public Lesson findLessonById(String LId)
	{
		try{
			String sql="select * from lesson where pk_LId=?";
			return qr.query(sql, new BeanHandler<Lesson>(Lesson.class),LId);
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过did来返回一堆lesson check*
	 */
	
	public List<Lesson> findLessonByDId(String DId)
	{
		try {
			String sql="select * from lesson where fk_DId=? order by pk_LId desc";// order by pk_LId desc
			return qr.query(sql, new BeanListHandler<Lesson>(Lesson.class),DId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过LId返回一堆LESSON
	 */
	public List<Lesson> findLessonByTId(String TId)
	{
		try {
			String sql="select * from lesson where fk_L_TPhone=?";
			return qr.query(sql, new BeanListHandler<Lesson>(Lesson.class),TId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
