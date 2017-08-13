package com.example.ding.study.entity;

import java.io.Serializable;

/**
 * 课程章节
 * Created by ding on 2015/10/21.
 */
public class LessonSection implements Serializable {

    private String pk_SId;  //课程Id+_+章数 Len32+2
    private String fk_LId;  //Len 32

    //课程名称
    private String SName;   //Len 20
    //课程信息
    private String SInfo;   //Len 50
    //学习人数
    private int SCount;    //Len 255
    //课程图标
    private String SIcon;//100
    //课时：几小节
    private String STime;//20

    public LessonSection() {

    }

    public LessonSection(String SName, String SIcon, String STime, int SCount) {
        this.SName = SName;
        this.SIcon = SIcon;
        this.STime = STime;
        this.SCount = SCount;
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
