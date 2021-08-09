package com.sskj.fabi.presenter;

import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.fabi.bean.OrderBean;
import com.sskj.fabi.bean.OrderRecordBean;
import com.sskj.fabi.ui.fragment.FabiRecordFragment;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonConverter;

import java.util.List;

import io.reactivex.Flowable;


public class FabiRecordFragmentPresenter extends BasePresenter<FabiRecordFragment> {
  /*  public Flowable<List<OrderRecordBean>> getData(String page, String pageSize,String status) {
       return httpService.getOrderRecord(page,pageSize,status)

                .converter(new JsonConverter<HttpData<PageBean<OrderRecordBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(haiPageBeanHttpData -> haiPageBeanHttpData.getData().getList())
               ;
    }*/
  public Flowable<List<OrderBean.ContentBean>> queryOrder1(String coinId,String advertiseType,int pageNo, int pageSize,String status,String orderSn){
     return httpService.getOrder1(coinId,advertiseType,pageNo,pageSize,status,orderSn).converter(new JsonConverter<BaseBean<OrderBean>>() {
      }).adapt(new FlowableBody<>()).map(data->
          data.getData().getContent()
      );
  }
    public Flowable<List<OrderBean.ContentBean>> queryOrder2(String coinId,String advertiseType,int pageNo, int pageSize,String status,String orderSn){
        return httpService.getOrder2(coinId,advertiseType,pageNo,pageSize,status,orderSn).converter(new JsonConverter<BaseBean<OrderBean>>() {
        }).adapt(new FlowableBody<>()).map(data->
                data.getData().getContent()
        );
    }
}
