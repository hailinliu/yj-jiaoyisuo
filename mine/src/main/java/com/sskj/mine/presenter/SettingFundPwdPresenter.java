package com.sskj.mine.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.SPConfig;
import com.sskj.lib.bean.enums.CodeTypeEnum;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.router.provider.SendSmsProvider;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.ui.activity.SettingFundPwdActivity;

public class SettingFundPwdPresenter extends BasePresenter<SettingFundPwdActivity> {
    // 获取验证码
    public void sendCode(String account, String slideCode) {
        SendSmsProvider sendSmsProvider = ARouter.getInstance().navigation(SendSmsProvider.class);
        sendSmsProvider.send(this, account, SPUtil.get(SPConfig.AREA_CODE, ""), "",CodeTypeEnum.STATUS_4, slideCode, new SendSmsProvider.OnSend() {
            @Override
            public void onSuccess() {
                mView.onSendCodeSuccess();
            }

        });

    }

    //设置资金密码
    public void setFundPwd(String code, String pwd) {
        httpService.setFundPwd(code, pwd)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView)
                        .execute(httpData -> {
                            ToastUtil.showShort(httpData.getMsg());
                            mView.onSetSuccess();
                        }));
    }
}
