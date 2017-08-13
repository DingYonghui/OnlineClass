package schoolstudy.view.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.jack.myapplication.R;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import schoolstudy.service.LessonAndSelection;
import schoolstudy.service.LessonAndSelectionImp;
import schoolstudy.view.activity.CourseActivity;
import schoolstudy.view.activity.VideoPlayActivity;
import schoolstudy.view.adapter.SectionAdapter;
import schoolstudy.view.entity.Part;
import schoolstudy.view.entity.Section;

/**
 * 这个是课程章节的fragment
 */
public class FragmentCourseSelection extends Fragment {

    /**
     *创建控件
     */
    private ListView lvCourseSection;               //ListView

    /**
     *创建变量
     */
    private SectionAdapter adapter;                 //创建适配器
    private String lessonId;                        //课程Id
    private List<Section> data;                     //适配器数据
    private LessonAndSelection service;             //服务对象
    private ExpanableListViewClickListener mListener;   //自定义接口
    private String videoUri;                        //视屏播放的Uri
    private int curPosition;                       //视频播放的位置
    private int videoCount ;                        //视频的数量
    private VideoBroadCast mBroadCast;              //自定义广播
    private AdapterView adapterView;                //自定义AdapterView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_selection,null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initValues();
        updataDatas();
        addListeners();
    }

    /**
     * 添加监听事件
     */
    private void addListeners() {

        lvCourseSection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapterView = parent;
                courseSectionClick(position);
            }
        });
    }

    /**
     * 更新数据
     */
    private void updataDatas() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("blackdog","data = service.getSectionAndPart(lessonId);");
                    data = service.getSectionAndPart(lessonId);

                    //ding添加
                    if(data.size()==0){
                        return;
                    }

                    for(int i = 0;i<data.size();i++){
                        Section s = data.get(i);
                        Log.i("blackdog",s.getName());
                        List<Part> parts = s.getCount();
                        videoCount = parts.size();
                        for(int j = 0;j<parts.size();j++){
                            Log.i("blackdog",parts.get(j).getName());
                            Log.i("blackdog",parts.get(j).getVideoUri());
                        }
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("blackdog","bindAdapter();");
                            bindAdapter();
                        }
                    });
                }catch (SocketTimeoutException e){
                    showToast("服务器错误");
                }catch (ConnectTimeoutException e){
                    showToast("连接超时");
                }
                catch (Exception e) {
                    e.printStackTrace();
                    showToast("错误");
                }
            }
        }).start();
    }

    /**
     * 显示Toast
     */
    private void showToast(final String content){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(),content,Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 绑定适配器
     */
    private void bindAdapter() {

        adapter = new SectionAdapter(getActivity(),data);
        lvCourseSection.setAdapter(adapter);
        videoUri = data.get(0).getCount().get(0).getVideoUri();
    }

    /**
     * 初始化变量
     */
    private void initValues() {
        data = new ArrayList<>();
        service = new LessonAndSelectionImp();
        //注册广播
        mBroadCast = new VideoBroadCast();
        IntentFilter filter = new IntentFilter("videoChange");
        getActivity().registerReceiver(mBroadCast,filter);
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        View view = getView();
        lvCourseSection = (ListView) view.findViewById(R.id.elvCurseSelection);
    }

    /**
     * 得到lessonId
     */
    public void setLessonId(String id){
        this.lessonId = id;
    }

    public interface ExpanableListViewClickListener{
        public void Click(String videoUri,String partId);
    }

    /*
    **得到视频默认的VideoUri
     */
    public String getVideoUri(){
        return videoUri;
    }

    public void setExpanableListViewClickListener(ExpanableListViewClickListener listener){
        this.mListener = listener;
    }

    /**
     * 自定义广播,通过自定义广播来改变视频播放
     */
    class VideoBroadCast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
           if(context.getPackageName().equals("schoolstudy.view.activity.VideoPlayActivity")){
               Log.e("blackdog",context.getPackageName());
           }
            Log.e("blackdog",context.getPackageName());

            int flag = intent.getIntExtra("flag",0);
            if(flag == CourseActivity.VIDEONEXT) {
                if (curPosition++< videoCount) {
                    lvCourseSection.setSelection(curPosition);
                    courseSectionClick(curPosition);
                }else{
                    curPosition = videoCount-1;
                }

            }else if(flag == CourseActivity.VIDEOPRE){
                if (curPosition-- >0) {
                    lvCourseSection.setSelection(curPosition);
                    courseSectionClick(curPosition);
                }
                else{
                    curPosition = 0;
                }
            }
        }
    }

    //点击ListView的方法
    private void courseSectionClick(int position){
        if(mListener == null){
            return;
        }
        if(adapterView == null){
            return;
        }
        Map<Integer,Object> map = (Map<Integer, Object>) adapterView.getItemAtPosition(position);
        String videoUri = ((Part)map.get(1)).getVideoUri();
        String partId = ((Part)map.get(1)).getId();
        curPosition = position;
        mListener.Click(videoUri,partId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadCast);
    }
}
