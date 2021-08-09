package com.sskj.mine.presenter;

import com.sskj.common.base.HttpData;
import com.sskj.lib.http.CallBackOption;
import com.sskj.mine.bean.DataBean;
import com.sskj.mine.ui.activity.PromotionActivity;

public class PromotionActivityPresenter extends BasePresenter<PromotionActivity> {

    public void getPromotion(String id){
        httpService.getPromotion(id).execute(new CallBackOption<HttpData<DataBean>>() {
        }.loadBind(mView).execute(httpData -> {
            if(httpData.getData()!=null){
                mView.setData(httpData.getData());
            }

        }));

    }
}
