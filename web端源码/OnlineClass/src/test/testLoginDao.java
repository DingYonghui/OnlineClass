package test;

import org.junit.Test;

import com.mori.dao.StudentUserDao;
import com.mori.domain.StudentUser;

public class testLoginDao {
	
	StudentUserDao sUD=new StudentUserDao();
//	@Test
//	public void testLogin() {
//		StudentUser a=new StudentUser();
//		a.setPk_SPhone("18826079508");
//		a.setSKey("999999999");
//		StudentUser s=sUD.checkStudentUser(a);
//		//System.out.print(s.toString());
//		if(s!=null)
//		{
//			System.out.print("登陆成功");
//		}
//		else{
//			System.out.print("登陆失败");
//					
//		}
//	}
//	@Test
//	public void  test1()
//	{
//		String a="188260795108";
//		StudentUser b=sUD.findByPk_sPhone(a);
//		if(b!=null)
//		{
//			System.out.print("已被注册");
//		}
//		else{
//			System.out.print("没被注册");
//		}
//	}
//	@Test
//	public void  test2()
//	{
//		String a="18826079508";
//		sUD.updatePower(a, 1);
//	}
}
