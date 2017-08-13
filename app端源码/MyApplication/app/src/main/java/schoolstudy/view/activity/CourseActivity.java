package schoolstudy.view.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;


import com.example.jack.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import schoolstudy.view.adapter.CourseFragmentAdapter;
import schoolstudy.view.fragment.FragmentCourseSelection;
import schoolstudy.view.fragment.FragmentCourseware;
import schoolstudy.view.fragment.FragmentStudyAndCommunication;


/**
 * 课程类，用于播放视频等基本的学习任务
 */
public class CourseActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    /**
     * 创建控件
     * @param
     */
    private TextView tvTopTitle;            //标题栏
    private TextView tvCourseSelction;      //课程章节tab
    private TextView tvStudyAndCommunication;           //学习交流tab
    private TextView tvCourseware;          //课件tab
    private ViewPager vpCourse;             //ViewPager
    private VideoView vvCourse;             //播放视频控件
    private Button btnFullScreen;           //全屏按钮

    /**
     * 创建变量
     * @param
     */

    //三个tab的标识
    private static final int COURSESELECTIONFLAG = 0;
    private static final int STUDYANDCOMMUNICATIONFLAG = 1;
    private static final int COURSEWAREFLAG = 2;
    private int currentFlag;            //当前活动中的tab

    //播放视频的Flag
    public static final int VIDEONEXT = 0;
    public static final int VIDEOPRE = 1;

    //tab的颜色
    private int selectColor;            //选中时候的颜色
    private int notSelectColor;         //为选中的颜色

    //三个fragment
    private FragmentCourseSelection fragmentCourseSelection;
    private FragmentStudyAndCommunication fragmentStudyAndCommunication;
    private FragmentCourseware fragmentCourseware;

    //适配器以及数据
    private CourseFragmentAdapter fragmentAdapter;
    private List<Fragment> data;

    //传过来的课程id
    private String courseId;
    private String videoUri;

    //播放视频的控制器
    private MediaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        initValues();           //初始化变量
        initViews();            //初始化控件
        bindAdapter();         //为ViewPager添加fragment
        addListeners();         //添加监听事件
    }

    /**
     * 绑定数据
     */
    private void bindAdapter() {
        vpCourse.setAdapter(fragmentAdapter);
    }

    /**
     * 添加监听事件
     */
    private void addListeners() {
        tvCourseSelction.setOnClickListener(this);
        tvStudyAndCommunication.setOnClickListener(this);
        tvCourseware.setOnClickListener(this);
        vpCourse.setOnPageChangeListener(this);
        btnFullScreen.setOnClickListener(this);
        fragmentCourseSelection.setExpanableListViewClickListener(new FragmentCourseSelection.ExpanableListViewClickListener() {
            @Override
            public void Click(String videoUri1,String partId) {
                videoUri = videoUri1;
                btnFullScreen.setVisibility(View.VISIBLE);
                fragmentStudyAndCommunication.setPartId(partId);
                //为全屏按钮设置为可见
                changeVideo(videoUri1);
            }
        });
        vvCourse.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                sendVideoChange(VIDEONEXT);
            }
        });
        controller.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVideoChange(VIDEONEXT);
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVideoChange(VIDEOPRE);
            }
        });
    }

    /**
     * 改变视屏
     */
    private void changeVideo(String videoUri){
        vvCourse.pause();
        vvCourse.resume();
        vvCourse.setMediaController(controller);
        vvCourse.setVideoURI(Uri.parse(videoUri));
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        //TextView
        tvTopTitle = (TextView) findViewById(R.id.tvCourseTitle);
        tvCourseSelction = (TextView) findViewById(R.id.tvCourseSelection);
        tvStudyAndCommunication = (TextView) findViewById(R.id.tvStudyAndCoummunication);
        tvCourseware = (TextView) findViewById(R.id.tvCourseware);

        //ViewPager
        vpCourse = (ViewPager) findViewById(R.id.vpCourse);

        //VideoView
        vvCourse = (VideoView) findViewById(R.id.vvCourse);

        //Button
        btnFullScreen = (Button) findViewById(R.id.btnFullScreen);
        btnFullScreen.setVisibility(View.GONE);                 //默认为全屏按钮设置为隐藏
    }

    /**
     * 初始化变量
     */
    private void initValues() {

        //为courseId赋值
        courseId = getIntent().getStringExtra("id");

        currentFlag = COURSESELECTIONFLAG;
        selectColor = getResources().getColor(R.color.green);
        notSelectColor = getResources().getColor(R.color.gray);

        //初始化fragment
        fragmentCourseSelection = new FragmentCourseSelection();
        fragmentCourseSelection.setLessonId(courseId);
        fragmentStudyAndCommunication = new FragmentStudyAndCommunication();
        fragmentCourseware = new FragmentCourseware();

        //初始化数据与适配器
        data = new ArrayList<>();
        data.add(COURSESELECTIONFLAG,fragmentCourseSelection);
        data.add(STUDYANDCOMMUNICATIONFLAG,fragmentStudyAndCommunication);
        data.add(COURSEWAREFLAG,fragmentCourseware);
        fragmentAdapter = new CourseFragmentAdapter(getSupportFragmentManager(),data);

        //播放视频的控制器
        controller = new MediaController(this);

    }

    /**
     * 控件的点击事件
     */
    public void onClick(View v) {
        switch (v.getId()){
            //ViewPager的三个标题栏目的点击事件
            case R.id.tvCourseSelection:
                chageTabColor(COURSESELECTIONFLAG);
                break;
            case R.id.tvStudyAndCoummunication:
                chageTabColor(STUDYANDCOMMUNICATIONFLAG);
                break;
            case R.id.tvCourseware:
                chageTabColor(COURSEWAREFLAG);
                break;
            case R.id.btnFullScreen:
                Intent intent = new Intent(this,VideoPlayActivity.class);
                intent.putExtra("videoUri",videoUri);
                intent.putExtra("isPlaying",vvCourse.isPlaying());
                intent.putExtra("progress",vvCourse.getCurrentPosition());
                startActivity(intent);
                break;
        }
    }

    /**
     * 改变tab的颜色
     */
    private void chageTabColor(int flag){
        if(flag == currentFlag){
            return;
        }
        switch (flag){
            case COURSESELECTIONFLAG:
                tvCourseSelction.setTextColor(selectColor);
                tvStudyAndCommunication.setTextColor(notSelectColor);
                tvCourseware.setTextColor(notSelectColor);
                currentFlag = COURSESELECTIONFLAG;
                vpCourse.setCurrentItem(COURSESELECTIONFLAG);
                break;
            case STUDYANDCOMMUNICATIONFLAG:
                tvCourseSelction.setTextColor(notSelectColor);
                tvStudyAndCommunication.setTextColor(selectColor);
                tvCourseware.setTextColor(notSelectColor);
                currentFlag = STUDYANDCOMMUNICATIONFLAG;
                vpCourse.setCurrentItem(STUDYANDCOMMUNICATIONFLAG);
                break;
            case COURSEWAREFLAG:
                tvCourseSelction.setTextColor(notSelectColor);
                tvStudyAndCommunication.setTextColor(notSelectColor);
                tvCourseware.setTextColor(selectColor);
                currentFlag = COURSEWAREFLAG;
                vpCourse.setCurrentItem(COURSEWAREFLAG);
                break;
        }
    }

    /**
     * Viewpager的监听事件
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        chageTabColor(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //发送广播
    private void sendVideoChange(int flag){
        Intent intent = new Intent("videoChange");
        intent.putExtra("flag",flag);
        sendBroadcast(intent);
    }

}
