package developPage;

public class CheckNickNameLength {
	/*
	 * 
	 */
	
	public boolean check(String name)
	{
		
		return name.length()>=2&&name.length()<20;
	}
}
