package test;

import org.junit.Test;

import developPage.CheckPhoneRegex;

public class testCkeckPhone {
	@Test
	public void testCheckPhone()
	{
		CheckPhoneRegex cpr=new CheckPhoneRegex();
		System.out.print(cpr.check("13716888886"));
	}

}
