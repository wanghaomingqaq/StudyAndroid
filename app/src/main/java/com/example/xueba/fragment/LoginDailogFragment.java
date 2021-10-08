package com.example.xueba.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;
import com.example.xueba.R;
import com.example.xueba.fragment.wechatFragment;
import com.example.xueba.utils.ToastUtil;


import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.DialogFragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/2/8.
 */

public class LoginDailogFragment extends DialogFragment implements View.OnClickListener {

    private EditText mUsername;
    private EditText mPassword;
    private Button btn;
    private ImageView iv;
    private TextView toReg;


    public interface LoginInputListener{
        void onLoginInputComplete(String userName, String password);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置背景透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_login_dailog, null);
        iv= view.findViewById(R.id.login_iv);
        toReg= view.findViewById(R.id.login_register);
        mUsername= view.findViewById(R.id.login_et1);
        btn= view.findViewById(R.id.login_btn);
        mPassword= view.findViewById(R.id.login_et2);
        iv.setOnClickListener(this);
        toReg.setOnClickListener(this);
        btn.setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_iv:
                dismiss();
                break;
            case R.id.login_btn:
                LoginInputListener listener= (LoginInputListener) getActivity();
                listener.onLoginInputComplete(mUsername.getText().toString(), mPassword.getText().toString());
//                dismiss();
                postLogin("http://107.182.186.214:8000/login/");
                break;

        }
    }

    public void postLogin(String url){
        // 1 获取OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        String user = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        System.out.println(user+password+"aaaaaaaaaaaaaaa");
        // 2 构建参数
        FormBody formBody = new FormBody.Builder()
                .add("username",user)
                .add("password",password)
                .build();
        // 3 构建 request
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        // 4 将Request封装为Call
        Call call = client.newCall(request);
        // 5 异步调用
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("网络错误");
                // ...
//                ToastUtil.showMsg(getActivity(),"网络错误");
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response!=null && response.isSuccessful()){

                    final String responseData = response.body().string().toString();
                    System.out.println(responseData+"data");

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(responseData.equals("true")){
                                dismiss();
                            }else if(responseData.equals("UserNotFound")){
                                ToastUtil.showMsg(getActivity(),"用户不存在，请注册");
                            }
                            else {
                                ToastUtil.showMsg(getActivity(),"密码错误");
                            }
                        }
                    });

                }
            }
        });
    }


}