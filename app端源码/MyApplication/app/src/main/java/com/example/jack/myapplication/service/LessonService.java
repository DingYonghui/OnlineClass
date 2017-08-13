package com.example.jack.myapplication.service;

import com.example.jack.myapplication.entity.Lesson;

import java.util.List;

/**
 * Created by ding on 2015/10/21.
 */
public interface LessonService {
    //得到课程对象
    public List<Lesson> getLessons(String url) throws Exception ;
}
