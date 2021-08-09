package com.sskj.hangqing.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.ui.fragment.WeixinFragment;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;

import java.io.File;

public class WeixinFragmentPresenter extends BasePresenter<WeixinFragment> {
    public void onload(File file){
        OkGo.<BaseBean<String>>post(HttpConfig.BASE_URL + HttpConfig.UPLOADIMAGE)
                .isMultipart(false)
                .params("file", file)
                .execute(new JsonCallBack<BaseBean<String>>(this) {
                    @Override
                    public void onSuccess(Response<BaseBean<String>> response) {
                        BaseBean body = response.body();
                        mView.putList(body.getData().toString());
                    }

                    @Override
                    public void onError(Response<BaseBean<String>> response) {
                        super.onError(response);
                        BaseBean body = response.body();
                        //mView.uploadFail();
                        ToastUtil.showShort(body.getMessage());
                    }
                });
    }

    public void bindWeixin(String wechat,String qrCodeUrl,String jyPassword,String realName){
        OkGo.<BaseBean<String>>post(HttpConfig.BASE_URL+"/uc/approve/bind/wechat")
                .params("wechat",wechat)
                .params("qrCodeUrl",qrCodeUrl)
                .params("jyPassword",jyPassword,true)
                .params("realName",realName)
                .execute(new CallBackOption<BaseBean<String>>() {
                }.loadBind(mView).execute(httpdata->{
                    mView.setUI(httpdata.getMessage());

                }));
        }
    public void updataWeixin(String wechat,String qrCodeUrl,String jyPassword,String realName){
        OkGo.<BaseBean<String>>post(HttpConfig.BASE_URL+"/uc/approve/update/wechat")
                .params("wechat",wechat)
                .params("qrCodeUrl",qrCodeUrl)
                .params("jyPassword",jyPassword,true)
                .params("realName",realName)
                .execute(new CallBackOption<BaseBean<String>>() {
                }.loadBind(mView).execute(httpdata->{
                    mView.setUI(httpdata.getMessage());

                }));
    }
}
