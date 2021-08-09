package com.sskj.level.util;

import com.sskj.common.http.AbsConverter;
import com.sskj.common.util.GSonUtil;
import com.sskj.level.bean.LevelHistoryBean;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class JsonConver2<T> extends AbsConverter<T> {
    @Override
    public T convertResponse(Response response) throws Throwable {

        ResponseBody body = response.body();
        if (body == null) return null;
        T data;
        LevelHistoryBean bean = GSonUtil.GsonToBean(body.string(), LevelHistoryBean.class);
        data = (T)bean;
        return  data;
    }
}
