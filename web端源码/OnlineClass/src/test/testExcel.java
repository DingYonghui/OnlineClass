package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.junit.Test;

import com.mori.domain.ExcelStudent;
import com.mori.service.StudentUserService;
import com.mori.util.ExportExcel;

import developPage.CheckPhoneRegex;

public class testExcel {
	@Test
	public void testCheckPhone() throws IOException
	{
		 ExportExcel<ExcelStudent> ex = new ExportExcel<ExcelStudent>();
	      String[] headers = { "学生账号", "密码", "姓名龄" };
	      String LId="13531188587C0001";
			StudentUserService sus=new StudentUserService();
			List<ExcelStudent> list=sus.returnExcel(LId);
			 OutputStream out = new FileOutputStream("D://testExcel//a.xls");
		
			 ex.exportExcel(headers, list, out);
			 out.close();
	       
	         System.out.println("excel导出成功！");
	}
}
