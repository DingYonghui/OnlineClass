package com.example.jack.myapplication.activity.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

public class RegisterActivity extends Activity {

    private static ProgressDialog dialog = null;

    private Button btn_return_to_login;

    private EditText et_phone_id;
    private EditText et_set_a_password;
    private CheckBox cb_password_can_be_see ;
    private EditText et_resure_a_password ;
    private Spinner spinner_department;
    private EditText et_email_address;

    private Button btn_register;
    private TextView tv_have_accounts ;

    //业务处理类
    private UserService userService = new UserServiceImpl();
    //自定义的IHandler
    private IHandler handler = new IHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        initListener();
    }

    //初始化
    public void initViews() {
        btn_return_to_login = (Button) findViewById(R.id.btn_return_to_login);
        et_phone_id = (EditText) findViewById(R.id.et_phone_id);
        et_set_a_password = (EditText) findViewById(R.id.et_set_a_password);
        cb_password_can_be_see = (CheckBox)findViewById(R.id.cb_password_can_be_see);
        et_resure_a_password = (EditText)findViewById(R.id.et_resure_a_password);
        spinner_department = (Spinner) findViewById(R.id.spinner_department);
        et_email_address = (EditText)findViewById(R.id.et_email_address);
        btn_register = (Button) findViewById(R.id.btn_register);
        tv_have_accounts = (TextView)findViewById(R.id.tv_have_accounts);
    }
    private String pk_SPhone ;
    private String SKey ;
    private String sDepartment;
    private String sEmail;
    void initListener() {

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pk_SPhone = et_phone_id.getText().toString();
                SKey = et_set_a_password.getText().toString();
                String sureKey = et_resure_a_password.getText().toString();
                sDepartment = spinner_department.getSelectedItem().toString();
                sEmail = et_email_address.getText().toString();
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

                if(!sureKey.equals(SKey)){
                    showTip("确认密码和密码必须相同");
                    return;
                }

                /**
                 * loading.....
                 */
                if (dialog == null) {
                    dialog = new ProgressDialog(RegisterActivity.this);
                }
                dialog.setTitle("请等待");
                dialog.setMessage("注册中...");
                //设置不能取消
                dialog.setCancelable(false);
                dialog.show();

                //副线程
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //Log.i("joinRun", "进入线程了");
                        try {
                            //调用业务处理类的注册方法
                            userService.userRegister(pk_SPhone, SKey,sEmail,sDepartment);
                            //发送空的消息
                            handler.sendEmptyMessage(1);
                        } catch (ConnectTimeoutException ce) {
                            ce.printStackTrace();
                            informMainUI("请求服务器超时");
                        } catch (SocketTimeoutException se) {
                            se.printStackTrace();
                            informMainUI("服务器响应超时");
                        } catch (UserException ue) {
                            ue.printStackTrace();
                            informMainUI(ue.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            informMainUI("注册失败");
                        }
                    }
                });
                //启动线程
                thread.start();

            }
        });

        //返回登录界面
        btn_return_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        spinner_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //获取被选中的position
                //View view1 = (TextView)parent.getChildAt(position);
                //Toast.makeText(getBaseContext(),"选择了："+view1.,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cb_password_can_be_see.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb_password_can_be_see.isChecked()) {
                    //设置EditText的密码为可见的
                    et_set_a_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置密码为隐藏的
                    et_set_a_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        tv_have_accounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
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

    //注册失败时：信息提示
    public void showTip(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //注册成功的操作：先问是否立即登录，如果是则跳转到主界面
    private void startMainActivity() {
        //弹出框
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("提示").setMessage("注册成功，是否立即登录？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        //登陆标志设为true
                        SharedUtils.putLoginBoolean(getBaseContext(), true);
                        //将账号密码存起来
                        SharedUtils.putUserId(getBaseContext(), pk_SPhone);
                        SharedUtils.putUserPassword(getBaseContext(), SKey);
                    }
                })
                .show();
    }

    /**
     * 自定义的Handler
     */
    private class IHandler extends Handler {
        //弱引用
        private final WeakReference<Activity> mActivity;

        public IHandler(RegisterActivity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }

        public void handleMessage(Message msg) {

            if (dialog != null) {
                //使dialog消失
                dialog.dismiss();
            }

            int flag = msg.what;
            switch (flag) {
                case 0://注册失败
                    String errorMsg = (String) msg.getData().getSerializable("ErrorMsg");
                    ((RegisterActivity) mActivity.get()).showTip(errorMsg);
                    break;
                case 1://注册成功
                    ((RegisterActivity) mActivity.get()).showTip("注册成功");
                    //执行注册成功时的操作
                    startMainActivity();
                    break;
                default:
                    break;
            }
        }
    }

}
