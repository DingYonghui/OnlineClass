package developPage;

public class CheckPhoneRegex {
	/*
	 * ^[1][358][0-9]{9}$
	 */
	
	public boolean check(String phone)
	{
		
		return phone.matches("^[1][358][0-9]{9}$");
	}
}
