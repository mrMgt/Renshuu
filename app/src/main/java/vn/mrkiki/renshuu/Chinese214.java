package vn.mrkiki.renshuu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
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
import android.widget.ListView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import vn.mrkiki.renshuu.chinese214.View1;
import vn.mrkiki.renshuu.database.DatabaseHelper;
import vn.mrkiki.renshuu.database.DatabaseUtil;
import vn.mrkiki.renshuu.database.Param;


/**
 * Created by linhnd on 2016/08/05.
 */
public class Chinese214 extends FragmentActivity {

    private Context mContext;
    ListView listview1, listview2, listview3, listview4, listview5, listview6, listview7, listview8;
    Chinese214ArrayAdapter arrayAdapter;
    List<String[]> listData;
    ViewPager pager;
    CustomPagerAdapter adapter;
    private DatabaseHelper db;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.view_chinese_214_layout);
        listview1 = new ListView(mContext);
        listview2 = new ListView(mContext);
        listview3 = new ListView(mContext);
        listview4 = new ListView(mContext);
        listview5 = new ListView(mContext);
        listview6 = new ListView(mContext);
        listview7 = new ListView(mContext);
        listview8 = new ListView(mContext);


        Vector<View> pages = new Vector<View>();
        pages.add(listview1);
        pages.add(listview2);
        pages.add(listview3);
        pages.add(listview4);
        pages.add(listview5);
        pages.add(listview6);
        pages.add(listview7);
        pages.add(listview8);
        pager = (ViewPager) findViewById(R.id.viewpaper_214chinese);
        adapter = new CustomPagerAdapter(mContext,pages);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
            }
        });

        listData = new ArrayList<String[]>();

        listData = buildData();


        arrayAdapter = new Chinese214ArrayAdapter(this, listData);
        listview1.setAdapter(arrayAdapter);
        arrayAdapter = new Chinese214ArrayAdapter(this, listData);
        listview2.setAdapter(arrayAdapter);
        arrayAdapter = new Chinese214ArrayAdapter(this, listData);
        listview3.setAdapter(arrayAdapter);
        arrayAdapter = new Chinese214ArrayAdapter(this, listData);
        listview4.setAdapter(arrayAdapter);
        arrayAdapter = new Chinese214ArrayAdapter(this, listData);
        listview5.setAdapter(arrayAdapter);
        arrayAdapter = new Chinese214ArrayAdapter(this, listData);
        listview6.setAdapter(arrayAdapter);
        arrayAdapter = new Chinese214ArrayAdapter(this, listData);
        listview7.setAdapter(arrayAdapter);
        arrayAdapter = new Chinese214ArrayAdapter(this, listData);
        listview8.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_screen_slide, menu);

        menu.findItem(R.id.action_previous).setEnabled(pager.getCurrentItem() > 0);

        // Add either a "next" or "finish" button to the action bar, depending on which page
        // is currently selected.
        MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
                (pager.getCurrentItem() == adapter.getCount() - 1)
                        ? R.string.action_finish
                        : R.string.action_next);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                NavUtils.navigateUpTo(this, new Intent(this, KanjiMainView.class));
                return true;

            case R.id.action_previous:
                // Go to the previous step in the wizard. If there is no previous step,
                // setCurrentItem will do nothing.
                pager.setCurrentItem(pager.getCurrentItem() - 1);
                return true;

            case R.id.action_next:
                // Advance to the next step in the wizard. If there is no next step, setCurrentItem
                // will do nothing.
                pager.setCurrentItem(pager.getCurrentItem() + 1);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public List<String[]> buildData() {
        List<String[]> listData = new ArrayList<String[]>();
        for (int i=0; i<=7; i++) {
            listData.add(new String[]{"aaaa", "bbbb", "cccc", "dddd"});
        }
        return listData;
    }

    public List<String[]> getDataFromDb() {

    }

    public void prepareData(String csvfile) {
        if (DatabaseUtil.isCreatedDB()) {
            db = DatabaseUtil.getDatabaseHelper(getClass().toString());

            db.openWriteData();


            int statusInsert = db.insertItems(SQL.SC0001BL_SQL_02,
                    prInsert.getParam());
            if (statusInsert < 1) {
                throw new RuntimeException();
            }
        }
    }

    private Param getInsertParam(String tableName) {
        Param param = new Param();
        param.add(tableName);
        return param;
    }



}

