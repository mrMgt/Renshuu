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
import vn.mrkiki.renshuu.database.SQL;

/**
 * Created by linhnd on 2016/08/15.
 */
public class KanjiGoSetting extends Activity implements View.OnClickListener {

    Button btImportData, btCreateDB, btTestData;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_setting_layout);

        btImportData = (Button) findViewById(R.id.importData);
        btCreateDB = (Button) findViewById(R.id.createDB);
        btTestData = (Button) findViewById(R.id.testGetData);

        btCreateDB.setOnClickListener(this);
        btImportData.setOnClickListener(this);
        btTestData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createDB:
                createDB();
                break;
            case R.id.importData:
                importData();
                break;
            case R.id.testGetData:
                importData();
                break;
            default:
                break;
        }
    }

    public void createDB() {
        String tableCreate = getTableCreate();
        String tableDrop = null;


        DatabaseUtil.setDatabaseHelper(this, Constants.DATABASE_PATH,
                Constants.DATABASE_NAME, Constants.DB_VERSION, tableCreate, tableDrop);


    }


    public String getTableCreate() {
        String sql = "";
        String tableChinese = SQL.SQL_CREATE_TABLE_CHINESE;

        String tableExcercise = SQL.SQL_CREATE_TABLE_EXERCISE;

        sql = tableChinese + ";" + tableExcercise;
        return sql;
    }

    public void importData() {
        if (DatabaseUtil.isCreatedDB()) {
            databaseHelper = DatabaseUtil.getDatabaseHelper(getClass().toString());
            databaseHelper.openWriteData();
            //read file csv to list

            //loop on that list and insert
        }
    }
}

