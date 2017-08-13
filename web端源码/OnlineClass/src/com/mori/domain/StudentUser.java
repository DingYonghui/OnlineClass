package com.mori.domain;

public class StudentUser {
	/*
	 * 数据库表学生用户实体
	 */
	private String pk_SPhone;// 主键
	private String SKey;// 16
	private String Url;// 255
	private int Power;// 0 1
	private String veriyCode;//非数据库字段   验证码存储
	public String getVeriyCode() {
		return veriyCode;
	}

	public void setVeriyCode(String veriyCode) {
		this.veriyCode = veriyCode;
	}

	private String registNickName;// 不存入数据库 注册用！

	public String getRegistNickName() {
		return registNickName;
	}

	public void setRegistNickName(String registNickName) {
		this.registNickName = registNickName;
	}

	public String getPk_SPhone() {
		return pk_SPhone;
	}

	public void setPk_SPhone(String pk_SPhone) {
		this.pk_SPhone = pk_SPhone;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public int getPower() {
		return Power;
	}

	public void setPower(int Power) {
		this.Power = Power;
	}

	public String getSKey() {
		return SKey;
	}

	public void setSKey(String SKey) {
		this.SKey = SKey;
	}

	@Override
	public String toString() {
		return "StudentUser [pk_SPhone=" + pk_SPhone + ", SKey=" + SKey
				+ ", Url=" + Url + ", Power=" + Power + ", registNickName="
				+ registNickName + "]";
	}

}
