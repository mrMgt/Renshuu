package vn.mrkiki.renshuu.services;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import vn.mrkiki.renshuu.database.DatabaseHelper;
import vn.mrkiki.renshuu.database.SQL;
import vn.mrkiki.renshuu.listener.TaskListener;
import vn.mrkiki.renshuu.model.Kanji;
import vn.mrkiki.renshuu.utils.DialogUtils;
import vn.mrkiki.renshuu.utils.Param;

/**
 * Created by linhnd on 2016/08/22.
 */
public class ExercisesService extends AsyncTask<Void, Void, Void> {

    private List<Kanji> kanjiList;
    private DatabaseHelper db;

    private List<TaskListener> myListeners = new ArrayList<TaskListener>();

    public ExercisesService(Context context, List<Kanji> kanjiList) {
        DialogUtils.showProgressDialog(context);
        this.kanjiList = kanjiList;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        importData();
        return null;
    }

    @Override
    protected void onPostExecute(Void voids) {
        super.onPostExecute(voids);
        for (TaskListener tl : myListeners) {
            tl.onResultAvailable();
        }
        DialogUtils.hideProgressDialog();
    }

    public void addListener(TaskListener tl) {
        myListeners.add(tl);
    }

    private void importData() {
        try {
            db.openWriteData();
            db.startTransaction();
            Param param = null;
            for (Kanji item : kanjiList) {
                param = getKanjiParam(item.getName(), item.getMean(), item.getExample());
                int statusInsert = db.insertItems(SQL.SQL_02,
                        param.getParam());
                if (statusInsert < 1) {

                }
            }

            db.setTranactionSuccessfull();
            db.endTransaction();
        } catch (Exception ex) {
            db.endTransaction();
        }
    }

    private Param getKanjiParam(String name, String mean, String example) {
        Param param = new Param();
        param.add(name);
        param.add(mean);
        param.add(example);
        return param;
    }
}
