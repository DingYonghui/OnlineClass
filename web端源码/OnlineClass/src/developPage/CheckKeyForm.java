package developPage;

public class CheckKeyForm {
/*
 * 6-8低级 返回0
 * 9-11中级 返回1	
 * 12-16高级	返回2
 * 不符合格式返回-1
 *^[\\d\\D]{6,16}$

 */
	public int check(String key)
	{
		if(key.matches("^[\\d]{6,16}$"))
			return 0;
		else{
			if(key.matches("^[\\d\\D]{6,8}$"))
				return 0;
			else if(key.matches("^[\\d\\D]{9,11}$"))
				return 1;
			else if(key.matches("^[\\d\\D]{12,16}$"))
				return 2;
		}
	
		return -1;
	}
}
