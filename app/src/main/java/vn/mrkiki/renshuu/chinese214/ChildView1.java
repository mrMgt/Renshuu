package vn.mrkiki.renshuu.chinese214;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.view.ViewPager.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vn.mrkiki.renshuu.Chinese214Row;
import vn.mrkiki.renshuu.ChineseArrayAdapter;
import vn.mrkiki.renshuu.R;

/**
 * Created by linhnd on 2016/08/09.
 */
public class ChildView1 {
    ViewPager viewPager;
    private List<View> list = new ArrayList<View>();
    private Context mContext;
    ChineseArrayAdapter chineseArrayAdapter;
    static Chinese214Row[] listData = null;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    RelativeLayout relativeLayout;

    public ChildView1(Context mContext) {
        this.mContext = mContext;
        initData();
    }

    public void init(Context context) {
        mContext = context;


        for (Chinese214Row item:listData) {
            textView1 = new TextView(mContext);
            textView1.setText(item.getText1());
            textView1.setTextSize(20);

            textView2 = new TextView(mContext);
            textView2.setText(item.getText2());
            textView2.setTextSize(20);

            textView3 = new TextView(mContext);
            textView3.setText(item.getText3());
            textView3.setTextSize(20);

            textView4 = new TextView(mContext);
            textView4.setText(item.getText4());
            textView4.setTextSize(20);

            list.add(textView1);
            list.add(textView2);
            list.add(textView3);
            list.add(textView4);

        }

//        for (int i = 0; i < 5; i++) {

//            Button view = new Button(mContext);
//            view.setText("ViewPager中的TextView：" + i);
//            view.setTextSize(20);
//            list.add(view);
//        }


        viewPager = (ViewPager) View.inflate(mContext, R.layout.layout_viewpager, null);

        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, 200);
        viewPager.setLayoutParams(lp);
        viewPager.setAdapter(new MyPageAdapter());

    }


    public void initData() {
        Chinese214Row chinese214Row1 = new Chinese214Row();
        chinese214Row1.setText1("Text1");
        chinese214Row1.setText2("Text2");
        chinese214Row1.setText3("Text3");
        chinese214Row1.setText4("Text4");
        Chinese214Row chinese214Row2 = new Chinese214Row();
        chinese214Row2.setText1("Text5");
        chinese214Row2.setText2("Text6");
        chinese214Row2.setText3("Text7");
        chinese214Row2.setText4("Text8");
        listData = new Chinese214Row[]{chinese214Row1,chinese214Row2};
    }

    class MyPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

    }
}
