package test;

import org.junit.Test;

import developPage.CheckKeyForm;


public class testCheckKeyForm {
	@Test
	public void testCheck()
	{
		CheckKeyForm cKF=new CheckKeyForm();
		System.out.print(cKF.check("1234567890122a"));
	}
}
