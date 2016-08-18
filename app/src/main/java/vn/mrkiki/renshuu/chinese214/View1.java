package vn.mrkiki.renshuu.chinese214;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by linhnd on 2016/08/09.
 */
public class View1 {
    public ListView listview;
    private Context mContext;
    private MyListviewAdapter adapter;
    public View1(Context context) {
        init(context);
    }
    public void init(Context context) {
        mContext = context;
        listview = new ListView(context);
        adapter = new MyListviewAdapter();
        listview.setAdapter(adapter);
    }

    class MyListviewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null){
                ChildView1 child = new ChildView1(mContext);
                child.init(mContext);
                convertView = child.viewPager;
            }
//            System.out.println(convertView.toString());
            return convertView;
        }
        @Override
        public int getViewTypeCount() {
            return 2;
        }
        @Override
        public int getItemViewType(int position) {

            return 0;
        }

    }
}
