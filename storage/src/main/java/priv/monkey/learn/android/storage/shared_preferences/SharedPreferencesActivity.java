package priv.monkey.learn.android.storage.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import priv.monkey.learn.android.storage.R;

public class SharedPreferencesActivity extends AppCompatActivity {
    //只能保存基本数据类型：boolean,float,int, long, string
    //以XML形式保存在 /data/data/Package_Name/shared_prefs目录下
    private static final String FILE_NAME = "SharedPreferencesDemo";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText mEditText;
    private Button mButton_write;
    private Button mButton_read;

    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        linearLayout = findViewById(R.id.linear_layout);

        //1. 第一个参数为 文件名， 第二个参数为 进入模式
        //MODE_PRIVATE代表之后当前应用程序可用
        mSharedPreferences=getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        //2. Editor
        mEditor=mSharedPreferences.edit();

        //两个参数 一个为key，一个为value
        //key相同，第二次写入会覆盖第一次的value
        mEditor.putBoolean("boolean",true);
        mEditor.putInt("int",4);
        mEditor.putString("string","String");

        //3.提交
        mEditor.commit();

        mEditText=findViewById(R.id.edit_text_shared_preferences);
        mButton_write=findViewById(R.id.button_write_shared_preferences);
        mButton_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = mEditText.getText().toString();
                mEditor.putString("EditText",string);
                mEditor.commit();
//                Toast.makeText(SharedPreferencesActivity.this, "写入成功", Toast.LENGTH_SHORT).show();
                Snackbar.make(linearLayout, "写入成功", Snackbar.LENGTH_SHORT).show();
            }
        });
        mButton_read=findViewById(R.id.button_read_shared_preferences);
        mButton_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getString两个参数，第一个为key值，第二个是如果没有找到key，返回默认的是什么
                String string= mSharedPreferences.getString("EditText","没有该key值");
                mEditText.setText(string);
//                Toast.makeText(SharedPreferencesActivity.this, "读出成功", Toast.LENGTH_SHORT).show();
                Snackbar.make(linearLayout, "读出成功", Snackbar.LENGTH_SHORT).show();

            }
        });
    }



}
