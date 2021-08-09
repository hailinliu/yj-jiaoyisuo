package com.sskj.level.presenter;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.level.bean.TradeInfoBean;
import com.sskj.level.bean.TradeListBean;
import com.sskj.level.http.HttpConfig;
import com.sskj.level.ui.activity.TradeActivity;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.CallBackOption;


public class TradeActivityPresenter extends BasePresenter<TradeActivity> {




    public void getAllList() {
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/level//order/closeAll")
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpData -> mView.setUI(httpData.getMsg())));

    }
}
