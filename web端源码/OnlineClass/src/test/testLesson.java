package test;

import java.util.List;

import org.junit.Test;

import com.mori.dao.LessonDao;
import com.mori.dao.PartDao;
import com.mori.dao.SectionDao;
import com.mori.domain.Lesson;
import com.mori.domain.PageBean;

public class testLesson {
	LessonDao ld=new LessonDao();
	

//	@Test
//	public void test1()
//	{
//		Lesson form=new Lesson();
//		form.setPk_LId("C3069");
//		form.setFk_L_TPhone("18826079509");
//		form.setLInfo("这是一本简单的小情书");
//		form.setLName("情书课程");
//		ld.add(form);
//	}
//	@Test
//	public void test2()
//	{
//		String name="情";
//		String name1="书";
//		String name2="啊";
//		List<Lesson> l=ld.query(name2);
//		if(l.size()>=1)
//			System.out.print("找到啦 它叫做"+l.get(0).getLName());
//		else{
//			System.out.print("找不到");
//		}
//	}
//	@Test
//	public void test3()
//	{
//		String name="情";
//		String name1="书";
//		String name2="啊";
//		PageBean<Lesson> l=ld.findBackPgByName(1, 1, name);
//		if(l.getBeanList().size()>=1)
//			System.out.print("找到啦 它叫做"+l.getBeanList().get(0).getLName());
//		else{
//			System.out.print("找不到");
//		}
//	}
//	@Test
//	public void test4()
//	{
//		Lesson form=new Lesson();
//		form.setLName("大情书");
//		form.setPk_LId("C3069");
//		ld.update(form);
//	}
//	@Test
//	public void test5()
//	{
//		Lesson form=new Lesson();
//		String id="C30619";
//		Lesson a=ld.findLessonById(id);
//	if(a!=null)
//		System.out.print(a.toString());
//	else
//		System.out.print("找不到");
//	}
//	@Test
//	public void test6()
//	{
//	
//		String id="C3069";
//		ld.delete(id);
//	
//	}
	@Test
	public void test7()
	{
	
		String id="8";
		List<Lesson> l=ld.findLessonByDId(id);
		
		System.out.print(l.get(1).toString()+ " "+l.get(0).toString());
	}
}
