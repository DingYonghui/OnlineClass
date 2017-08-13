package com.example.jack.myapplication.activity.user;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jack.myapplication.R;
import com.example.jack.myapplication.activity.MainActivity;
import com.example.jack.myapplication.exception.UserException;
import com.example.jack.myapplication.service.UserService;
import com.example.jack.myapplication.service.UserServiceImpl;
import com.example.jack.myapplication.utils.SharedUtils;

import org.apache.http.conn.ConnectTimeoutException;

import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

public class LoginActivity extends Activity {

    //控件
    private EditText et_login_id;
    private EditText et_login_password;
    private TextView tv_find_back_password;
    private TextView tv_register;
    private Button btn_login;

    //业务处理类
    private UserService userService = new UserServiceImpl();

    //自定义的Handler
    private IHandler handler = new IHandler(this);

    //登录成功的标记
    private static final int FLAG_LOGIN_SUCCESS = 1;
    //业务错误
    private static final String MSG_LOGIN_ERROR = "登陆错误";
    //private static final String MSG_LOGIN_SUCCESS ="登陆成功" ;
    public static final String MSG_LOGIN_FAILED = "登陆失败，登陆|登陆密码出错";

    //loading...
    private static ProgressDialog dialog = null;

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
    public void initViews() {
        et_login_id = (EditText) findViewById(R.id.et_login_id);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        tv_find_back_password = (TextView) findViewById(R.id.tv_find_back_password);
        tv_register = (TextView) findViewById(R.id.tv_register);
        btn_login = (Button) findViewById(R.id.btn_login);
    }
    /**
     * 初始化事件监听
     */
    private void initListener() {
        //登录按钮
        btn_login.setOnClickListener(new View.OnClickListener() {
            String pk_SPhone = "";
            String SKey = "";
            @Override
            public void onClick(View v) {
                pk_SPhone = et_login_id.getText().toString();
                SKey = et_login_password.getText().toString();
                /**
                 * 本地，输入值验证：空、长度等
                 */
                if (pk_SPhone.equals("")) {
                    showTip("登陆账号不能为空");
                    return;
                }

                /**
                 * 判断手机号码是否符合格式
                 */
                if (!pk_SPhone.matches("^[1][358][0-9]{9}$")) {
                    showTip("手机号不符合格式");
                    return;
                }

                if (SKey.equals("")) {
                    showTip("登陆密码不能为空");
                    return;
                }

                /**
                 * loading.....
                 */
                if (dialog == null) {
                    dialog = new ProgressDialog(LoginActivity.this);
                }
                dialog.setTitle("请等待");
                dialog.setMessage("登录中...");
                //设置不能取消
                dialog.setCancelable(false);
                dialog.show();

                //副线程,网络访问
               new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //Log.i("joinRun", "进入线程了");
                        try {
                            //调用业务层登录的方法
                            userService.userLogin(pk_SPhone, SKey);
                            //登录成功，即在业务层中没有抛出错误，则发生消息通知UI线程做出反应
                            handler.sendEmptyMessage(FLAG_LOGIN_SUCCESS);
                            //登陆成功后，将登陆的标志设为true
                            SharedUtils.putLoginBoolean(getBaseContext(), true);
                            //登陆成功后，保存userId
                            SharedUtils.putUserId(getBaseContext(),pk_SPhone);
                            //登陆成功后，保存密码
                            SharedUtils.putUserPassword(getBaseContext(),SKey);
                        }
                        //服务器超时异常
                        catch (ConnectTimeoutException ce) {
                            ce.printStackTrace();
                           informMainUI("请求服务器超时");
                        }
                        //服务器响应超时异常
                        catch (SocketTimeoutException se) {
                            se.printStackTrace();
                            informMainUI("服务器响应超时");
                        }
                        //业务类异常
                        catch (UserException ue) {
                            ue.printStackTrace();
                            informMainUI(ue.getMessage());
                        }
                        //所有异常
                        catch (Exception e) {
                            e.printStackTrace();
                            informMainUI(MSG_LOGIN_ERROR);
                        }
                    }
                }).start();

            }
        });

        //注册按钮的监听
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tv_find_back_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"功能正在开发中",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //通知UI主线程
    public void informMainUI(String msg){
        //Message对象
        Message message = Message.obtain();
        //Bundle对象
        Bundle bundle = new Bundle();
        //bundle对象放入值
        bundle.putSerializable("ErrorMsg",msg );
        //message对象放入bundle信息
        message.setData(bundle);
        //handler发送消息    : 数据-->bundle-->meg-->handler
        handler.sendMessage(message);
    }

    //登录失败的提示
    private void showTip(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //登录成功的操作：跳转到主界面
    private void startMainActivity() {
        finish();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    /**
     * 自定义的Handler
     */
    private static class IHandler extends Handler {

        //弱引用
        private final WeakReference<Activity> mActivity;

        //构造方法：获得本activity
        public IHandler(LoginActivity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }

        //处理发送过来的信息
        public void handleMessage(Message msg) {
            //((LoginActivity)mActivity.get()).showTip();
            if (dialog != null) {
                //使dialog消失
                dialog.dismiss();
            }
            //得到消息的标记
            int flag = msg.what;
            //根据标记判断，作出反应
            switch (flag) {
                case 0://登陆失败：显示错误信息
                    //得到message传来的名字为“ErrorMsg”的字符串
                    String errorMsg = (String) msg.getData().getSerializable("ErrorMsg");
                    //得到LoginActivity的对象
                    ((LoginActivity) mActivity.get()).showTip(errorMsg);
                    break;
                case 1://登陆成功：跳转到主页面
                    //((LoginActivity)mActivity.get()).showTip(MSG_LOGIN_SUCCESS);
                    ((LoginActivity) mActivity.get()).startMainActivity();
                    break;
                default:
                    break;
            }
        }
    }
}
