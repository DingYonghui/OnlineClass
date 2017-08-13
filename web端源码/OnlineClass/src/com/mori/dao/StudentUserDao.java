package com.mori.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;

import com.mori.Exception.StudentException;
import com.mori.domain.StudentUser;

public class StudentUserDao {
	private QueryRunner qr = new TxQueryRunner();// QR 不断抽取数据库查询 连接池的共性 提供了抽象父类
													// 而QR就是实现这个接口的类
													// 而TxQR是itcast教的一个小工具

	// 完善了QR 继承并实现c3p0 ds与QR的衔接（）写好了支持开关归还线程池连接 的代码 并支持事务
	// 以及线程池并发问题也用ThreadLocal解决
	//publi List<StudentUser> returnStudentList(String fk)
	/*
	 * 注册 *check过
	 */
	public void add(StudentUser form) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into studentuser values(?,?,?,?)";
			Object[] params = { form.getPk_SPhone(), form.getSKey(),
					form.getUrl(), form.getPower()

			};
			System.out.print(form.getPower());
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * 查询判断账号（手机号）是否被注册 *check
	 */
	public StudentUser findByPk_sPhone(String pk_sPhone) {
		try {
			String sql = "select * from studentuser where pk_SPhone=?";
			return qr.query(sql,
					new BeanHandler<StudentUser>(StudentUser.class), pk_sPhone);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * 检验账号密码*check过
	 */
	public StudentUser checkStudentUser(StudentUser form) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from studentuser where pk_SPhone=? and SKey=?";
			Object[] params = { form.getPk_SPhone(), form.getSKey()

			};
			return qr.query(sql,
					new BeanHandler<StudentUser>(StudentUser.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * 更新权限 ------------------授权操作 check
	 */
	public void updatePower(String SPhone, int power) {
		try {
			String sql = "update studentuser set Power=? where pk_SPhone=?";
			Object[] params = { power, SPhone };
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/*
	 * Url权限关联由POWER引出的表决定 暂时不处理URL字段
	 */

	/*---------------        批量       ---------------*/
	/*
	 * 批量增加 INSERT IGNORE INTO table 顺便帮学生的lesson_student表添加字段 check*
	 */
	public void batchAdd(List<StudentUser> formList) throws StudentException {
		try {
			String sql = "insert into studentuser values(?,?,?,?)";

			Object[][] params = new Object[formList.size()][4];
			for (int i = 0; i < formList.size(); i++) {
				params[i][0] = formList.get(i).getPk_SPhone();
				params[i][1] = formList.get(i).getSKey();
				params[i][2] = formList.get(i).getUrl();
				params[i][3] = formList.get(i).getPower();
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


	/*---------------        批量       ---------------*/
	/*
	 * 批量前获取查找到的重复的帐号
	 */
	public List<StudentUser> batchQuery() {
		return null;
		
	}

}
