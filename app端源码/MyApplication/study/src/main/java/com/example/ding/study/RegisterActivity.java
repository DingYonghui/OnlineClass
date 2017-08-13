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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ding.study.exception.UserException;
import com.example.ding.study.service.UserService;
import com.example.ding.study.service.UserServiceImpl;

import org.apache.http.conn.ConnectTimeoutException;

import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

public class RegisterActivity extends Activity {

    private LinearLayout layout_return_to_login;
    private EditText et_phone_id ;
    private TextView tv_obtain_TestGetCode;
    private EditText et_text_message ;
    private EditText et_set_a_password ;
    private TextView tv_register ;
    private Spinner spinner_department ;

    private static ProgressDialog dialog = null ;

    //业务处理类
    private UserService userService = new UserServiceImpl();

    private IHandler handler = new IHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        initListener();
    }


    //初始化
    public void initViews(){
        layout_return_to_login = (LinearLayout)findViewById(R.id.layout_return_to_login);
        et_phone_id = (EditText)findViewById(R.id.et_phone_id);
        tv_obtain_TestGetCode = (TextView)findViewById(R.id.tv_obtain_TestGetCode);
        et_text_message = (EditText)findViewById(R.id.et_text_message);
        et_set_a_password = (EditText)findViewById(R.id.et_set_a_password);
        tv_register = (TextView)findViewById(R.id.tv_register);
        spinner_department = (Spinner)findViewById(R.id.spinner_department);

        //final String[] array_department = getResources().getStringArray(R.array.department_category);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.simple_spiner_department_item,array_department);
        //spinner_department.setAdapter(adapter);
        spinner_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index = parent.getSelectedItemPosition();//获取被选中的position
                //Toast.makeText(getBaseContext(),"选择了："+array_department[index],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void initListener(){
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String phone_id = et_phone_id.getText().toString();
                final String password = et_set_a_password.getText().toString();

                /**
                 * 判断手机号码是否符合格式
                 */
                if(!phone_id.matches("^[1][358][0-9]{9}$")){
                    try {
                        throw new UserException("手机号不符合格式");
                    } catch (UserException e) {
                        e.printStackTrace();
                    }
                }

                /**
                 * loading.....
                 */
                if(dialog == null){
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

                        Log.i("joinRun", "进入线程了");
                        try{
                            userService.userRegister(phone_id,password);
                            handler.sendEmptyMessage(1);
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
                            data.putSerializable("ErrorMsg","注册失败");
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


        //返回登录界面
        layout_return_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });

    }



    /**
     * 自定义的Handler
     */
    private static class IHandler extends Handler {
        //弱引用
        private final WeakReference<Activity> mActivity ;

        public IHandler(RegisterActivity activity){
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
                    ((RegisterActivity)mActivity.get()).showTip(errorMsg);
                    break;
                case 1://登陆成功
                    ((RegisterActivity)mActivity.get()).showTip("注册成功");
                    break;
                default:
                    break;
            }

        }

    }

    //注册失败时的信息提示
    private void showTip(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }




}
