package test;

import java.util.List;

import org.junit.Test;

import com.mori.dao.PartExchangeDao;
import com.mori.dao.PartResponseDao;
import com.mori.domain.PageBean;
import com.mori.domain.PartExchange;
import com.mori.domain.PartResponse;

public class testExchangeDao {
	PartResponseDao prd=new PartResponseDao();
	PartExchangeDao ped=new PartExchangeDao();
//	@Test
//	public void test1()
//	{
//		PartExchange form=new PartExchange();
//		form.setFk_PId("C306");
//		form.setPEByWho("Ken");
//		form.setPEByWho_Id("18826079508");
//		form.setPEContent("超赞");
//		form.setPETime("2015.11.12");
//		form.setPk_PEId("C3061");
//		ped.add(form);
//	}
//	@Test
//	public void test2()
//	{
//		 String id="C3061";
//		// List<PartExchange> pe=ped.findBackListById(id);
//		// if(pe.size()>=1)
//		// System.out.print("找到");
//		// else
//		// System.out.print("No");
//		PageBean<PartExchange> pb=ped.findBackPgById(1, 8, id);	
//		if(pb.getBeanList().size()>=1)
//		{
//			System.out.print("找到");
//		}
//		else
//		{
//			System.out.print("No");
//		}
//	}
//	@Test
//	public void test3()
//	{
//		String a="111";
//		ped.deleteById(a);
//	}
//	@Test
//	public void test4()
//	{
//		String a="222";
//		prd.deleteById(a);
//	}
//	@Test 
//	public void test5()
//
//	{
//		PartResponse form=new PartResponse();
//		form.setPk_PRId("C3069");
//	
//		prd.add(form);
//	}
	@Test 
	public void test6()

	{
		 String id="222";
//			 List<PartResponse> pe=prd.findBackListById(id);
//			 if(pe.size()>=1)
//			 System.out.print("找到");
//			 else
//			 System.out.print("No");
//			 
//			 for(PartResponse a:pe)
//				 System.out.print(a.toString());
			PageBean<PartResponse> pb=prd.findBackPgById(1, 8, id);	
			if(pb.getBeanList().size()>=1)
			{
				System.out.print("找到");
			}
			else
			{
				System.out.print("No");
			}
		 for(PartResponse a:pb.getBeanList())
		 System.out.print(a.toString());
	}
}
