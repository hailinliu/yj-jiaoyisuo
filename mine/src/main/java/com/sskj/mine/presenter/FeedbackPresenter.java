package com.sskj.mine.presenter;

import com.sskj.common.base.App;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.http.CallBackOption;
import com.sskj.mine.R;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.ui.activity.FeedbackActivity;

public class FeedbackPresenter extends BasePresenter<FeedbackActivity> {
    public void sendRequest(String content, String contact) {

        httpService.feedBack(content, contact)
                .execute(new CallBackOption<HttpData>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            ToastUtil.showShort(App.INSTANCE.getString(R.string.mine_feedback_sumbit_success));
                            if (httpData.getStatus() == HttpConfig.OK) {

                                mView.success();
                            }
                        }));
    }
}
