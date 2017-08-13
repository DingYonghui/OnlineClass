package com.example.ding.study;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ding.study.exception.UserException;
import com.example.ding.study.service.UserService;
import com.example.ding.study.service.UserServiceImpl;
import com.example.ding.study.utils.SharedUtils;

import org.apache.http.conn.ConnectTimeoutException;

import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

public class LoginActivity extends Activity {

    //控件
    private EditText et_login_id ;
    private EditText et_login_password ;
    private TextView tv_find_back_password ;
    private TextView btn_register;
    private Button btn_login;

    //业务处理类
    private UserService userService = new UserServiceImpl();

    //自定义的Handler
    private IHandler handler = new IHandler(this);

    //业务错误
    private static final int FLAG_LOGIN_SUCCESS = 1 ;
    private static final String MSG_LOGIN_ERROR ="登陆错误" ;
    //private static final String MSG_LOGIN_SUCCESS ="登陆成功" ;
    public static final String MSG_LOGIN_FAILED ="登陆失败，登陆|登陆密码出错" ;

    //loading...
    private static ProgressDialog dialog = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化控件
        initViews();
        //绑定监听者
        initListener();
    }

    //初始化控件
    public void initViews(){
        et_login_id = (EditText)findViewById(R.id.et_login_id);
        et_login_password = (EditText)findViewById(R.id.et_login_password);
        tv_find_back_password = (TextView)findViewById(R.id.tv_find_back_password);
        btn_register = (TextView)findViewById(R.id.btn_register);
        btn_login = (Button)findViewById(R.id.btn_login);
    }
    /**
     * 初始化事件监听
     */
    private void initListener() {
        //登录按钮
        btn_login.setOnClickListener(new View.OnClickListener() {
            String login_id = null ;
            String login_password = null ;
            @Override
            public void onClick(View v) {
                login_id = et_login_id.getText().toString();
                login_password = et_login_password.getText().toString();
                /**
                 * 本地，输入值验证：空、长度等
                 */
                if(login_id.equals("")){
                    //Message对象
                    Message message = new Message();
                    //Bundle对象
                    Bundle data = new Bundle();
                    //bundle对象放入值
                    data.putSerializable("ErrorMsg","登陆账号不能为空");
                    //message对象放入bundle信息
                    message.setData(data);
                    //handler发送消息    : 数据-->bundle-->meg-->handler
                    handler.sendMessage(message);
                    return;
                }

                /**
                 * 判断手机号码是否符合格式
                 */
                if(!login_id.matches("^[1][358][0-9]{9}$")){
                    //Message对象
                    Message message = new Message();
                    //Bundle对象
                    Bundle data = new Bundle();
                    //bundle对象放入值
                    data.putSerializable("ErrorMsg","手机号不符合格式");
                    //message对象放入bundle信息
                    message.setData(data);
                    //handler发送消息    : 数据-->bundle-->meg-->handler
                    handler.sendMessage(message);
                    return;
                }

                if(login_password.equals("")){
                    //Message对象
                    Message message = new Message();
                    //Bundle对象
                    Bundle data = new Bundle();
                    //bundle对象放入值
                    data.putSerializable("ErrorMsg", "登陆密码不能为空");
                    //message对象放入bundle信息
                    message.setData(data);
                    //handler发送消息    : 数据-->bundle-->meg-->handler
                    handler.sendMessage(message);
                    return;
                }

                /**
                 * loading.....
                 */
                if(dialog == null){
                    dialog = new ProgressDialog(LoginActivity.this);
                }
                dialog.setTitle("请等待");
                dialog.setMessage("登录中...");
                //设置不能取消
                dialog.setCancelable(false);
                dialog.show();

                //副线程,网络访问
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Log.i("joinRun", "进入线程了");
                        try{
                            userService.userLogin(login_id,login_password);
                            handler.sendEmptyMessage(FLAG_LOGIN_SUCCESS);
                            //登陆成功后，将登陆的标志设为true
                            SharedUtils.putLoginBoolean(getBaseContext(),true);
                        }catch (ConnectTimeoutException ce){
                            ce.printStackTrace();
                            //Message对象
                            Message message = new Message();
                            //Bundle对象
                            Bundle data = new Bundle();
                            //bundle对象放入值
                            data.putSerializable("ErrorMsg","请求服务器超时");
                            //message对象放入bundle信息
                            message.setData(data);
                            //handler发送消息    : 数据-->bundle-->meg-->handler
                            handler.sendMessage(message);
                        } catch (SocketTimeoutException se){
                            se.printStackTrace();
                            //Message对象
                            Message message = new Message();
                            //Bundle对象
                            Bundle data = new Bundle();
                            //bundle对象放入值
                            data.putSerializable("ErrorMsg","服务器响应超时");
                            //message对象放入bundle信息
                            message.setData(data);
                            //handler发送消息    : 数据-->bundle-->meg-->handler
                            handler.sendMessage(message);
                        } catch (UserException ue){
                            ue.printStackTrace();
                            Message message = new Message();
                            Bundle data = new Bundle();
                            data.putSerializable("ErrorMsg", ue.getMessage());
                            message.setData(data);
                            handler.sendMessage(message);
                        } catch (Exception e){
                            e.printStackTrace();
                            //Message对象
                            Message message = new Message();
                            //Bundle对象
                            Bundle data = new Bundle();
                            //bundle对象放入值
                            data.putSerializable("ErrorMsg",MSG_LOGIN_ERROR);
                            //message对象放入bundle信息
                            message.setData(data);
                            //handler发送消息    : 数据-->bundle-->meg-->handler
                            handler.sendMessage(message);
                        }

                    }
                });
                //启动线程
                thread.start();

            }

        });

        //注册按钮的监听
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //登录失败的提示
    private void showTip(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //登登录成功的操作
    private void startMainActivity(){
        //登陆成功后跳转到主界面
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    /**
     * 自定义的Handler
     */
    private static class IHandler extends Handler {
        //弱引用
        private final WeakReference<Activity> mActivity ;

        public IHandler(LoginActivity activity){
            mActivity = new WeakReference<Activity>(activity);
        }
        public void handleMessage(Message msg) {
            //((LoginActivity)mActivity.get()).showTip();

            if(dialog != null){
                //使dialog消失
                dialog.dismiss();
            }

            int flag = msg.what;
            switch (flag){
                case 0://登陆失败
                    String errorMsg = (String)msg.getData().getSerializable("ErrorMsg");
                    ((LoginActivity)mActivity.get()).showTip(errorMsg);
                    break;
                case 1://登陆成功
                    //((LoginActivity)mActivity.get()).showTip(MSG_LOGIN_SUCCESS);
                    ((LoginActivity)mActivity.get()).startMainActivity();
                    break;
                default:
                    break;
            }

        }

    }

}
