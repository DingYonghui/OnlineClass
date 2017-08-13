package com.mori.domain;

public class ExcelStudent {
	private String pk_SPhone;// 主键
	private String SKey;// 16
	private String SName;// 10
	public String getPk_SPhone() {
		return pk_SPhone;
	}
	public void setPk_SPhone(String pk_SPhone) {
		this.pk_SPhone = pk_SPhone;
	}
	public String getSKey() {
		return SKey;
	}
	public void setSKey(String sKey) {
		SKey = sKey;
	}
	public String getSName() {
		return SName;
	}
	public void setSName(String sName) {
		SName = sName;
	}
	@Override
	public String toString() {
		return "ExcelStudent [pk_SPhone=" + pk_SPhone + ", SKey=" + SKey
				+ ", SName=" + SName + "]";
	}
}
