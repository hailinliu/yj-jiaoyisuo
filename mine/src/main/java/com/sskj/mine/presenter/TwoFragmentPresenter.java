package com.sskj.mine.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.util.GSonUtil;
import com.sskj.mine.bean.BIBIBean;
import com.sskj.mine.bean.FaBiBean;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.ui.fragment.TwoFragment;

public class TwoFragmentPresenter extends BasePresenter<TwoFragment> {
    public void getFaBi(String balance,String name){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/otc/wallet/get")
                .params("balance",balance)
                .params("name",name)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            FaBiBean bean = GSonUtil.GsonToBean(response.body(), FaBiBean.class);
                            if(bean!=null&&bean.getData()!=null&&bean.getData().size()>0){
                                mView.setFaBi(bean.getData());
                            }

                        }

                       // mView.setBIBI(bean.getData());
                    }

                });
    }
}
