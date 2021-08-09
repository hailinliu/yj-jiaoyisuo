package com.sskj.login.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.bean.enums.CodeTypeEnum;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.router.provider.SendSmsProvider;
import com.sskj.login.http.HttpConfig;
import com.sskj.login.ui.activity.PwdForgetActivity;


public class PwdForgetActivityPresenter extends BasePresenter<PwdForgetActivity> {


    /**
     * 重置登录密码
     *
     * @param mobile 手机号/邮箱号
     * @param opwd   密码
     * @param code   验证码
     * @return
     */
    public void resetLoginPwd(String areaCode, String mobile, String opwd, String code) {
        httpService.resetLoginPwd(areaCode, mobile, opwd, code)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.resetPwdSuccess();
                            }
                        }));
    }

    public void sendCode(String slideCode, String account, String areaCode) {

        SendSmsProvider smsProvider = ARouter.getInstance().navigation(SendSmsProvider.class);
        smsProvider.send(this, account, areaCode,"", CodeTypeEnum.STATUS_2, slideCode,new SendSmsProvider.OnSend() {
            @Override
            public void onSuccess() {
                mView.sendCheckCodeSuccess();

            }

        });
    }
}
