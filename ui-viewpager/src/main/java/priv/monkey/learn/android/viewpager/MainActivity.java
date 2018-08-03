package priv.monkey.learn.android.viewpager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        MonkeyPagerAdapter monkeyPagerAdapter = new MonkeyPagerAdapter(this,viewPager);
        MonkeyPagerThreeAdapter monkeyPagerThreeAdapter = new MonkeyPagerThreeAdapter(this, viewPager);
        viewPager.setAdapter(monkeyPagerThreeAdapter);

//        viewPager.setCurrentItem(1, false);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.e("onPageScrolled", "" + position + "          " + positionOffset + "            " + positionOffsetPixels);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                Log.e("onPageSelected", position+"");
//                if (position == 0 || position == 2) {
//                    monkeyPagerAdapter.switchToMiddle(position);
//                }
//            }

//            @Override
//            public void onPageScrollStateChanged(int state) {
//                Log.e("onPScrollStateChanged", state+"");
//
//            }
//        });
//        viewPager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
//            @Override
//            public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {
//
//            }
//        });
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FragmentActivity.class));
            }
        });
    }
}
