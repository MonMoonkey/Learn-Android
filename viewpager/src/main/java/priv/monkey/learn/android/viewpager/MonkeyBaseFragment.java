package priv.monkey.learn.android.viewpager;

import android.app.Fragment;

public class MonkeyBaseFragment extends Fragment{

    public String mParam1;
    public String mParam2;

    public String getmParam1() {
        return mParam1;
    }

    public void setmParam1(String mParam1) {
        this.mParam1 = mParam1;
    }

    public String getmParam2() {
        return mParam2;
    }

    public void setmParam2(String mParam2) {
        this.mParam2 = mParam2;
    }
}
