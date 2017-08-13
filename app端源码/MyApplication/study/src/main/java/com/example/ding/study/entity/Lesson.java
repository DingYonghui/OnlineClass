package com.example.ding.study.entity;

public class Lesson {
	private String pk_LId;// 每门课都有那本书的ID  +课程者TPhone 组合成ID  由课程建设者设定 Len 32
	private String LName;//Len 20
	private String LInfo;//Len 50
	private String LIcon;//Len 100
	private String fk_L_TPhone;//Len 11
	private int LCount;//Len 255(数据库每个int 4字节  255=8字节 就32字节 没错)
//考虑到有的课程课程号一样 老师不一样  所以老师在建设课程的时候输入的只是课程ID  而主键还是要老师PHONE+课程ID组合成唯一辨识 我了个去劳资又要改表又要
	//从最里面改 = = ==== = = == = == = =所以就在逻辑上偷偷这样定义  自己手动添加PHONE 和ID组合再存入PK 在APP中JSON也有PK 不显示 隐藏起来  
	//点击课程显示章什么的再通过这个隐藏字段去FIND 显示详细章 节
	public int getLCount() {
		return LCount;
	}

	public void setLCount(int lCount) {
		LCount = lCount;
	}

	public String getFk_L_TPhone() {
		return fk_L_TPhone;
	}

	public void setFk_L_TPhone(String fk_L_TPhone) {
		this.fk_L_TPhone = fk_L_TPhone;
	}

	@Override
	public String toString() {
		return "Lessons [pk_LId=" + pk_LId + ", LName=" + LName + ", LInfo="
				+ LInfo + ", LIcon=" + LIcon + ", fk_L_TPhone=" + fk_L_TPhone
				+ ", LCount=" + LCount + "]";
	}

	public String getPk_LId() {
		return pk_LId;
	}

	public void setPk_LId(String pk_LId) {
		this.pk_LId = pk_LId;
	}

	public String getLName() {
		return LName;
	}

	public void setLName(String lName) {
		LName = lName;
	}

	public String getLInfo() {
		return LInfo;
	}

	public void setLInfo(String lInfo) {
		LInfo = lInfo;
	}

	public String getLIcon() {
		return LIcon;
	}

	public void setLIcon(String lIcon) {
		LIcon = lIcon;
	}

}
