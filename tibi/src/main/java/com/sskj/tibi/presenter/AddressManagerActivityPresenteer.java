package com.sskj.tibi.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.tibi.bean.CongRecordBean;
import com.sskj.tibi.bean.NewAddressListBean;
import com.sskj.tibi.bean.NewJsonConverter2;
import com.sskj.tibi.bean.NewJsonConverter3;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.ui.activity.AddressManagerActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public class AddressManagerActivityPresenteer extends BasePresenter<AddressManagerActivity> {
    public Flowable<List<NewAddressListBean.DataBean.ContentBean>> getAddress(String pageNo, String pageSize, String unit){

            return OkGo.<NewAddressListBean>post(HttpConfig.BASE_URL+"/uc/withdraw/address/page")
                    .params("unit",unit)
                    .params("pageNo",pageNo)
                    .params("pageSize",pageSize)
                    .converter(new NewJsonConverter3<>())
                    .adapt(new FlowableBody<>())
                    .map(data->
                            data.getData().getContent()
                    ).onErrorReturnItem(new ArrayList<>());

        }



    }


