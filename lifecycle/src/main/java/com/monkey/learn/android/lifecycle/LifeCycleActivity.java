package com.monkey.learn.android.lifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class LifeCycleActivity extends AppCompatActivity {
    private LifeCycleFragment mLifeCycleFragment;
    private LifeCycleFragment mLifeCycleFragment_2;
    private Button mButton;
    private Button mButton_2;
    private Button mButton_3;
    private Button mButton_4;
    private FrameLayout mFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        Log.i("LifeCycle","Activity.onCreate");
        mButton=findViewById(R.id.button_activity);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LifeCycleActivity.this, TestActivity.class);
                Log.i("LifeCycle", "------Activity启动TestActivity------");
                startActivityForResult(intent,0);
            }
        });
        mLifeCycleFragment=(LifeCycleFragment) getFragmentManager().findFragmentById(R.id.fragment_life_cycle_static);
        mButton_2=findViewById(R.id.button_activity_2);
        mButton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mLifeCycleFragment.getContext(), TestActivity.class);
                mLifeCycleFragment.startActivityForResult(intent,10);
            }
        });
        mFrameLayout=findViewById(R.id.fragment_life_cycle_dynamic);

        mButton_3=findViewById(R.id.button_activity_3);
        mButton_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFrameLayout.setVisibility(View.VISIBLE);
                mLifeCycleFragment_2=LifeCycleFragment.newInstance("动态加载Fragment",View.GONE);
                getFragmentManager().beginTransaction().add(R.id.fragment_life_cycle_dynamic,mLifeCycleFragment_2).commit();
            }
        });
        mButton_4=findViewById(R.id.button_activity_4);
        mButton_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(mLifeCycleFragment_2).commit();
                mFrameLayout.setVisibility(View.GONE);
            }
        });

        ;
//        mLifeCycleFragment=LifeCycleFragment.newInstance("静态加载Fragment", R.color.background_grey_light,View.VISIBLE);
//        getFragmentManager().beginTransaction().add(R.id.fragment_life_cycle_static,mLifeCycleFragment,"xyz").commit();


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LifeCycle","Activity.onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LifeCycle","Activity.onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LifeCycle","Activity.onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LifeCycle","Activity.onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LifeCycle","Activity.onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("LifeCycle","Activity.onRestart");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("LifeCycle", "Activity得到返回值: requestCode=" + String.valueOf(requestCode) + " resultCode=" + String.valueOf(resultCode));

    }
}
