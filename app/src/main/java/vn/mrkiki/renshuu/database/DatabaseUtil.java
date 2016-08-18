package vn.mrkiki.renshuu.database;

import android.content.Context;

import java.io.File;

/**
 * Created by linhnd on 2016/08/15.
 */
public class DatabaseUtil {
    private static DatabaseHelper db = null;

    public static void setDatabaseHelper(Context context, String path,
                                         String name, int version) {
        updateFullMode(path + File.separator + name);

        try {
            db = DatabaseHelper.getInstance(context, path, name, version);
        } catch (Exception e) {
            db = null;
        }
    }

    public static void clearDatabaseHelper() {
        DatabaseHelper.clearInstance();
    }

    private static boolean updateFullMode(String filePath) {
        File file = new File(filePath);
        if (!file.exists())
            return false;

        if (!file.isFile())
            return false;

        if (!file.canRead()) {
            file.setReadable(true);
        }

        if (!file.canWrite()) {
            file.setWritable(true);
        }

        if (!file.canExecute()) {
            file.setExecutable(true);
        }

        return true;
    }

    public static boolean isCreatedDB() {
        return db != null;
    }

    public static void close() {
        db.close();
    }

    public static DatabaseHelper getDatabaseHelper(String className) {
        db.setClassHolder(className);
        return db;
    }


}
