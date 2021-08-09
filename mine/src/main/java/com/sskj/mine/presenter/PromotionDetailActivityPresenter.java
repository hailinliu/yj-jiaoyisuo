package com.sskj.mine.presenter;

import com.sskj.common.base.HttpData;
import com.sskj.lib.http.CallBackOption;
import com.sskj.mine.ui.activity.PromotionDetailActivity;

public class PromotionDetailActivityPresenter extends BasePresenter<PromotionDetailActivity> {
    public void geterweima(String num){
        httpService.getErweiMa(num).execute(new CallBackOption<HttpData>() {
        }.loadBind(mView).execute(httpData -> {
           httpData.getData();
        }));
    }
}
