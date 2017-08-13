package com.example.jack.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jack.myapplication.R;
import com.example.jack.myapplication.consts.MyHttpURL;
import com.example.jack.myapplication.entity.Lesson;
import com.example.jack.myapplication.utils.ImageLoader;

import java.util.List;


/**
 * listView的适配器
 * Created by ding on 2015/10/13.
 */
public class LessonListAdapter extends BaseAdapter {

    //保存所有需要显示的图片的url地址
    public String[] imageUrls;
    //数据源：课程信息集合
    List<Lesson> lessons;
    //运行上下文
    private Context context;
    //视图容器
    private LayoutInflater inflater;
    //图片下载的工具类
    private ImageLoader mImageLoader;

    //构造方法
    public LessonListAdapter(Context context, List<Lesson> lessons, ListView listView) {
        //并设置上下文
        this.context = context;
        //创建视图容器
        inflater = LayoutInflater.from(context);
        //数据源
        this.lessons = lessons;

        imageUrls = new String[lessons.size()];
        for (int i = 0; i < lessons.size(); i++) {
            //将图片url保存到该数组里面
            imageUrls[i] = lessons.get(i).getLIcon();
        }
        //初始化ImageLoader,加载显示图片的工具类，将listview传进去
        mImageLoader = new ImageLoader();
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
            convertView = inflater.inflate(R.layout.item_lesson_list_content, null);

            //获取控件对象
            holder.ivLessonIcon = (ImageView) convertView.findViewById(R.id.ivLessonIcon);
            holder.tvLessonName = (TextView) convertView.findViewById(R.id.tvLessonName);
            holder.tvLessonSectionNumber = (TextView) convertView.findViewById(R.id.tvLessonSectionNumber);

            //将设置好的布局保存到缓存（convertView ）中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Lesson lesson = (Lesson) getItem(position);

        //图片url
        String url = lesson.getLIcon();
        String lname = lesson.getLName();
        String lcount = lesson.getLCount()+"";
        Log.i("提示：", "图片url为：" + url);
        //if(url!=null||!url.equals(MyHttpURL.IP)){
            //调用mImageLoader中的showImageByAsyncTask方法加载并显示图片
       //}
        if(url == null ||url.equals("")){
            holder.ivLessonIcon.setImageResource(R.mipmap.no);
        }else{
            url = "http://120.24.5.239:8080/ClassOnline/"+url ;
            mImageLoader.showImageByAsyncTask(holder.ivLessonIcon, url);
        }



        if (lname!=null){
            holder.tvLessonName.setText(lname);
        }
        if (lcount != null){
            holder.tvLessonSectionNumber.setText( "   "+ lcount+"章节" );
        }

        return convertView;
    }

    //自定义控件集合 ：优化性能，按需填充并重新使用view来减少对象的创建。
    class ViewHolder {
        private ImageView ivLessonIcon;//课程图标
        private TextView tvLessonName;//课程标题
        private TextView tvLessonSectionNumber;//课程学习人数
    }


}
