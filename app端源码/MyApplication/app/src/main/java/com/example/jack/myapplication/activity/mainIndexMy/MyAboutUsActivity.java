package com.example.jack.myapplication.activity.mainIndexMy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jack.myapplication.R;

public class MyAboutUsActivity extends Activity {

    private Button btn_back ;
    private Button btn_update_version ;
    private Button btn_use_permission ;
    private Button btn_specification ;

    void initViews(){
          btn_back = (Button)findViewById(R.id.btn_back);
          btn_update_version = (Button)findViewById(R.id.btn_update_version);
          btn_use_permission = (Button)findViewById(R.id.btn_use_permission);
          btn_specification = (Button)findViewById(R.id.btn_specification);
    }

    void initListeners(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_update_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"功能正在开发中",Toast.LENGTH_SHORT).show();
            }
        });
        btn_use_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"功能正在开发中",Toast.LENGTH_SHORT).show();
            }
        });
        btn_specification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"功能正在开发中",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_about_us);
        initViews();
        initListeners();
    }

}
