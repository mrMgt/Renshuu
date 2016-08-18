package vn.mrkiki.renshuu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mr on 8/19/2016.
 */

public class Chinese214ArrayAdapter extends ArrayAdapter {
    private final Context context;
    private final List<String[]> values;

    public Chinese214ArrayAdapter(Context context, List<String[]> values) {
        super(context, R.layout.view_chinese_214_items, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.view_chinese_214_items, parent, false);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text1 = (TextView) rowView.findViewById(R.id.text1);
            viewHolder.text2 = (TextView) rowView.findViewById(R.id.text2);
            viewHolder.text3 = (TextView) rowView.findViewById(R.id.text3);
            viewHolder.text4 = (TextView) rowView.findViewById(R.id.text4);

            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();

        holder.text1.setText(values.get(position)[0]);
        holder.text2.setText(values.get(position)[1]);
        holder.text3.setText(values.get(position)[2]);
        holder.text4.setText(values.get(position)[3]);

        return rowView;
    }

    static class ViewHolder {
        public TextView text1;
        public TextView text2;
        public TextView text3;
        public TextView text4;
    }
}
