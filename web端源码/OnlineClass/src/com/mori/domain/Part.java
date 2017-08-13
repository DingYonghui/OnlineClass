package com.mori.domain;

public class Part {
	private String fk_SId;//Len 34   
	private String pk_PId;////Len 34+2 SId+_+章数
	private String PName;//Len20
	private String PVideoPath;//Len100
	private String PTime;//Len20
	private String PIcon;//Len 100  不需要了   
	private int PCount;//Len 255 
	@Override
	public String toString() {
		return "Part [fk_SId=" + fk_SId + ", pk_PId=" + pk_PId + ", PName="
				+ PName + ", PVideoPath=" + PVideoPath + ", PTime=" + PTime
				+ ", PIcon=" + PIcon + ", PCount=" + PCount + "]";
	}
	public String getFk_SId() {
		return fk_SId;
	}
	public void setFk_SId(String fk_SId) {
		this.fk_SId = fk_SId;
	}
	public String getPk_PId() {
		return pk_PId;
	}
	public void setPk_PId(String pk_PId) {
		this.pk_PId = pk_PId;
	}
	public String getPName() {
		return PName;
	}
	public void setPName(String pName) {
		PName = pName;
	}
	public String getPVideoPath() {
		return PVideoPath;
	}
	public void setPVideoPath(String pVideoPath) {
		PVideoPath = pVideoPath;
	}
	public String getPTime() {
		return PTime;
	}
	public void setPTime(String pTime) {
		PTime = pTime;
	}
	public String getPIcon() {
		return PIcon;
	}
	public void setPIcon(String pIcon) {
		PIcon = pIcon;
	}
	public int getPCount() {
		return PCount;
	}
	public void setPCount(int pCount) {
		PCount = pCount;
	}
	
}
