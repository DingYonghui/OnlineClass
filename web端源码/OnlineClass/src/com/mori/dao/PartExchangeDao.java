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

public class PartExchangeDao {
	private QueryRunner qr = new TxQueryRunner();

//	// String sql="select count(*) from partexchange"; 查总记录数目
//	/*
//	 * findAll 分页
//	 */
//	// 用BeanList 让最后control遍历显示
//	public PageBean<PartExchange> findAllBackPg(int pc, int ps) {
//		// TODO Auto-generated method stub
//		/*
//		 * 获得PB 设置PC PS 查询获取TR 得到beanList设置给PB
//		 */
//		try {
//			PageBean<PartExchange> pb = new PageBean<PartExchange>();
//			pb.setPc(pc);
//			pb.setPs(ps);
//			/*
//			 * tr
//			 */
//			String sql = "select count(*) from partexchange";
//			Number num = (Number) qr.query(sql, new ScalarHandler());
//			int tr = num.intValue();
//			pb.setTr(tr);
//			/*
//			 * beanList
//			 */
//			sql = "select * from partexchange limit ?,?";//从?开始查询?条记录
//			List<PartExchange> beanList = qr.query(sql,
//					new BeanListHandler<PartExchange>(PartExchange.class),
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
//	public List<PartExchange> findAllBackList() {
//		try {
//			String sql = "select * from partexchange";
//			return qr.query(sql, new BeanListHandler<PartExchange>(
//					PartExchange.class));
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//
//	}
	/*
	 * 新增一条问  *check
	 */
	public void add(PartExchange form)
	{
		try {
			String sql = "insert into partexchange values(?,?,?,?,?,?)";
			Object[] params={form.getFk_PId()
					,form.getPk_PEId(),form.getPEByWho(),form.getPEByWho_Id(),form.getPEContent(),form.getPETime()};
		qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 通过fk值找到所有的讨论帖记录并返回  *check 
	 */
	public List<PartExchange> findBackListById(String fk_PId)
	{
		try {
			String sql="select * from partexchange where fk_PId=? order by pk_PEId desc";
			return qr.query(sql, new BeanListHandler<PartExchange>(PartExchange.class),fk_PId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}
	/*
	 * 通过fk值找到所有的讨论帖记录并返回 需要翻页 *check
	 */
	public PageBean<PartExchange> findBackPgById(int pc, int ps,String fk_PId)
	{
		try {
			PageBean<PartExchange> pb = new PageBean<PartExchange>();
			pb.setPc(pc);
			pb.setPs(ps);
			/*
			 * tr
			 */
			String sql = "select count(*) from partexchange";
			Number num;
			num = (Number) qr.query(sql, new ScalarHandler());
			int tr = num.intValue();
			pb.setTr(tr);
			/*
			 * beanList
			 */
			sql="select * from partexchange where fk_PId=? limit ?,?";
			List<PartExchange> beanList = qr.query(sql,
					new BeanListHandler<PartExchange>(PartExchange.class),
					fk_PId,(pc - 1) * ps, ps);
			pb.setBeanList(beanList);
			return pb;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		throw new RuntimeException(e);
		}
		
	}
	
	/*
	 * 删除一条 check*
	 */
	public void deleteById(String pk_PEId) {
		try {
			String sql = "delete from partexchange where pk_PEId=?";
			Object[] params = { pk_PEId };
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	public PartExchange getExchangeByPEId(String pEId) {
		// TODO Auto-generated method stub
		try {
			String sql="select * from partexchange where pk_PEId=?";
			
			return qr.query(sql, new BeanHandler<PartExchange>(PartExchange.class),pEId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
}
