package com.mori.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;

import com.mori.domain.Part;
import com.mori.domain.Section;

public class PartDao {
	private QueryRunner qr = new TxQueryRunner();

	/*
	 * 添加节 check
	 */
	public void add(Part form) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into part values(?,?,?,?,?,?,?)";
			Object[] params = {form.getPk_PId(),form.getFk_SId(),form.getPName(),
					form.getPVideoPath(),form.getPTime(),form.getPIcon(),form.getPCount()
			};

			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * 删除节 check
	 */
	public void delete(String id) {

		try {
			String sql = "delete from part where pk_PId=?";
			Object[] params = { id };
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

	/*
	 * 更新节 check
	 */
	public void update(Part form) {
		try {
			String sql = "update part set pk_PId=?,fk_SId=?,PName=?,PVideoPath=?"
					+ ",PTime=?,PIcon=? and PCount=? where pk_PId=?";
			Object[] params = {form.getPk_PId(),form.getFk_SId(),form.getPName(),
					form.getPVideoPath(),form.getPTime(),form.getPIcon(),form.getPCount(),form.getPk_PId()};
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/*
	 * 找Part check?
	 */
	public Part findPartById(String PId)
	{
		try{
			String sql="select * from part where pk_PId=?";
			return qr.query(sql, new BeanHandler<Part>(Part.class),PId);
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过Sid 找一堆Part CHECK*
	 */
	public List<Part> findPartBySectionId(String SId)
	{
		try {
			String sql="select * from part where fk_SId=?";
			return qr.query(sql, new BeanListHandler<Part>(Part.class),SId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
//	/*
//	 * 批处理好像不能批量查？
//	 */
//	public List<List<Part>> batchPartList(String[] SId) 
//	{
//		try {
//			String sql="select * from part where fk_SId=?";
//			Object[][] params=new Object[1][];
//			params[0]=SId;
//			qr.batch(sql, params);
//			
//		return null;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			throw new RuntimeException(e);
//		}
//		
//		
//	}
}
