package schoolstudy.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.jack.myapplication.R;


/**
 *这个类是用于播放视频
 */
public class VideoPlayActivity extends Activity {

    /**
     * 创建控件
     */
    private VideoView videoView;            //创建VideoView控件
    private ImageButton btnReturn;          //创建返回按钮

    /**
     * 创建变量
     */
    private MediaController controller;     //创建Controller
    private String videoUri;                //创建播放视频的uri
    private int progress;                   //视频播放的进度
    private boolean isPlaying;              //视频是否播放
    private Intent intent;                  //传过来的Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_play);
        initViews();
        initValues();
        initListeners();
    }

    /**
     * 初始化事件
     */
    private void initListeners() {
        controller.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                btnReturn.setVisibility(visibility);
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                sendVideoChange(CourseActivity.VIDEONEXT);
            }
        });
        controller.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVideoChange(CourseActivity.VIDEONEXT);
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVideoChange(CourseActivity.VIDEOPRE);
            }
        });
    }

    //发送广播
    private void sendVideoChange(int flag){
        Intent intent = new Intent("videoChange");
        intent.putExtra("flag",flag);
        sendBroadcast(intent);
    }

    /**
     * 创建变量
     */
    private void initValues() {
        intent = getIntent();
        controller = new MediaController(this);
        videoView.setMediaController(controller);
        videoUri = intent.getStringExtra("videoUri");
        videoView.setVideoURI(Uri.parse(videoUri));
        progress = intent.getIntExtra("progress",0);
        isPlaying = intent.getBooleanExtra("isPlaying",false);
        if(isPlaying){
            videoView.start();
        }
        videoView.seekTo(progress);
    }

    /**
     * 改变视频
     */
    public void changeVideo(String videoUri){
        videoView.resume();
        videoView.setMediaController(controller);
        videoView.setVideoURI(Uri.parse(videoUri));
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        videoView = (VideoView) findViewById(R.id.vvPlay);
        btnReturn = (ImageButton) findViewById(R.id.btnReturn);
    }



    /**
     * 在还没有创建的时候强制横屏
     */
    @Override
    protected void onResume() {
        super.onResume();
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }
}
