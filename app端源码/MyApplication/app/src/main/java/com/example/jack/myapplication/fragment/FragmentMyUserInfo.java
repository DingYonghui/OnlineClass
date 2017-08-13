package com.example.jack.myapplication.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jack.myapplication.R;
import com.example.jack.myapplication.service.UserService;
import com.example.jack.myapplication.service.UserServiceImpl;
import com.example.jack.myapplication.test.TestData;
import com.example.jack.myapplication.utils.SharedUtils;

public class FragmentMyUserInfo extends Fragment {

    private View view;

    private Button btn_back;

    private LinearLayout layout_sname;
    private TextView tv_sname;
    private LinearLayout layout_ssex;
    private TextView tv_ssex;
    private LinearLayout layout_sschool;
    private TextView tv_sschool;
    private LinearLayout layout_sdepartment;
    private TextView tv_sdepartment;
    private LinearLayout layout_smajor;
    private TextView tv_smajor;
    private LinearLayout layout_sclass;
    private TextView tv_sclass;
    private LinearLayout layout_semail;
    private TextView tv_semail;
    private LinearLayout layout_sphone;
    private TextView tv_sphone;

    private OnUserInfoItemClickListener mUserInfoItemClickListener;

    private String sname = "";
    private String ssex = "";
    private String sschool = "";
    private String sdepartment = "";
    private String smajor = "";
    private String sclass = "" ;
    private String semail = "";
    private String sphone = "";

    public FragmentMyUserInfo() {
        // Required empty public constructor
    }

    void initViews() {
        btn_back = (Button) view.findViewById(R.id.btn_back);
        layout_sname = (LinearLayout) view.findViewById(R.id.layout_sname);
        tv_sname = (TextView) view.findViewById(R.id.tv_sname);
        layout_ssex = (LinearLayout) view.findViewById(R.id.layout_ssex);
        tv_ssex = (TextView) view.findViewById(R.id.tv_ssex);
        layout_sschool = (LinearLayout) view.findViewById(R.id.layout_sschool);
        tv_sschool = (TextView) view.findViewById(R.id.tv_sschool);
        layout_sdepartment = (LinearLayout) view.findViewById(R.id.layout_sdepartment);
        tv_sdepartment = (TextView) view.findViewById(R.id.tv_sdepartment);
        layout_smajor = (LinearLayout) view.findViewById(R.id.layout_smajor);
        tv_smajor = (TextView) view.findViewById(R.id.tv_smajor);
        layout_sclass = (LinearLayout) view.findViewById(R.id.layout_sclass);
        tv_sclass = (TextView) view.findViewById(R.id.tv_sclass);
        layout_semail = (LinearLayout) view.findViewById(R.id.layout_semail);
        tv_semail = (TextView) view.findViewById(R.id.tv_semail);
        layout_sphone = (LinearLayout) view.findViewById(R.id.layout_sphone);
        tv_sphone = (TextView) view.findViewById(R.id.tv_sphone);




    }

