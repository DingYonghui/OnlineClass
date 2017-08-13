package com.example.ding.study;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ding.study.fragment.FragmentMainIndexHome;
import com.example.ding.study.fragment.FragmentMainIndexLesson;
import com.example.ding.study.fragment.FragmentMainIndexMy;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {


    private RadioGroup rbg_main_bottom_tabs ;
    private RadioButton rb_main_index_home ;
    //管理fragment的类
    private FragmentManager fragmentManager = null  ;

    //初始化：绑定控件、实例化fragment、获取数据等
    void init(){
        rbg_main_bottom_tabs = (RadioGroup)findViewById(R.id.rbg_main_bottom_tabs);
        rb_main_index_home = (RadioButton)findViewById(R.id.rb_main_index_home);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化：绑定控件
        this.init();
        //默认选中首页按钮
        rb_main_index_home.setChecked(true);
        //为按钮组设置监听者
        rbg_main_bottom_tabs.setOnCheckedChangeListener(this);
        //初始化：显示首页
        changeFragment(new FragmentMainIndexHome(), false);
    }

    /**
     * 按钮组监听者要实现的方法：当按钮选中时
     * @param group 按钮组
     * @param checkedId 被点击的按钮的id
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_main_index_home://首页
                changeFragment(new FragmentMainIndexHome(),true);
                break;
            case R.id.rb_main_index_lesson://课程
                changeFragment(new FragmentMainIndexLesson(),true);
                break;
            case R.id.rb_main_index_my://我的
                changeFragment(new FragmentMainIndexMy(),true);
                break;
            default:
                break;
        }
    }

    //切换不同的fragment
    public void changeFragment(Fragment fragment,boolean isInit){
        //开始事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //将fragment替换到main_content
        fragmentTransaction.replace(R.id.main_content, fragment);
        //回栈
        if(!isInit){
            fragmentTransaction.addToBackStack(null);
        }
        //提交事务
        fragmentTransaction.commit();
    }

    /**
     * 如果按了返回按钮，则提示是否退出程序
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            //弹出框
            AlertDialog dialog = new AlertDialog.Builder(this).setTitle("提示").setMessage("你确定要退出校学通？").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }
            ).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            }).show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
