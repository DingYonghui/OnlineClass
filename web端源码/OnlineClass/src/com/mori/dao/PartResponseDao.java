package com.mori.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mori.domain.PageBean;
import com.mori.domain.PartExchange;
import com.mori.domain.PartResponse;

import cn.itcast.jdbc.TxQueryRunner;

public class PartResponseDao {
	private QueryRunner qr = new TxQueryRunner();

//	/*
//	 * findAll 分页
//	 */
//	// 用BeanList 让最后control遍历显示
//	public PageBean<PartResponse> findAllBackPg(int pc, int ps) {
//		// TODO Auto-generated method stub
//		/*
//		 * 获得PB 设置PC PS 查询获取TR 得到beanList设置给PB
//		 */
//		try {
//			PageBean<PartResponse> pb = new PageBean<PartResponse>();
//			pb.setPc(pc);
//			pb.setPs(ps);
//			/*
//			 * tr
//			 */
//			String sql = "select count(*) from partresponse";
//			Number num = (Number) qr.query(sql, new ScalarHandler());
//			int tr = num.intValue();
//			pb.setTr(tr);
//			/*
//			 * beanList
//			 */
//			sql = "select * from partresponse limit ?,?";
//			List<PartResponse> beanList = qr.query(sql,
//					new BeanListHandler<PartResponse>(PartResponse.class),
//					(pc - 1) * ps, ps);
//			pb.setBeanList(beanList);
//			return pb;
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//
//	}
//
//	// 手机端不需要翻页 网页需要 所以提供两个dao
//	/*
//	 * findAll
//	 */
//
//	public List<PartResponse> findAllBackList() {
//		try {
//			String sql = "select * from partresponse";
//			return qr.query(sql, new BeanListHandler<PartResponse>(
//					PartResponse.class));
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//
//	}

	/*
	 * 新增一条评论 *check
	 */
	public void add(PartResponse form) {
		try {
			String sql = "insert into partresponse values(?,?,?,?,?,?,?)";
			Object[] params = { form.getFk_PEId(), form.getPk_PRId(),
					form.getPR_ToWho(), form.getPR_ByWho(),
					form.getPRContent(), form.getPR_ByWho_Name(),form.getPRTime()};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * 删除一条  *check
	 */
	public void deleteById(String pk_PRId) {
		try {
			String sql = "delete from partresponse where pk_PRId=?";
			Object[] params = { pk_PRId };
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过fk值找到所有的讨论帖记录并返回 check*
	 */
	public List<PartResponse> findBackListById(String fk_PEId)
	{
		
		try {
			String sql = "select * from partresponse where fk_PEId=? order by pk_PRId desc";
			return qr.query(sql, new BeanListHandler<PartResponse>(PartResponse.class),fk_PEId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		
		
	}
	/*
	 * 通过fk值找到所有的讨论帖记录并返回 需要翻页 *check
	 */
	public PageBean<PartResponse> findBackPgById(int pc, int ps,String fk_PEId)
	{
		// TODO Auto-generated method stub
		/*
		 * 获得PB 设置PC PS 查询获取TR 得到beanList设置给PB
		 */
		try {
			PageBean<PartResponse> pb = new PageBean<PartResponse>();
			pb.setPc(pc);
			pb.setPs(ps);
			/*
			 * tr
			 */
			String sql = "select count(*) from partresponse";
			Number num = (Number) qr.query(sql, new ScalarHandler());
			int tr = num.intValue();
			pb.setTr(tr);
			/*
			 * beanList
			 */
			sql = "select * from partresponse where fk_PEId=? limit ?,?";
			List<PartResponse> beanList = qr.query(sql,
					new BeanListHandler<PartResponse>(PartResponse.class),
					fk_PEId,(pc - 1) * ps, ps);
			pb.setBeanList(beanList);
			return pb;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public PartResponse getPartResponseByPRId(String pRId) {
		// TODO Auto-generated method stub
		try {
			String sql="select * from partresponse where pk_PRId=?";
			
			return qr.query(sql, new BeanHandler<PartResponse>(PartResponse.class),pRId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
}
