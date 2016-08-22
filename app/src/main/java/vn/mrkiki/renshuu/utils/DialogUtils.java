package vn.mrkiki.renshuu.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import vn.mrkiki.renshuu.R;

/**
 * Created by linhnd on 2016/08/22.
 */
public class DialogUtils {
    private static ProgressDialog progressDialog;
    private static AlertDialog.Builder builder;

    public static void showProgressDialog(Context context) {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.loading));
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.cancel();
    }


}
