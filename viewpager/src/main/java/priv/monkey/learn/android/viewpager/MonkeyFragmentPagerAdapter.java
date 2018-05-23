package priv.monkey.learn.android.viewpager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
//import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MonkeyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public MonkeyFragmentPagerAdapter(FragmentManager fm,List<Fragment> fl) {
        super(fm);
        this.fragmentList=fl;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
