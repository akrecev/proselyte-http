package com.kretsev.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
    private final static String TEXT_HTML = "text/html";
    private final static String APPLICATION_JSON = "application/json";
    private final static String MULTIPART_FORM_DATA = "multipart/form-data";
    private final static Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public static String getTextHtml() {
        return TEXT_HTML;
    }

    public static String getApplicationJson() {
        return APPLICATION_JSON;
    }

    public static String getMultipartFormData() {
        return MULTIPART_FORM_DATA;
    }

    public static Gson getGSON() {
        return GSON;
    }
}
