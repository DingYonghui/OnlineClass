package com.mori.domain;

public class Student_Lesson {
	/*
	 * 学生课程对应
	 */
	private String LId;// Len 32
	private String SPhone;// Len 11

	@Override
	public String toString() {
		return "Student_Lesson [LId=" + LId + ", SPhone=" + SPhone + "]";
	}

	public String getLId() {
		return LId;
	}

	public void setLId(String lId) {
		LId = lId;
	}

	public String getSPhone() {
		return SPhone;
	}

	public void setSPhone(String sPhone) {
		SPhone = sPhone;
	}
}
