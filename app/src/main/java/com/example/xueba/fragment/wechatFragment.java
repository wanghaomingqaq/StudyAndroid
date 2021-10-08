package com.example.xueba.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.xueba.R;
import com.example.xueba.utils.ToastUtil;


import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class wechatFragment extends Fragment implements LoginDailogFragment.LoginInputListener {
    public TextView coin_num;
    public TextView dio_num;
    JSONObject login_input = new JSONObject();
    public wechatFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wechat,container,false);
        coin_num = (TextView) view.findViewById(R.id.coin_num);
        dio_num = (TextView) view.findViewById(R.id.dio_num);
        boolean login_flag = false;
        if(login_flag==false){
            LoginDailogFragment login = new LoginDailogFragment();
            login.show(getActivity().getFragmentManager(),"LoginDailogFragment");
        }


//        postLogin("http://107.182.186.214:8000/login/");

        received("http://107.182.186.214:8000/hupu/");
        System.out.println(coin_num+"coin_num");

        return view;

   }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton btn_play_3 = (ImageButton) getActivity().findViewById(R.id.play_3_play);
        ImageButton btn_pause_3 = (ImageButton) getActivity().findViewById(R.id.play_3_pause);
        ImageButton btn_play_1 = (ImageButton) getActivity().findViewById(R.id.play_1);
        ImageButton btn_pause_1 = (ImageButton) getActivity().findViewById(R.id.play_1_pause);
        ImageButton btn_play_2 = (ImageButton) getActivity().findViewById(R.id.play_2);
        ImageButton btn_pause_2 = (ImageButton) getActivity().findViewById(R.id.play_2_pause);
        TextView time_2 = (TextView) getActivity().findViewById(R.id.timecount_center);
        TextView time_1 = (TextView) getActivity().findViewById(R.id.timecount_1);
        btn_play_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chronometer chronometer = (Chronometer) getActivity().findViewById(R.id.timecount3);
                btn_pause_1.setVisibility(View.INVISIBLE);
                btn_play_1.setVisibility(View.INVISIBLE);
                btn_pause_2.setVisibility(View.INVISIBLE);
                btn_play_2.setVisibility(View.INVISIBLE);
                btn_pause_3.setVisibility(View.VISIBLE);
                btn_play_3.setVisibility(View.INVISIBLE);


                chronometer.start();
                chronometer.setBase(SystemClock.elapsedRealtime());
                btn_pause_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        chronometer.stop();
                        int coin = Integer.parseInt(getChronometerSeconds(chronometer))/60;
                        coin_num.setText(String.valueOf(coin+Integer.parseInt(coin_num.getText().toString())));
                        btn_pause_1.setVisibility(View.INVISIBLE);
                        btn_play_1.setVisibility(View.VISIBLE);
                        btn_pause_2.setVisibility(View.INVISIBLE);
                        btn_play_2.setVisibility(View.VISIBLE);
                        btn_pause_3.setVisibility(View.INVISIBLE);
                        btn_play_3.setVisibility(View.VISIBLE);
                        postFormData("http://107.182.186.214:8000/hupu/");
                    }
                });

            }
        });
        btn_play_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDownTimer timer = new CountDownTimer(8000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        btn_play_2.setVisibility(View.INVISIBLE);
                        btn_pause_2.setVisibility(View.VISIBLE);

                        btn_pause_1.setVisibility(View.INVISIBLE);
                        btn_play_1.setVisibility(View.INVISIBLE);
                        btn_pause_3.setVisibility(View.INVISIBLE);
                        btn_play_3.setVisibility(View.INVISIBLE);

                        time_2.setText(format(millisUntilFinished));
                        btn_pause_2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtil.showMsg(getActivity(),"不能暂停哦");
                            }
                        });
                    }
                    @Override
                    public void onFinish() {
                        dio_num.setText(String.valueOf(1+Integer.parseInt(dio_num.getText().toString())));
                        coin_num.setText(String.valueOf(30+Integer.parseInt(coin_num.getText().toString())));
                        btn_play_2.setVisibility(View.VISIBLE);
                        btn_pause_2.setVisibility(View.INVISIBLE);
                        btn_pause_1.setVisibility(View.INVISIBLE);
                        btn_play_1.setVisibility(View.VISIBLE);
                        btn_pause_3.setVisibility(View.INVISIBLE);
                        btn_play_3.setVisibility(View.VISIBLE);
                        postFormData("http://107.182.186.214:8000/hupu/");
                    }
                    };
                if(getActivity()!=null){
                    timer.start();
                }
            }
        });
        btn_play_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CountDownTimer timer = new CountDownTimer(16000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        btn_play_1.setVisibility(View.INVISIBLE);
                        btn_pause_1.setVisibility(View.VISIBLE);
                        btn_pause_2.setVisibility(View.INVISIBLE);
                        btn_play_2.setVisibility(View.INVISIBLE);
                        btn_pause_3.setVisibility(View.INVISIBLE);
                        btn_play_3.setVisibility(View.INVISIBLE);
                        time_1.setText(format(millisUntilFinished));
                        btn_pause_1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtil.showMsg(getActivity(),"不能暂停哦");
                            }
                        });
                    }
                    @Override
                    public void onFinish() {
                        dio_num.setText(String.valueOf(2+Integer.parseInt(dio_num.getText().toString())));
                        coin_num.setText(String.valueOf(60+Integer.parseInt(coin_num.getText().toString())));

                        btn_play_1.setVisibility(View.VISIBLE);
                        btn_pause_1.setVisibility(View.INVISIBLE);
                        btn_pause_2.setVisibility(View.INVISIBLE);
                        btn_play_2.setVisibility(View.VISIBLE);
                        btn_pause_3.setVisibility(View.INVISIBLE);
                        btn_play_3.setVisibility(View.VISIBLE);
                        postFormData("http://107.182.186.214:8000/hupu/");

                    }
                };
                if(getActivity()!=null){
                    timer.start();
                }

            }
        });

    }

