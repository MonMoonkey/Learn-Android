package priv.monkey.learn.android.viewpager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentCompat;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;
//import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MonkeyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<MonkeyBaseFragment> fragmentList;
    private int mI;
    private static final String TAG = "FragmentPagerAdapter";

    public MonkeyFragmentPagerAdapter(FragmentManager fm,List<MonkeyBaseFragment> fl) {
        super(fm);
        this.fragmentList=fl;
        Log.e(" ", "ds");
    }

    @Override
    public Fragment getItem(int position) {
//        this.mI=position;
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        Log.w(TAG, "getCount");
        return fragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.w(TAG, "instantiateItem");
        Object f = super.instantiateItem(container, position);
        String p = String.valueOf(position);
       String s = f.getClass().getSimpleName();
        switch (s) {
            case "MonkeyFragment1":
                MonkeyFragment1 f1 = (MonkeyFragment1) f;
                f1.setmParam1(p+"-"+p+"-"+p+"-"+p+"-"+p);
                break;
            case "MonkeyFragment2":
                MonkeyFragment2 f2 = (MonkeyFragment2) f;
                f2.setmParam1(p+"-"+p+"-"+p+"-"+p+"-"+p);
                break;
            case "MonkeyFragment3":
                MonkeyFragment3 f3 = (MonkeyFragment3) f;
                f3.setmParam1(p+"-"+p+"-"+p+"-"+p+"-"+p);
                break;
            case "MonkeyFragment4":
                MonkeyFragment4 f4 = (MonkeyFragment4) f;
                f4.setmParam1(p+"-"+p+"-"+p+"-"+p+"-"+p);
                break;
        }
//        Log.i("dd", "dd");
//        TextView tv = f.getActivity().findViewById(R.id.text);
//        tv.setText("Number: "+position);
        return f;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Log.e(TAG+"TTT", String.valueOf(mI));
        return POSITION_NONE;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container,position,object);
        mI = position;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.w(TAG, "destroyItem");
        super.destroyItem(container, position, object);
    }

}
