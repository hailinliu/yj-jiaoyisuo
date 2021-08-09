package com.sskj.lib.router.provider;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.lib.BaseHttpConfig;
import com.sskj.lib.base.IPresenter;
import com.sskj.lib.bean.enums.CodeTypeEnum;
import com.sskj.lib.http.JsonCallBack;

@Route(path = "/lib/service/sendSms")
public class SendSmsProviderImpl implements SendSmsProvider {

    /**
     * type=10 表示注册
     *
     * @param iPresenter
     * @param mobile
     * @param areaCode
     * @param type
     * @param onSend
     */
    @Override
    public void send(IPresenter iPresenter, String mobile, String areaCode,String vcKey,CodeTypeEnum type, String sliderCode, OnSend onSend) {
        if (mobile.contains("@")) {
            areaCode = null;
        } else {
            areaCode = areaCode.replace("+","");
        }
        OkGo.<HttpData>get(BaseHttpConfig.BASE_URL + "/app/user/getCode")
                .params("account", mobile)
                .params("areaCode", areaCode)
                .params("codeType", type.getType())
                .params("sliderCode", sliderCode)
                .params("vcKey",vcKey)
                .execute(new JsonCallBack<HttpData>(iPresenter) {
                    @Override
                    public void onSuccess(Response<HttpData> response) {
                        onSend.onSuccess();
                    }

                    @Override
                    public void onError(Response<HttpData> response) {
                        super.onError(response);
                    }
                });
    }

    @Override
    public void init(Context context) {

    }
}
