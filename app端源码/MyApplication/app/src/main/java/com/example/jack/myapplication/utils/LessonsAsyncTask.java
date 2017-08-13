package com.example.jack.myapplication.utils;

/**
 * Created by jack on 2015/11/11.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.jack.myapplication.adapter.LessonListAdapter;
import com.example.jack.myapplication.entity.Lesson;
import com.example.jack.myapplication.service.LessonService;
import com.example.jack.myapplication.service.LessonServiceImpl;

import java.util.List;

/**
 * 实现网络的异步访问,获取课程数据
 */
public class LessonsAsyncTask extends AsyncTask<String, Void, List<Lesson>> {

    Context context;
    ListView lv_lesson;

    LessonService lessonService = new LessonServiceImpl();

    List<Lesson> lessons  ;
    LessonListAdapter lessonListAdapter  ;


    public LessonsAsyncTask(Context context ,ListView lv_lesson) {
        this.context = context ;
        Log.i("传递进来的listview",lv_lesson.toString());
        this.lv_lesson = lv_lesson ;
    }

    @Override
    protected List<Lesson> doInBackground(String... params) {
        Log.i("在线程中访问的url为：",params[0]);
        //原来为lessons = getJsonData(params[0]);
        try {
            lessons = lessonService.getLessons(params[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.i("在线程中获得的数据为：", lessons.toString());
        return lessons ;//params[0]:请求网址,调用getJsonData()方法获取课程数据
    }

    //在主线程中用
    @Override
    protected void onPostExecute(List<Lesson> lessons) {
        super.onPostExecute(lessons);
        //将生成的lessons(数据源)适配到listView
        Log.i("处理中的系别数据：", lessons.toString());
        lessonListAdapter = new LessonListAdapter(context, lessons, lv_lesson);
        Log.i("适配器构造完成", "OK");
        lv_lesson.setAdapter(lessonListAdapter);
    }

    /**
     * 通过url，从网络数据中获得json数据，解析到我们所封装的 List<Lesson>对象
     *
     * @param url
     * @return
     */
//    private List<Lesson> getJsonData(String url) {
//        //所有的课程信息
//        List<Lesson> lessons = new ArrayList<Lesson>();
//        try {
//            //通过url,调用readStream()获得网络数据
//            String jsonString = readStream(new URL(url).openStream());
//            JSONObject jsonObject;
//            Lesson lesson;
//            try {
//                jsonObject = new JSONObject(jsonString);
//                //找到json数据中名为data的数据
//                JSONArray jsonArray = jsonObject.getJSONArray("data");
//
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    jsonObject = jsonArray.getJSONObject(i);
//                    lesson = new Lesson();
//                    lesson.setFk_L_TPhone(jsonObject.getString("fk_L_TPhone"));
//                    lesson.setPk_LId(jsonObject.getString("pk_LId"));
//                    lesson.setLInfo(jsonObject.getString("LInfo"));
//                    lesson.setLIcon(jsonObject.getString("LIcon"));
//                    lesson.setLName(jsonObject.getString("LName"));
//                    lesson.setLCount(Integer.parseInt(jsonObject.getString("LCount")));
//                    lessons.add(lesson);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return lessons;
//    }
//
//    /**
//     * 通过is解析读取网络数据
//     *
//     * @param is
//     * @return
//     */
//    private String readStream(InputStream is) {
//        InputStreamReader isr;
//        String result = "";
//        String line = "";
//        try {
//            //字节流转化为字符流
//            isr = new InputStreamReader(is, "utf-8");
//            //缓冲流
//            BufferedReader br = new BufferedReader(isr);
//            while ((line = br.readLine()) != null) {
//                result = result + line;
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

}

