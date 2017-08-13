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

public class MyDownloadActivity extends Activity {

    private Button btn_back ;

    void initViews(){
        btn_back = (Button)findViewById(R.id.btn_back);
    }

    void initListeners(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_download);
        initViews();
        initListeners();
    }


}
