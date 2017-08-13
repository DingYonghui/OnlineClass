package com.example.jack.myapplication.utils;

import android.app.Application;

/**
 * 让CrashApplication取代android.app.Application的地位，在我们的代码中生效，还需要修改AndroidManifest.xml：
 * Created by jack on 2015/11/15.
 */
public class CrashApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
