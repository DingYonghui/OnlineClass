package com.example.ding.study.test;

import android.content.Context;


import com.example.ding.study.R;
import com.example.ding.study.entity.LessonPart;
import com.example.ding.study.entity.LessonSection;
import com.example.ding.study.entity.LessonTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  测试的数据：
 *  取得系名的数据:从xml文件获取
 *  取得课程数据：自己定义
 *  得到章节数据：自己定义
 *  得到小节数据：自己定义
 * Created by ding on 2015/10/13.
 */
public class TestData {

    /**
     *  取得系名的数据
     */
    public static List<Map<String, String>> getDepartmentCategory(Context context ) {
        //从配置文件拿到系名数据
        String[] lessons_category = context.getResources().getStringArray(R.array.department_category);

        //保存系名数据
        List<Map<String, String>> categories = new ArrayList<Map<String, String>>();

        for (int i = 0; i < lessons_category.length; i++) {
            Map<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("department_category_title", lessons_category[i]);
            categories.add(hashMap);
        }
        return categories;
    }

    /**
     *  测试用：取得课程数据
     *  封装lessons数据到 List<Map<String, Object>>,用于给适配器提供数据
     */
    public static List<LessonTest> getLessonsData() {

        List<LessonTest> list = new ArrayList<LessonTest>();

        LessonTest lessonTest = new LessonTest(1, "ding", "Android开发", 8, 57, "苏格拉底");

        for (int i = 0; i < 30; i++) {
            list.add(lessonTest);
        }
        return list;
    }

    /**
     * 得到章节数据
     * @return list
     */
    public static List<LessonSection> getLessonSectionData(){

        List<LessonSection> list = new ArrayList<LessonSection>();

        LessonSection lessonTest = new LessonSection("第一章 java基础","http://www.baidu.com","5",54);

        for (int i = 0; i < 30; i++) {
            list.add(lessonTest);
        }
        return list;
    }

    /**
     * 得到小节数据
     * @return
     */
    public static List<LessonPart> getLessonPart(){
        List<LessonPart> list = new ArrayList<LessonPart>();
        String PVideoPath = "http://www.baidu.com" ;
        String PIcon = "http://www.baidu.com" ;
        LessonPart lessonTest = new LessonPart("java环境搭建",PVideoPath,PIcon,"55",300);
        for (int i = 0; i < 30; i++) {
            list.add(lessonTest);
        }
        return list;
    }

}
