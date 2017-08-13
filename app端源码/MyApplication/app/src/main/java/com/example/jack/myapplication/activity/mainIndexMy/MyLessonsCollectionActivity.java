package com.example.jack.myapplication.activity.mainIndexMy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.jack.myapplication.R;
import com.example.jack.myapplication.adapter.LessonListAdapter;
import com.example.jack.myapplication.entity.Lesson;
import com.example.jack.myapplication.service.UserServiceImpl;
import com.example.jack.myapplication.utils.SharedUtils;
import com.example.jack.myapplication.utils.Statics;


import java.util.List;

import schoolstudy.view.activity.CourseActivity;


public class MyLessonsCollectionActivity extends Activity implements  SwipeRefreshLayout.OnRefreshListener{
    private Button btn_back ;
    private ListView lesson_collection_content ;
    private SwipeRefreshLayout swipeRefreshLayout ;

    UserServiceImpl service = new UserServiceImpl();
    List<Lesson> lessons ;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LessonListAdapter adapter = new LessonListAdapter(getBaseContext(),lessons,lesson_collection_content);
            lesson_collection_content.setAdapter(adapter);
        }
    };

    void initLessonCollections(){
        final String id = SharedUtils.getUserId(getBaseContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                //通过网络获得课程
                lessons = service.getMyLesson(id);
                Log.i("收藏的课程为：",lessons.toString());
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    void initViews(){
        btn_back = (Button)findViewById(R.id.btn_back);
        lesson_collection_content = (ListView)findViewById(R.id.lesson_collection_content);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
    }

    void initListeners(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        lesson_collection_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Lesson lesson = (Lesson) parent.getItemAtPosition(position);
                String LName = lesson.getLName();
                String pk_LId = lesson.getPk_LId();

                Intent intent = new Intent(getBaseContext(), CourseActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("LName", LName);
                bundle.putString("id", pk_LId);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void onRefresh() {
        initLessonCollections();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lessons_collection);
        initLessonCollections();
        initViews();
        initListeners();
    }

}
