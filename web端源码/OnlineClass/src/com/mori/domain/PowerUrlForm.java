package com.mori.domain;

public class PowerUrlForm {
	/*
	 * 过滤器什么的也行 弄个权限集中表也行 存放所有url servlet 
	 * 然后在各个角色中存放权限值  （关联到权限表）根据 0 1 2 3 4来获取相应url权限
	 *	根据用户的power和相应操作的url  查找这个总表 
	 */
	private int PCode;//Len 1
	private String PUrl;//255
	@Override
	public String toString() {
		return "PowerUrlForm [PCode=" + PCode + ", PUrl=" + PUrl + "]";
	}
	public int getPCode() {
		return PCode;
	}
	public void setFk_PCode(int PCode) {
		this.PCode = PCode;
	}
	public String getPUrl() {
		return PUrl;
	}
	public void setPUrl(String pUrl) {
		PUrl = pUrl;
	}
}
