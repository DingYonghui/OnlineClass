package com.example.jack.myapplication.activity.mainIndexMy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jack.myapplication.R;

public class MySettingCenterActivity extends Activity {

    private Button btn_back ;
    private LinearLayout layout_permission_no_wifi_to_play ;
    private LinearLayout layout_clean_cache ;
    private LinearLayout layout_cache_path ;
    private LinearLayout layout_advice_feedback ;
    private LinearLayout layout_push_notifications ;
    private LinearLayout layout_give_grade ;

    void initViews(){
        btn_back = (Button)findViewById(R.id.btn_back);
        layout_permission_no_wifi_to_play = (LinearLayout)findViewById(R.id.layout_permission_no_wifi_to_play);
        layout_clean_cache = (LinearLayout)findViewById(R.id.layout_clean_cache);
        layout_cache_path = (LinearLayout)findViewById(R.id.layout_cache_path);
        layout_advice_feedback = (LinearLayout)findViewById(R.id.layout_advice_feedback);
        layout_push_notifications = (LinearLayout)findViewById(R.id.layout_push_notifications);
        layout_give_grade = (LinearLayout)findViewById(R.id.layout_give_grade);
    }

    void initListeners(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layout_permission_no_wifi_to_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "功能正在开发中", Toast.LENGTH_SHORT).show();
            }
        });
        layout_clean_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"功能正在开发中",Toast.LENGTH_SHORT).show();
            }
        });
        layout_cache_path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"功能正在开发中",Toast.LENGTH_SHORT).show();
            }
        });
        layout_advice_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"功能正在开发中",Toast.LENGTH_SHORT).show();
            }
        });
        layout_push_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"功能正在开发中",Toast.LENGTH_SHORT).show();
            }
        });
        layout_give_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"功能正在开发中",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting_center);
        initViews();
        initListeners();
    }

}
