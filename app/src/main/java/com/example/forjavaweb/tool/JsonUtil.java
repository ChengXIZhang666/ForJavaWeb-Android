package com.example.forjavaweb.tool;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * json数据和字符串、Javabean等之间的转化
 */
public class JsonUtil {
    private static Gson gson;

    /**
     * 将数据对象转化为json字符串
     *
     * @param originData
     * @return
     * @throws IOException
     */
    public static String ObjectToJson(Object originData) throws IOException {
        return getGson().toJson(originData);
    }

    /**
     * 将json数据转化为对应对象，json无数据时返回null
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T JsonToObject(String json, Class<T> classOfT) {
        if (json == null || "".equals(json.trim())) {
            return null;
        }
        return getGson().fromJson(json, classOfT);
    }

    /**
     * 功能同上
     *
     * @param json
     * @param typeOfT
     * @param <T>
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T JsonToObject(String json, Type typeOfT) throws JsonSyntaxException {
        if (json == null || "".equals(json.trim())) {
            return null;
        }
        return getGson().fromJson(json, typeOfT);
    }

    public static Gson getGson() {
        return gson = (gson == null ? new Gson() : new Gson());
    }
}
