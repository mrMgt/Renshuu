package vn.mrkiki.renshuu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;

import java.io.File;

import vn.mrkiki.renshuu.database.DatabaseHelper;
import vn.mrkiki.renshuu.database.DatabaseUtil;

/**
 * Created by linhnd on 2016/08/15.
 */
public class KanjiGoSetting extends Activity implements View.OnClickListener {

    Button btImportData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_setting_layout);

        btImportData = (Button) findViewById(R.id.importData);
        btImportData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.importData:
                importData();
                break;
            default:
                break;
        }
    }

    public void importData() {
        //read data from csv file to a List

        String sql = "INSERT INTO kanjiChineseSet(name, mean) VALUES(?, ?)";
        String sqlCreateTableKanjiChineseSet = "CREATE TABLE COMPANY(" +
                "ID INT PRIMARY KEY NOT NULL, " +
                "NAME TEXT NOT NULL, " +
                "MEAN TEXT NOT NULL)";

        String database_path = Environment.getExternalStorageDirectory().getPath().toString()
                + File.separator
                + "kanjigo";


//        DatabaseHelper database = DatabaseHelper.getInstance(getApplicationContext(),database_path,  )



    }
}

