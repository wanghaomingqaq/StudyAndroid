package com.example.xueba.customView;
import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;


public class FixedViewPager extends ViewPager {
    public FixedViewPager(@NonNull Context context) {
        super(context);
    }

    public FixedViewPager(@NonNull Context context, @Nullable AttributeSet attr) {
        super(context, attr);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item,false);
    }

}

