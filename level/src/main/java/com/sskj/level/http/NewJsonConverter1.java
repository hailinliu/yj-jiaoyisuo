package com.sskj.level.http;


import com.sskj.common.http.AbsConverter;
import com.sskj.common.util.GSonUtil;
import com.sskj.level.bean.RecordHistoryBean;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class NewJsonConverter1<T> extends AbsConverter<T> {
    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) return null;
        T data;
        RecordHistoryBean bean = GSonUtil.GsonToBean(body.string(), RecordHistoryBean.class);
        data = (T)bean;
       return  data;
        //data = gson.fromJson(body.string(), type);

    }
}
