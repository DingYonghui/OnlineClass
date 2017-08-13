package com.example.jack.myapplication.fragment;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.jack.myapplication.R;
import com.example.jack.myapplication.utils.Statics;
import com.example.jack.myapplication.consts.MyHttpURL;
import com.example.jack.myapplication.entity.Department;
import com.example.jack.myapplication.entity.Lesson;
import com.example.jack.myapplication.test.TestData;
import com.example.jack.myapplication.utils.LessonsAsyncTask;

import java.util.List;

import schoolstudy.view.activity.CourseActivity;


public class FragmentMainIndexLessonByTab extends Fragment implements  SwipeRefreshLayout.OnRefreshListener, TabHost.TabContentFactory,TabHost.OnTabChangeListener {

    //fragment_lesson布局
    View view;
    //课程listView
    private static ListView lv_lesson = null;

    //标识符
    public String currentDepartmentId = "0" ;
    public boolean flag_from_main = false ;
    //下拉刷新
    private SwipeRefreshLayout swipeRefreshLayout;
    ViewPager viewpager ;
    //tab管理
    TabHost tabHost ;
    TabWidget tabWidget ;

    public FragmentMainIndexLessonByTab() {
    }

    @Override
    public void onStart() {
        super.onStart();
        //如果是从首页点击过来的,则按传过来的系别适配数据
        if(flag_from_main){
            updateLessons(currentDepartmentId);
            Statics.FLAG_FROM_MAIN = false ;
            Log.i("提示：", "事件从首页来，并执行完了onStart方法");
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
        view = inflater.inflate(R.layout.fragment_main_index_lesson_by_tab, container, false);
        initViews();
        initListeners();
        Log.i("提示：", "执行完了MainIndexLesson的onCreateView方法");
        return view;

    }

    /**
     * 初始化方法
     */
    void initViews() {

        //viewpager = (ViewPager)view.findViewById(R.id.viewpager);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        // 从布局中获取TabHost并建立
        tabHost = (TabHost) view.findViewById(R.id.tabhost);
        tabHost.setup();
        initTabHost();
    }

    void initTabHost(){

        List<Department> departments = TestData.getDepartments();
        // 加上30个标签
        for (int i = 0; i < departments.size(); i++) {
            String tag = departments.get(i).getPk_DId();
            String dName = departments.get(i).getDName();

            Log.i("提示：","开始创建tab的Item");
            RelativeLayout rlayout_tabTitle = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_lesson_tab_title, null);
            TextView articleTabLabel = (TextView) rlayout_tabTitle.findViewById(R.id.tab_label);
            articleTabLabel.setText(dName);

            tabHost.addTab(tabHost.newTabSpec(tag).setIndicator(rlayout_tabTitle).setContent(this));
        }

        //注意这个就是改变Tabhost默认样式的地方，一定将这部分代码放在上面这段代码的下面，不然样式改变不了
        tabWidget = tabHost.getTabWidget();
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            View view = tabWidget.getChildAt(i);
            TextView textView = (TextView) view.findViewById(R.id.tab_label);
            textView.setGravity(Gravity.CENTER);
            View v = (View)view.findViewById(R.id.underline);
            if(i == 0){
                //设置tab中标题文字的颜色，不然默认为黑色
                textView.setTextColor(getResources().getColorStateList(R.color.top_title_color_2));
                v.setVisibility(View.VISIBLE);
            }else {
                textView.setTextColor(getResources().getColorStateList(android.R.color.darker_gray));
                v.setVisibility(View.INVISIBLE);
            }
        }
    }

    void initListeners() {
        tabHost.setOnTabChangedListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
//        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//                tabHost.setCurrentTab(i);
//            }
//        });
        Log.i("提示：", "执行完了initListeners方法");
        lv_lesson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Lesson lesson = (Lesson) parent.getItemAtPosition(position);
                String LName = lesson.getLName();
                String pk_LId = lesson.getPk_LId();

                Intent intent = new Intent(getContext(), CourseActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("LName",LName);
                bundle.putString("id",pk_LId);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });
    }

    //更新课程
    public void updateLessons(String dId){
        tabHost.setCurrentTab(Integer.parseInt(dId));
        StringBuffer url = new StringBuffer(MyHttpURL.LESSON_URL);
        url.append("?pk_DId=");
        url.append(dId);
        new LessonsAsyncTask(getContext(),lv_lesson).execute(url.toString());
    }

    @Override
    public View createTabContent(String tag) {

       if(lv_lesson == null){
           lv_lesson = new MyListView(getContext());
       }

        Log.i("提示：",new StringBuffer("执行完了createTabContent").toString());
        return lv_lesson;
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

    /**
     * tab点击事件处理
     * @param tabId
     */
    @Override
    public void onTabChanged(String tabId) {

        Log.i("提示：", new StringBuffer("选中的tabId为：").append(tabId).toString());
        for (int i =0; i < tabWidget.getChildCount(); i++) {
            View view = tabWidget.getChildAt(i);
            TextView tv = (TextView) view.findViewById(R.id.tab_label);
            View v = (View)view.findViewById(R.id.underline);
            //改变当前tab标题的字体颜色
            if(tabHost.getCurrentTab()==i){
                tv.setTextColor(getResources().getColorStateList(R.color.top_title_color_2));
                v.setVisibility(View.VISIBLE);
            }
            else {
                tv.setTextColor(getResources().getColorStateList(android.R.color.darker_gray));
                v.setVisibility(View.INVISIBLE);
            }
            tv.setCompoundDrawables(null,null,null, null);
        }
        //将当前的系别id设为tabId
        currentDepartmentId = tabId ;
        //更新课程
        updateLessons(tabId);
    }

    class MyListView extends ListView implements AbsListView.OnScrollListener{

        public MyListView(Context context) {
            super(context);
            this.setOnScrollListener(this);
        }
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            View firstView = view.getChildAt(firstVisibleItem);
            // 当firstVisibleItem是第0位。如果firstView==null说明列表为空，需要刷新;或者top==0说明已经到达列表顶部, 也需要刷新
            if (firstVisibleItem == 0 && (firstView == null || firstView.getTop() == 0)) {
                swipeRefreshLayout.setEnabled(true);
            } else {
                swipeRefreshLayout.setEnabled(false);
            }
        }
    }

}
