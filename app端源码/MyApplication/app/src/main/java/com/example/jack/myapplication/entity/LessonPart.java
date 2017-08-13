package com.example.jack.myapplication.entity;

//视频小节
public class LessonPart {

	private String fk_SId;//Len 34   
	private String pk_PId;////Len 34+2 SId+_+章数

	private String PName;//Len20
	private String PVideoPath;//Len100

	private String PTime;//Len20
	private String PIcon;//Len 100


	private int PCount;//Len 255


	public LessonPart(String PName, String PVideoPath, String PIcon, String PTime, int PCount) {
		this.PName = PName;
		this.PVideoPath = PVideoPath;
		this.PTime = PTime;
		this.PIcon = PIcon;
		this.PCount = PCount;
	}

	@Override
	public String toString() {
		return "LessonPart [fk_SId=" + fk_SId + ", pk_PId=" + pk_PId + ", PName="
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
