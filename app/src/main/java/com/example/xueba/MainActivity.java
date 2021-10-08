package com.example.xueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;

import com.example.xueba.fragment.LoginDailogFragment;
import com.example.xueba.fragment.discoverFragment;
import com.example.xueba.fragment.listFragment;
import com.example.xueba.fragment.wechatFragment;
import com.example.xueba.fragment.stackFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity   implements LoginDailogFragment.LoginInputListener,BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    BottomNavigationView mNavigationView;
    wechatFragment  wechatFragments = new wechatFragment();
    listFragment  listFragments = new listFragment();
    discoverFragment discoverFragments =new discoverFragment();
    stackFragment stackFragments = new stackFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //页面初始化导航栏
        init();
    }

    private void init() {

        //获取页面标签对象
        viewPager = findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(this);
        mNavigationView = findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(this);


        //页面切换
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return  wechatFragments;
                    case 1:
                        return  listFragments;
                    case 2:
                        return  discoverFragments;
                    case 3:
                        return stackFragments;
                }

                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }

    //实现接口的相关方法  implements上面两个方法后 alt+enter就会弹出这些接口，直接回车实现他们
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mNavigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        viewPager.setCurrentItem(menuItem.getOrder());
        return true;
    }

    @Override
    public void onLoginInputComplete(String userName, String password) {
        System.out.println("ccccccccc");
    }
}

