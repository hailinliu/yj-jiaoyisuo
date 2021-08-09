package com.sskj.tibi.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.convert.Converter;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.http.JsonConverter;
import com.sskj.tibi.bean.ContentBean;
import com.sskj.tibi.bean.NewJsonConverter4;
import com.sskj.tibi.bean.TibiRecordBean;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.ui.activity.WithdrawRecordActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import okhttp3.Response;


public class WithdrawRecordActivityPresenter extends BasePresenter<WithdrawRecordActivity> {
    public Flowable<List<TibiRecordBean.DataBean.ContentBean>> getTibiRecord(String pageNo, String pageSize, String unit){
       return OkGo.<TibiRecordBean>post(HttpConfig.BASE_URL+"/uc/withdraw/record")
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
                .params("unit",unit)
                .converter(new NewJsonConverter4<TibiRecordBean>(){
                    @Override
                    public TibiRecordBean convertResponse(Response response) throws Throwable {
                        return super.convertResponse(response);
                    }
                })
               .adapt(new FlowableBody<>())
                .map(data->data.getData().getContent())
              .onErrorReturnItem(new ArrayList<>());
    }
/*public void getTibiRecord(String pageNo, String pageSize, String unit){
    OkGo.<String>post(HttpConfig.BASE_URL+"/uc/withdraw/record")
            .params("pageNo",pageNo)
            .params("pageSize",pageSize)
            .params("unit",unit)
            .execute(new StringCallback(){
                @Override
                public void onSuccess(Response<String> response) {
                    TibiRecordBean bean= GSonUtil.GsonToBean(response.body(),TibiRecordBean.class);
                   mView.setData(bean.getData().getContent());
                    ;
                }
            });
}*/
}
