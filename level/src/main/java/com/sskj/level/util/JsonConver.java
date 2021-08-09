package com.sskj.level.util;

import com.sskj.common.http.AbsConverter;
import com.sskj.common.util.GSonUtil;
import com.sskj.level.bean.DealLevelBean;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class JsonConver<T> extends AbsConverter<T> {
    @Override
    public T convertResponse(Response response) throws Throwable {

        ResponseBody body = response.body();
        if (body == null) return null;
        T data;
        DealLevelBean bean = GSonUtil.GsonToBean(body.string(), DealLevelBean.class);
        data = (T)bean;
        return  data;
    }
}
