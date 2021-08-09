package com.sskj.lib.http;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.sskj.common.base.HttpData;
import com.sskj.common.http.AbsConverter;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.router.provider.LogoutProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * Created by lvzhihao on 17-7-4.
 */

public abstract class JsonConverter<T> extends AbsConverter<T> {

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) return null;
        T data;
        try {
            String jsonBody = body.string();
            jsonBody = jsonBody.replace("\"data\":\"\"}", "\"data\":null}");
            jsonBody = jsonBody.replace("\"list\":\"\"", "\"list\":[]");
//            if (data instanceof PageBean){
//                jsonBody.replaceFirst("\"data\":\\[[\\s\\S]*?]","\"data\":{\"list\":[]}");
//            }
            Gson gson = GSonUtil.gson;
            Type genericSuperclass = getClass().getGenericSuperclass();
            Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            try {
                if (((ParameterizedType) type).getActualTypeArguments() != null && ((ParameterizedType) type).getActualTypeArguments().length > 0) {
                    if (((ParameterizedType) (((ParameterizedType) type).getActualTypeArguments()[0])).getRawType() == PageBean.class) {
                        jsonBody = jsonBody.replaceFirst("\"data\":\\[[\\s\\S]*?]", "\"data\":{\"list\":[]}");
                    }
                }
            } catch (ClassCastException e) {

            }
            data = gson.fromJson(jsonBody, type);
        } catch (Exception e) {
            throw new JsonParseException();
        }
        int status = 0;
        String msg = "";
        if (data instanceof HttpData) {
            status = ((HttpData) data).getStatus();
            msg = ((HttpData) data).getMsg();
        }
        //  根据状态码，统一处理（如用户过期，重新登录）
        if (status == 404 || status == 505 || status == 401||status==4000) {
            SPUtil.clear();
            HttpHeaders httpHeaders = OkGo.getInstance().getCommonHeaders();
            httpHeaders.clear();
            ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
           /* LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
            logoutProvider.logout(msg);*/
        }
        return data;
    }

}
