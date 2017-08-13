package schoolstudy.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;


import com.example.jack.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import schoolstudy.service.LessonAndSelection;
import schoolstudy.service.LessonAndSelectionImp;
import schoolstudy.view.adapter.CourseAdapter;
import schoolstudy.view.adapter.DepartmentAdapter;
import schoolstudy.view.entity.Course;
import schoolstudy.view.entity.Static;

/**
 * 推荐碎片，用于在MainActivity中动态添加的布局
 */
public class FragmentRecommend extends Fragment {

    /**
     *初始化控件
     */
    private ListView lvRecommend;           //布局中的ListView
    private GridView gvRecommend;           //布局中的GridView
    private SwipeRefreshLayout refreshRecommend;            //布局中的下拉刷新控件
    private View view;                      //整个布局

    /**
     *初始化变量
     */
    private DepartmentAdapter departmentAdapter;            //GridView适配器
    private OnDepartmentClickListener mDepartmentClickListener;             //GridView点击事件
    private CourseAdapter courseAdapter;                    //ListView适配器
    private List<Course> courses;                           //ListView数据
    private LessonAndSelection sevice;                        //创建服务器类
    private List<Course> data;                              //ListViewd的数据集合

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("blackdog","oncreateView");
        return inflater.inflate(R.layout.fragment_recommend,null);
    }
    
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();                //初始化控件
        initValues();               //初始化变量
        bindAdapters();             //绑定适配器
        addListeners();             //添加监听事件
        updateListViewData();       //直接更新数据
    }



    /**
     * 更新数据
     */
    private void updateListViewData() {
        final String id = getActivity().getSharedPreferences("config",getActivity().MODE_PRIVATE).getString("fk_DId","0");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    data = sevice.getLesson(id);
                    //
                    if(data.size()==0){
                        return;
                    }
                    Log.i("blackdog",data.toString());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for(Course course:data){
                                boolean isExist = false;
                                for(int i = 0;i<courses.size();i++){
                                    if(((Course)courses.get(i)).getTitle().equals(course.getTitle())){
                                        isExist = true;
                                    }
                                }
                                if(!isExist)
                                courses.add(course);
                            }
                            courseAdapter.notifyDataSetChanged();
                            Log.i("blackdog", courseAdapter.getCount() + "");
                            refreshRecommend.setRefreshing(false);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshRecommend.setRefreshing(false);
                        }
                    });
                }
            }
        }).start();
    }

    /**
     * 绑定适配器
     */
    private void bindAdapters() {
        gvRecommend.setAdapter(departmentAdapter);
        lvRecommend.setAdapter(courseAdapter);
    }

    /**
     * 给控件添加监听事件
     */
    private void addListeners() {

        //GridView的点击事件
        gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mDepartmentClickListener != null){
                    int dId = (int) parent.getItemAtPosition(position);
                    if(dId==8){
                        dId = 1 ;
                    }
                    mDepartmentClickListener.departmentClick((dId-1)+"");
                }
            }
        });

        //ListView的点击事件
        lvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mDepartmentClickListener != null){
                    mDepartmentClickListener.courseClick(((Course) parent.getItemAtPosition(position)).getId());
                }
            }
        });

        //refresh的刷新事件
        refreshRecommend.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateListViewData();           //更新数据
            }
        });

        lvRecommend.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View firstView = view.getChildAt(firstVisibleItem);
                // 当firstVisibleItem是第0位。如果firstView==null说明列表为空，需要刷新;或者top==0说明已经到达列表顶部, 也需要刷新
                if (firstVisibleItem == 0 && (firstView == null || firstView.getTop() == 0)) {
                    refreshRecommend.setEnabled(true);
                } else {
                    refreshRecommend.setEnabled(false);
                }
            }
        });
    }

    /**
     * 初始化变量
     */
    private void initValues() {
        departmentAdapter = new DepartmentAdapter(getActivity().getApplicationContext(),null);
        courses = new ArrayList<>();
        courseAdapter = new CourseAdapter(getActivity(),courses);
        sevice = new LessonAndSelectionImp();
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        view = getView();
        lvRecommend = (ListView) view.findViewById(R.id.lvRecommond);
        gvRecommend = (GridView) view.findViewById(R.id.gvRecommend);
        refreshRecommend = (SwipeRefreshLayout) view.findViewById(R.id.refreshRecommend);
    }

    /**
     * 自定义GridView的点击事件，为了让GridView点击时候可以跳转到fragmentCourse
     */
    public interface OnDepartmentClickListener{
        void departmentClick(String departmentId);
        void courseClick(String id);
    }

    /**
     * 事件监听器
     */
    public void setOnDepartmentClickListener(OnDepartmentClickListener listener){
        this.mDepartmentClickListener = listener;
    }

}
