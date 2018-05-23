package priv.monkey.learn.android.viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        ViewPager viewPager = findViewById(R.id.fragment_viewpager);
        MonkeyFragmentPagerAdapter fragmentPagerAdapter = new MonkeyFragmentPagerAdapter(getFragmentManager(),)
    }
}
