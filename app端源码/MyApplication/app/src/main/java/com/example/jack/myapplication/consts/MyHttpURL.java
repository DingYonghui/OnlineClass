package com.example.jack.myapplication.consts;

/**
 * Created by ding on 2015/10/19.
 */
public class MyHttpURL {
    public static final String IP = "http://120.24.5.239:8080";
    //用户登陆url
    public static final String LOGIN_URL = IP+"/ClassOnline/LoginServlet";
    //用户注册url
    public static final String REGISTER_URL =  IP+"/ClassOnline/RegisterServlet";
    //用户完善信息的url
    public static final String USER_COMPLETE_INFORMATION_URL =  IP+"/ClassOnline/UserCompleteInformationServlet";
    //课程的url
    public static final String LESSON_URL =  IP+"/ClassOnline/LessonServlet";

    public static final String USER_URL = IP + "/ClassOnline/UserServlet";

    //我的收藏，即学生学习的课程
    public static final String GET_MY_LESSON_URL =  IP+"/ClassOnline/GetMyLessonServlet";

}
