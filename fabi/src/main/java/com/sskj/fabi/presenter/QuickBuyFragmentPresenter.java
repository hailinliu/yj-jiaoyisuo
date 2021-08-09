package com.sskj.fabi.presenter;

import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.http.HttpConfig;
import com.sskj.fabi.ui.fragment.QuickBuyFragment;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;


public class QuickBuyFragmentPresenter extends BasePresenter<QuickBuyFragment> {

    public void buy(String payType, String code, String num) {
        httpService.quickBuy(payType, code, num)
                .execute(new CallBackOption<HttpData<String>>() {
                }.loadBind(mView)
                        .execute(httpData -> {
                            mView.buySuccess(httpData.getData());
                        }));
    }
}
