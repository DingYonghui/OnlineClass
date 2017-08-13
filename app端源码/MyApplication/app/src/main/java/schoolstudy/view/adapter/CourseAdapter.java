package schoolstudy.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jack.myapplication.R;

import java.util.List;

import schoolstudy.service.LessonAndSelection;
import schoolstudy.service.LessonAndSelectionImp;
import schoolstudy.view.entity.Course;

/**
 * 这个类是listView的课程的适配器类
 */
public class CourseAdapter extends ArrayAdapter {
    private List<Course> mData;
    private int res = R.layout.item_listview;
    private LayoutInflater inflater;
    private LessonAndSelection service;
    private Context mContext;
    public CourseAdapter(Context context,List<Course> data) {
        super(context, R.layout.item_listview);
        mData = data;
        mContext = context;
        inflater = LayoutInflater.from(context);
        service = new LessonAndSelectionImp();
    }

    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {

        return mData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = inflater.inflate(res,null);
            holder = new ViewHolder();
            holder.ivCourse = (ImageView) convertView.findViewById(R.id.ivCourse);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvCourseTitle);
            holder.tvLecturer = (TextView) convertView.findViewById(R.id.tvCourseLecturer);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Course course = (Course) getItem(position);

        if(!TextUtils.isEmpty(course.getImageUri()))
            holder.ivCourse.setImageURI(Uri.parse(course.getImageUri()));

        holder.tvTitle.setText(course.getTitle());
        holder.tvLecturer.setText(course.getLecturer());
        return convertView;
    }

    private class ViewHolder{
        ImageView ivCourse;
        TextView tvTitle;
        TextView tvLecturer;
    }


}
