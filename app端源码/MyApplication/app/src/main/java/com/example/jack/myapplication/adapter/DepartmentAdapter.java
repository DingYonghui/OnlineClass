package com.example.jack.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jack.myapplication.R;
import com.example.jack.myapplication.entity.Department;

import java.util.List;

/**
 * Created by ding on 2015/10/13.
 */
public class DepartmentAdapter extends BaseAdapter {

    //运行上下文
    private Context context;
    private Activity activity;
    //视图容器
    private LayoutInflater inflater;

    //数据源：课程信息集合
    List<Department> departments;

    ListView lv_lesson ;

    public DepartmentAdapter() {
    }
    public DepartmentAdapter(Context context, List<Department> departments, Activity activity,ListView lv_lesson) {
        this.activity = activity;
        //并设置上下文
        this.context = context;
        //创建视图容器
        inflater = LayoutInflater.from(context);
        //数据源
        this.departments = departments;

        this.lv_lesson = lv_lesson ;
    }
    @Override
    public int getCount() {
        return departments.size();
    }
    @Override
    public Object getItem(int position) {
        return departments.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView,final ViewGroup parent) {

        //控件集
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            //实例化控件集
            holder = new ViewHolder();
            //获取list_item布局文件的视图  ： 根据自定义的Item布局加载布局
            convertView = inflater.inflate(R.layout.item_department_title, null);
            //获得布局中的TextView
            holder.department_category_title = (TextView) convertView.findViewById(R.id.department_category_title);
            //将设置好的布局保存到缓存（convertView ）中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获取系别数据
        final Department department = (Department) getItem(position);
        //设置标题
        holder.department_category_title.setText(department.getDName());
        //更新第一个TextView的字体颜色
        if (position == 0) {
            //holder.department_category_title.setBackgroundResource(R.mipmap.department_title_background);
            holder.department_category_title.setTextColor(0xFF6DFF2D);

        }

        return convertView;
    }

    //自定义控件集合 ：优化性能，按需填充并重新使用view来减少对象的创建。
    class ViewHolder {
        private TextView department_category_title;
    }

}
