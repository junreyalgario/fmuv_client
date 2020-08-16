package fmuv.client.utils;

import android.util.Log;

import fmuv.client.data.remote.dto.HttpResponse;

public class DebugUtil {

    public static void apiError(Throwable t) {
        Log.d("ddd", "REST API ERROR MESSAGE: "+  t.getMessage());
        Log.d("ddd", "REST API ERROR toString: "+  t.toString());
    }

    public static void apiResponse(retrofit2.Response<HttpResponse> response) {
        Log.d("ddd", "REST API RESPONSE RAW: "+  response.raw());
        Log.d("ddd", "REST API RESPONSE HEADER: "+ response.headers());
        Log.d("ddd", "REST API SESSION: "+ response.headers().get("Set-Cookie"));
    }

}
