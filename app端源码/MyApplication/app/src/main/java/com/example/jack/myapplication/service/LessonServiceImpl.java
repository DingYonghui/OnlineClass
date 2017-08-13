package com.example.jack.myapplication.service;

import com.example.jack.myapplication.entity.Lesson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack on 2015/11/21.
 */
public class LessonServiceImpl implements LessonService {

    @Override
    public List<Lesson> getLessons(String url) throws Exception {

        //所有的课程信息
        List<Lesson> lessons = new ArrayList<Lesson>();
        try {
            //通过url,调用readStream()获得网络数据
            String jsonString = readStream(new URL(url).openStream());
            JSONObject jsonObject;
            Lesson lesson;
            try {
                jsonObject = new JSONObject(jsonString);
                //找到json数据中名为data的数据
                JSONArray jsonArray = jsonObject.getJSONArray("data");

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    lesson = new Lesson();
                    lesson.setFk_L_TPhone(jsonObject.getString("fk_L_TPhone"));
                    lesson.setPk_LId(jsonObject.getString("pk_LId"));
                    lesson.setLInfo(jsonObject.getString("LInfo"));
                    lesson.setLIcon(jsonObject.getString("LIcon"));
                    lesson.setLName(jsonObject.getString("LName"));
                    lesson.setLCount(Integer.parseInt(jsonObject.getString("LCount")));
                    lessons.add(lesson);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    /**
     * 通过is解析读取网络数据
     *
     * @param is
     * @return
     */
    private String readStream(InputStream is) {
        InputStreamReader isr;
        String result = "";
        String line = "";
        try {
            //字节流转化为字符流
            isr = new InputStreamReader(is, "utf-8");
            //缓冲流
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result = result + line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
