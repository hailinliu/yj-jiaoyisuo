package com.sskj.invite.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.SPUtil;
import com.sskj.invite.bean.MineInviteBean;
import com.sskj.invite.http.HttpConfig;
import com.sskj.invite.ui.activity.InviteActivity;
import com.sskj.lib.SPConfig;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;


/**
 * @author Hey
 * Create at  2019/05/03
 */
public class InvitePresenter extends BasePresenter<InviteActivity> {
    public void getShareInfo() {
        httpService.getShare()
                .execute(new CallBackOption<HttpData<MineInviteBean>>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK) {
                                mView.updateUi(httpData.getData());
                            }
                        }));
    }
}
