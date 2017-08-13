package test;

import org.junit.Test;

import com.mori.dao.TeacherUserDao;
import com.mori.domain.TeacherUser;
import com.mori.domain.Teacher_Department;
import com.mori.service.TeacherService;

public class testTeacher {
	TeacherUserDao tud=new TeacherUserDao();
	TeacherService ts=new TeacherService();
//	@Test
//	public void test()
//	{
//		TeacherUser form=new TeacherUser();
//		form.setPk_TPhone("13531188587");
//		form.setPower(2);
//		form.setTName("凌玲琪");
//		tud.add(form);
//	}
//	@Test
//	public void test1()
//	{
//		tud.updatePower("13531188587", 3);
//	}
//	@Test
//	public void test2()
//	{
//		TeacherUser form=new TeacherUser();
//		form.setPk_TPhone("13531188587");
//		form.setTKey("222222");
//		System.out.print(tud.checkTeacherUser(form).toString());
//	}
	@Test
	public void test3()
	{
		Teacher_Department td=new Teacher_Department();
		td.setDId("8");
		td.setTPhone("18999999999");
		ts.updata(td);
	}
}
