package com.example.baseapp.utils;
/**
 * Created by user on 2018/6/29.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * json解析辅助工具类
 * <p>
 * Created by zhuofeng on 2015/4/8.
 */
public class JsonUtils {
    private static Gson gson = null;

    static {
        if (gson == null) {
            // 只转换加上@Expose注解的成员变量。
            gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        }
    }

    private JsonUtils() {
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成list
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    /**
     * 通过key获取value
     *
     * @param json
     * @param key
     * @return
     */
    public static String getJSONObjectKeyVal(String json, String key) {
        JSONObject object = null;
        try {
            object = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (object == null || key == null) {
            return "";
        }
        String result = null;
        Object obj;
        try {
            obj = object.get(key);
            if (obj == null || obj.equals(null)) {
                result = "";
            } else {
                result = obj.toString();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
