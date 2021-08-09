package com.sskj.hangqing.presenter;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.HttpData;
import com.sskj.hangqing.bean.HangqingBannerBean;
import com.sskj.hangqing.bean.NoticeBean;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.ui.fragment.HangqingFragment;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.CallBackOption;

import java.util.List;


public class HangqingFragmentPresenter extends BasePresenter<HangqingFragment> {

    public void getBanner() {
        httpService.getBanner1("0")
                .execute(new CallBackOption<BaseBean<List<HangqingBannerBean>>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updateBanner(httpData.getData())))
        ;
    }


    public void getNotice() {
        httpService.getNotice("1", "10")
                .execute(new CallBackOption<BaseBean<NoticeBean>>() {
                }.unLoadBind(mView)
                        .execute(httpData -> mView.updateNotice(httpData.getData().getContent())));
    }

    public void checkCode(String qrcodeId){
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/uc/login/qrcode/loginVerify")
                .params("qrcodeId",qrcodeId)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpData -> {
                   mView.setData(httpData.getMsg());
                }));
    }
}
