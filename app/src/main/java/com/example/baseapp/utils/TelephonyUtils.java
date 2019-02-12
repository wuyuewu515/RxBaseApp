package com.example.baseapp.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.util.UUID;

import androidx.core.content.ContextCompat;

import static androidx.core.content.PermissionChecker.checkSelfPermission;


/**
 * 设备唯一标识获取
 *
 * @author zfgx
 */
public class TelephonyUtils {

    private static final String TAG = "TelephonyUtils";
    private static String uuid;

    /**
     * 获取设备的IMEL机器码
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        String IMEI = "";
        TelephonyManager tm = getTelephonyManager(context);
        if (tm == null)
            return IMEI;

        if (checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            IMEI = tm.getDeviceId();
        }

        return IMEI;
    }

    /**
     * 获取设备的SIM卡序列号
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getICCID(Context context) {
        TelephonyManager tm = getTelephonyManager(context);
        if (tm == null)
            return "";
        return tm.getSimSerialNumber();
    }

    /**
     * 获取国际移动用户识别码
     *
     * @param context
     * @return
     */
    public static String getIMSI(Context context) {
        String IMSI = "";
        TelephonyManager tm = getTelephonyManager(context);
        if (tm == null)
            return "";
        if (checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            IMSI = tm.getSubscriberId();
        }

        return IMSI;
    }

    /**
     * 获取移动用户手机号码（有的手机卡获取不到）
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getMSISDN(Context context) {
        TelephonyManager tm = getTelephonyManager(context);
        if (tm == null)
            return "";
        return tm.getLine1Number();
    }

    public static TelephonyManager getTelephonyManager(Context context) {
        TelephonyManager tm = null;
        // 大于api23时检查是否有权限
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                return tm;
            } else {
                Log.i(TAG, "读取设备信息READ_PHONE_STATE权限被禁用");
            }
        } else {
            try {
                tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tm;
        }
        return tm;
    }

    public static String getDeviceId(Context context) {
        StringBuilder deviceId = new StringBuilder();
        // 渠道标志
        try {
            //IMEI（imei）
            TelephonyManager tm = getTelephonyManager(context);
            if (tm != null) {
                @SuppressLint("MissingPermission") String imei = tm.getDeviceId();
                if (!TextUtils.isEmpty(imei)) {
                    deviceId.append(imei);
                    return deviceId.toString();
                }
                //序列号（sn）
                @SuppressLint("MissingPermission") String sn = tm.getSimSerialNumber();
                if (!TextUtils.isEmpty(sn)) {
                    deviceId.append(sn);
                    return deviceId.toString();
                }
            }

            /*后台不要MAC*/
            //wifi mac地址
           /* WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            @SuppressLint("MissingPermission") WifiInfo info = wifi.getConnectionInfo();
            String wifiMac = info.getMacAddress();
            if (!StringUtils.isEmpty(wifiMac)) {
                deviceId.append(wifiMac);
                return deviceId.toString();
            }*/

            //如果上面都没有， 则生成一个id：随机码
            String uuid = getUUID(context);
            if (!TextUtils.isEmpty(uuid)) {
                deviceId.append(uuid);
                return deviceId.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            deviceId.append(getUUID(context));
        }
        return deviceId.toString();
    }

    /**
     * 得到全局唯一UUID
     */
    public static String getUUID(Context context) {
        SharedPreferences mShare = context.getSharedPreferences("sysCacheMap", Context.MODE_PRIVATE);
        if (mShare != null) {
            uuid = mShare.getString("uuid", "");
        }
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
        }
        if (mShare != null) {
            mShare.edit().putString("uuid", uuid).apply();
        }
        return uuid;
    }
}
