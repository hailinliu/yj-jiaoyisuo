package com.sskj.fabi.presenter;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.fabi.bean.NewFabiAllBean;
import com.sskj.fabi.ui.fragment.FibiNewFragment;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;

import java.util.List;

public class FibiNewFragmentPresenter extends BasePresenter<FibiNewFragment> {

    public void getFabiCoinAll(){
        httpService.getAllList().execute(new CallBackOption<NewFabiAllBean>() {
        }.loadBind(mView).execute(httpdata->{
            mView.setUi(httpdata.getData());

        }));
      /* httpService.getAllList().execute(new StringCallback(){

           @Override
           public void onSuccess(Response<String> response) {
               response.body();
           }

           @Override
           public void onError(Response<String> response) {
               super.onError(response);
           }
       });*/
    }

}
