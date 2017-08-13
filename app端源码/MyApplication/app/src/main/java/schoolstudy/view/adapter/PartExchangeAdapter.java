package schoolstudy.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jack.myapplication.R;

import java.util.List;

import schoolstudy.view.entity.PartExchange;

/**
 * Created by liaoliao on 2015/11/30.
 */
public class PartExchangeAdapter extends BaseAdapter {

    private List<PartExchange> mData;
    private LayoutInflater inflater;

    public PartExchangeAdapter(Context context, List<PartExchange> data){
        inflater = LayoutInflater.from(context);
        mData = data;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_study_and_communication,null);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        PartExchange partExchange = (PartExchange) getItem(position);
        holder.tvName.setText(partExchange.getName());
        holder.tvContent.setText(partExchange.getContent());
        return convertView;
    }

    class ViewHolder{
        TextView tvName;
        TextView tvContent;
    }
}
