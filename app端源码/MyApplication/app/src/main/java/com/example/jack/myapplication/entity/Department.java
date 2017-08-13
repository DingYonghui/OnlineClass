package com.example.jack.myapplication.entity;



/**
 * 院系
 * Created by ding on 2015/10/21.
 */
public class Department {


	private String DName;
	private String pk_DId;

	public String getDName() {
		return DName;
	}

	public void setDName(String dName) {
		DName = dName;
	}

	public String getPk_DId() {
		return pk_DId;
	}

	public void setPk_DId(String pk_DId) {
		this.pk_DId = pk_DId;
	}

	@Override
	public String toString() {
		return "Department [DName=" + DName + ", pk_DId=" + pk_DId + "]";
	}
}
