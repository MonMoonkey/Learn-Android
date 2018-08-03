package priv.monkey.learn.android.viewpager;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MonkeyPagerThreeAdapter extends PagerAdapter {
    private Context context;
    private List<TextView> textViews;
    private ViewPager viewPager;
    private int currentPosition;
    private ViewGroup viewGroup;

    public MonkeyPagerThreeAdapter(Context c, final ViewPager viewPager) {
        this.context = c;
        this.viewPager = viewPager;
        textViews = new ArrayList<>(3);

        for (int i = 1; i < 4; i++) {
            TextView tv = new TextView(context);
            tv.setGravity(Gravity.CENTER);
            tv.setHeight(50);
            tv.setWidth(100);
            tv.setTextSize(20);
            tv.setTextColor(Color.BLACK);

            tv.setText("Pager " + i + " 号");
            textViews.add(i-1, tv);
        }
//        textViews[0] = textViews[2];
//        textViews[4] = textViews[2];

//        {
//            TextView tv = new TextView(context);
//
//            tv.setGravity(Gravity.CENTER);
//            tv.setHeight(50);
//            tv.setWidth(100);
//            tv.setTextSize(20);
//            tv.setTextColor(Color.BLACK);
//
//            tv.setText("Pager " + 3 + " 号");
//            textViews.add(0, tv);
//        }
//
//        {
//            TextView tv = new TextView(context);
//
//            tv.setGravity(Gravity.CENTER);
//            tv.setHeight(50);
//            tv.setWidth(100);
//            tv.setTextSize(20);
//            tv.setTextColor(Color.BLACK);
//
//            tv.setText("Pager " + 1 + " 号");
//            textViews.add(tv);
//        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("onPageScrolled", "" + position + "          " + positionOffset + "            " + positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//        若viewpager滑动未停止，直接返回
                if (state != ViewPager.SCROLL_STATE_IDLE) return;
                if (currentPosition == 2) {
                    viewGroup.removeViewAt(1);
                    viewGroup.addView(getIdexTextView(4),1);
                }
//        若当前为第一张，设置页面为倒数第二张
//                if (currentPosition == 0) {
//                    viewPager.setCurrentItem(textViews.size()-2,false);
//                } else if (currentPosition == textViews.size()-1) {
////        若当前为倒数第一张，设置页面为第二张
//                    viewPager.setCurrentItem(1,false);
//                }
            }
        });

//        viewPager.setCurrentItem(1,false);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        Log.e("isViewFromObject", "" + String.valueOf(view == object));
        return view == object;
//        return ;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (position == 0) {
            viewGroup = container;
        }else{
            Log.e("XIXIPI", String.valueOf(viewGroup.equals(container)));
        }

//        TextView tv = new TextView(context);
//        tv.setGravity(Gravity.CENTER);
//        tv.setHeight(50);
//        tv.setWidth(100);
//        tv.setTextSize(20);
//        tv.setTextColor(Color.BLACK);
//        tv.setText("Monkey学ViewPager" + position + "号");
//        TextView textView = textViews[position];
//        if (null != textView.getParent()) {
//            ViewGroup viewGroup = (ViewGroup) textView.getParent();
//            viewGroup.removeView(textView);
//        }

        // 添加到ViewPager容器
        container.addView(textViews.get(position));

        // 返回填充的View对象
        return textViews.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void switchToMiddle(int position) {
        viewPager.setCurrentItem(1,false);
    }

    private TextView getIdexTextView(int i) {
        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setHeight(50);
        tv.setWidth(100);
        tv.setTextSize(20);
        tv.setTextColor(Color.BLACK);

        tv.setText("Pager " + i + " 号");
        return tv;
    }
}
