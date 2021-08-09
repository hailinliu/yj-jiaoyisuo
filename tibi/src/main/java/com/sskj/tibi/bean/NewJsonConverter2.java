package com.sskj.tibi.bean;

import com.sskj.common.http.AbsConverter;
import com.sskj.common.util.GSonUtil;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class NewJsonConverter2<T> extends AbsConverter<T> {
    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) return null;
        T data;
        CongRecordBean bean = GSonUtil.GsonToBean(body.string(), CongRecordBean.class);
        data = (T)bean;
        return  data;
        //data = gson.fromJson(body.string(), type);

    }
}