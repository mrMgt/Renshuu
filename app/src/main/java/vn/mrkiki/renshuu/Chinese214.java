package vn.mrkiki.renshuu;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import vn.mrkiki.renshuu.database.DatabaseHelper;
import vn.mrkiki.renshuu.listener.TaskListener;
import vn.mrkiki.renshuu.services.Chinese214ReadData;
import vn.mrkiki.renshuu.utils.Param;


/**
 * Created by linhnd on 2016/08/05.
 */
public class Chinese214 extends FragmentActivity implements TaskListener {

    private Context mContext;
    ListView listview1, listview2, listview3, listview4, listview5, listview6, listview7, listview8;
    Chinese214ArrayAdapter arrayAdapter;
    List<String[]> listData;
    ViewPager pager;
    CustomPagerAdapter adapter;
    private DatabaseHelper db;
    Chinese214ReadData  chinese214ReadData;
    private final String CSV_PATH = "data.csv";


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

        listData = readData();

        arrayAdapter = new Chinese214ArrayAdapter(this, getSubData("page1"));
        listview1.setAdapter(arrayAdapter);
        arrayAdapter = new Chinese214ArrayAdapter(this, getSubData("page2"));
        listview2.setAdapter(arrayAdapter);
        arrayAdapter = new Chinese214ArrayAdapter(this, getSubData("page3"));
        listview3.setAdapter(arrayAdapter);
        arrayAdapter = new Chinese214ArrayAdapter(this, getSubData("page4"));
        listview4.setAdapter(arrayAdapter);
        arrayAdapter = new Chinese214ArrayAdapter(this, getSubData("page5"));
        listview5.setAdapter(arrayAdapter);
        arrayAdapter = new Chinese214ArrayAdapter(this, getSubData("page6"));
        listview6.setAdapter(arrayAdapter);
        arrayAdapter = new Chinese214ArrayAdapter(this, getSubData("page7"));
        listview7.setAdapter(arrayAdapter);
        arrayAdapter = new Chinese214ArrayAdapter(this, getSubData("page8"));
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


    private Param getInsertParam(String tableName) {
        Param param = new Param();
        param.add(tableName);
        return param;
    }


    @Override
    public void onResultAvailable(Object... objects) {

        if (objects != null) {
            this.listData = (List<String[]>)objects[0];
        }
    }

    private List<String[]> readData() {
        List<String[]> listData = null;
        String line;
        String item1 = null;
        String item2 = null;
        String item3 = null;
        String item4 = null;
        String[] rowList = null;
        InputStream csvStream = null;
        BufferedReader reader = null;

        try {
            AssetManager assetManager = this.getAssets();

            csvStream = assetManager.open(CSV_PATH);
            reader = new BufferedReader(new InputStreamReader(csvStream));
            listData = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");

                if (rowData != null) {
                    listData.add(rowData);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                csvStream.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return listData;
    }

    private List<String[]> getSubData(String page) {
        List<String[]> listSubData = new ArrayList<>();
        String row[];
        if (listData != null && listData.size() > 0) {

            for (String[] item : listData) {
                if (page.equals(item[0])) {
                    row = new String[4];
                    for(int i=1;i<item.length;i++) {
                        if (item[i] != null) {
                            row[i-1]=item[i];
                        }
                    }
                    listSubData.add(row);
                }
            }
        }
        return listSubData;
    }


}

