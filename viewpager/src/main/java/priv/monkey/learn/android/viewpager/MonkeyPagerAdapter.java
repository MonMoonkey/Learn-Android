package priv.monkey.learn.android.viewpager;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MonkeyPagerAdapter extends PagerAdapter {
    private Context context;

    public MonkeyPagerAdapter(Context c) {
        this.context = c;
    }
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setHeight(50);
        tv.setWidth(100);
        tv.setTextSize(20);
        tv.setTextColor(Color.BLACK);
        tv.setText("Monkey学ViewPager" + position + "号");

        // 添加到ViewPager容器
        container.addView(tv);

        // 返回填充的View对象
        return tv;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
