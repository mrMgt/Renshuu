package vn.mrkiki.renshuu.utils;

import android.content.res.Resources;

/**
 * Created by linhnd on 2016/08/22.
 */
public class ResourceUtil {
    private static Resources res;
    
    public static String getString(int id) {
        return res.getString(id);
    }
}
