package com.mori.domain;

public class AdminUser {
	private String pk_ANumber;//Len:6
	private String AKey;//Len:16
	private String AName;//Len:10
	private int Power;//4 Len:1
	private String Url;//Len:100
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public int getPower() {
		return Power;
	}
	public void setPower(int power) {
		Power = power;
	}
	public String getPk_ANumber() {
		return pk_ANumber;
	}
	public void setPk_ANumber(String pk_ANumber) {
		this.pk_ANumber = pk_ANumber;
	}
	public String getAKey() {
		return AKey;
	}
	public void setAKey(String aKey) {
		AKey = aKey;
	}
	public String getAName() {
		return AName;
	}
	public void setAName(String aName) {
		AName = aName;
	}
	@Override
	public String toString() {
		return "AdminUser [pk_ANumber=" + pk_ANumber + ", AKey=" + AKey
				+ ", AName=" + AName + ", Power=" + Power + ", Url=" + Url
				+ "]";
	}
}
