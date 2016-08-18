package vn.mrkiki.renshuu.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by linhnd on 2016/08/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private Logger log = Logger.getLogger(DatabaseHelper.class.toString());

    // Database Version
    private static int DATABASE_VERSION = 1;

    // database path default to save
    private static String DATABASE_PATH = Environment
            .getExternalStorageDirectory().toString();

    // database name
    private static String DATABASE_NAME = "KANJIGO.db";

    // Variables instance
    private static DatabaseHelper instance = null;

    // SQLiteDatabase
    private static SQLiteDatabase db;

    // SQLiteStatement
    private static SQLiteStatement sqliteState;

    // Query list create
    private static String TABLE_CREATE = null;

    // Query list drop
    private static String TABLE_DROP = null;

    /**
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_PATH + File.separator + DATABASE_NAME, null,
                DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        if (TABLE_CREATE != null) {
            String[] queries = TABLE_CREATE.split(";");
            // Category table create query
            for (String query : queries)
                db.execSQL(query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (TABLE_DROP != null) {
            String[] queries = TABLE_DROP.split(";");
            // Drop older table if existed
            for (String query : queries)
                db.execSQL(query);
            // Create new table from database update
            onCreate(db);
        }
    }

    public static DatabaseHelper getInstance(Context context,
                                             String databasePath, String databaseName, int databaseVersion,
                                             String tableCreate, String tableDrop) throws Exception {
        if (instance == null) {
            if (databasePath != null)
                DATABASE_PATH = databasePath;
            if (databaseName != null)
                DATABASE_NAME = databaseName;
            if (databaseVersion != 0)
                DATABASE_VERSION = databaseVersion;
            TABLE_CREATE = tableCreate;
            TABLE_DROP = tableDrop;
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    public static DatabaseHelper getInstance(Context context,
                                             String databasePath, String databaseName, int databaseVersion)
            throws Exception {
        if (instance == null) {
            if (databasePath != null)
                DATABASE_PATH = databasePath;
            if (databaseName != null)
                DATABASE_NAME = databaseName;
            if (databaseVersion != 0)
                DATABASE_VERSION = databaseVersion;
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    public static void clearInstance() {
        instance = null;
    }

    public void openWriteData() {
        if (instance != null)
            db = instance.getWritableDatabase();
    }

    public void openReadData() {
        if (instance != null)
            db = instance.getReadableDatabase();
    }

    public void closeData() {
        db.close();
    }

    public void startTransaction() {
        // db.beginTransaction();
        db.beginTransactionNonExclusive();
    }

    public void setTranactionSuccessfull() {
        db.setTransactionSuccessful();
    }

    public void endTransaction() {
        db.endTransaction();
    }

    public void excuteSQL(String query, String... params) throws IOException {
        db.execSQL(query, params);
    }

    public int insertItems(String query, String... params) throws Exception {

        sqliteState = db.compileStatement(query);
        sqliteState.bindAllArgsAsStrings(params);

        long result = -1;
        result = sqliteState.executeInsert();

        return (int) result;
    }

    public int updateItems(String query, String... params) {
        sqliteState = db.compileStatement(query);
        sqliteState.bindAllArgsAsStrings(params);

        int result = -1;
        try {
            result = sqliteState.executeUpdateDelete();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    public int deleteItems(String query, String... params) {
        sqliteState = db.compileStatement(query);
        sqliteState.bindAllArgsAsStrings(params);

        int result = -1;
        try {
            result = sqliteState.executeUpdateDelete();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return result;
    }

    public Cursor selectItems(String query, String... params) {
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }

    private String classHolder;

    public String getClassHolder() {
        return classHolder;
    }

    public void setClassHolder(String classHolder) {
        this.classHolder = classHolder;
    }
}
