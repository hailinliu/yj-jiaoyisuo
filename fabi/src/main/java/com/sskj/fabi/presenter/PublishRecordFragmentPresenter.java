package com.sskj.fabi.presenter;

import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.bean.PublishRecordBean;
import com.sskj.fabi.ui.fragment.PublishRecordFragment;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonConverter;

import java.util.List;

import io.reactivex.Flowable;


public class PublishRecordFragmentPresenter extends BasePresenter<PublishRecordFragment> {
    public Flowable<List<PublishRecordBean>> getData(String page, String pageSize, String tradeType) {
        return httpService.getPublishRecord(page, pageSize, tradeType)
                .converter(new JsonConverter<HttpData<PageBean<PublishRecordBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(haiPageBeanHttpData -> haiPageBeanHttpData.getData().getList())
                .flatMap(Flowable::fromIterable)
                .filter(publishRecordBean -> publishRecordBean.getStockCode()!=null)
                .toList()
                .toFlowable();
    }

    public void cancelPublishOrder(String tradeType, String id) {
        httpService.cancelPublishOrder(tradeType, id)
                .execute(new CallBackOption<HttpData>() {
                }.unLoadBind(mView)
                .execute(httpData->{
                    ToastUtil.showShort(httpData.getMsg());
                    mView.cancelSuccess();
                }));
    }
}
