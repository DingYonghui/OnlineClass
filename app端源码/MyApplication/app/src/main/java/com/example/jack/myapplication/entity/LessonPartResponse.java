package com.example.jack.myapplication.entity;
/**
 * 视频小节交流：回答
 * @author ding
 *
 */
public class LessonPartResponse {
	
	private String fk_PEId;// Len 32
	private String pk_PRId;// Len 32 UUID
	private String PR_ToWho;// Len 11 //回复帖子本身 则默认空  回复回复人 则要写是回复谁
	private String PRContent;// Len 80
	private String PR_ByWho;// Len 11
	private String PR_ByWho_Name;// 昵称 Len 10

	public String getPR_ByWho_Name() {
		return PR_ByWho_Name;
	}

	public void setPR_ByWho_Name(String pR_ByWho_Name) {
		PR_ByWho_Name = pR_ByWho_Name;
	}

	private String PRTime;

	public String getFk_PEId() {
		return fk_PEId;
	}

	public void setFk_PEId(String fk_PEId) {
		this.fk_PEId = fk_PEId;
	}

	public String getPk_PRId() {
		return pk_PRId;
	}

	public void setPk_PRId(String pk_PRId) {
		this.pk_PRId = pk_PRId;
	}

	public String getPR_ToWho() {
		return PR_ToWho;
	}

	public void setPR_ToWho(String pR_ToWho) {
		PR_ToWho = pR_ToWho;
	}

	public String getPRContent() {
		return PRContent;
	}

	public void setPRContent(String pRContent) {
		PRContent = pRContent;
	}

	public String getPR_ByWho() {
		return PR_ByWho;
	}

	public void setPR_ByWho(String pR_ByWho) {
		PR_ByWho = pR_ByWho;
	}

	public String getPRTime() {
		return PRTime;
	}

	public void setPRTime(String pRTime) {
		PRTime = pRTime;
	}

	@Override
	public String toString() {
		return "PartReponse [fk_PEId=" + fk_PEId + ", pk_PRId=" + pk_PRId
				+ ", PR_ToWho=" + PR_ToWho + ", PRContent=" + PRContent
				+ ", PR_ByWho=" + PR_ByWho + ", PR_ByWho_Name=" + PR_ByWho_Name
				+ ", PRTime=" + PRTime + "]";
	}

}
