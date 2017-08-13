package com.example.jack.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.jack.myapplication.R;
import com.example.jack.myapplication.activity.user.LoginActivity;
import com.example.jack.myapplication.utils.SharedUtils;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeStartActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去掉titleBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome_start);

        //定时器:实现延时跳转
        Timer timer = new Timer();
        //延时3秒执行任务
        timer.schedule(new Task(), 3000);
    }

    //定时器任务:实现页面跳转
    class Task extends TimerTask {
        @Override
        public void run() {
            //先判断是不是第一次打开软件，
            if (SharedUtils.getWelcomeBoolean(getBaseContext())) {
                //如果不是第一次打开，则再判断是否已经登陆过，
                if (SharedUtils.getLoginBoolean(getBaseContext())) {
                    //如果已经登录过，则直接调到主界面
                    startActivity(new Intent(WelcomeStartActivity.this, MainActivity.class));
                } else {
                    //否则，调到登陆界面
                    startActivity(new Intent(WelcomeStartActivity.this, LoginActivity.class));
                }
            }
            //  如果是第一次打开软件，则跳转到引导页面
            else {
                startActivity(new Intent(WelcomeStartActivity.this, WelcomeGuideActivity.class));
                //将已经第一次打开的标志设为true
                SharedUtils.putWelcomeBoolean(getBaseContext(), true);
            }
            //结束打开页面
            finish();
        }
    }
}
