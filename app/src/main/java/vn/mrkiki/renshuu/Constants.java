package vn.mrkiki.renshuu;

import android.os.Environment;

import java.io.File;

/**
 * Created by linhnd on 2016/08/19.
 */
public class Constants {
    public static final String DATABASE_NAME = "kanjigo.db";
    public static final int DB_VERSION = 1;
    public static final String RESOURE_DIR = "kanjigo";
    public static final String DATABASE_DIR = "database";
    public static final String DATABASE_PATH = Environment
            .getExternalStorageDirectory().getPath().toString()
            + File.separator
            + RESOURE_DIR
            + File.separator;

}
