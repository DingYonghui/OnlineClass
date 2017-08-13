package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mori.dao.Student_LessonDao;
import com.mori.domain.StudentUser;
import com.mori.domain.Student_Lesson;
import com.mori.service.StudentUserService;

public class testL_S {
	Student_LessonDao sl = new Student_LessonDao();
	StudentUserService ss=new StudentUserService();

	// @Test
	// public void test()
	// {
	// Student_Lesson form=new Student_Lesson();
	// form.setLId("C3069");
	// form.setSPhone("18826079508");
	// sl.add(form);
	// }
	// @Test
	// public void test1()
	// {
	// Student_Lesson form=new Student_Lesson();
	// form.setLId("C3069");
	// form.setSPhone("18826079508");
	// sl.delete(form);
	// }
//	@Test
//	public void test2() {
//		StudentUser a = new StudentUser();
//		StudentUser a1 = new StudentUser();
//		a.setPk_SPhone("18934161756");
//		a1.setPk_SPhone("18924786526");
//		List<StudentUser> list = new ArrayList<StudentUser>();
//		list.add(a);
//		list.add(a1);
//		sl.batchAdd("C3069", list);
//	}
	@Test
	public void studentLessonList(){
		
		System.out.print(ss.studentLessonList("13511111111").toString());
	}
}
