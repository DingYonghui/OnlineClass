package com.example.ding.study.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by ding on 2015/10/13.
 */
public class DepartmentCategoryAdapter extends SimpleAdapter {
    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     *                 第一个参数 表示访问整个android应用程序接口，基本上所有的组件都需要
     *                 第二个参数表示生成一个Map(String ,Object)列表选项
     *                 第三个参数表示 界面布局 的 id  表示该文件作为列表项的组件
     *                 第四个参数表示该Map对象的哪些key对应value来生成列表项
     *                 第五个参数表示来填充的组件 Map对象key对应的资源一依次填充组件 顺序有对应关系
     *                 注意的是map对象可以key可以找不到 但组件的必须要有资源填充  因为 找不到key也会返回null 其实就相当于给了一个null资源
     */

    public DepartmentCategoryAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        //更新第一个TextView的字体颜色
        if (position == 0) {
            TextView category = (TextView) view;
            category.setTextColor(0xFF6DFF2D);
        }
        return view;
    }

}
