package com.example.jack.myapplication.fragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.example.jack.myapplication.R;


public class FragmentMainIndexHome extends Fragment {
    /**
     *初始化控件
     */
    private ListView lvRecommend;           //布局中的ListView
    private GridView gvRecommend;           //布局中的GridView
    private SwipeRefreshLayout refreshRecommend;            //布局中的下拉刷新控件
    private View view;                      //整个布局

    public FragmentMainIndexHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_index_home, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();                //初始化控件
        initValues();               //初始化变量
        addListeners();             //添加监听事件
    }

    /**
     * 给控件添加监听事件
     */
    private void addListeners() {

    }

    /**
     * 初始化变量
     */
    private void initValues() {

    }

    /**
     * 初始化控件
     */
    private void initViews() {
        view = getView();
        //lvRecommend = (ListView) view.findViewById(R.id.lv_Recommend);
        //gvRecommend = (GridView) view.findViewById(R.id.gv_Recommend);
        refreshRecommend = (SwipeRefreshLayout) view.findViewById(R.id.refreshRecommend);
    }
}
