package com.example.ding.study.service;


/**
 * Created by ding on 2015/10/17.
 */
public interface UserService {

    //登陆
    public void userLogin(String id, String password) throws Exception;
    //注册
    public void userRegister(String id, String password) throws Exception;
    //完善个人信息
    public void userCompleteInformation(
            String SName, String SHeadIcon,
            String SAge, String SGender, String SNickName,
            String SSchool, String SDepartment, String SMajor,
            String SClass, String SDefaultPhone, String SEmail,
            String SRegistTime) throws Exception ;
}
