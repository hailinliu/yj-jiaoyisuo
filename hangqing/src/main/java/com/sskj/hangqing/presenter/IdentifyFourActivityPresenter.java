package com.sskj.hangqing.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.ui.activity.IdentifyFourActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;

import java.io.File;

public class IdentifyFourActivityPresenter extends BasePresenter<IdentifyFourActivity> {
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
                        mView.uploadFail();
                        ToastUtil.showShort(body.getMessage());
                    }
                });
    }
    public void submit(String country,String handHeldIdCard,String idCard,String idCardBack,String idCardFront,String realName,int type){
        OkGo.<BaseBean<String>>post(HttpConfig.BASE_URL+"/uc/approve/real/name\n")
                .params("country",country)
                .params("handHeldIdCard",handHeldIdCard)
                .params("idCard",idCard)
                .params("idCardBack",idCardBack)
                .params("idCardFront",idCardFront)
                .params("realName",realName)
                .params("type",type).execute(new CallBackOption<BaseBean<String>>() {
                }.loadBind(mView).execute(httpdata->{
                    mView.setUI(httpdata.getMessage());

                }));
    }
}

