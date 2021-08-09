package com.sskj.level.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.level.bean.LevelHistoryBean;
import com.sskj.level.http.HttpConfig;
import com.sskj.level.ui.fragment.LevelCurrentEntrustFragment;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.JsonCallBack;

public class LevelCurrentEntrustFragmentPresenter extends BasePresenter<LevelCurrentEntrustFragment> {
    public void getEntrustListRefresh(String pageNo,String pageSize,String symbol){
        OkGo.<String>post(HttpConfig.BASE_URL+"/level/order/current")
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
                .params("symbol",symbol)
                .execute(/*new CallBackOption<String>() {
            }.loadBind(mView).execute(httpdata->{
                ToastUtil.showShort(httpdata);
            })*/new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LevelHistoryBean bean = GSonUtil.GsonToBean(response.body(), LevelHistoryBean.class);
                       // bean.toString();
                        mView.updateUI(bean.getContent());
                    }
                });
    }
    public void cancelEntrust(String orderId) {
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/level/order/cancel")
                .params("orderId",orderId)
                .execute(new JsonCallBack<HttpData>(this) {
            @Override
            public void onSuccess(Response<HttpData> response) {
                HttpData httpData = response.body();
                mView.cancelReturn(httpData.getMsg());
            }
        });

    }
}
