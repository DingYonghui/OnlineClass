package com.example.jack.myapplication.activity;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jack.myapplication.R;
import com.example.jack.myapplication.fragment.FragmentMainIndexLessonByTab;
import com.example.jack.myapplication.fragment.FragmentMainIndexMy;

import schoolstudy.view.activity.CourseActivity;
import schoolstudy.view.fragment.FragmentRecommend;


public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private  RadioGroup rbg_main_bottom_tabs ;
    private  RadioButton rb_main_index_home ;
    private  RadioButton rb_main_index_lesson ;
    private  RadioButton rb_main_index_my ;

    //管理fragment的类
    private static FragmentManager fragmentManager = null  ;
    //开始事务
    FragmentTransaction fragmentTransaction ;

    //3个fragment
    private static FragmentRecommend fragmentMainIndexHome = null ;
    private static FragmentMainIndexLessonByTab fragmentMainIndexLesson = null ;
    private static FragmentMainIndexMy fragmentMainIndexMy = null ;
    //之前显示的fragment
    private static Fragment currentFragment = null ;

    private boolean flag_first_time_to_fragment_lesson = true ;

    void initValues() {
        //初始化当前fragment
        if(currentFragment == null){
            currentFragment = new FragmentRecommend();
        }
        fragmentMainIndexHome = new FragmentRecommend();
        fragmentMainIndexLesson = new FragmentMainIndexLessonByTab();
        fragmentMainIndexMy = new FragmentMainIndexMy();

        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_content, fragmentMainIndexLesson).hide(fragmentMainIndexLesson);
        fragmentTransaction.commit();


        Log.i("提示","初始化了3个fragment");

    }

    //初始化：绑定控件、实例化fragment、获取数据等
    void initViews(){
        rbg_main_bottom_tabs = (RadioGroup)findViewById(R.id.rbg_main_bottom_tabs);
        rb_main_index_home = (RadioButton)findViewById(R.id.rb_main_index_home);
        rb_main_index_lesson = (RadioButton)findViewById(R.id.rb_main_index_lesson);
        rb_main_index_my = (RadioButton)findViewById(R.id.rb_main_index_my);
        Log.i("提示","初始化了Views");
    }

    void intiListeners(){
        //默认选中首页按钮
        rb_main_index_home.setChecked(true);
        //为按钮组设置监听者
        rbg_main_bottom_tabs.setOnCheckedChangeListener(this);

        //设置DepartmentClickListener
        fragmentMainIndexHome.setOnDepartmentClickListener(new FragmentRecommend.OnDepartmentClickListener() {

            public void departmentClick(String dId) {
                //将如果是从首页点击过来的标志设为true
                fragmentMainIndexLesson.flag_from_main = true;
                fragmentMainIndexLesson.currentDepartmentId = dId;
                //第一次跳转到fragmentLesson
//                if(flag_first_time_to_fragment_lesson){
//                    //选中课程按钮,并跳转到第二个页面
//                    rb_main_index_lesson.setChecked(true);
//                    //更改标志符
//                    flag_first_time_to_fragment_lesson = false ;
//                }else{
                    rb_main_index_lesson.setChecked(true);
                    //不是第二次跳转是，还要更新课程
                    fragmentMainIndexLesson.updateLessons(dId);
              //  }
            }

            @Override
            public void courseClick(String id) {
                intentToCourseActivity(id);
            }
        });
        Log.i("提示", "初始化了监听者");
    }

    /**
     * 跳转到课程视频页面
     */
    private void intentToCourseActivity(String lessonId){
        Intent intent = new Intent(MainActivity.this, CourseActivity.class);
        intent.putExtra("id", lessonId);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化：绑定控件
        initViews();
        initValues();
        intiListeners();
        //初始化：显示首页
        changeFragment(fragmentMainIndexHome, false);
        Log.i("提示", "执行完了onCreate方法");
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

                if(fragmentMainIndexHome == null ){
                    Log.i("提示", "如果fragmentMainIndexHome为空");
                    fragmentMainIndexHome = new FragmentRecommend();
                }
                changeFragment(fragmentMainIndexHome,true);
                Log.i("提示", "执行完了onCheckedChanged方法");
                break;
            case R.id.rb_main_index_lesson://课程
                if(fragmentMainIndexLesson==null){
                    fragmentMainIndexLesson = new FragmentMainIndexLessonByTab();
                }
                changeFragment(fragmentMainIndexLesson,true);
                Log.i("提示", "执行完了onCheckedChanged方法");
                break;
            case R.id.rb_main_index_my://我的
                if(fragmentMainIndexMy == null ){
                    fragmentMainIndexMy = new FragmentMainIndexMy();
                }
                changeFragment(fragmentMainIndexMy,true);
                Log.i("提示", "执行完了onCheckedChanged方法");
                break;
            default:
                break;
        }
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
            fragmentTransaction.hide(currentFragment).add(R.id.main_content, toFragment).show(toFragment);

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
