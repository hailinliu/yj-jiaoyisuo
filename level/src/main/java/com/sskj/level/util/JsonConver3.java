package com.sskj.level.util;

import com.sskj.common.http.AbsConverter;
import com.sskj.common.util.GSonUtil;
import com.sskj.level.bean.HoldLevelHistoryBean;
import com.sskj.level.bean.LevelHistoryBean;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class JsonConver3<T> extends AbsConverter<T> {
    @Override
    public T convertResponse(Response response) throws Throwable {

        ResponseBody body = response.body();
        if (body == null) return null;
        T data;
        HoldLevelHistoryBean bean = GSonUtil.GsonToBean(body.string(), HoldLevelHistoryBean.class);
        data = (T)bean;
        return  data;
    }
}
