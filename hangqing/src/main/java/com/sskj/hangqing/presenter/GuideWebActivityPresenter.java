package com.sskj.hangqing.presenter;

import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.hangqing.bean.HelpBean;
import com.sskj.hangqing.ui.activity.GuideWebActivity;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;

import java.util.List;


public class GuideWebActivityPresenter extends BasePresenter<GuideWebActivity> {


    public void help(String type) {
        httpService.help(type)
                .execute(new CallBackOption<HttpData<HelpBean>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updateHelp(httpData.getData())));
    }

    public void aboutUs() {
        httpService.aboutUs()
                .execute(new CallBackOption<HttpData<HelpBean>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updateHelp(httpData.getData())));
    }

    public void registerAgreement() {
        httpService.registerAgreement()
                .execute(new CallBackOption<HttpData<HelpBean>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updateHelp(httpData.getData())));
    }

    public void privateService() {
        httpService.privateService()
                .execute(new CallBackOption<HttpData<HelpBean>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updatePrivate(httpData.getData())));
    }

    public void tradeRule() {
        httpService.tradeRule()
                .execute(new CallBackOption<HttpData<List<HelpBean>>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updateTradeRule(httpData.getData())));
    }

    public void tradeAnti() {
        httpService.tradeAnti()
                .execute(new CallBackOption<HttpData<HelpBean>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updateTradeAnti(httpData.getData())));
    }
}
