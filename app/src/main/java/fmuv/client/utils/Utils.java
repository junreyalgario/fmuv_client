package fmuv.client.utils;

import com.google.gson.Gson;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.7.5
 */

public class Utils {

    private Utils() {}

    public static String ObjectToJson(Object object) {
        return new Gson().toJson(object);
    }

}
