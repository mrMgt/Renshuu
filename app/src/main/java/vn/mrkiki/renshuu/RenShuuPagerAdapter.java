package vn.mrkiki.renshuu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by linhnd on 2016/08/08.
 */
public class RenShuuPagerAdapter  extends FragmentStatePagerAdapter {

    private List<String> data;
    private Context ctx;

    public RenShuuPagerAdapter(FragmentManager fm, List<String> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return ScreenSlidePageFragment.customizeCreate(position, this.data.get(position));
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }
}
