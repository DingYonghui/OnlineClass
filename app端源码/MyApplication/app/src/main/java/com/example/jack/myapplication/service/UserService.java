package com.example.jack.myapplication.service;


import android.content.Context;

/**
 * Created by ding on 2015/10/17.
 */
public interface UserService {

    //登陆
    public void userLogin(String id, String password) throws Exception;
    //注册
    public void userRegister(String id, String password,String sEmail,String sDepartment) throws Exception;
    //完善个人信息
    public void completeUserInformation(String pk_SPhone,
            String SName, String SHeadIcon,
            String SAge, String SGender, String SNickName,
            String SSchool, String SDepartment, String SMajor,
            String SClass, String SDefaultPhone, String SEmail,
            String SRegistTime) throws Exception ;

    public String getUserInformation(String pk_SPhone, String sKey,Context context) throws Exception;
}
