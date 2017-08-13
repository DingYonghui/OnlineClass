package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mori.Exception.StudentException;
import com.mori.dao.StudentUserDao;
import com.mori.dao.StudentUserInfoDao;
import com.mori.domain.StudentUser;



public class testRegistDao {
	StudentUserDao sUD=new StudentUserDao();
	StudentUserInfoDao sUID=new StudentUserInfoDao();
//	public void regist(StudentUser form)
//	{
//		sUD.add(form);
//		sUID.add(form.getRegistNickName(), form.getPk_SPhone());
//	}
//	@Test
//	public void testStudentDao()
//	{
//		StudentUser a=new StudentUser();
//		a.setPk_SPhone("18826079504");
//		a.setRegistNickName("小螺号");
//		a.setSKey("9999999");
//		sUD.add(a);
//		sUID.add(a.getRegistNickName(), a.getPk_SPhone());
//	}
	@Test
	public void test() throws StudentException
	{
		List<StudentUser>formList=new ArrayList<StudentUser>();
		StudentUser a=new StudentUser();
		a.setPk_SPhone("18934161722");
		a.setRegistNickName("小螺号");
		a.setSKey("9999999");
		StudentUser b=new StudentUser();
		b.setPk_SPhone("18924786522");
		b.setRegistNickName("小螺号");
		b.setSKey("9999999");
		formList.add(a);
		formList.add(b);
		sUD.batchAdd(formList);
	}
}
