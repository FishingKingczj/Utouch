package com.example.czj.utouch.tools;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @Author: Czj
 * @Date: 2018/4/16 23:31
 * @Description:
 */

public abstract class BaseHttpCallBack<T> {
    protected Type mType;

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public BaseHttpCallBack() {
        mType = getSuperclassTypeParameter(getClass());
    }

    protected abstract void onFailure(Call call, IOException e);

    protected abstract void onSuccess(Call call, Response response, T t);

    protected abstract void onError(Call call, int statusCode, int errorCode);
}
