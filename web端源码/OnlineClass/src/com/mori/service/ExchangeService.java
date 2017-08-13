package com.mori.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.mori.Exception.LessonException;
import com.mori.dao.PartExchangeDao;
import com.mori.dao.PartResponseDao;
import com.mori.domain.Lesson;
import com.mori.domain.PartExchange;
import com.mori.domain.PartResponse;
import com.mori.domain.Section;

public class ExchangeService {
	/*
	 * 回复 评论讨论贴的增 删 查 服务
	 */
	private PartExchangeDao ped=new PartExchangeDao();
	private PartResponseDao prd=new PartResponseDao();
	public PartExchange getExchangeByPEId(String PEId)
	{
		return ped.getExchangeByPEId(PEId);
	}
	/*
	 * 1.新增一条交流贴
	 */
	public void addExchange(PartExchange form) throws LessonException
	{
				if(ped.getExchangeByPEId(form.getPk_PEId())!=null)
					throw new LessonException("同一条讨论帖别提交两次");
		ped.add(form);
	}
	
	/*
	 * 2.新增一条回复
	 * 
	 * 无论是沙发还是跟着的回复 都属于同一条exchange 所以区别就在控制层弄
	 * toWho 字段
	 */
	public void addResponse(PartResponse form) throws LessonException
	{
		if(prd.getPartResponseByPRId(form.getPk_PRId())!=null)
			throw new LessonException("同一条回复帖别提交两次");
		prd.add(form);
	}
	/*
	 * 3.删除一条交流贴
	 */
	public void deletExchange(String id)
	{
		prd.deleteById(id);
	}
	/*
	 * 获取P交流贴记录 json
	 */
	public JSONArray returnExchangeByPart(String PId)
	{
		List<PartExchange> list=ped.findBackListById(PId);
		JSONArray json=JSONArray.fromObject(list);
		return json;
	}
	/*
	 * 获取P回复贴JSON
	 */
	public JSONArray returnResponseByPEId(String PEId)
	{
		List<PartResponse> list=prd.findBackListById(PEId);
		JSONArray json=JSONArray.fromObject(list);
		return json;

		
	}
	/*
	 * 获取P回复贴JSON
	 */
	public List<PartResponse> returnResponseByPEIdNoJson(String PEId)
	{
		List<PartResponse> list=prd.findBackListById(PEId);
		return  list;

		
	}
	/*
	 * 获取P交流贴记录
	 */
	public List<PartExchange> returnExchangeByPartNoJson(String PId)
	{
		List<PartExchange> list=ped.findBackListById(PId);
		
		return list;
	}
	
}
