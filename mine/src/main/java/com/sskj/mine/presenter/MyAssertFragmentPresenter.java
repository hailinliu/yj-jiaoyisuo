package com.sskj.mine.presenter;

import com.lzy.okgo.request.base.Request;
import com.sskj.common.base.HttpData;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.ILoadBind;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.mine.bean.MyAssertBean;
import com.sskj.mine.http.HttpService;
import com.sskj.mine.ui.fragment.MyAssertFragment;

public class MyAssertFragmentPresenter extends BasePresenter<MyAssertFragment> {
        public void getData(String id,int type){
            httpService.getAsserts(id,type).execute(new CallBackOption<HttpData<MyAssertBean>>() {

            }.loadBind(mView).execute(httpData -> {
                if(httpData.getData()!=null){
                    mView.getAssert(httpData.getData());
                }

            }));
        }
}
