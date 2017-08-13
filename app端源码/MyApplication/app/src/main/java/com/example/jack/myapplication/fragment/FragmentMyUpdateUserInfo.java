package com.example.jack.myapplication.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jack.myapplication.R;

public class FragmentMyUpdateUserInfo extends Fragment {

    private View view;
    private Button btn_back;
    private Button btn_ok;
    private EditText et_student_info ;

    private OnBtnClickListener onBtnClickListener ;

    void initViews() {
        btn_back = (Button)view.findViewById(R.id.btn_back);
        btn_ok = (Button)view.findViewById(R.id.btn_ok);
        et_student_info = (EditText)view.findViewById(R.id.et_student_info);
    }

    void initListener(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onBtnClickListener!=null)
                    onBtnClickListener.onBtnBackClick();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = et_student_info.getText().toString();
                if(onBtnClickListener!=null)
                    onBtnClickListener.onBtnOkClick(data);
            }
        });
    }
    void initValues() {

    }

    public FragmentMyUpdateUserInfo() {
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
        view =  inflater.inflate(R.layout.fragment_my_update_user_info, container, false);

        initViews();
        initListener();
        initValues();

        return view;
    }

    public void setCurrentText(String currentText){
        et_student_info.setText(currentText);
    }

    /**
     * 事件监听器
     */
    public void setBtnOnClickListener(OnBtnClickListener listener) {
        onBtnClickListener = listener;
    }

    /**
     * 自定义的点击事件，为了让点击时候可以跳转到其他fragment
     */
    public interface OnBtnClickListener {
        void onBtnBackClick();
        void onBtnOkClick(String data);
    }

}
