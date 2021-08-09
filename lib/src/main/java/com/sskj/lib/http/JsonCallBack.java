package com.sskj.lib.http;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.BaseHttpConfig;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.IPresenter;
import com.sskj.lib.router.provider.LogoutProvider;
import com.sskj.lib.util.ExceptionUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.UnknownServiceException;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;

public abstract class JsonCallBack<T> extends AbsCallback<T> {


    private IPresenter mBasePresenter;

    private boolean showDialog = true;
    private boolean isShowToast = true;
    private int dealCode = 0;
    private MutableLiveData<T> tMutableLiveData;
    private String requestUrl;

    public JsonCallBack(IPresenter basePresenter) {
        this.mBasePresenter = basePresenter;
        showDialog = true;
    }

    public JsonCallBack(IPresenter basePresenter, int dealCode) {
        this.mBasePresenter = basePresenter;
        showDialog = true;
        this.dealCode = dealCode;
    }

    public JsonCallBack(boolean isShowToast) {
        this.isShowToast = isShowToast;
    }


    public JsonCallBack(IPresenter basePresenter, boolean isShow) {
        this.mBasePresenter = basePresenter;
        showDialog = isShow;
    }

    public JsonCallBack() {
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        if (mBasePresenter != null) {
            if ( mBasePresenter.mView instanceof LifecycleOwner){
                tMutableLiveData = new MutableLiveData<>();
                tMutableLiveData.observe((LifecycleOwner) mBasePresenter.mView, t -> {
                    onSuccess(t);
                });
            }
            if (showDialog) {
                mBasePresenter.showLoading();
            }
        }
    }
    public  void onSuccess(T httpData){
        Logger.e("Please impl onSuccess :"+requestUrl);
    }

    @Override
    public void onSuccess(Response<T> response) {
        tMutableLiveData.postValue(response.body());
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (mBasePresenter != null) {
            mBasePresenter.hideLoading();
        }
    }


    @Override
    public T convertResponse(okhttp3.Response response) throws Exception {
        ResponseBody body = response.body();
        requestUrl = response.request().url().toString();
        if (body == null) return null;
        T data;
        String jsonBody = body.string();
        jsonBody = jsonBody.replace("\"data\":\"\"}", "\"data\":null}");
        JSONObject jsonObject = new JSONObject(jsonBody);
        int code = jsonObject.getInt("code");
        String msg = jsonObject.getString("message");

        if(code==4000){
            LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
            logoutProvider.logout(msg);
            SPUtil.clear();
            HttpHeaders httpHeaders = OkGo.getInstance().getCommonHeaders();
            httpHeaders.clear();
            ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
        }
        if (code == 505) {
            LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
            logoutProvider.logout(msg);
        } else if (code != 0) {
            if (isShowToast) {
                ToastUtil.showShort(msg);
            }
            throw new UnknownServiceException();
        }
        try {
            Gson gson = GSonUtil.gson;
            Type genericSuperclass = getClass().getGenericSuperclass();
            Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            data = gson.fromJson(jsonBody, type);
        }catch (JsonSyntaxException e) {
            throw new JsonParseException();
        }
        return data;
    }



    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        if (mBasePresenter != null) {
            mBasePresenter.hideLoading();
        }
        if (!isShowToast) {
            return;
        }
        ExceptionUtil.dealException(response.getException());
    }
}
