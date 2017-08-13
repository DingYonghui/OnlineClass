package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mori.dao.PartDao;
import com.mori.domain.Part;

public class testPart {
	PartDao pd=new PartDao();
//	@Test
//	public void test()
//	{
//		Part form=new Part();
//		form.setFk_SId("C3069_1");
//		form.setPk_PId("C3069_1_1");
//		form.setPName("第一节");
//		pd.add(form);
//	}
//	@Test
//	public void test1()
//	{
//		Part form=new Part();
//		
//		form.setFk_SId("C3069_1");
//		form.setPk_PId("C3069_1_1");
//		form.setPName("第一节de ");
//		pd.update(form);
//	}
	
//		@Test
//		public void test2()
//		{
//			
//			
//	
//			pd.delete("C3069_1_1");;
//		}
//	
	
	@Test
	public void test3()
	{
		
		

		List<Part> a=new ArrayList<Part>();
		a=pd.findPartBySectionId("C3069_1");
		System.out.print(a.get(0).toString()+" "+a.get(1).toString());
	}
	
}
