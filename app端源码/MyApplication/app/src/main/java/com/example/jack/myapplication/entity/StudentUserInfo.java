package com.example.jack.myapplication.entity;

/**
 * 学生信息表：完善个人信息时会用到
 */
public class StudentUserInfo {
	private String pk_SPhone;// 11
	private String SNo;// 9
	private String SName;// 10
	private String SHeadIcon;// 100
	private int SAge;// 1
	private int SGender; // 3
	private String SNickName;// 10
	private String SSchool;// 20
	private String SDepartment;// 20
	private String SMajor;// 10
	private String SClass;// 10
	private String SDefaultPhone;// 默认是注册账号
	private String SEmail;// 35
	private String SRegistTime;// 30

	@Override
	public String toString() {
		return "StudentUserInfo [pk_SPhone=" + pk_SPhone + ", SNo=" + SNo
				+ ", SName=" + SName + ", SHeadIcon=" + SHeadIcon + ", SAge="
				+ SAge + ", SGender=" + SGender + ", SNickName=" + SNickName
				+ ", SSchool=" + SSchool + ", SDepartment=" + SDepartment
				+ ", SMajor=" + SMajor + ", SClass=" + SClass
				+ ", SDefaultPhone=" + SDefaultPhone + ", SEmail=" + SEmail
				+ ", SRegistTime=" + SRegistTime + "]";
	}

	public String getPk_SPhone() {
		return pk_SPhone;
	}

	public void setPk_SPhone(String pk_SPhone) {
		this.pk_SPhone = pk_SPhone;
	}

	public String getSNo() {
		return SNo;
	}

	public void setSNo(String sNo) {
		SNo = sNo;
	}

	public String getSName() {
		return SName;
	}

	public void setSName(String sName) {
		SName = sName;
	}

	public String getSHeadIcon() {
		return SHeadIcon;
	}

	public void setSHeadIcon(String sHeadIcon) {
		SHeadIcon = sHeadIcon;
	}

	public int getSAge() {
		return SAge;
	}

	public void setSAge(int sAge) {
		SAge = sAge;
	}

	public int getSGender() {
		return SGender;
	}

	public void setSGender(int sGender) {
		SGender = sGender;
	}

	public String getSNickName() {
		return SNickName;
	}

	public void setSNickName(String sNickName) {
		SNickName = sNickName;
	}

	public String getSSchool() {
		return SSchool;
	}

	public void setSSchool(String sSchool) {
		SSchool = sSchool;
	}

	public String getSDepartment() {
		return SDepartment;
	}

	public void setSDepartment(String sDepartment) {
		SDepartment = sDepartment;
	}

	public String getSMajor() {
		return SMajor;
	}

	public void setSMajor(String sMajor) {
		SMajor = sMajor;
	}

	public String getSClass() {
		return SClass;
	}

	public void setSClass(String sClass) {
		SClass = sClass;
	}

	public String getSDefaultPhone() {
		return SDefaultPhone;
	}

	public void setSDefaultPhone(String sDefaultPhone) {
		SDefaultPhone = sDefaultPhone;
	}

	public String getSEmail() {
		return SEmail;
	}

	public void setSEmail(String sEmail) {
		SEmail = sEmail;
	}

	public String getSRegistTime() {
		return SRegistTime;
	}

	public void setSRegistTime(String sRegistTime) {
		SRegistTime = sRegistTime;
	}

}