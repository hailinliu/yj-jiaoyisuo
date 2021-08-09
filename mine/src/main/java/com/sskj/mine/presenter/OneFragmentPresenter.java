package com.sskj.mine.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.util.GSonUtil;
import com.sskj.mine.bean.BIBIBean;
import com.sskj.mine.bean.SoloBean;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.ui.fragment.OneFragment;

import java.util.List;

public class OneFragmentPresenter extends BasePresenter<OneFragment> {
    public void getBiBi(String balance,String type){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/asset/wallet")
                .params("balance",balance)
                .params("name",type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            BIBIBean bean = GSonUtil.GsonToBean(response.body(), BIBIBean.class);
                            mView.setBIBI(bean.getData());
                        }

                    }
                });
    }

}
