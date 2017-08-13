package com.mori.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.mori.domain.ExcelStudent;
import com.mori.service.StudentUserService;

public class MoriExcelStudentUtil {
	public void outExcel(String LId,String path) throws IOException
	{

		 ExportExcel<ExcelStudent> ex = new ExportExcel<ExcelStudent>();
	      String[] headers = { "学生账号", "密码", "姓名" };
	      
			StudentUserService sus=new StudentUserService();
			List<ExcelStudent> list=sus.returnExcel(LId);
			 OutputStream out = new FileOutputStream(path);
		
			 ex.exportExcel(headers, list, out);
			 out.close();
	       
	         System.out.println(LId);
	}
}
