package com.example.jack.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.jack.myapplication.entity.Lesson;

import java.util.List;

/**
 * Created by jack on 2015-12-10.
 */
public class LessonCollectionAdapter extends BaseAdapter {

    List<Lesson> lessons ;

    LessonCollectionAdapter(Context context,List<Lesson> lessons){

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
