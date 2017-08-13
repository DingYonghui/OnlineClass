package com.example.ding.study.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.ding.study.R;
import com.example.ding.study.adapter.DepartmentCategoryAdapter;
import com.example.ding.study.adapter.LessonListAdapter;
import com.example.ding.study.consts.MyHttpURL;
import com.example.ding.study.entity.Lesson;
import com.example.ding.study.entity.LessonTest;
import com.example.ding.study.service.UserService;
import com.example.ding.study.service.UserServiceImpl;
import com.example.ding.study.test.TestData;

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
import java.util.Map;

public class FragmentMainIndexLesson extends Fragment {

    //fragment_lesson布局
    View view;
    //课程分类的Layout
    private LinearLayout layout_department_category;
    //课程listView
    private ListView lv_lesson;

    private TextView test ;

    //课程数据源
    private List<LessonTest> lessons_data;
    //课程listView适配器
    public LessonListAdapter lessonListAdapter;

    //业务处理类
    UserService service = new UserServiceImpl();


    /**
     * 初始化方法
     */
    void init() {
        //课程列表
        lv_lesson = (ListView) view.findViewById(R.id.lv_lesson);
        //系别分类
        layout_department_category = (LinearLayout) view.findViewById(R.id.layout_department_category);

    }

    public FragmentMainIndexLesson() {
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
        //加载fragment_lesson布局
        view = inflater.inflate(R.layout.fragment_main_index_lesson, null);
        this.init();
        //test = (TextView)view.findViewById(R.id.test);
        /**
         * 系别分类
         */
        //往布局里添加GridView控件
        layout_department_category.addView(initGridView());

        /**
         * 为课程列表适配数据
         */

        //从网络适配数据:异步访问获得数据，并解析数据、适配数据
        String url = MyHttpURL.LESSON_DOWNLOAD_URL+"?dId = defaultDep";//先获得默认的系别的课程
        new LessonsAsyncTask().execute(url);

        return view;

    }


    /**
     * 实现网络的异步访问
     */
    class LessonsAsyncTask extends AsyncTask<String,Void,List<Lesson>> {
        @Override
        protected List<Lesson> doInBackground(String... params) {
            return getJsonData(params[0]);//params[0]:请求网址,调用getJsonData()方法获取课程数据
        }
        @Override
        protected void onPostExecute(List<Lesson> lessons) {
            super.onPostExecute(lessons);
            //将生成的lessons(数据源)适配到listView
            LessonListAdapter lessonListAdapter = new LessonListAdapter(getContext(),lessons,getActivity(),lv_lesson);
            lv_lesson.setAdapter(lessonListAdapter);

        }
    }

    /**
     *  通过url，从网络数据中获得json数据，解析到我们所封装的 List<Lesson>对象
     * @param url
     * @return
     */
    private List<Lesson> getJsonData(String url){
        //所有的课程信息
        List<Lesson> lessons = new ArrayList<Lesson>();
        try {
            //通过url,调用readStream()获得网络数据
            String jsonString  = readStream(new URL(url).openStream());
            JSONObject jsonObject ;
            Lesson lesson ;
            try {
                jsonObject = new JSONObject(jsonString);
                //找到json数据中名为data的数据
                JSONArray jsonArray = jsonObject.getJSONArray("data");

                for(int i= 0;i<jsonArray.length();i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    lesson = new Lesson();
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
        return lessons ;
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

    /**
     * 初始化GridView：获得数据、添加适配器、设置事件
     */
    private GridView initGridView() {

        //得到系别分类数据
        List<Map<String, String>> departmentCategory = TestData.getDepartmentCategory(getContext());

        //把数据适配到适配器中
        SimpleAdapter adapter = new DepartmentCategoryAdapter(getContext(), departmentCategory, R.layout.department_category_title_item, new String[]{"department_category_title"}, new int[]{R.id.department_category_title});

        //动态生成GridView
        GridView category_gridView = new GridView(getContext());
        int columnWidth = 180;
        category_gridView.setColumnWidth(columnWidth);
        //自动调整
        category_gridView.setNumColumns(GridView.AUTO_FIT);
        //设置对齐方式
        category_gridView.setGravity(Gravity.CENTER);
        int width = columnWidth * departmentCategory.size();
        final ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        category_gridView.setLayoutParams(params);
        category_gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        //设置适配器，将数据放入category_gridView
        category_gridView.setAdapter(adapter);

        //设置事件：改变系别列表选中textView的颜色
        category_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView categoryTitle = null ;
                for (int i = 0; i < parent.getCount(); i++) {
                    categoryTitle = (TextView) parent.getChildAt(i);
                    categoryTitle.setTextColor(0xFFDEDEDE);
                }
                //categoryTitle.setBackgroundColor(Color.BLUE);
                categoryTitle = (TextView) view;
                categoryTitle.setTextColor(0xFF6DFF2D);
            }
        });
        return category_gridView;
    }


}
