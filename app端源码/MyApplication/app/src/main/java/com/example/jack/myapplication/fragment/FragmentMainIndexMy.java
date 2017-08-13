package com.example.jack.myapplication.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.jack.myapplication.activity.user.LoginActivity;
import com.example.jack.myapplication.R;
import com.example.jack.myapplication.activity.mainIndexMy.MyAboutUsActivity;
import com.example.jack.myapplication.activity.mainIndexMy.MyDownloadActivity;
import com.example.jack.myapplication.activity.mainIndexMy.MyLessonsCollectionActivity;
import com.example.jack.myapplication.activity.mainIndexMy.MySettingCenterActivity;
import com.example.jack.myapplication.activity.mainIndexMy.MyStudyRecordActivity;
import com.example.jack.myapplication.activity.mainIndexMy.MyUserInformationActivity;
import com.example.jack.myapplication.consts.MyHttpURL;
import com.example.jack.myapplication.entity.StudentUserInfo;
import com.example.jack.myapplication.http.HttpUtils;
import com.example.jack.myapplication.service.UserService;
import com.example.jack.myapplication.service.UserServiceImpl;
import com.example.jack.myapplication.utils.SharedUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;


public class FragmentMainIndexMy extends Fragment {

    View view = null;
    StudentUserInfo studentUserInfo = null;
    UserService service = new UserServiceImpl();
    private TextView tv_user_information;
    private TextView tv_user_name ;
    private Button tv_study_record;
    private Button tv_lessons_collection;
    private Button tv_my_download;
    private Button tv_setting_center;
    private Button tv_about_us;
    private Button tv_logout_account;

    public void initValues() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                studentUserInfo = getStudentUserInfo(SharedUtils.getUserId(getContext()));

                handler.sendEmptyMessage(1);
            }
        }).start();



        //Log.i("通过fragment：",studentUserInfo.toString());
//        try {
//            String result = service.getUserInformation(SharedUtils.getUserId(getContext()), SharedUtils.getUserPassword(getContext()),getContext());
//            Log.i("通过service：",result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_index_my, container, false);

        //初始化
        //initValues();
        initViews();
        initListener();

        //初始化个人信息
