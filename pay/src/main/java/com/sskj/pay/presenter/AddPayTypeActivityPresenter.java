package com.sskj.pay.presenter;

import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.bean.UploadBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.pay.http.HttpConfig;
import com.sskj.pay.ui.activity.AddPayTypeActivity;


public class AddPayTypeActivityPresenter extends BasePresenter<AddPayTypeActivity> {

    public void upload(String filePath) {
        httpService.upload(filePath)
                .execute(new CallBackOption<HttpData<String>>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            mView.uploadSuccess(httpData.getData());
                        }));
    }

    public void addOrEditPayType(boolean isAdd, String type, String username, String account, String img, String bank, String branch, String pwd) {
        httpService.addOrEditPayType(isAdd, type, username, account, img, bank, branch, pwd)
                .execute(new CallBackOption<HttpData>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            ToastUtil.showShort(httpData.getMsg());
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.addOrEditSuccess();
                            }
                        }));
    }

}
