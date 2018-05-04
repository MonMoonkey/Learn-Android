package com.monkey.learn.android.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivityBroadcastReceiver extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private Button button2;
    private Button button3;

    private MyDynamicRegisterReceiver broadcastReceiver;


    private MyFirstBroadcastReceiver myFirstBroadcastReceiver;
    private MySecondBroadcastReceiver mySecondBroadcastReceiver;
    private MyThirdBroadcastReceiver myThirdBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_broadcast_receiver);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        //动态注册receiver
        broadcastReceiver = new MyDynamicRegisterReceiver();
        IntentFilter intentFilter = new IntentFilter("monkey.test2");
        registerReceiver(broadcastReceiver, intentFilter);

        //以下为测试有序广播

//        myFirstBroadcastReceiver = new MyFirstBroadcastReceiver();    //First通过静态方法注册了
        mySecondBroadcastReceiver = new MySecondBroadcastReceiver();
        myThirdBroadcastReceiver = new MyThirdBroadcastReceiver();
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction("monkey.test.order");
        intentFilter1.setPriority(0);//代码设置
//        registerReceiver(myFirstBroadcastReceiver, intentFilter1);
        registerReceiver(mySecondBroadcastReceiver, intentFilter1);
        intentFilter1.setPriority(5);
        registerReceiver(myThirdBroadcastReceiver, intentFilter1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button:
                Log.e("Monkey.Learn", "broadcastRecevier123");
                intent = new Intent("monkey.test1");
                sendBroadcast(intent);
                break;
            case R.id.button2:
                intent = new Intent("monkey.test.order");
                intent.putExtra("message", "pass");
                sendOrderedBroadcast(intent, "monkey.permission.test");
                break;
            case R.id.button3:
                intent = new Intent("monkey.test.order");
                intent.putExtra("message", "nopass");
                sendOrderedBroadcast(intent,"monkey.permission.test");
                break;
        }
    }

    /**
     * 内部类的receiver如果静态注册，必须为static的
     */
    public static class MyFirstBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context,"最高优先级receiver",Toast.LENGTH_SHORT).show();
            String msg = intent.getStringExtra("message");
            Log.e("Monkey", "First "+msg);
            if (msg.equals("pass")) {
                Bundle bundle = new Bundle();
                bundle.putString("message", msg + " First receiver");
                setResultExtras(bundle);
            } else {
                abortBroadcast();
            }
        }
    }

    /**
     * 内部类的receiver如果动态注册，可以不是static的，但是必须在本类中实例化
     */
    public class MySecondBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context, "第二优先级receiver", Toast.LENGTH_SHORT).show();
            String msg = intent.getStringExtra("message");
            Log.e("Monkey", "Second "+msg);
            if (msg.startsWith("pass")) {
                Bundle bundle = new Bundle();
                bundle.putString("message", msg + " Second receiver");
                setResultExtras(bundle);
            } else {
                abortBroadcast();
            }
        }
    }

    public class MyThirdBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context, "最低优先级receiver", Toast.LENGTH_SHORT).show();
            String msg = intent.getStringExtra("message");

            Log.e("Monkey", "Third "+msg);

        }
    }
}
