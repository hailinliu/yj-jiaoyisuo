package com.sskj.mine.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.SPConfig;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.mine.bean.MineShopBean;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.presenter.BasePresenter;
import com.sskj.mine.ui.activity.SellerApplyActivity;


/**
 * @author Hey
 * Create at  2019/05/01
 */
public class SellerApplyPresenter extends BasePresenter<SellerApplyActivity> {
    // 获取冻结保证金
    public void getMoney() {
        httpService.getShopMoney()
                .execute(new CallBackOption<HttpData<MineShopBean>>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.updateUi(httpData.getData());
                            }
                        }));
    }

    public void shopVerify() {
        httpService.applyShop()
                .execute(new CallBackOption<HttpData<Object>>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                ToastUtil.showShort(httpData.getMsg());
                                mView.finish();
                            }
                        }));
    }
}
