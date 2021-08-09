package com.sskj.invite.presenter;

import com.sskj.common.base.HttpData;
import com.sskj.invite.bean.InviteInfo;
import com.sskj.invite.http.HttpConfig;
import com.sskj.invite.ui.activity.MineInviteActivity;
import com.sskj.lib.http.CallBackOption;


public class MineInviteActivityPresenter extends BasePresenter<MineInviteActivity> {

    public void getShareInfo() {
        httpService.getShareInfo()
                .execute(new CallBackOption<HttpData<InviteInfo>>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.updateUi(httpData.getData());
                            }
                        }));
    }


}
