package schoolstudy.view.entity;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.List;

/**
 * 这个类主要用于装在全局变量
 */
public class Static {

    public static final String IP = "120.24.5.239";                             //连接服务器的ip地址
    public static final String LOGINURI = "http://"+IP+":8080/ClassOnline/doLogin";                       //登录的uri
    public static final String REGISTERURI = "http://"+IP+":8080/ClassOnline/doRegister";                    //注册的uri
    public static final String GETLESSONS = "http://"+IP+":8080/ClassOnline/getLesson";                    //课程Uri的uri
    public static final String VIDEOTEST = "http://"+IP+":8080/ClassOnline/video/test.3gp";                    //测试视频
    public static final String GETSECTIONANDPARTS = "http://"+IP+":8080/ClassOnline/getSectionAndPart";                    //测试视频
    public static final String DOPAETEXCHANGE = "http://"+IP+":8080/ClassOnline/doPartExchange";                    //测试视频
    public static final String GETPAETEXCHANGE = "http://"+IP+":8080/ClassOnline/getPartExchange";                    //测试视频


    /**
     * 显示等待对话框
     */
    public static ProgressDialog showWaitingDialog(Context context,String content){
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(content);
        dialog.setCancelable(false);
        return dialog;
    }

}
