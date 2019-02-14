package com.example.baseapp.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.example.baseapp.MyApplication;

/**
 * @author: Five_伍
 * @create: 2019/2/14
 * @Describe: toast封装类
 */
public class ToastUtil {

    private static Toast toast = null; //toast

    /**
     * 自定义Toast,避免重复,界面中部弹出
     */
    public static void showCenterToast(Object msg) {
        String content;
        if (msg == null) {
            content = "null";
        } else {
            content = msg.toString();
        }
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(), content, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 自定义Toast,避免重复,界面底部弹出
     */
    public static void showToast(Object msg) {
        String content;
        if (msg == null) {
            content = "数据出错";
        } else {
            content = msg.toString();
        }
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(), content, Toast.LENGTH_SHORT);
         //   toast.setGravity(Gravity.BOTTOM, 0, 100);
        } else {
        //    toast.setGravity(Gravity.BOTTOM, 0, 100);
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 自定义Toast,避免重复,界面底部弹出
     */
    public static void showToast(int stringId) {
        String content;
        try {
            content = MyApplication.getContext().getString(stringId);
        } catch (Exception e) {
            content = String.valueOf(stringId);
        }

        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(), content, Toast.LENGTH_SHORT);
           // toast.setGravity(Gravity.BOTTOM, 0, 0);
        } else {
        //    toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setText(content);
        }
        toast.show();
    }
}
