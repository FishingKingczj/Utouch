package com.example.czj.utouch.tools;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;

/**
 * @Author: Czj
 * @Date: 2018/4/17 0:14
 * @Description:
 */

public abstract class LoadHttpCallBack<T> extends BaseHttpCallBack<T> {
    private Context context;

    public Context getContext() {
        return context;
    }

    public LoadHttpCallBack(Context context) {
        this.context = context;
    }

    @Override
    protected void onFailure(Call call, IOException e) {
        Toast.makeText(getContext(), "连接失败，请检查权限或网络连接", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onError(Call call, int statusCode, int errorCode) {
        Toast.makeText(getContext(), "Error: " + errorCode + "\nStatus: " + statusCode, Toast.LENGTH_LONG).show();
    }
}
