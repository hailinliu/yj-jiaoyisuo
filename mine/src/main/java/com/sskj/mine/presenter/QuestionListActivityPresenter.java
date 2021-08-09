package com.sskj.mine.presenter;

import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.JsonConverter;
import com.sskj.mine.bean.QuestionBean;
import com.sskj.mine.ui.activity.QuestionListActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;


public class QuestionListActivityPresenter extends BasePresenter<QuestionListActivity> {

    public Flowable<List<QuestionBean>> getData(int page) {
        return httpService.getQuestionList(page)
                .converter(new JsonConverter<HttpData<PageBean<QuestionBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getData().getList())
                .onErrorReturnItem(new ArrayList<>());


    }
}
