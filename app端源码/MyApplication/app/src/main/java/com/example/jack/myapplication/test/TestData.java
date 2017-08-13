package com.example.jack.myapplication.test;

import android.content.Context;

import com.example.jack.myapplication.R;
import com.example.jack.myapplication.entity.Department;
import com.example.jack.myapplication.entity.Lesson;
import com.example.jack.myapplication.entity.LessonPart;
import com.example.jack.myapplication.entity.LessonSection;
import com.example.jack.myapplication.entity.LessonTest;

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

    public static String[]  getDepartmentsForArray(){
        return new String[]{"互联网系","金融系","工管系","外语系","法律系","财传系",
                "应数系"," 公管系","信管系","会计系","保险系","经贸系","劳经系","其他"};
    }

    public static List<Department> getDepartments(){

        List<Department> departments = new ArrayList<Department>();

        Department department1 = new Department();
        department1.setDName("互联网系");
        department1.setPk_DId("0");
        departments.add(department1);

        Department department2 = new Department();
        department2.setDName("金融系");
        department2.setPk_DId("1");
        departments.add(department2);

        Department department3 = new Department();
        department3.setDName("工管系");
        department3.setPk_DId("2");
        departments.add(department3);

        Department department4 = new Department();
        department4.setDName("外语系");
        department4.setPk_DId("3");
        departments.add(department4);

        Department department5 = new Department();
        department5.setDName("法律系");
        department5.setPk_DId("4");
        departments.add(department5);

        Department department6 = new Department();
        department6.setDName("财传系");
        department6.setPk_DId("5");
        departments.add(department6);

        Department department7 = new Department();
        department7.setDName("应数系");
        department7.setPk_DId("6");
        departments.add(department7);

        Department department8 = new Department();
        department8.setDName("公管系");
        department8.setPk_DId("7");
        departments.add(department8);

        Department department9 = new Department();
        department9.setDName("信管系");
        department9.setPk_DId("8");
        departments.add(department9);

        Department department10 = new Department();
        department10.setDName("会计系");
        department10.setPk_DId("9");
        departments.add(department10);

        Department department11 = new Department();
        department11.setDName("保险系");
        department11.setPk_DId("10");
        departments.add(department11);

        Department department12 = new Department();
        department12.setDName("经贸系");
        department12.setPk_DId("11");
        departments.add(department12);

        Department department13 = new Department();
        department13.setDName("劳经系");
        department13.setPk_DId("12");
        departments.add(department13);

        return departments ;
    }


}
