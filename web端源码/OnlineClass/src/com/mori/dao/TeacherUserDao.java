package com.mori.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mori.domain.StudentUser;
import com.mori.domain.TeacherUser;

import cn.itcast.jdbc.TxQueryRunner;

public class TeacherUserDao {
	private QueryRunner qr = new TxQueryRunner();
	public void updata(TeacherUser form)
	{
		try {
			String sql="update teacheruser set pk_TPhone=?,TKey=? and TName=? where pk_TPhone=?";
			Object[] params={form.getPk_TPhone(),form.getTKey(),form.getTName(),form.getPk_TPhone()};
			qr.update(sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/*
	 * 添加一个导师 check
	 */
	public void add(TeacherUser form)
	{
		try {
			String sql="insert into teacheruser values(?,?,?,?,?,?)";
			Object[] params={form.getPk_TPhone(),form.getTKey(),form.getTName(),form.getPower(),form.getUrl(),form.getTIsPass()};
			qr.update(sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		throw new RuntimeException(e);
		}
	}
	
	
	
	
	/*
	 * 更新权限 ------------------授权操作  check
	 */
	public void updatePower(String TPhone,int power)
	{
		try {
			String sql="update teacheruser set Power=? where pk_TPhone=?";
			Object[] params={power,TPhone};
			qr.update(sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/*
	 * 检验账号密码*check过
	 */
	public TeacherUser checkTeacherUser(TeacherUser form) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from teacheruser where pk_TPhone=? and TKey=?";
			Object[] params = { form.getPk_TPhone(), form.getTKey()

			};
			return qr.query(sql,
					new BeanHandler<TeacherUser>(TeacherUser.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	/*
	 * 通过审核  
	 */
	public void Pass(String TPhone)
	{
		try {
			String sql="update teacheruser set TIsPass=? where pk_TPhone=?";
			Object[] params={1,TPhone};
			qr.update(sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/*
	 * 检验是否被注册
	 */
	public TeacherUser findByPk_sPhone(String pk_TPhone) {
		try {
			String sql = "select * from teacheruser where pk_TPhone=?";
			return qr.query(sql,
					new BeanHandler<TeacherUser>(TeacherUser.class), pk_TPhone);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	public List<TeacherUser> findUnPass() {
		// TODO Auto-generated method stub
		
		try {
			String sql="select * from teacheruser where TIsPass=?";
			return qr.query(sql,
					new BeanListHandler<TeacherUser>(TeacherUser.class), "0");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void delete(String tPhone) {
		// TODO Auto-generated method stub
		

			try {
				String sql = "delete from teacheruser where pk_TPhone=?";
				Object[] params = {tPhone };
				qr.update(sql, params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}

		
	}
}
