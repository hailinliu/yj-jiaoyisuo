package com.sskj.lib.http;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.BaseHttpConfig;
import com.sskj.lib.RConfig;
import com.sskj.lib.router.provider.LogoutProvider;
import com.sskj.lib.util.ExceptionUtil;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.UnknownServiceException;

import okhttp3.ResponseBody;

public abstract class CallBackOption<T> extends AbsCallback<T> {



    enum Strategy {
        LOAD_BIND,//显示加载框和界面生命周期绑定
        UNLOAD_BIND,//不显示加载框和界面生命周期绑定
        UNLOAD_UNBIND//不显示加载框不和界面生命周期绑定

    }
    private ILoadBind<T> iLoadBind;

    public CallBackOption<T> execute(ILoadBind<T> t) {
        iLoadBind = t;
        return this;
    }
    private IBaseViewLife iBaseViewLife;
    private LifecycleOwner lifecycleOwner;
    private Strategy strategy;
    private MutableLiveData<T> tMutableLiveData;

    public CallBackOption() {
    }

    /**
     * 显示加载框和界面生命周期绑定
     *
     * @param iBaseViewLife
     * @return
     */
    public CallBackOption<T> loadBind(IBaseViewLife iBaseViewLife) {
        this.iBaseViewLife = iBaseViewLife;
        strategy = Strategy.LOAD_BIND;

        return this;
    }

    /**
     * 不显示加载框和界面生命周期绑定
     *
     * @return
     */
    public CallBackOption<T> unLoadBind(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        strategy = Strategy.UNLOAD_BIND;

        return this;
    }

    /**
     * 不显示加载框不和界面生命周期绑定
     *
     * @return
     */
    public CallBackOption<T> unLoadunBind() {
        strategy = Strategy.UNLOAD_UNBIND;

        return this;
    }


    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        if (strategy == Strategy.LOAD_BIND) {
            tMutableLiveData = new MutableLiveData<>();
            tMutableLiveData.observe(iBaseViewLife, t -> iLoadBind.excute(t));
            iBaseViewLife.showLoading();
        } else if (strategy == Strategy.UNLOAD_BIND) {
            tMutableLiveData = new MutableLiveData<>();
            tMutableLiveData.observe(lifecycleOwner, t -> iLoadBind.excute(t));
        }

    }


    @Override
    public void onSuccess(Response<T> response) {
        if (strategy == Strategy.UNLOAD_UNBIND) {
            iLoadBind.excute(response.body());
        } else {
            tMutableLiveData.postValue(response.body());
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (strategy == Strategy.LOAD_BIND) {
            iBaseViewLife.hideLoading();
        }
    }


    @Override
    public T convertResponse(okhttp3.Response response) throws Exception {
        ResponseBody body = response.body();
        if (body == null) return null;
        T data;
        String jsonBody = body.string();
       // ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
        jsonBody = jsonBody.replace("\"data\":\"\"}", "\"data\":null}");
        if(!jsonBody.isEmpty()){
            if(jsonBody.contains("code")&&jsonBody.contains("message")){
                JSONObject jsonObject = new JSONObject(jsonBody);
                int code = jsonObject.getInt("code");
                String msg = jsonObject.getString("message");
               // jsonBody = jsonObject.getString("data");
               // String newJsonBody = ""

                if (code == 401 || code == 505) {
                    LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
                    logoutProvider.logout(msg);
                }else if(code==4000){
                    SPUtil.clear();
                    HttpHeaders httpHeaders = OkGo.getInstance().getCommonHeaders();
                    httpHeaders.clear();
                    ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
                    return null;
                }
                else if (code != 0) {
                    ToastUtil.showShort(msg);
                    throw new UnknownServiceException();
                }
            }

            try {
                Gson gson = GSonUtil.gson;
                Type genericSuperclass = getClass().getGenericSuperclass();
                Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
                data = gson.fromJson(jsonBody, type);
            } catch (JsonSyntaxException e) {
                throw new JsonParseException();
            }
            return data;
        }
       return null;

    }


    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        if (strategy == Strategy.LOAD_BIND) {
            iBaseViewLife.hideLoading();
        }
        ExceptionUtil.dealException(response.getException());
    }
}
