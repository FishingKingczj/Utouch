package com.example.czj.utouch.tools;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * @Author: Czj
 * @Date: 2018/4/16 22:54
 * @Description:
 */

public class HttpManager {

    private static HttpManager mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;
    private Gson mGson;

    private final static int TIMEOUT = 10;

    private final static int UNIVERSAL_ERROR = 4000;
    private final static int IO_ERROR = 4001;
    private final static int JSON_ERROR = 4002;

    private final static String BASIC_URL = "http://140.143.25.150:8080/utouch";
    public final static String LOGIN_URL = BASIC_URL + "/login";

    private HttpManager() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.newBuilder().connectTimeout(TIMEOUT, TimeUnit.SECONDS).readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS);
        mHandler = new Handler(Looper.getMainLooper());
        mGson = new Gson();
    }

    public static HttpManager getInstance() {
        if (mInstance == null) {
            synchronized (HttpManager.class) {
                if (mInstance == null) {
                    mInstance = new HttpManager();
                }
            }
        }
        return mInstance;
    }

    //创建 Request对象
    private Request buildRequest(String url, Map<String, String> params) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        RequestBody requestBody = buildFormData(params);
        builder.post(requestBody);
        return builder.build();
    }

    //构建请求所需的参数表单
    private RequestBody buildFormData(Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }

    private void doRequest(Request request, final BaseHttpCallBack callBack) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("HttpManager", "Fail：" + e.toString());
                callBackFailure(callBack, call, e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                Log.d("HttpManager", "get Response");

                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string();
                        if (callBack.mType == String.class) {
                            callBackSuccess(callBack, call, response, result);
                        } else {
                            Log.d("HttpManager", "get Json: " + result);
                            Object object = mGson.fromJson(result, callBack.mType);
                            callBackSuccess(callBack, call, response, object);
                        }
                    } catch (JsonParseException e) {
                        Log.d("HttpManager", "Error: Json analysis error");
                        callBackError(callBack, call, response.code(), JSON_ERROR);
                    } catch (IOException e) {
                        callBackError(callBack, call, response.code(), IO_ERROR);
                    }
                } else {
                    Log.d("HttpManager", "Error: " + response.code());
                    callBackError(callBack, call, response.code(), UNIVERSAL_ERROR);
                }
            }
        });
    }

    private void callBackSuccess(final BaseHttpCallBack callBack, final Call call, final Response response, final Object object) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onSuccess(call, response, object);
                }
            }
        });
    }

    private void callBackError(final BaseHttpCallBack callBack, final Call call, final int statusCode, final int errorCode) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onError(call, statusCode, errorCode);
                }
            }
        });
    }

    private void callBackFailure(final BaseHttpCallBack callBack, final Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onFailure(call, e);
                }
            }
        });
    }

    public void postRequest(String url, Map<String, String> params, final BaseHttpCallBack callBack) {
        Request request = buildRequest(url, params);
        doRequest(request, callBack);
    }

    public void postRequestByInterface(String in, Map<String, String> params, final BaseHttpCallBack callBack) {
        postRequest(BASIC_URL + in, params, callBack);
    }
}
