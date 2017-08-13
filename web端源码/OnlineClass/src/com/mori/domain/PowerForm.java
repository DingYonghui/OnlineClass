package com.mori.domain;

public class PowerForm {
	private int PCode;//0 1 2 3  4学生 助理 教师  二级管理员 admin    
	private String PName;//Len 3
	public int getPCode() {
		return PCode;
	}
	public void setPCode(int pCode) {
		PCode = pCode;
	}
	public String getPName() {
		return PName;
	}
	public void setPName(String pName) {
		PName = pName;
	}
	@Override
	public String toString() {
		return "PowerForm [PCode=" + PCode + ", PName=" + PName + "]";
	}
	
}
