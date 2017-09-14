package com.growingrecyclerviewtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by 明正 on 2017/9/14.
 */

public class VpActivity extends AppCompatActivity {

    private ViewPager mVp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);
        mVp = (ViewPager) findViewById(R.id.vp);
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fragments.add(new ItemFragment());
        }
        mVp.setAdapter(new VpFragmentAdapter(getSupportFragmentManager(), fragments));

    }
}
