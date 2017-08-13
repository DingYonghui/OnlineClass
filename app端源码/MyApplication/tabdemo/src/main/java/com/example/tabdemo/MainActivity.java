package com.example.tabdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends Activity implements TabHost.TabContentFactory{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        // 从布局中获取TabHost并建立
        TabHost tabHost = (TabHost) findViewById(R.id.myTabHost);
        tabHost.setup();

        // 加上30个标签
        for (int i = 1; i <= 30; i++)
        {
            String name = "Tab " + i;
            tabHost.addTab(tabHost.newTabSpec(name).setIndicator(name)
                    .setContent(this));
        }

    }

    @Override
    public View createTabContent(String tag)
    {
        final TextView tv = new TextView(this);
        tv.setText("Content for tab with tag " + tag);
        return tv;
    }
}
