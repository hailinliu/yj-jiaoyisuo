package com.sskj.mine.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.lib.bean.enums.CodeTypeEnum;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.router.provider.SendSmsProvider;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.ui.activity.BindMobileActivity;


/**
 * @author Hey
 * Create at  2019/05/01
 */
public class BindMobilePresenter extends BasePresenter<BindMobileActivity> {

    public void bindMobile(String mobile, String code, String tpwd, String areaCode) {
        httpService.bindMobile(mobile, code, tpwd, areaCode)
                .execute(new CallBackOption<HttpData>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.finish();
                            }
                        }));
    }


    public void updateMobile(String mobile, String id, String tpwd) {
        httpService.updateMobile(mobile, id, tpwd)
                .execute(new CallBackOption<HttpData>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.finish();
                            }
                        }));
    }


    public void sendCode(String mobile, String areaCode) {
        SendSmsProvider sendSmsProvider = ARouter.getInstance().navigation(SendSmsProvider.class);
        sendSmsProvider.send(this, mobile, areaCode, "",CodeTypeEnum.STATUS_3, null, () -> mView.onAuthCodeSuccess());
    }


}
