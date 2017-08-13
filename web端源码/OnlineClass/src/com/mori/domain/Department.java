package com.mori.domain;

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
