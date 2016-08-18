package vn.mrkiki.renshuu;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by linhnd on 2016/08/09.
 */
public class Chinese214ListData extends ListActivity{
    static Chinese214Row[] listData = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setListAdapter(new ChineseArrayAdapter(this,R.layout.view_214bo_item , listData));
    }

    public void runChinese() {
        initData();
        setListAdapter(new ChineseArrayAdapter(this,R.layout.view_214bo_item , listData));
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        //get selected items
        String selectedValue = (String) getListAdapter().getItem(position);
        Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

    }

    public void initData() {
        Chinese214Row chinese214Row1 = new Chinese214Row();
        chinese214Row1.setText1("Text1");
        chinese214Row1.setText1("Text2");
        chinese214Row1.setText1("Text3");
        chinese214Row1.setText1("Text4");
        Chinese214Row chinese214Row2 = new Chinese214Row();
        chinese214Row1.setText1("Text5");
        chinese214Row1.setText1("Text6");
        chinese214Row1.setText1("Text7");
        chinese214Row1.setText1("Text8");
        listData = new Chinese214Row[]{chinese214Row1,chinese214Row2};
    }
}
