package com.sskj.lib.http;

import com.google.gson.Gson;
import com.sskj.common.http.AbsConverter;
import com.sskj.common.util.GSonUtil;
import com.sskj.lib.bean.HistoryBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class NewJsonConverter<T> extends AbsConverter<T> {
    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) return null;
        T data;
        Gson gson = GSonUtil.gson;
        Type genericSuperclass = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        data = gson.fromJson(body.string(), type);
        return data;
    }
}
