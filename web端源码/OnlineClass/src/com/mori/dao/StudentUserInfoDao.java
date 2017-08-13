package com.mori.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mori.Exception.StudentException;
import com.mori.domain.StudentUserInfo;

import cn.itcast.jdbc.TxQueryRunner;

public class StudentUserInfoDao {
	private QueryRunner qr = new TxQueryRunner();

	/*
	 * 注册时候添加详细信息默认值 *check过
	 */
	public void add(String SNickName, String SPhone) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into studentuserinfo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[] params = { SPhone, "", "", "", 0, 0, SNickName, "", null,
					"", "", "", "", ""

			};
			// System.out.print(params.toString());
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * 更新个人信息 *check
	 */
	public void update(StudentUserInfo form) {
		try {
			String sql = "update studentuserinfo set pk_SPhone=?,SNo=?,SName=?,SHeadIcon=?"
					+ ",SAge=?,SGender=?,SNickName=?,SSchool=?,SDepartment=?,SMajor=?,SClass=?"
					+ ",SDefaultPhone=?,SEmail=?,SRegistTime=? where pk_SPhone=? ";
			Object[] params = { form.getPk_SPhone(), form.getSNo(),
					form.getSName(), form.getSHeadIcon(), form.getSAge(),
					form.getSGender(), form.getSNickName(), form.getSSchool(),
					form.getSDepartment(), form.getSMajor(), form.getSClass(),
					form.getSDefaultPhone(), form.getSEmail(),
					form.getSRegistTime(), form.getPk_SPhone() };
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/*
	 * 查看个人信息 *check
	 */
	public StudentUserInfo findById(String pk_SPhone) {
		try {
			String sql = "select * from studentuserinfo where pk_SPhone=?";
			return qr.query(sql, new BeanHandler<StudentUserInfo>(
					StudentUserInfo.class), pk_SPhone);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}
	/*
	 * 课程关系表查出一堆id 批处理这些ID来查询相应的详细信息并显示
	 */
//
	public void batchAdd(List<StudentUserInfo> formList) throws StudentException {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into studentuserinfo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			Object[][] params = new Object[formList.size()][14];
			for (int i = 0; i < formList.size(); i++) {
				params[i][0] = formList.get(i).getPk_SPhone();
				params[i][1] = formList.get(i).getSNo();
				params[i][2]=formList.get(i).getSName();
						params[i][3]=formList.get(i).getSHeadIcon();
						params[i][4]=formList.get(i).getSAge();
						params[i][5]=formList.get(i).getSGender();
						params[i][6]=formList.get(i).getSNickName();
						params[i][7]=formList.get(i).getSSchool();
						params[i][8]=formList.get(i).getSDepartment();//
						params[i][9]=formList.get(i).getSMajor();
						params[i][10]=formList.get(i).getSClass();
						params[i][11]=formList.get(i).getSDefaultPhone();
						params[i][12]=formList.get(i).getSEmail();
						
						params[i][13]=formList.get(i).getSRegistTime();
						
						
			}
			
			//System.out.print("zzzzzzzzzzzz");
			qr.batch(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new StudentException("BATCH异常");
		}
	}
}
