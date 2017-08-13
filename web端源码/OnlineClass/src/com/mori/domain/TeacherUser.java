package com.mori.domain;

public class TeacherUser {
	private String pk_TPhone;// 同样手机验证 不过最终要管理员审核  11
	private String TKey;//16
	private String TName;//10
	private int Power;// 权限 2 3
	private String Url;//100
		private int TIsPass;//默认0 未审核  教师自行注册的时候 后台没直接把字段添加 而是等管理员系统（管理员打电话了解确认是老师后）操作通过审核，即1 
	

	public int getTIsPass() {
		return TIsPass;
	}

	public void setTIsPass(int tIsPass) {
		TIsPass = tIsPass;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	@Override
	public String toString() {
		return "TeacherUser [pk_TPhone=" + pk_TPhone + ", TKey=" + TKey
				+ ", TName=" + TName + ", Power=" + Power + ", Url=" + Url
				+ ", TIsPass=" + TIsPass + "]";
	}

	public int getPower() {
		return Power;
	}

	public void setPower(int Power) {
		this.Power = Power;
	}

	public String getPk_TPhone() {
		return pk_TPhone;
	}

	public void setPk_TPhone(String pk_TPhone) {
		this.pk_TPhone = pk_TPhone;
	}

	public String getTKey() {
		return TKey;
	}

	public void setTKey(String tKey) {
		TKey = tKey;
	}

	public String getTName() {
		return TName;
	}

	public void setTName(String tName) {
		TName = tName;
	}
}
