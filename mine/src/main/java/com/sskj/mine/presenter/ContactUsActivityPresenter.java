package com.sskj.mine.presenter;

import com.sskj.common.base.HttpData;
import com.sskj.lib.http.CallBackOption;
import com.sskj.mine.bean.TwoCodeBean;
import com.sskj.mine.ui.activity.ContactUsActivity;

import java.util.ArrayList;
import java.util.List;

public class ContactUsActivityPresenter extends BasePresenter<ContactUsActivity> {
    public void getAddress(String id){
        httpService.getData(id).execute(new CallBackOption<HttpData<TwoCodeBean>>() {
        }.loadBind(mView).execute(twoCodeBeanHttpData -> {
            List<String> list =new ArrayList<>();
            list.add(twoCodeBeanHttpData.getData().getWeixin());
            list.add(twoCodeBeanHttpData.getData().getQq());
            mView.updateUI(list);
        }));
    }
}
