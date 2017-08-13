package com.example.ding.study.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ding.study.LoginActivity;
import com.example.ding.study.MyAboutUsActivity;
import com.example.ding.study.MyDownloadActivity;
import com.example.ding.study.MyLessonsCollectionActivity;
import com.example.ding.study.MySettingCenterActivity;
import com.example.ding.study.MyStudyRecordActivity;
import com.example.ding.study.R;
import com.example.ding.study.MyUserInformationActivity;
import com.example.ding.study.utils.SharedUtils;

public class FragmentMainIndexMy extends Fragment {

    private TextView tv_user_information;
    private TextView tv_study_record ;
    private TextView tv_lessons_collection;
    private TextView tv_my_download;
    private TextView tv_setting_center;
    private TextView tv_about_us;
    private TextView tv_logout_account ;
    View view = null ;

    public FragmentMainIndexMy() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_index_my, container, false);

        //初始化
        initViews();
        initListener();

        return view ;
    }

    void initViews(){
        tv_user_information = (TextView)view.findViewById(R.id.tv_user_information);
        tv_study_record = (TextView)view.findViewById(R.id.tv_study_record);
        tv_lessons_collection = (TextView)view.findViewById(R.id.tv_lessons_collection);
        tv_my_download = (TextView)view.findViewById(R.id.tv_my_download);
        tv_setting_center = (TextView)view.findViewById(R.id.tv_setting_center);
        tv_about_us = (TextView)view.findViewById(R.id.tv_about_us);
        tv_logout_account = (TextView)view.findViewById(R.id.tv_logout_account);
    }

    void initListener(){

        //个人信息
        tv_user_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyUserInformationActivity.class));
            }
        });

        //学习记录
        tv_study_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyStudyRecordActivity.class));
            }
        });

        //课程收藏
        tv_lessons_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyLessonsCollectionActivity.class));
            }
        });

        //我的离线
        tv_my_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyDownloadActivity.class));
            }
        });

        //设置中心
        tv_setting_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MySettingCenterActivity.class));
            }
        });


        //关于我们
        tv_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyAboutUsActivity.class);
                startActivity(intent);
            }
        });

        //退出登录
        tv_logout_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //弹出框
                AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("提示").setMessage("你确定要退出登录？").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }
                ).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                        getActivity().finish();
                        //将登录标志设为false
                        SharedUtils.putLoginBoolean(getContext(),false);
                    }
                }).show();

            }
        });
    }


}