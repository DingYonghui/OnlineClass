package com.example.ding.study.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 实现标记的写入与读取
 * Created by ding on 2015/10/7.
 */
public class SharedUtils {
    // 名字
    private static final String FILE_NAME_WELCOME ="wellStudy";
    //第一次打开的键值
    private static final String MODE_NAME_WELCOME ="welcome";


    // 名字
    private static final String FILE_NAME_LOGIN ="wellStudy";
    //第一次打开的键值
    private static final String MODE_NAME_LOGIN ="login";


    /**
     * 是否第一次打开软件的判断
     * @param context
     * @return
     */
    //读取boolean类型的值：读取第一次打开的名为MODE_NAME_WELCOME的键值
    public static boolean getWelcomeBoolean(Context context){
        return context.getSharedPreferences(FILE_NAME_WELCOME,Context.MODE_PRIVATE).getBoolean(MODE_NAME_WELCOME,false);
    }
    //写入boolean类型的值：读入第一次打开的名为MODE_NAME的键值
    public static void putWelcomeBoolean(Context context,boolean isFirst){
        //得到SharedPreferences的编辑对象
        SharedPreferences.Editor editor = context.getSharedPreferences(FILE_NAME_WELCOME,Context.MODE_APPEND).edit();
        editor.putBoolean(MODE_NAME_WELCOME,isFirst);
        editor.commit();
    }


    /**
     * 是否已登陆的判断
     * @param context
     * @return
     */
    //读取boolean类型的值：读取第一次打开的名为MODE_NAME_LOGIN的键值
    public static boolean getLoginBoolean(Context context) {
        return context.getSharedPreferences(FILE_NAME_LOGIN,Context.MODE_PRIVATE).getBoolean(MODE_NAME_LOGIN,false);
    }
    //写入boolean类型的值：读入第一次打开的名为MODE_NAME_LOGIN的键值
    public static void putLoginBoolean(Context context,boolean isFirst){
        SharedPreferences.Editor editor = context.getSharedPreferences(FILE_NAME_LOGIN,Context.MODE_APPEND).edit();
        editor.putBoolean(MODE_NAME_LOGIN,isFirst);
        editor.commit();
    }
}
