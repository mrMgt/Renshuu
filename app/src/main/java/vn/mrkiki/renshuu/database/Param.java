package vn.mrkiki.renshuu.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhnd on 2016/08/19.
 */
public class Param {
    private List<String> lstParam;

    public Param() {
        lstParam = new ArrayList<String>();
    }

    public void add(Object item) {
        if (item == null)
            lstParam.add("");
        else
            lstParam.add(String.valueOf(item));
    }

    public String[] getParam() {
        String[] result = new String[lstParam.size()];
        result = lstParam.toArray(result);
        return result;
    }
}
