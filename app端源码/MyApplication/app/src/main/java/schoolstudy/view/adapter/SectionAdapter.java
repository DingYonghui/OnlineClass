package schoolstudy.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jack.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import schoolstudy.view.entity.Part;
import schoolstudy.view.entity.Section;

/**
 * Created by blackdog on 2015/11/17.
 */
public class SectionAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Section> datas;
    private List<Map> list;
    private Map<Integer,Object> map;
    private final int TYPE_TITLE = 0;
    private final int TYPE_CONTENT = 1;

    public SectionAdapter(Context context, List<Section> data){
        inflater = LayoutInflater.from(context);
        datas = data;
        list = new ArrayList<>();
        for(int i = 0;i<datas.size();i++){
            map = new HashMap<>();
            map.put(0,datas.get(i).getName());
            list.add(map);
            List<Part> parts = datas.get(i).getCount();
            for(int j = 0;j<parts.size();j++){
                map = new HashMap<>();
                map.put(1,parts.get(j));
                list.add(map);
            }
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Map<Integer,Object> map = (Map<Integer, Object>) getItem(position);
        if(convertView == null){
            holder = new ViewHolder();
            switch (getItemViewType(position)){
                case TYPE_TITLE:
                    convertView = inflater.inflate(R.layout.item_course_section,null);
                    holder.textView = (TextView) convertView.findViewById(R.id.tvCourseSection);
                    break;
                case TYPE_CONTENT:
                    convertView = inflater.inflate(R.layout.item_course_part,null);
                    holder.textView = (TextView) convertView.findViewById(R.id.tvCoursePart);
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if(getItemViewType(position) == TYPE_TITLE){
            holder.textView.setText(""+map.get(0));
        }else{
            holder.textView.setText(((Part)map.get(1)).getName());
        }
        return convertView;

    }

    private class ViewHolder{
        TextView textView;
    }

    @Override
    public boolean isEnabled(int position) {
        Map<Integer, Object> map = (Map<Integer, Object>) getItem(position);
        if (map.get(0) == null) {
            return true;
        } else {
            return false;
        }
    }



    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public int getItemViewType(int position) {
        Map<Integer, Object> map = (Map<Integer, Object>) getItem(position);
        if (map.get(0) == null) {
            return TYPE_CONTENT;
        } else {
            return TYPE_TITLE;
        }
    }



}
 class ViewHolder{
        TextView textView;
}

