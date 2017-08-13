package schoolstudy.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jack.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import schoolstudy.service.PartExchangeImp;
import schoolstudy.view.adapter.PartExchangeAdapter;
import schoolstudy.view.entity.PartExchange;


/**
 * 这个是学习交流的fragment
 */
public class FragmentStudyAndCommunication extends Fragment {

    //创建控件
    private EditText editText;
    private TextView tvSend;
    private SwipeRefreshLayout refreshLayout;
    private ListView listView;

    //创建变量
    private PartExchangeImp service;
    private List<PartExchange> data;
    private PartExchangeAdapter adapter;
    private String partId = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_study_and_communication,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initValues();
        bindAdapter();
        initListeners();
}

    /**
     * 绑定适配器
     */
    private void bindAdapter() {
        adapter = new PartExchangeAdapter(getContext(),data);
        listView.setAdapter(adapter);
    }

    /**
     * 添加监听事件
     */
    private void initListeners() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try{
                    refreshLayout.setRefreshing(true);
                    updatePartExchange();
                }catch (Exception e){

                }finally {
                    refreshLayout.setRefreshing(false);
                }
            }
        });

        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(partId == null){
                    return;
                }
                String content = editText.getText().toString();
                if(TextUtils.isEmpty(content)){
                    return;
                }
                PartExchange part = new PartExchange();
                part.setName("测试");
                part.setContent(content);
                part.setPartId(partId);
                sendPartExchange(part);
            }
        });
    }

    /**
     * 更新ListView的数据
     */
    private void updatePartExchange(){
        if(partId == null){
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    data = service.getPartExchanges(partId);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bindAdapter();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 发送评论
     * @param partExchange
     */
    private void sendPartExchange(final PartExchange partExchange){
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("发送中");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    service.doPartExchange(partExchange);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(),"评论成功",Toast.LENGTH_LONG).show();
                        }
                    });
                }catch (Exception e){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(),"发送错误",Toast.LENGTH_LONG).show();
                        }
                    });
                }finally {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updatePartExchange();
                            editText.setText("");
                            dialog.dismiss();
                        }
                    });
                }
            }
        }).start();
    }

    /**
     * 初始化变量
     */
    private void initValues() {
        service = new PartExchangeImp();
        data = new ArrayList<>();
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        View view = getView();
        editText = (EditText) view.findViewById(R.id.etStudyAndCommunication);
        tvSend = (TextView) view.findViewById(R.id.tvSend);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshStudyAndCommunication);
        listView = (ListView) view.findViewById(R.id.lvStudyAndCoummunication);
    }

    public void setPartId(String partId){
        this.partId = partId;
        updatePartExchange();
    }

}

