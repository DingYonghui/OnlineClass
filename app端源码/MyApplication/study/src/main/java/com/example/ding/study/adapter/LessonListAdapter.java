package com.example.ding.study.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ding.study.LessonSectionActivity;
import com.example.ding.study.R;
import com.example.ding.study.entity.Lesson;
import com.example.ding.study.entity.LessonTest;
import com.example.ding.study.utils.ImageLoader;

import java.util.List;


/**
 * listView的适配器
 * Created by ding on 2015/10/13.
 */
public class LessonListAdapter extends BaseAdapter implements AbsListView.OnScrollListener{

    //运行上下文
    private Context context;
    private Activity activity ;

    /*
     * 使用了LayoutInflater来载入界面,作用类似于 findViewById(),
     * 不同点是LayoutInflater是用来找layout下xml布局文件，并且实例化！而findViewById()是找具体xml下的具体 widget控件.
     */
    //视图容器
    private LayoutInflater inflater;

    //数据源：课程信息集合
    List<Lesson> lessons;


    //图片下载的工具类
    private ImageLoader mImageLoader;

    //mStart:第一个可见元素  mEnd:可见元素长度
    private int mStart,mEnd ;
    //保存所有需要显示的图片的url地址
    public static String[] URLS  ;

    //是否第一次加载
    private boolean mFIrstIn ;


    //构造方法
    public LessonListAdapter(Context context, List<Lesson> lessons, Activity activity, ListView listView) {
        this.activity = activity;
        //并设置上下文
        this.context = context;
        //创建视图容器
        inflater = LayoutInflater.from(context);
        //数据源
        this.lessons = lessons;

        //初始化ImageLoader,加载显示图片的工具类，将listview传进去
        mImageLoader = new ImageLoader(listView);

        URLS = new String[lessons.size()];
        for (int i = 0;i<lessons.size();i++){
            //将图片url保存到该数组里面
            URLS[i]  = lessons.get(i).getLIcon();
        }
        mFIrstIn = true ;

        //绑定监听者
        listView.setOnScrollListener(this);
    }

    // 在此适配器中所代表的数据集中的条目数
    public int getCount() {
        return lessons.size();
    }

    //获取数据集中与指定索引对应的  数据项
    public Object getItem(int position) {
        return lessons.get(position);
    }

    //取在列表中与指定索引对应的行id
    public long getItemId(int position) {
        return position;
    }

    /*
    ListView Item设置  ：获取一个在数据集中指定索引的视图来显示数据
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //控件集
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            //实例化控件集
            holder = new ViewHolder();
            //获取list_item布局文件的视图  ： 根据自定义的Item布局加载布局
            convertView = inflater.inflate(R.layout.lesson_item, null);

            //获取控件对象
            holder.ivLessonIcon = (ImageView) convertView.findViewById(R.id.ivLessonIcon);
            holder.tvLessonName = (TextView) convertView.findViewById(R.id.tvLessonName);
            holder.tvLessonSectionNumber = (TextView)convertView.findViewById(R.id.tvLessonSectionNumber);

            //将设置好的布局保存到缓存（convertView ）中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Lesson lesson =(Lesson)getItem(position);

        //设置值
        //holder.ivLessonIcon.setImageResource(R.mipmap.ic_launcher);//设置默认图片

        //图片url
        String url = lesson.getLIcon();
        //图片控件  设置标记
        holder.ivLessonIcon.setTag(url);

        //调用mImageLoader中的showImageByThread方法加载并显示图片
        //mImageLoader.showImageByThread(holder.ivLessonIcon, url);

        //调用mImageLoader中的showImageByAsyncTask方法加载并显示图片
        mImageLoader.showImageByAsyncTask(holder.ivLessonIcon, url);

        holder.tvLessonName.setText(lesson.getLName());
        holder.tvLessonSectionNumber.setText(lesson.getLCount()+" 人学习");

        //单击了课程
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建意图对象
                Intent intent = new Intent(context, LessonSectionActivity.class);
                //新建Bundle对象
                Bundle bundle = new Bundle();
                //放入需要传递的值

                bundle.putString("lessonName",lesson.getLName()+"");//把课程的名字传过去
                bundle.putString("lessonId",lesson.getPk_LId()+"");
                //intent添加bundle
                intent.putExtras(bundle);
                activity.startActivity(intent);
            }
        });

        return convertView;
    }


    //自定义控件集合 ：优化性能，按需填充并重新使用view来减少对象的创建。
    class ViewHolder {
        private ImageView ivLessonIcon;//课程图标
        private TextView tvLessonName;//课程标题
        private TextView tvLessonSectionNumber ;//课程学习人数
    }


    /**
     * AbsListView.OnScrollListener的实现方法
     * 触屏滑动滚动完后
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //当前滚动状态为停止
        if(scrollState == SCROLL_STATE_IDLE){
            //加载可见项
            mImageLoader.loadImage(mStart,mEnd);
        }else{
            //停止加载可见项
            mImageLoader.CancelAllTask();
        }
    }


    /**
     * AbsListView.OnScrollListener的实现方法
     * 整个过程都执行
     * @param view
     * @param firstVisibleItem  第一个可见元素,表示在屏幕中第一条显示的数据在adapter中的位置,
     * @param visibleItemCount  可见元素长度,则是屏幕中最后一条数据在adapter中的数据,
     * @param totalItemCount   则是adapter中的总条数!
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        mStart = firstVisibleItem ;
        mEnd  = firstVisibleItem + visibleItemCount ;
        //第一次加载时预加载数据
        if( mFIrstIn && visibleItemCount > 0 ){
            //加载图片
            mImageLoader.loadImage(mStart,mEnd);
            //第一次加载的标志设为空
            mFIrstIn = false ;
        }

    }


}
