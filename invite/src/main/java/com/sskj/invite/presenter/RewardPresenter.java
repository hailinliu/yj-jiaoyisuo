package com.sskj.invite.presenter;

import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.invite.bean.RewardBean;
import com.sskj.invite.ui.activity.RewardActivity;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.JsonConverter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;


/**
 * @author Hey
 * Create at  2019/05/03
 */
public class RewardPresenter extends BasePresenter<RewardActivity> {

    public Flowable<List<RewardBean>> getData(String page, String pageSize) {
        return httpService.getRewardList(page, pageSize)
                .converter(new JsonConverter<HttpData<PageBean<RewardBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(inviteRecordHttpData -> inviteRecordHttpData.getData().getList())
                .onErrorReturnItem(new ArrayList<>())
                ;
    }
}
