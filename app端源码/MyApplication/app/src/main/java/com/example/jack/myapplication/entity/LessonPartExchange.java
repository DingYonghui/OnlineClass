package com.example.jack.myapplication.entity;

/**
 * 视频小节交流：提问
 * @author ding
 *
 */
public class LessonPartExchange {
	private String fk_PId;//Len 36
	private String pk_PEId;//Len UUID 32
	private String PEByWho; // 昵称 Len 10 
	private String PEByWho_Id;// 提问人ID Len 11(手机号)
	private String PEContent;//Len 80
	private String PETime;//Len 20

	public String getPEByWho_Id() {
		return PEByWho_Id;
	}

	public void setPEByWho_Id(String pEByWho_Id) {
		PEByWho_Id = pEByWho_Id;
	}

	public String getPETime() {
		return PETime;
	}

	public void setPETime(String pETime) {
		PETime = pETime;
	}

	public String getFk_PId() {
		return fk_PId;
	}

	public void setFk_PId(String fk_PId) {
		this.fk_PId = fk_PId;
	}

	public String getPk_PEId() {
		return pk_PEId;
	}

	public void setPk_PEId(String pk_PEId) {
		this.pk_PEId = pk_PEId;
	}

	public String getPEByWho() {
		return PEByWho;
	}

	public void setPEByWho(String pEByWho) {
		PEByWho = pEByWho;
	}

	public String getPEContent() {
		return PEContent;
	}

	public void setPEContent(String pEContent) {
		PEContent = pEContent;
	}

	@Override
	public String toString() {
		return "LessonPartExchange [fk_PId=" + fk_PId + ", pk_PEId=" + pk_PEId
				+ ", PEByWho=" + PEByWho + ", PEByWho_Id=" + PEByWho_Id
				+ ", PEContent=" + PEContent + ", PETime=" + PETime + "]";
	}

}
