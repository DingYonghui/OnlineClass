package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mori.dao.SectionDao;
import com.mori.domain.Section;

public class testSection {
	SectionDao sd=new SectionDao();
//	@Test
//	public void test()
//	{
//		Section form=new Section();
//		form.setFk_LId("C3069");
//		form.setPk_SId("C3069_1");
//		sd.add(form);
//	}
//	@Test
//	public void test1()
//	{
//	sd.delete("C3069_1");
//	}
//	@Test
//	public void test2()
//	{
//		Section form=new Section();
//		form.setFk_LId("C3069");	
//		form.setPk_SId("C3069_1");
//		form.setSCount(2);
//	sd.update(form);
//	}
	@Test
	public void test3()
	{
		List<Section> a=new ArrayList<Section>();
		a=sd.findSectionByLessonId("C3069");
		System.out.print(a.get(1).toString()+ " "+a.get(0).toString());
	}
}
