package com.example.jack.myapplication.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.jack.myapplication.R;
import com.example.jack.myapplication.adapter.DepartmentAdapter;
import com.example.jack.myapplication.adapter.LessonListAdapter;
import com.example.jack.myapplication.utils.Statics;
import com.example.jack.myapplication.consts.MyHttpURL;
import com.example.jack.myapplication.entity.Department;
import com.example.jack.myapplication.entity.Lesson;
import com.example.jack.myapplication.service.LessonService;
import com.example.jack.myapplication.service.LessonServiceImpl;

import com.example.jack.myapplication.test.TestData;
import com.example.jack.myapplication.utils.LessonsAsyncTask;

import java.util.List;


import schoolstudy.view.activity.CourseActivity;



public class FragmentMainIndexLesson extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    //fragment_lesson布局
    View view;
    //课程分类的Layout
    private LinearLayout layout_department_category;
    //课程listView
    private ListView lv_lesson;

    //课程数据源
    private List<Lesson> lessons_data;
    //课程listView适配器
    public LessonListAdapter lessonListAdapter;

    //业务处理类
    LessonService service = new LessonServiceImpl();

    public String currentDepartmentId = "0" ;
    public boolean flag_from_main = false ;
    //下拉刷新
    private SwipeRefreshLayout swipeRefreshLayout;

    public FragmentMainIndexLesson() {
    }
    //    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public void onResume() {
        super.onResume();
    }


    //更新课程
    public void updateLessons(String dId){
        StringBuffer url = new StringBuffer(MyHttpURL.LESSON_URL);
        url.append("?pk_DId=");
        url.append(dId);
        new LessonsAsyncTask(getContext(),lv_lesson).execute(url.toString());
    }

    @Override
    public void onStart() {
        super.onStart();
        //如果是从首页点击过来的,则按传过来的系别适配数据
        if(flag_from_main){
            updateLessons(currentDepartmentId);
            Statics.FLAG_FROM_MAIN = false ;
            Log.i("提示：","事件从首页来，并执行完了onStart方法");
        }else {
            //否则，则按照第一个系别，从网络适配课程列表数据:包括异步访问获得数据，并解析数据、适配数据
            updateLessons("0");
            Log.i("提示：", "事件从底部Button来，并执行完了onStart方法");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //加载fragment_lesson布局
        view = inflater.inflate(R.layout.fragment_main_index_lesson, container, false);

        initViews();
        initListeners();
        Log.i("提示：", "执行完了MainIndexLesson的onCreateView方法");
        return view;

    }

    /**
     * 初始化方法
     */
    void initViews() {
        //系别分类
        layout_department_category = (LinearLayout) view.findViewById(R.id.layout_department_category);
        //课程列表
        lv_lesson = (ListView) view.findViewById(R.id.lv_lesson);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefreshLayout);
        //系别分类的GridView
        GridView gv_department_category = initGridView();
        //往布局里添加GridView控件、系别分类
        layout_department_category.addView(gv_department_category);

        //TabHost tabHost_department_category = initTabHost();
        //layout_department_category.addView(tabHost_department_category);


        Log.i("提示：", "执行完了initViews方法");
    }

    void initListeners() {
        swipeRefreshLayout.setOnRefreshListener(this);
        lv_lesson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lesson lesson = (Lesson) parent.getItemAtPosition(position);
                String lName = lesson.getLName();
                String pk_LId = lesson.getPk_LId();
                //新建意图对象
                intent = new Intent(getContext(), CourseActivity.class);
                //新建Bundle对象
                Bundle bundle = new Bundle();
                //放入需要传递的值
                bundle.putString("LName", lName);//把课程的名字传过去
                bundle.putString("pk_LId", pk_LId);
                Log.i("选择的课程名称为：", lName);
                Log.i("选择的课程id为：", pk_LId);
                Statics.CURRENT_LESSON_ID = pk_LId;
                //intent添加bundle
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Log.i("提示：", "执行完了initListeners方法");
    }

    /**
     * 实现获取数据功能以及更新数据，当更新完数据后，调用swipeRefreshLayout.setRefreshing(false);来关闭刷新。
     */
    public void onRefresh() {
        updateLessons(currentDepartmentId);
        swipeRefreshLayout.setRefreshing(false);
        Statics.flag_by_refresh = true ;
        Log.i("提示：", "执行完了onRefresh方法");
    }

    private TabHost initTabHost(){

        //动态生成GridView
        TabHost category_tabHost = new TabHost(getContext());
        category_tabHost.setup();

        TabHost.TabSpec spec1 = category_tabHost.newTabSpec("tag1");
        //spec.setContent(R.id.tab1);
        spec1.setIndicator("互联网系");
        category_tabHost.addTab(spec1);

        TabHost.TabSpec spec2 = category_tabHost.newTabSpec("tag2");
        //spec.setContent(R.id.tab1);
        spec1.setIndicator("金融系");
        category_tabHost.addTab(spec2);

        return category_tabHost;
    }


    /**
     * 初始化GridView：获得数据、添加适配器、设置事件
     */
    private GridView initGridView() {

        //得到系别分类数据
        List<Department> departments = TestData.getDepartments();
        //把数据适配到适配器中
        DepartmentAdapter adapter = new DepartmentAdapter(getContext(), departments,getActivity(),lv_lesson);

        //动态生成GridView
        GridView category_gridView = new GridView(getContext());
        int columnWidth = 180;
        category_gridView.setColumnWidth(columnWidth);
        //自动调整
        category_gridView.setNumColumns(GridView.AUTO_FIT);
        //设置对齐方式
        category_gridView.setGravity(Gravity.CENTER);
        int width = columnWidth * departments.size();
        final ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, 90);
        category_gridView.setLayoutParams(params);
        category_gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        //设置适配器，将数据放入category_gridView
        category_gridView.setAdapter(adapter);

        //设置事件：改变系别列表选中textView的颜色
        category_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void  onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Department department = (Department) parent.getItemAtPosition(position);
                String dName = department.getDName();
                String pk_dId = department.getPk_DId();

                TextView categoryTitle = null;
                for (int i = 0; i < parent.getCount(); i++) {
                    categoryTitle = (TextView) parent.getChildAt(i);
                    categoryTitle.setTextColor(0xFFDEDEDE);
                    //categoryTitle.setBackgroundColor(Color.WHITE);
                    categoryTitle.setBackground(null);

                }
                //重新赋值
                categoryTitle = (TextView) parent.getChildAt(position);
                categoryTitle.setTextColor(0xFF6DFF2D);
                //categoryTitle.setBackgroundResource(R.mipmap.department_title_background);
                Log.i("提示：","点击系别栏后系别名称为"+ dName);
                Log.i("提示：","点击系别栏后系别id为"+ pk_dId);
                currentDepartmentId = pk_dId ;
                //再次异步访问，刷新数据
                updateLessons(pk_dId);
            }
        });
        Log.i("提示：", "执行完了initGridView方法");
        return category_gridView;
    }

}
