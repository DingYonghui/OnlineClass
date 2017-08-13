package com.mori.domain;

import java.util.ArrayList;
import java.util.List;

public class Section {
	private String pk_SId;//课程Id+_+章数 Len32+2   
	private String fk_LId;//Len 32
	private String SName;//Len 20
	private String SInfo;//Len 50
	private int SCount;//Len 255小节数
	private String SIcon;//100
	private String STime;//20 小节总时间
	
	private List<Part> partList=new ArrayList<Part>();//非字段
	public List<Part> getPartList() {
		return partList;
	}
	public void setPartList(List<Part> partList) {
		this.partList = partList;
	}
	public String getPk_SId() {
		return pk_SId;
	}
	public void setPk_SId(String pk_SId) {
		this.pk_SId = pk_SId;
	}
	public String getFk_LId() {
		return fk_LId;
	}
	public void setFk_LId(String fk_LId) {
		this.fk_LId = fk_LId;
	}
	public String getSName() {
		return SName;
	}
	public void setSName(String sName) {
		SName = sName;
	}
	public String getSInfo() {
		return SInfo;
	}
	public void setSInfo(String sInfo) {
		SInfo = sInfo;
	}
	public int getSCount() {
		return SCount;
	}
	public void setSCount(int sCount) {
		SCount = sCount;
	}
	public String getSIcon() {
		return SIcon;
	}
	public void setSIcon(String sIcon) {
		SIcon = sIcon;
	}
	public String getSTime() {
		return STime;
	}
	public void setSTime(String sTime) {
		STime = sTime;
	}
	@Override
	public String toString() {
		return "Section [pk_SId=" + pk_SId + ", fk_LId=" + fk_LId + ", SName="
				+ SName + ", SInfo=" + SInfo + ", SCount=" + SCount
				+ ", SIcon=" + SIcon + ", STime=" + STime + "]";
	}
	
}
