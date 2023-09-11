package com.kretsev.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
    private final static String JSON_CONTENT_TYPE = "application/json";
    private final static Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public static String getJsonContentType() {
        return JSON_CONTENT_TYPE;
    }
    public static Gson getGSON() {
        return GSON;
    }
}
