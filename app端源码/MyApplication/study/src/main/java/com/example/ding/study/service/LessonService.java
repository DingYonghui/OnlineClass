package com.example.ding.study.service;


import com.example.ding.study.entity.Lesson;
import com.example.ding.study.entity.LessonSection;

import java.util.List;

/**
 * Created by ding on 2015/10/21.
 */
public interface LessonService {

    //得到课程对象
    public List<Lesson> getLessons(String url) throws Exception ;
    //得到课程章节
    public List<LessonSection> getLessonChapters(String url) throws Exception ;

}
