package priv.monkey.learn.android.glide;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image1;
    private ImageView image2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image1 = findViewById(R.id.image_1);
        image2 = findViewById(R.id.image_2);

        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
//        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528476991296&di=fe7039b850945b2eab51b24d3b8eeac8&imgtype=0&src=http%3A%2F%2Fimg1.xcarimg.com%2Fmotonews%2F24455%2F26792%2F26852%2F640_480_20171107233113757260540345405.jpg").into(image1);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_1:
                Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528476991296&di=fe7039b850945b2eab51b24d3b8eeac8&imgtype=0&src=http%3A%2F%2Fimg1.xcarimg.com%2Fmotonews%2F24455%2F26792%2F26852%2F640_480_20171107233113757260540345405.jpg").into(image1);
                Log.e("Fri", "Run");
                break;
            case R.id.button_2:
                Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528480167018&di=d1119065605630480ae04167a309cad1&imgtype=0&src=http%3A%2F%2Fimg05.tooopen.com%2Fproducts%2F20150922%2Ftooopen_95E7735E-FBE0-69E2-4D6C-F40070129D40.GIF").into(image2);
                break;
        }
    }
}
