package com.example.ding.study.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.TextView;

import com.example.ding.study.LessonPartActivity;
import com.example.ding.study.LessonSectionActivity;
import com.example.ding.study.R;
import com.example.ding.study.entity.Lesson;
import com.example.ding.study.entity.LessonSection;

import java.util.List;

/**
 * Created by ding on 2015/11/4.
 */
public class LessonSectionListAdapter extends BaseAdapter {

    //运行上下文
    private Context context;
    private Activity activity;

    //视图容器
    private LayoutInflater inflater;

    //数据源：课程信息集合
    List<LessonSection> lessonSections;

    public LessonSectionListAdapter(Context context, List<LessonSection> lessonSections, Activity activity) {
        this.activity = activity;
        //并设置上下文
        this.context = context;
        //创建视图容器
        inflater = LayoutInflater.from(context);
        //数据源
        this.lessonSections = lessonSections;

    }


    @Override
    public int getCount() {
        return lessonSections.size();
    }

    @Override
    public Object getItem(int position) {
        return lessonSections.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //控件集
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            //实例化控件集
            holder = new ViewHolder();
            //获取list_item布局文件的视图  ： 根据自定义的Item布局加载布局
            convertView = inflater.inflate(R.layout.lesson_section_item, null);

            holder.iv_lesson_section_icon = (ImageView) convertView.findViewById(R.id.iv_lesson_section_icon);
            holder.tv_lesson_section_name = (TextView) convertView.findViewById(R.id.tv_lesson_section_name);
            holder.tv_lesson_section_count_and_time = (TextView) convertView.findViewById(R.id.tv_lesson_section_count_and_time);

            //将设置好的布局保存到缓存（convertView ）中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final LessonSection lessonSection = (LessonSection) getItem(position);

        //测试：设为默认图片
        holder.iv_lesson_section_icon.setImageResource(R.mipmap.ic_launcher);
        holder.tv_lesson_section_name.setText(lessonSection.getSName());
        holder.tv_lesson_section_count_and_time.setText(lessonSection.getSCount() +"人学习，"+ lessonSection.getSTime()+"小节");

        //单击了课程
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建意图对象
                Intent intent = new Intent(context, LessonPartActivity.class);
                //新建Bundle对象
                Bundle bundle = new Bundle();
                //放入需要传递的值

                bundle.putString("lessonSectionName", lessonSection.getSName() + "");//把课程的名字传过去
                bundle.putString("lessonSectionId", lessonSection.getPk_SId() + "");
                //intent添加bundle
                intent.putExtras(bundle);
                activity.startActivity(intent);
            }
        });


        return convertView;
    }


    //自定义控件集合 ：优化性能，按需填充并重新使用view来减少对象的创建。
    class ViewHolder {
        private ImageView iv_lesson_section_icon;
        private TextView tv_lesson_section_name;
        private TextView tv_lesson_section_count_and_time;

    }
}
