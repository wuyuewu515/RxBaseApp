package com.example.baseapp.utils;

import android.app.Activity;
import android.content.DialogInterface;

import com.example.baseapp.views.LoaddingDialog;


/**
 * Created by Administrator on 2017/10/13.
 */
public class LoadingDialogUtils {

    private LoaddingDialog dialog;

    private volatile static LoadingDialogUtils loadingDialog;
    private DialogInterface.OnDismissListener listener;

    private LoadingDialogUtils() {

    }

    public static LoadingDialogUtils getInstance() {
        if (null == loadingDialog) {
            synchronized (LoadingDialogUtils.class) {
                if (null == loadingDialog)
                    loadingDialog = new LoadingDialogUtils();
            }
        }
        return loadingDialog;
    }

    public void show(Activity activity, String msg) {
        if (activity.isFinishing()) return;
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
        dialog = new LoaddingDialog(activity, msg);
        dialog.show();
    }

    public void show(Activity activity) {
        show(activity, "加载中...");
    }

    public void dismiss(Activity activity) {
        if (activity.isFinishing()) return;
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public void setDismissListener(DialogInterface.OnDismissListener listener) {
        if (null != dialog)
            dialog.setOnDismissListener(listener);
    }


}

