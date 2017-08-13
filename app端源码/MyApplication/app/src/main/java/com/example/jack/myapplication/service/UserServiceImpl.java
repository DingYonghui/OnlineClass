package com.example.jack.myapplication.service;



import android.content.Context;
import android.util.Log;

import com.example.jack.myapplication.activity.user.LoginActivity;
import com.example.jack.myapplication.consts.MyHttpURL;
import com.example.jack.myapplication.entity.Lesson;
import com.example.jack.myapplication.exception.UserException;
import com.example.jack.myapplication.http.HttpUtils;
import com.example.jack.myapplication.utils.SharedUtils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
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
        //沉睡1秒，模拟网络连接
        //Thread.sleep(1000);
//        if(pk_SPhone.equals(sKey)){
//
//        }else{
//            throw new Exception(LoginActivity.MSG_LOGIN_FAILED);
//        }


        //得到HttpClient对象
        client = HttpUtils.getClient();
        /**
         * 为 post 请求设置参数
         * NameValuePair--->  List<NameValuePair>  --->  HttpEntity --->  HttpPost --->  HttpClient
         */
        //通过uri 创建 post请求
        HttpPost post = new HttpPost(MyHttpURL.LOGIN_URL);
        Log.i("登录url", MyHttpURL.LOGIN_URL);
        //封装需要发送给服务器的参数数据
        NameValuePair param_pk_SPhone = new BasicNameValuePair("pk_SPhone",pk_SPhone);
        NameValuePair param_sKey = new BasicNameValuePair("sKey",sKey);
        //把参数放入list中
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(param_pk_SPhone);
        parameters.add(param_sKey);
        //把参数放入post
        post.setEntity(new UrlEncodedFormEntity(parameters,HTTP.UTF_8));
        //发送post请求，并获得回应
        HttpResponse response=client.execute(post);

        //状态码
        int statusCode = response.getStatusLine().getStatusCode();
        //服务器连接失败
        if(statusCode!=HttpStatus.SC_OK){
            throw new UserException("服务器连接失败");
        }

        String result = EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
        if (result.equals("1")){

        }else{
            throw new UserException(LoginActivity.MSG_LOGIN_FAILED);
        }

    }

    /**
     * 注册
     * @param pk_SPhone
     * @param sKey
     * @throws Exception
     */
    @Override
    public void userRegister(String pk_SPhone, String sKey,String sEmail,String sDepartment) throws Exception {

        /**
         * 测试用
         */
        //沉睡3秒，模拟网络连接
       // Thread.sleep(1000);
//        if(pk_SPhone.equals(sKey)){
//
//        }else{
//            throw new Exception(LoginActivity.MSG_LOGIN_FAILED);
//        }

        client = HttpUtils.getClient();
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
        if (result.equals("1")){

        }else if(result.equals("0")){
            throw new UserException("注册失败");
        }else if(result.equals("-1")){
            throw new UserException("用户已注册");
        }

    }

    /**
     *  得到用户个人信息
     * @param pk_SPhone
     * @param sKey
     */
    public String getUserInformation(String pk_SPhone, String sKey,Context context) throws Exception{

        client = HttpUtils.getClient();
        //由url创建post请求
        HttpPost post = new HttpPost(MyHttpURL.USER_URL);

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("status","getStudentInfo"));
        parameters.add(new BasicNameValuePair("pk_SPhone", pk_SPhone));
        post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));

        HttpResponse response = client.execute(post);

        //服务器连接失败
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            throw new UserException("服务器连接失败");
        }

        String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
        if(result.equals("0")){
            throw new UserException("获取学生信息失败");
        }
        Log.i("学生个人信息为：", result.toString());
        return result;

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
    public void completeUserInformation(
            String pk_SPhone, String SName, String SHeadIcon,
            String SAge, String SGender, String SNickName,
            String SSchool, String SDepartment, String SMajor,
            String SClass, String SDefaultPhone,
            String SEmail, String SRegistTime) throws Exception {

        Log.i("提示：","进入了completeUserInformation方法");

        Log.i("个人信息：",pk_SPhone);
        Log.i("个人信息：",SName);
        Log.i("个人信息：",SHeadIcon);
        Log.i("个人信息：",SAge);
        Log.i("个人信息：",SGender);
        Log.i("个人信息：",SNickName);
        Log.i("个人信息：",SSchool);
        Log.i("个人信息：",SDepartment);
        Log.i("个人信息：",SMajor);
        Log.i("个人信息：",SClass);
        Log.i("个人信息：",SDefaultPhone);
        Log.i("个人信息：",SEmail);
        Log.i("个人信息：",SRegistTime);

        client = HttpUtils.getClient();
        //由url创建post请求
        HttpPost post = new HttpPost(MyHttpURL.USER_URL);

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("pk_SPhone",pk_SPhone));
        parameters.add(new BasicNameValuePair("SName",SName));
        parameters.add(new BasicNameValuePair("SHeadIcon", SHeadIcon));
        parameters.add(new BasicNameValuePair("SGender", SGender));
        parameters.add(new BasicNameValuePair("SNickName", SNickName));
        parameters.add(new BasicNameValuePair("SSchool", SSchool));
        parameters.add(new BasicNameValuePair("SDepartment", SDepartment));
        parameters.add(new BasicNameValuePair("SMajor", SMajor));
        parameters.add(new BasicNameValuePair("SClass", SClass));
        parameters.add(new BasicNameValuePair("SDefaultPhone", SDefaultPhone));
        parameters.add(new BasicNameValuePair("SEmail", SEmail));
        parameters.add(new BasicNameValuePair("SRegistTime", SRegistTime));
        parameters.add(new BasicNameValuePair("status", "completeUserInformation"));


        /**
         * Json数据的封装
         */
//        JSONObject object = new JSONObject();
//        object.put("data", parameters);

        post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));

        HttpResponse response = client.execute(post);
        //服务器连接失败
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            throw new Exception("服务器连接失败");
        }

        String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
        Log.i("提示：","服务器连接成功且获得回应");
        if (result.equals("1")){

        }else{
            throw new Exception("完善个人信息失败");
        }
    }

    //课程收藏
    public List<Lesson> getMyLesson(String sId){

        String url = new StringBuffer(MyHttpURL.USER_URL).append("?status=getMyLessons&&pk_SPhone=").append(sId).toString();
        Log.i("收藏的课程的url为：",url);
        //所有的课程信息
        List<Lesson> lessons = new ArrayList<Lesson>();
        try {
            //通过url,调用readStream()获得网络数据
            String jsonString = readStream(new URL(url).openStream());
            //Log.i("收藏的课程的url为：",url);
            JSONObject jsonObject;
            Lesson lesson;
            try {
                //找到json数据中名为data的数据
                JSONArray jsonArray = new JSONArray(jsonString);

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    lesson = new Lesson();
                    lesson.setFk_L_TPhone(jsonObject.getString("fk_L_TPhone"));
                    lesson.setPk_LId(jsonObject.getString("pk_LId"));
                    lesson.setLInfo(jsonObject.getString("LInfo"));
                    lesson.setLIcon(jsonObject.getString("LIcon"));
                    lesson.setLName(jsonObject.getString("LName"));
                    lesson.setLCount(Integer.parseInt(jsonObject.getString("LCount")));
                    lessons.add(lesson);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lessons;
    }




    /**
     * 通过is解析读取网络数据
     *
     * @param is
     * @return
     */
    private String readStream(InputStream is) {
        InputStreamReader isr;
        String result = "";
        String line = "";
        try {
            //字节流转化为字符流
            isr = new InputStreamReader(is, "utf-8");
            //缓冲流
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result = result + line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}



