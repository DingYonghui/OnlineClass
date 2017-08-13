package com.example.ding.study;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ding.study.adapter.LessonListAdapter;
import com.example.ding.study.adapter.LessonSectionListAdapter;
import com.example.ding.study.consts.MyHttpURL;
import com.example.ding.study.entity.Lesson;
import com.example.ding.study.entity.LessonSection;

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

public class LessonSectionActivity extends Activity {

    private TextView tv_lesson_name ;
    private ListView lv_lesson_sections ;

    private Activity activity ;

    void init(){
        tv_lesson_name = (TextView)findViewById(R.id.tv_lesson_name);
        lv_lesson_sections = (ListView)findViewById(R.id.lv_lesson_sections);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_section);

        activity = this ;
        init();

        Bundle bundle = getIntent().getExtras();
        tv_lesson_name.setText(bundle.getString("lessonName").toString());

        String lessonId = bundle.getString("lessonId").toString();
        String url = "http://192.168.0.1:8080/well/LessonSectionDownloadServlet?lessonId="+lessonId ;
        //从网络适配数据:异步访问获得数据，并解析数据、适配数据
        new LessonSectionsAsyncTask().execute(url);

    }


    class LessonSectionsAsyncTask extends AsyncTask<String,Void,List<LessonSection>> {
        @Override
        protected List<LessonSection> doInBackground(String... params) {
            return getJsonData(params[0]);//params[0]:请求网址,调用getJsonData()方法获取课程数据
        }
        @Override
        protected void onPostExecute(List<LessonSection> lessonSections) {
            super.onPostExecute(lessonSections);
            //将生成的lessons(数据源)适配到listView
            LessonSectionListAdapter lessonListAdapter = new LessonSectionListAdapter(getBaseContext(),lessonSections,activity);
            lv_lesson_sections.setAdapter(lessonListAdapter);

        }
    }

    /**
     *  通过url，从网络数据中获得json数据，解析到我们所封装的 List<Lesson>对象
     * @param url
     * @return
     */
    private List<LessonSection> getJsonData(String url){
        //所有的课程信息
        List<LessonSection> lessonSections = new ArrayList<LessonSection>();
        try {
            //通过url,调用readStream()获得网络数据
            String jsonString  = readStream(new URL(url).openStream());
            JSONObject jsonObject ;
            LessonSection lessonSection ;
            try {
                jsonObject = new JSONObject(jsonString);
                //找到json数据中名为data的数据
                JSONArray jsonArray = jsonObject.getJSONArray("data");

                for(int i= 0;i<jsonArray.length();i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    lessonSection = new LessonSection();
                    lessonSection.setPk_SId(jsonObject.getString("pk_SId"));
                    lessonSection.setFk_LId(jsonObject.getString("fk_LId"));
                    lessonSection.setSIcon(jsonObject.getString("SIcon"));
                    lessonSection.setSName(jsonObject.getString("SName"));
                    lessonSection.setSCount(Integer.parseInt(jsonObject.getString("SCount")));
                    lessonSection.setSTime(jsonObject.getString("STime"));
                    lessonSections.add(lessonSection);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lessonSections ;
    }

    /**
     * 通过is解析读取网络数据
     * @param is
     * @return
     */
    private String readStream(InputStream is){
        InputStreamReader isr ;
        String result  = "";
        String line = "";
        try {
            //字节流转化为字符流
            isr = new InputStreamReader(is,"utf-8");
            //缓冲流
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null){
                result = result + line ;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result ;
    }




}
