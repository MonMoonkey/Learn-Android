package com.monkey.learn.android.lifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.w("LifeCycle","Test.onCreate");

        setResult(8);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w("LifeCycle","Test.onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("LifeCycle","Test.onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("LifeCycle","Test.onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("LifeCycle","Test.onStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.w("LifeCycle","Test.onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("LifeCycle","Test.onDestroy");
    }

}
