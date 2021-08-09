package com.sskj.bibi.presenter;

import com.lzy.okgo.model.Response;
import com.sskj.bibi.http.HttpConfig;
import com.sskj.bibi.ui.fragment.PankouFragment;
import com.sskj.common.base.HttpData;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.WSFiveBean;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.http.JsonCallBack;

import java.util.ArrayList;
import java.util.List;


public class PankouFragmentPresenter extends BasePresenter<PankouFragment> {

    public void getProduct(String qu,String code) {
        httpService.getProduct(qu,code)
                .tag(this)
                .execute(new JsonCallBack<HttpData<List<CoinBean>>>() {
                    @Override
                    public void onSuccess(Response<HttpData<List<CoinBean>>> response) {
                        HttpData<List<CoinBean>> httpData = response.body();
                        if (httpData.getStatus() == HttpConfig.OK) {
                            if (httpData.getData().size() > 0) {
                                mView.updateTitle(httpData.getData().get(0));
                            }
                        }
                    }
                });
    }

    public void getPankou(String code) {
       /* httpService.getPankou(code)
                .tag(this)
                .execute(new JsonCallBack<HttpData<WSFiveBean1>>() {
                    @Override
                    public void onSuccess(Response<HttpData<WSFiveBean1>> response) {
                        HttpData<WSFiveBean1> httpData = response.body();
                        if (httpData.getStatus() == HttpConfig.OK) {
                            if (httpData.getData() != null ) {
                                WSFiveBean1 wsFiveBean = httpData.getData();
                                mView.updateFive(wsFiveBean);
                            } else {
                                WSFiveBean1 wsFiveBean = new WSFiveBean1();
                                wsFiveBean.setCode(code);
                                wsFiveBean.getAsk().setItems(new ArrayList<>());
                                //wsFiveBean.setBids(new ArrayList<>());
                                wsFiveBean.getBid().setItems(new ArrayList<>());
                                mView.updateFive(wsFiveBean);
                            }
                        }
                    }
                });*/
    }

}
