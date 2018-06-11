package priv.monkey.learn.android.wheel.timber;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import timber.log.Timber;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.tag("LifeCycles");
        Timber.d("Activity Created");
        findViewById(R.id.hello).setOnClickListener(this);
        findViewById(R.id.hi).setOnClickListener(this);
        findViewById(R.id.hey).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Timber.i("A button with ID %s was clicked to say '%s'.", view.getId(), ((Button)view).getText());
        Toast.makeText(this, "Check logcat for a greeting!", Toast.LENGTH_LONG).show();
    }
}
