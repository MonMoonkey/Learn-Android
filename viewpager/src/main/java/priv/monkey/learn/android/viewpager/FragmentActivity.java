package priv.monkey.learn.android.viewpager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ViewPager viewPager = findViewById(R.id.fragment_viewpager);
        FragmentManager fm = getFragmentManager();
        final List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MonkeyFragment1.newInstance("111", "111"));
        fragmentList.add(MonkeyFragment2.newInstance("222", "222"));
        fragmentList.add(MonkeyFragment3.newInstance("333", "333"));
        fragmentList.add(MonkeyFragment4.newInstance("444", "444"));

        final MonkeyFragmentPagerAdapter fragmentPagerAdapter = new MonkeyFragmentPagerAdapter(fm, fragmentList);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setPageMargin(30);
//        viewPager.setPageTransformer(true,new MonkeyViewPagerTransform());

        Button button = findViewById(R.id.fragment_button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentList.remove(0);
                fragmentPagerAdapter.notifyDataSetChanged();
            }
        });

        Button button2 = findViewById(R.id.fragment_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentList.add(1,MonkeyFragment4.newInstance("haha","xixi"));
                fragmentPagerAdapter.notifyDataSetChanged();

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
