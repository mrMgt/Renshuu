package vn.mrkiki.renshuu.services;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import vn.mrkiki.renshuu.listener.TaskListener;

/**
 * Created by linhnd on 2016/08/22.
 */
public class Chinese214ReadData extends AsyncTask<Void, Void, List<String[]>> {

    private List<TaskListener> myListeners = new ArrayList<TaskListener>();

    private Context context;
    private final String CSV_PATH = "data.csv";

    public Chinese214ReadData(Context context) {
        this.context = context;
    }


    @Override
    protected List<String[]> doInBackground(Void... voids) {

        return readData();
    }

    public void addListener(TaskListener tl) {
        myListeners.add(tl);
    }

    protected void onPostExecute(List<String[]> listData) {
        super.onPostExecute(listData);
        for (TaskListener tl : myListeners) {
            tl.onResultAvailable(listData);
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
            AssetManager assetManager = context.getAssets();

            csvStream = assetManager.open(CSV_PATH);
            reader = new BufferedReader(new InputStreamReader(csvStream));
            listData = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");

//                rowList = new String[]{
//                        rowData[0]!=null?rowData[0]:null,
//                        rowData[1]!=null?rowData[1]:null,
//                        rowData[2]!=null?rowData[2]:null,
//                        rowData[3]!=null?rowData[3]:null
//                };

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
}
