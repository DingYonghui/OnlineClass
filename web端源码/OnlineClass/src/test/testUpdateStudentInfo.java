package test;

import net.sf.json.JSONArray;

import org.junit.Test;

import com.mori.dao.StudentUserInfoDao;
import com.mori.domain.StudentUserInfo;
import com.mori.service.StudentUserService;

public class testUpdateStudentInfo {
	StudentUserInfoDao suid=new StudentUserInfoDao();
//	@Test
//	public void test() {
//		StudentUserInfo sui=new StudentUserInfo();
//		sui.setPk_SPhone("18826079508");
//		sui.setSAge(10);
//		
//		sui.setSGender(0);
//		suid.update(sui);
//	}
//	@Test
//	public void test1() {
//		String sid="18826079508";
//		StudentUserInfo sui=suid.findById(sid);
//		if(sui!=null)
//			System.out.print("查到啦");
//		else
//			System.out.print("没");
//
//	}
	@Test
	public void test2()
	{
		String LId="13531188587C0001";
		StudentUserService sus=new StudentUserService();
	
		System.out.print(sus.returnExcel(LId).toString());
	}
}
