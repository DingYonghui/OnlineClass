package com.example.jack.myapplication.activity.mainIndexMy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jack.myapplication.R;
import com.example.jack.myapplication.fragment.FragmentMainIndexLessonByTab;
import com.example.jack.myapplication.fragment.FragmentMainIndexMy;
import com.example.jack.myapplication.fragment.FragmentMyUpdateUserInfo;
import com.example.jack.myapplication.fragment.FragmentMyUserInfo;

import schoolstudy.view.fragment.FragmentRecommend;

public class MyUserInformationActivity extends FragmentActivity {

    private FrameLayout user_info_main_content ;

    //管理fragment的类
    private static FragmentManager fragmentManager = null  ;
    //开始事务
    FragmentTransaction fragmentTransaction ;

    private static FragmentMyUserInfo myUserInfo = null ;
    private static FragmentMyUpdateUserInfo myUpdateUserInfo = null ;
    //之前显示的fragment
    private static Fragment currentFragment = null ;

    public static String currenttag ;

    void initValues() {
        fragmentManager = getSupportFragmentManager();
        myUserInfo = new FragmentMyUserInfo();
        myUpdateUserInfo = new FragmentMyUpdateUserInfo();
        currentFragment = new FragmentMyUserInfo();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.user_info_main_content, myUpdateUserInfo).hide(myUpdateUserInfo);
        fragmentTransaction.commit();

    }

    void initViews(){
        user_info_main_content = (FrameLayout)findViewById(R.id.user_info_main_content);
    }

    void initLinsters(){
        //设置DepartmentClickListener
        myUserInfo.setOnUserInfoItemClickListener(new FragmentMyUserInfo.OnUserInfoItemClickListener() {

            @Override
            public void infoItemClick(String currentInfo, String tag) {

                currenttag = tag ;
                Log.i("提示：当前标志为：", tag);
                if(myUpdateUserInfo == null){
                    myUpdateUserInfo = new FragmentMyUpdateUserInfo();
                }

                changeFragment(myUpdateUserInfo, true);

                myUpdateUserInfo.setCurrentText(currentInfo);

            }

            @Override
            public void updateUserData() {




            }
        });

        myUpdateUserInfo.setBtnOnClickListener(new FragmentMyUpdateUserInfo.OnBtnClickListener() {
            @Override
            public void onBtnBackClick() {
                changeFragment(myUserInfo, true);
            }

            @Override
            public void onBtnOkClick(String data) {

                changeFragment(myUserInfo, true);
                myUserInfo.updateUserInfoByTag(data, currenttag);

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_user_information);
        initValues();
        initViews();
        initLinsters();

        //初始化：显示首页
        changeFragment(myUserInfo, false);


            Bundle bundle = getIntent().getExtras();
            myUserInfo.setArguments(bundle);


    }

    //切换不同的fragment，修改显示的内容 不会重新加载 正确的切换方式是add()，切换时hide()，add()另一个Fragment；再次切换时，只需hide()当前，show()另一个。
    public void changeFragment(Fragment toFragment,boolean isInit){

        if (currentFragment == null || toFragment == null)
            return;

        //开始事务
        fragmentTransaction = fragmentManager.beginTransaction();
        //将fragment替换到main_content
        if (!toFragment.isAdded()) {
            // 隐藏当前的fragment，add下一个到Activity中
            fragmentTransaction.hide(currentFragment).add(R.id.user_info_main_content, toFragment).show(toFragment);

        } else {
            // 隐藏当前的fragment，显示下一个
            fragmentTransaction.hide(currentFragment).show(toFragment);
        }

        currentFragment = toFragment;
        //回栈
        if(!isInit){
            fragmentTransaction.addToBackStack(null);
        }
        //提交事务
        fragmentTransaction.commit();
        Log.i("提示", "执行完了changeFragment方法");

    }

    /**
     * 按了返回按钮
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
