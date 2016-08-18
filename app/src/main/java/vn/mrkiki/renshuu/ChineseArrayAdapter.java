package vn.mrkiki.renshuu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/**
 * Created by linhnd on 2016/08/09.
 */
public class ChineseArrayAdapter extends ArrayAdapter<Chinese214Row> {

    private final Context context;
    private final Chinese214Row[] rowList;

    public ChineseArrayAdapter(Context context, int textViewResourceId, Chinese214Row[] rowList) {
        super(context,textViewResourceId, rowList);
        this.context = context;
        this.rowList = rowList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.view_214bo_item, parent, false);
        TextView textView1 = (TextView) rowView.findViewById(R.id.textItem1);
        TextView textView2 = (TextView) rowView.findViewById(R.id.textItem1);
        TextView textView3 = (TextView) rowView.findViewById(R.id.textItem1);
        TextView textView4 = (TextView) rowView.findViewById(R.id.textItem1);

        textView1.setText(rowList[position].getText1());
        textView2.setText(rowList[position].getText2());
        textView3.setText(rowList[position].getText3());
        textView4.setText(rowList[position].getText4());

        // Change icon based on name
        Chinese214Row cs = rowList[position];
        System.out.println("CS" + cs.getText1());

        return rowView;
    }
}
