package vn.mrkiki.renshuu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.mrkiki.renshuu.chinese214.View1;


/**
 * Created by linhnd on 2016/08/05.
 */
public class Chinese214 extends FragmentActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    Chinese214ListData chinese214ListData;
    private List<View> list = new ArrayList<View>();
    private MyPageAdapter adapter;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_214bo_layout);
        viewpager = (ViewPager)findViewById(R.id.pager214bo);


        // Instantiate a ViewPager and a PagerAdapter.
//        mPager = (ViewPager) findViewById(R.id.pager214bo);
//        mPagerAdapter = new Chinese214PagerAdapter(getFragmentManager(), chinese214ListData);
//
//        mPager.setAdapter(mPagerAdapter);
//        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                // When changing pages, reset the action bar actions since they are dependent
//                // on which page is currently active. An alternative approach is to have each
//                // fragment expose actions itself (rather than the activity exposing actions),
//                // but for simplicity, the activity provides the actions in this sample.
//                invalidateOptionsMenu();
//            }
//        });

        initData();
        adapter = new MyPageAdapter();
        viewpager.setAdapter(adapter);
    }

    private void initData() {
        list.add(new View1(this).listview);
        list.add(new View1(this).listview);
        list.add(new View1(this).listview);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.activity_screen_slide, menu);
//
//        menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);
//
//        // Add either a "next" or "finish" button to the action bar, depending on which page
//        // is currently selected.
//        MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
//                (mPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
//                        ? R.string.action_finish
//                        : R.string.action_next);
//        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                // Navigate "up" the demo structure to the launchpad activity.
//                // See http://developer.android.com/design/patterns/navigation.html for more.
//                NavUtils.navigateUpTo(this, new Intent(this, KanjiMainView.class));
//                return true;
//
//            case R.id.action_previous:
//                // Go to the previous step in the wizard. If there is no previous step,
//                // setCurrentItem will do nothing.
//                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
//                return true;
//
//            case R.id.action_next:
//                // Advance to the next step in the wizard. If there is no next step, setCurrentItem
//                // will do nothing.
//                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    class MyPageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewGroup)container).addView((View)list.get(position));
            return list.get(position);

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

    }


}

