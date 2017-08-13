package com.mori.domain;

public class Teacher_Department {
	private String TPhone;
	private String DId;
	public String getTPhone() {
		return TPhone;
	}
	public void setTPhone(String tPhone) {
		TPhone = tPhone;
	}
	public String getDId() {
		return DId;
	}
	public void setDId(String DId) {
		this.DId = DId;
	}
	@Override
	public String toString() {
		return "Teacher_Department [TPhone=" + TPhone + ", DId=" + DId + "]";
	}
	
	
}