    private int selectedIndex ;
    void initListener() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //弹出框
                AlertDialog dialog = new AlertDialog.Builder(getContext()).setTitle("提示").setMessage("是否确定修改个人信息？").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                getActivity().finish();
                            }
                        }
                ).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        final UserService service = new UserServiceImpl();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                /**
                                 * 访问网络更新数据
                                 */
                                String pk_SPhone = SharedUtils.getUserId(getContext());
                                String SName = tv_sname.getText().toString() ;

                                String SGender = tv_ssex.getText().toString();
                                if(SGender.equals("男")){
                                    SGender = "0" ;
                                }else if(SGender.equals("女")){
                                    SGender = "1" ;
                                }else {
                                    SGender = "2" ;
                                }
                                Log.i("提示：","待上传的性别为："+SGender);

                                String SSchool = tv_sschool.getText().toString();


                                String SDepartment =tv_sdepartment.getText().toString();
                                if(SDepartment.equals("互联网系")){
                                    SDepartment = "0";
                                } else if(SDepartment.equals("金融系")){
                                    SDepartment = "1";
                                }else if(SDepartment.equals("工管系")){
                                    SDepartment = "2";
                                }else if(SDepartment.equals("外语系")){
                                    SDepartment = "3";
                                }else if(SDepartment.equals("法律系")){
                                    SDepartment = "4";
                                }else if(SDepartment.equals("财传系")){
                                    SDepartment = "5";
                                }else if(SDepartment.equals("应数系")){
                                    SDepartment = "6";
                                }else if(SDepartment.equals("公管系")){
                                    SDepartment = "7";
                                }else if(SDepartment.equals("信管系")){
                                    SDepartment = "8";
                                }else if(SDepartment.equals("会计系")){
                                    SDepartment = "9";
                                }else if(SDepartment.equals("保险系")){
                                    SDepartment = "10";
                                }else if(SDepartment.equals("经贸系")){
                                    SDepartment = "11";
                                }else if(SDepartment.equals("劳经系")){
                                    SDepartment = "12";
                                }else if(SDepartment.equals("其他")){
                                    SDepartment = "-1";
                                }

                                String SMajor = tv_smajor.getText().toString();
                                String SClass = tv_sclass.getText().toString();

                                String SDefaultPhone = "1" ;
                                String SEmail = "1" ;
                                String SRegistTime = "1" ;
                                String SAge = "1" ;
                                String SNickName = "1" ;
                                String SHeadIcon = "1" ;
                                try {
                                    service.completeUserInformation(
                                            pk_SPhone,SName,  SHeadIcon,
                                            SAge,  SGender,  SNickName,
                                            SSchool,  SDepartment,  SMajor,
                                            SClass,  SDefaultPhone,
                                            SEmail, SRegistTime);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        getActivity().finish();
                    }
                }).show();
                mUserInfoItemClickListener.updateUserData();
            }

        });


        layout_sname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserInfoItemClickListener != null) {
                    String currentInfo = tv_sname.getText().toString();
                    Log.i("提示：当前姓名为：", currentInfo);
                    mUserInfoItemClickListener.infoItemClick(currentInfo, "1");
                }
            }
        });

        layout_ssex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] array = new String[]{"男","女","保密"};

                Dialog alterDialog = new AlertDialog.Builder(getContext())
                        .setTitle("你的性别是")
                        .setIcon(R.mipmap.user_head)
                        .setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedIndex = which ;
                            }
                        }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ssex = array[selectedIndex];
                                updateUserInfoByTag(ssex,"7");
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
                alterDialog.show();


            }
        });
        layout_sschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserInfoItemClickListener != null) {
                    String currentInfo = tv_sschool.getText().toString();
                    Log.i("提示：当前姓名为：", currentInfo);
                    mUserInfoItemClickListener.infoItemClick(currentInfo, "2");
                }
            }
        });
        layout_sdepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] array =TestData.getDepartmentsForArray();

                Dialog alterDialog = new AlertDialog.Builder(getContext())
                        .setTitle("你的系别是")
                        .setIcon(R.mipmap.user_head)
                        .setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedIndex = which ;
                            }
                        }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sdepartment = array[selectedIndex];
                                updateUserInfoByTag(sdepartment,"8");
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
                alterDialog.show();


            }
        });
        layout_smajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserInfoItemClickListener != null) {
                    String currentInfo = tv_smajor.getText().toString();
                    Log.i("提示：当前姓名为：", currentInfo);
                    mUserInfoItemClickListener.infoItemClick(currentInfo, "3");
                }
            }
        });
        layout_sclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUserInfoItemClickListener != null){
                    String currentInfo = tv_sclass.getText().toString();
                    Log.i("提示：当前姓名为：",currentInfo);
                    mUserInfoItemClickListener.infoItemClick(currentInfo,"4");
                }
            }
        });
        layout_semail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserInfoItemClickListener != null) {
                    String currentInfo = tv_semail.getText().toString();
                    Log.i("提示：当前姓名为：", currentInfo);
                    mUserInfoItemClickListener.infoItemClick(currentInfo, "5");
                }
            }
        });
        layout_sphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserInfoItemClickListener != null) {
                    String currentInfo = tv_sphone.getText().toString();
                    Log.i("提示：当前姓名为：", currentInfo);
                    mUserInfoItemClickListener.infoItemClick(currentInfo, "6");
                }
            }
        });

    }

    void initValues() {

    }

    public void updateUserInfoByTag(String data, String tag){
        if(tag.equals("1")){
            sname = data;
            tv_sname.setText(data);
        }
        else  if(tag.equals("2")){
            sschool = data;
            tv_sschool.setText(data);
        }
        else  if(tag.equals("3")){
            smajor = data ;
            tv_smajor.setText(data);
        }
        else  if(tag.equals("4")){
            sclass = data ;
            tv_sclass.setText(data);
        }
        else  if(tag.equals("5")){
            semail = data;
            tv_semail.setText(data);
        }
        else  if(tag.equals("6")){
            sphone = data ;
            tv_sphone.setText(data);
        }
        else  if(tag.equals("7")){
            //ssex = data ;
            tv_ssex.setText(data);
        }
        else  if(tag.equals("8")){
            //sdepartment = data ;
            tv_sdepartment.setText(data);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_user_info, container, false);
        initViews();
        initListener();
        initValues();

        Bundle bundle =  getArguments();
        tv_sname.setText(bundle.getString("SName",""));

        String sgender = bundle.getString("SGender");
        Log.i("提示：","接收过来的性别为:"+sgender);

        if(sgender.equals("0"))
             sgender = "男" ;
        else if(sgender.equals("1")){
            sgender = "女" ;
        }else{
            sgender = "保密" ;
        }
        tv_ssex.setText(sgender);

        tv_sschool.setText(bundle.get("SSchool").toString());

        String sDepartmesnt = bundle.getString("SDepartment", "0");
        if(sDepartmesnt.equals("0"))
            sDepartmesnt = "互联网系" ;
        else if(sDepartmesnt.equals("1")){
            sDepartmesnt = "金融系" ;
        }else if(sDepartmesnt.equals("2")){
            sDepartmesnt = "工管系" ;
        }
        else if(sDepartmesnt.equals("3")){
            sDepartmesnt = "外语系" ;
        }
        else if(sDepartmesnt.equals("4")){
            sDepartmesnt = "法律系" ;
        }
        else if(sDepartmesnt.equals("5")){
            sDepartmesnt = "财传系" ;
        }
        else if(sDepartmesnt.equals("6")){
            sDepartmesnt = "应数系" ;
        }
        else if(sDepartmesnt.equals("7")){
            sDepartmesnt = "公管系" ;
        }
        else if(sDepartmesnt.equals("8")){
            sDepartmesnt = "信管系" ;
        }
        else if(sDepartmesnt.equals("9")){
            sDepartmesnt = "会计系" ;
        }
        else if(sDepartmesnt.equals("10")){
            sDepartmesnt = "保险系" ;
        }
        else if(sDepartmesnt.equals("11")){
            sDepartmesnt = "经贸系" ;
        }
        else if(sDepartmesnt.equals("12")){
            sDepartmesnt = "劳经系" ;
        }else{
            sDepartmesnt = "其他" ;
        }
        tv_sdepartment.setText(sDepartmesnt);


        tv_smajor.setText(bundle.get("SMajor").toString());
        tv_sclass.setText(bundle.get("SClass").toString());

        return view;
    }

    /**
     * 事件监听器
     */
    public void setOnUserInfoItemClickListener(OnUserInfoItemClickListener listener) {
        mUserInfoItemClickListener = listener;
    }

    /**
     * 自定义的点击事件，为了让点击时候可以跳转到其他fragment
     */
    public interface OnUserInfoItemClickListener {
        void infoItemClick(String currentInfo, String tag);
        void updateUserData();
    }

}
