package priv.monkey.learn.android.storage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import priv.monkey.learn.android.storage.file.FileStorageActivity;
import priv.monkey.learn.android.storage.shared_preferences.SharedPreferencesActivity;
import priv.monkey.learn.android.storage.sqlite_database.SQLiteDatabaseActivity;

public class MainActivity extends AppCompatActivity {


    private static final String[] mDemoList= new String[]{
            "SharedPreferencesActivity","FileStorageActivity","SQLiteDatabaseActivity"
    };
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView=findViewById(R.id.list_view);
        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mDemoList));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, mDemoList[i], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                switch (i){
                    case 0:
                        intent.setClass(MainActivity.this, SharedPreferencesActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, FileStorageActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(MainActivity.this, SQLiteDatabaseActivity.class);
                        startActivity(intent);
                        break;
                }

            }
        });
    }
}