//        if(SharedUtils.getUserId(getContext())!=null &&! SharedUtils.getUserId(getContext()).equals("")){
//            studentUserInfo = getStudentUserInfo(SharedUtils.getUserId(getContext()));
//            tv_user_information.setText(studentUserInfo.getSNickName().toString());
//        }
        flag = true ;
        return view;
    }

    void initViews() {
        tv_user_information = (TextView) view.findViewById(R.id.tv_user_information);
        tv_user_name = (TextView)view.findViewById(R.id.tv_user_name);
        tv_study_record = (Button) view.findViewById(R.id.tv_study_record);
        tv_lessons_collection = (Button) view.findViewById(R.id.tv_lessons_collection);
        tv_my_download = (Button) view.findViewById(R.id.tv_my_download);
        tv_setting_center = (Button) view.findViewById(R.id.tv_setting_center);
        tv_about_us = (Button) view.findViewById(R.id.tv_about_us);
        tv_logout_account = (Button) view.findViewById(R.id.tv_logout_account);
    }

    void initListener() {

        //个人信息
        tv_user_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                if (studentUserInfo != null) {

                    bundle.putString("pk_SPhone", studentUserInfo.getPk_SPhone());
                    bundle.putString("SGender", studentUserInfo.getSGender()+"");
                    bundle.putString("SNo", studentUserInfo.getSNo());
                    bundle.putString("SName", studentUserInfo.getSName());
                    bundle.putString("SHeadIcon", studentUserInfo.getSHeadIcon());
                    bundle.putString("SAge", studentUserInfo.getSAge()+"");
                    bundle.putString("SNickName", studentUserInfo.getSNickName());
                    bundle.putString("SSchool", studentUserInfo.getSSchool());
                    Log.i("准备发送过去的学校:",studentUserInfo.getSSchool());
                    bundle.putString("SDepartment", studentUserInfo.getSDepartment());
                    bundle.putString("SMajor", studentUserInfo.getSMajor());
                    bundle.putString("SClass", studentUserInfo.getSClass());
                    bundle.putString("SDefaultPhone", studentUserInfo.getSDefaultPhone());
                    bundle.putString("SEmail", studentUserInfo.getSEmail());
                    bundle.putString("SRegistTime", studentUserInfo.getSRegistTime());
                }

                Intent intent = new Intent(getActivity(), MyUserInformationActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });

        //学习记录
        tv_study_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyStudyRecordActivity.class));
            }
        });

        //课程收藏
        tv_lessons_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyLessonsCollectionActivity.class));
            }
        });

        //我的离线
        tv_my_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyDownloadActivity.class));
            }
        });

        //设置中心
        tv_setting_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MySettingCenterActivity.class));
            }
        });


        //关于我们
        tv_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyAboutUsActivity.class));
            }
        });

        //退出登录
        tv_logout_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //弹出框
                AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("提示").setMessage("你确定要退出登录？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }
                        ).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                                //将登录标志设为false
                                SharedUtils.putLoginBoolean(getContext(), false);
                            }
                        }).show();

            }
        });
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tv_user_name.setText(studentUserInfo.getSName());
        }
    };


    //根据pk_SPhone通过网络取得学生信息的数据，暂时无用
    public StudentUserInfo getStudentUserInfo(String pk_SPhone) {

        studentUserInfo = new StudentUserInfo();
        try {
            //通过url,调用readStream()获得网络数据
            String url = "http://120.24.5.239:8080/ClassOnline/UserServlet?status=getStudentInfo&&pk_SPhone=" + pk_SPhone;
            Log.i("获取个人信息的url为：", url);

            String jsonString = readStream(new URL(url).openStream());

            //Log.i("获取的个人信息为：",jsonString);
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(jsonString);
                //找到json数据中名为data的数据
                JSONObject temp = jsonObject.getJSONObject("data");
                Log.i("获取个人信息的json为：", temp.toString());

                String pk_SPhone1 = temp.getString("pk_SPhone") ;
                if(pk_SPhone1!=null)
                studentUserInfo.setPk_SPhone(pk_SPhone1);
                Log.i("获取学生手机号为：", pk_SPhone1);

                if(temp.getString("SNo")!=null)
                studentUserInfo.setSNo(temp.getString("SNo"));
                Log.i("获取学生学号为：", temp.getString("SNo"));


                String sname = temp.getString("SName") ;
                if(sname!=null)
                studentUserInfo.setSName(sname);
                Log.i("获取学生姓名为：", sname);
                //studentUserInfo.setSHeadIcon(temp.getString("SHeadIcon"));
                //Log.i("获取学生头像为：", temp.getString("SHeadIcon"));

                //studentUserInfo.setSAge(Integer.getInteger(temp.getString("SAge")));
                //Log.i("获取学生年龄为：", temp.getString("SAge"));

                studentUserInfo.setSGender(Integer.parseInt(temp.getString("SGender")));
                Log.i("获取学生性别为：", temp.getString("SGender"));

                if(temp.getString("SNickName")!=null)
                studentUserInfo.setSNickName(temp.getString("SNickName"));
                Log.i("获取学生昵称为：", temp.getString("SNickName"));

                if(temp.getString("SSchool")!=null)
                studentUserInfo.setSSchool(temp.getString("SSchool"));
                Log.i("获取学生学校为：", temp.getString("SSchool"));

                if(temp.getString("SDepartment")!=null)
                studentUserInfo.setSDepartment(temp.getString("SDepartment"));
                Log.i("获取学生系别为：", temp.getString("SDepartment"));

                if(temp.getString("SMajor")!=null)
                studentUserInfo.setSMajor(temp.getString("SMajor"));
                Log.i("获取学生专业为：", temp.getString("SMajor"));

                if(temp.getString("SClass")!=null)
                studentUserInfo.setSClass(temp.getString("SClass"));
                Log.i("获取学生班级为：", temp.getString("SClass"));

                if(temp.getString("SDefaultPhone")!=null)
                    studentUserInfo.setSDefaultPhone(temp.getString("SDefaultPhone"));
                Log.i("获取学生默认手机为：", temp.getString("SDefaultPhone"));

                if(temp.getString("SEmail")!=null)
                studentUserInfo.setSEmail(temp.getString("SEmail"));
                Log.i("获取学生邮箱为：", temp.getString("SEmail"));

                if(temp.getString("SRegistTime")!=null)
                  studentUserInfo.setSRegistTime(temp.getString("SRegistTime"));
                Log.i("获取学生注册时间为：", temp.getString("SRegistTime"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentUserInfo;
    }


    /**
     * 通过is解析读取网络数据
     *
     * @param is
     * @return
     */
    private String readStream(InputStream is) {

        InputStreamReader isr;
        String result = "";
        String line = "";
        try {
            //字节流转化为字符流
            isr = new InputStreamReader(is, "utf-8");
            //缓冲流
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result = result + line;
            }
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(getContext(), "读取网络数据，并转换", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    boolean flag = false ;

    //当前Activity由被覆盖状态回到前台或解锁屏：系统会调用onResume方法，再次进入运行状态。
    @Override
    public void onResume() {
        super.onResume();

        //if(flag){
            initValues();
        //}


    }
}
