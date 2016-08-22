package vn.mrkiki.renshuu;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import vn.mrkiki.renshuu.database.DatabaseHelper;
import vn.mrkiki.renshuu.database.DatabaseUtil;
import vn.mrkiki.renshuu.database.SQL;
import vn.mrkiki.renshuu.listener.TaskListener;
import vn.mrkiki.renshuu.model.Kanji;
import vn.mrkiki.renshuu.services.ExercisesService;

/**
 * Created by linhnd on 2016/08/15.
 */
public class KanjiGoSetting extends Activity implements TaskListener, View.OnClickListener {

    Button btImportData;
    Button btCreateDB;
    Button btTestData;
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

                break;
            default:
                break;
        }
    }

    public void createDB() {
        if (!DatabaseUtil.isCreatedDB()) {
            String tableCreate = getTableCreate();
            String tableDrop = null;


            DatabaseUtil.setDatabaseHelper(this, Constants.DATABASE_PATH,
                    Constants.DATABASE_NAME, Constants.DB_VERSION, tableCreate, tableDrop);
        }
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
            List<Kanji> kanjiList = readDataFromFile();
            if (kanjiList != null && kanjiList.size() > 0) {
                //loop on that list and insert
                ExercisesService exercisesService = new ExercisesService(this, kanjiList);
                exercisesService.execute();
            }
        }
    }

    private List<Kanji> readDataFromFile() {
        List<Kanji> kanjiList = null;

        String line;
        String[] rowList = null;
        InputStream csvStream = null;
        BufferedReader reader = null;
        Kanji kanji = null;

        try {
            AssetManager assetManager = this.getAssets();

            csvStream = assetManager.open("kanji.csv");
            reader = new BufferedReader(new InputStreamReader(csvStream));
            kanjiList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");

                if (rowData != null) {

                    kanji = new Kanji();
                    kanji.setId(rowData[0]);
                    kanji.setName(rowData[1]);
                    kanji.setMean(rowData[2]);
                    kanji.setChinese(rowData[3]);
                    kanji.setExample(rowData[4]);
                    kanjiList.add(kanji);
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
        return kanjiList;

    }


    @Override
    public void onResultAvailable(Object... objects) {

    }
}

