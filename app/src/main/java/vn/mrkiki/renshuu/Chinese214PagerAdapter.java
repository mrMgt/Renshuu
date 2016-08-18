package vn.mrkiki.renshuu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

/**
 * Created by linhnd on 2016/08/08.
 */
public class Chinese214PagerAdapter extends FragmentStatePagerAdapter {

    Chinese214ListData chinese214ListData;

    public Chinese214PagerAdapter(FragmentManager fm, Chinese214ListData chinese214ListData) {
        super(fm);
        this.chinese214ListData = chinese214ListData;
    }

    @Override
    public Fragment getItem(int i) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