//方法们
    private String format(long time){
        String day = time/(24*60*60*1000)>9?time/(24*60*60*1000)+"":"0"+time/(24*60*60*1000);
        long hourRemain = time%(24*60*60*1000);
        String hour = hourRemain/3600000>9?hourRemain/3600000+"":"0"+hourRemain/3600000;
        long minRemain = hourRemain%3600000;
        String min = minRemain/60000>9?minRemain/60000+"":"0"+minRemain/60000;
        long secRemain = minRemain%60000;
        String sec = secRemain/1000>9?secRemain/1000+"":"0"+secRemain/1000;
        return hour+":"+min+":"+sec;
    }

    public  static String getChronometerSeconds(Chronometer cmt) {
        int totalss = 0;
        String string = cmt.getText().toString();
        if (string.length() == 7) {

            String[] split = string.split(":");
            String string2 = split[0];
            int hour = Integer.parseInt(string2);
            int Hours = hour * 3600;
            String string3 = split[1];
            int min = Integer.parseInt(string3);
            int Mins = min * 60;
            int SS = Integer.parseInt(split[2]);
            totalss = Hours + Mins + SS;
            return String.valueOf(totalss);
        } else if (string.length() == 5) {

            String[] split = string.split(":");
            String string3 = split[0];
            int min = Integer.parseInt(string3);
            int Mins = min * 60;
            int SS = Integer.parseInt(split[1]);

            totalss = Mins + SS;
            return String.valueOf(totalss);
        }
        return String.valueOf(totalss);
    }


    public void received(String url){
        // 1 获取OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 2设置请求
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        // 3封装call
        Call call = client.newCall(request);
        // 4异步调用,并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // ...
                System.out.println("网络错误");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                System.out.println("网络正常"+response+"fdf"+response.isSuccessful());
                if (response!=null && response.isSuccessful()){
                    final String responseData = response.body().string().toString();
                    System.out.println(responseData+"data");

                    JSONObject res = JSONObject.parseObject(responseData);
                    String coin = res.getString("coin_num");
                    String dio = res.getString("dio_num");
                    String username = res.getString("username");
                    String password = res.getString("password");


                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            coin_num.setText(coin);
                            dio_num.setText(dio);
                        }
                    });



                }
            }
        });

    }



     public void postFormData(String url){
        // 1 获取OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 2 构建参数
        FormBody formBody = new FormBody.Builder()
                .add("coin", coin_num.getText().toString())
                .add("dio",dio_num.getText().toString())
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
                // ...
                ToastUtil.showMsg(getActivity(),"网络错误");
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response!=null && response.isSuccessful()){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            received("http://107.182.186.214:8000/hupu/");
                        }
                    });

                }
            }
        });
    }


    public void postLogin(String url){
        // 1 获取OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        String user = login_input.getString("username");
        String password = login_input.getString("password");
        System.out.println(user+password+"aaaaaaaaaaaaaaa");
        // 2 构建参数
        FormBody formBody = new FormBody.Builder()
                .add("user",user)
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
                // ...
                ToastUtil.showMsg(getActivity(),"网络错误");
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response!=null && response.isSuccessful()){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            received("http://107.182.186.214:8000/login/");
                        }
                    });

                }
            }
        });
    }

    @Override
    public void onLoginInputComplete(String userName, String password) {
        login_input.put("usename",userName);
        login_input.put("passwoed",password);

    }
}