package schoolstudy.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jack.myapplication.R;

import java.util.List;

import schoolstudy.view.entity.Department;

/**
 * Z这个类是GridView的适配器
 */
public class DepartmentAdapter extends ArrayAdapter {

    private List<Department> mData;         //创建内部类
    private LayoutInflater mInflater;       //创建一个inflater
    private int res = R.layout.item_gridview;           //item布局资源
    private String[] departments;

    /**
     *系的图标
     */
    private int[] resImages = new int[]{R.drawable.department_internet,R.drawable.department_finance,R.drawable.department_engineer_manage,
                                        R.drawable.department_foreign,R.drawable.department_law,R.drawable.department_money_transfer,R.drawable.department_math,
                                        R.drawable.department_more
    };
    private int[] departmentId = new int[]{
        1,2,3,4,5,6,7,8
    };

    public DepartmentAdapter(Context context,List<Department> data) {
        super(context, R.layout.item_gridview);
        mInflater = LayoutInflater.from(context);
        mData = data;
        departments = context.getResources().getStringArray(R.array.departments);
    }

    /**
     * 规定GridView只有8个
     */
    public int getCount() {
        return 8;
    }

    public Object getItem(int position) {
//        return mData.get(position);
        return departmentId[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(res,null);
            holder = new ViewHolder();
            holder.ivDepartment = (ImageView) convertView.findViewById(R.id.ivDepartment);
            holder.tvDepartment = (TextView) convertView.findViewById(R.id.tvDepartment);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivDepartment.setImageResource(resImages[position]);
        if(position != 7) {
            holder.tvDepartment.setText(departments[position]);
        }else{
            holder.tvDepartment.setText("更多");
        }
        return convertView;
    }

    /**
     * 创建一个ViewHolder类，用于缓存数据
     */
    private class ViewHolder{
        ImageView ivDepartment;
        TextView tvDepartment;
    }
}
