package com.example.jack.myapplication.entity;

import java.io.Serializable;

/**
 * Created by ding on 2015/10/11.
 */
public class LessonTest implements Serializable {

    private int lesson_id;
    private String lesson_icon;
    private String lesson_name;
    private int lesson_peroid;
    private int lesson_minute;
    private String lesson_teacher;

    public LessonTest(int lesson_id, String lesson_icon, String lesson_name, int lesson_peroid, int lesson_minute, String lesson_teacher) {
        this.lesson_id = lesson_id;
        this.lesson_icon = lesson_icon;
        this.lesson_name = lesson_name;
        this.lesson_peroid = lesson_peroid;
        this.lesson_minute = lesson_minute;
        this.lesson_teacher = lesson_teacher;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getLesson_icon() {
        return lesson_icon;
    }

    public void setLesson_icon(String lesson_icon) {
        this.lesson_icon = lesson_icon;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public int getLesson_peroid() {
        return lesson_peroid;
    }

    public void setLesson_peroid(int lesson_peroid) {
        this.lesson_peroid = lesson_peroid;
    }

    public int getLesson_minute() {
        return lesson_minute;
    }

    public void setLesson_minute(int lesson_minute) {
        this.lesson_minute = lesson_minute;
    }

    public String getLesson_teacher() {
        return lesson_teacher;
    }

    public void setLesson_teacher(String lesson_teacher) {
        this.lesson_teacher = lesson_teacher;
    }
}
