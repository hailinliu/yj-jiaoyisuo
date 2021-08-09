package com.sskj.invite.presenter;

import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.invite.bean.TeamBean;
import com.sskj.invite.ui.activity.TeamActivity;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.JsonConverter;

import java.util.List;

import io.reactivex.Flowable;


/**
 * @author Hey
 * Create at  2019/05/03
 */
public class TeamPresenter extends BasePresenter<TeamActivity> {

    public Flowable<List<TeamBean>> getData(String page, String pageSize) {
        return httpService.getTeamList(page, pageSize)
                .converter(new JsonConverter<HttpData<PageBean<TeamBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(inviteRecordHttpData -> inviteRecordHttpData.getData().getList());
    }

}
