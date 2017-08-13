package com.example.ding.study.service;


import com.example.ding.study.LoginActivity;
import com.example.ding.study.consts.MyHttpURL;
import com.example.ding.study.exception.UserException;
import com.example.ding.study.http.LoginAndRegisterException;
import com.example.ding.study.http.MyHttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户操作实现类
 * Created by ding on 2015/10/17.
 */
public class UserServiceImpl implements UserService {

    private HttpClient client = null ;

    /**
     * 用户登陆
     * @param pk_SPhone 手机号码
     * @param sKey 密码
     * @throws Exception
     */
    @Override
    public void userLogin(String pk_SPhone, String sKey) throws Exception {

        /**
         * 测试用
         */
        //沉睡3秒，模拟网络连接
        Thread.sleep(3000);
        if(pk_SPhone.equals(sKey)){

        }else{
            throw new UserException(LoginActivity.MSG_LOGIN_FAILED);
        }


//        //得到HttpClient对象
//        client = MyHttpClient.getClient();
//        /**
//         * 为 post 请求设置参数
//         * NameValuePair--->  List<NameValuePair>  --->  HttpEntity --->  HttpPost --->  HttpClient
//         */
//        //通过uri 创建 post请求
//        HttpPost post = new HttpPost(MyHttpURL.LOGIN_URL);
//        //封装需要发送给服务器的数据
//        NameValuePair param_pk_SPhone = new BasicNameValuePair("pk_SPhone",pk_SPhone);
//        NameValuePair param_sKey = new BasicNameValuePair("sKey",sKey);
//        //把参数放入list中
//        List<NameValuePair> parameters = new ArrayList<>();
//        parameters.add(param_pk_SPhone);
//        parameters.add(param_sKey);
//        //把参数放入post
//        post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
//        //发送post请求，并获得回应
//        HttpResponse response=client.execute(post);
//
//        //状态码
//        int statusCode = response.getStatusLine().getStatusCode();
//        //服务器连接失败
//        if(statusCode!=HttpStatus.SC_OK){
//            throw new UserException("服务器连接失败");
//        }
//
//        String result = EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
//        if (result.equals("success")){
//
//        }else{
//            throw new UserException(LoginActivity.MSG_LOGIN_FAILED);
//        }

    }

    @Override
    public void userRegister(String pk_SPhone, String sKey) throws Exception {

        client = MyHttpClient.getClient();
        //由url创建post请求
        HttpPost post = new HttpPost(MyHttpURL.REGISTER_URL);

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("pk_SPhone",pk_SPhone));
        parameters.add(new BasicNameValuePair("sKey", sKey));

        post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));

        HttpResponse response = client.execute(post);
        //服务器连接失败
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            throw new UserException("服务器连接失败");
        }

        String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
        if (result.equals("success")){

        }else{
            throw new UserException("注册失败");
        }

//        int type = Integer.parseInt(EntityUtils.toString(response.getEntity(), "utf-8").trim());                //获取返回码
//        if(type == 0){
//            throw new LoginAndRegisterException("注册失败");
//        }else if(type ==-1){
//            throw new LoginAndRegisterException("已存在此用户");
//        }

    }

    /**
     * 完善个人信息
     * @param SName
     * @param SHeadIcon
     * @param SAge
     * @param SGender
     * @param SNickName
     * @param SSchool
     * @param SDepartment
     * @param SMajor
     * @param SClass
     * @param SDefaultPhone
     * @param SEmail
     * @param SRegistTime
     * @throws Exception
     */
    public void userCompleteInformation(
            String SName, String SHeadIcon, String SAge, String SGender, String SNickName,
            String SSchool, String SDepartment, String SMajor, String SClass, String SDefaultPhone,
            String SEmail, String SRegistTime) throws Exception {

        client = MyHttpClient.getClient();
        //由url创建post请求
        HttpPost post = new HttpPost(MyHttpURL.USER_COMPLETE_INFORMATION_URL);

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("SName",SName));
        parameters.add(new BasicNameValuePair("SHeadIcon", SHeadIcon));
        parameters.add(new BasicNameValuePair("SGender", SGender));
        parameters.add(new BasicNameValuePair("SNickName", SNickName));
        parameters.add(new BasicNameValuePair("SDepartment", SDepartment));
        parameters.add(new BasicNameValuePair("SMajor", SMajor));
        parameters.add(new BasicNameValuePair("SClass", SClass));
        parameters.add(new BasicNameValuePair("SDefaultPhone", SDefaultPhone));
        parameters.add(new BasicNameValuePair("SEmail", SEmail));
        parameters.add(new BasicNameValuePair("SRegistTime", SRegistTime));

        post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));

        HttpResponse response = client.execute(post);
        //服务器连接失败
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            throw new LoginAndRegisterException("服务器连接失败");
        }

        String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
        if (result.equals("success")){

        }else{
            throw new UserException("完善个人信息失败");
        }
    }


//    @Override
//    public void userRegister(String id, String password, List<String> hobbies) throws Exception {
//
//     //   HttpClient client = new DefaultHttpClient();
//        String uri = "http://192.168.0.8:8080/register.do";
    //   HttpPost post = new HttpPost(uri);

        /**
         * Json数据的封装
         */
//        JSONObject object = new JSONObject();
//        object.put("LoginId", id);
//        JSONArray array = new JSONArray();
//        if (hobbies != null) {
//            for (String temp : hobbies) {
//                array.put(temp);
//            }
//        }
//        object.put("hobbies", array);

      //  NameValuePair parameter = new BasicNameValuePair("Date", object.toString());
       // List<NameValuePair> params = new ArrayList<NameValuePair>();
        //params.add(parameter);

       // post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
       // HttpResponse response = client.execute(post);

       // int statusCode = response.getStatusLine().getStatusCode();

        //请求成功
    //    if (statusCode != HttpStatus.SC_OK) {

     //   }

        /**
         * 从相应中取得服务器的返回结果
         */
       // String results = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

        //解析json数据
      //  JSONObject jsonResults = new JSONObject(results);

//        String result = jsonResults.getString("result");
//        if (result.equals("success")) {
//            //注册成功
//        } else {
//            //注册失败
//            String errorMsg = jsonResults.getString("errorMsg");
//
//        }


   // }



}



