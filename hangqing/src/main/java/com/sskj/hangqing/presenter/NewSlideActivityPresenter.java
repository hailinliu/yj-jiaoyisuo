package com.sskj.hangqing.presenter;

import com.sskj.hangqing.ui.activity.NewSlideActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.http.CallBackOption;

public class NewSlideActivityPresenter extends BasePresenter<NewSlideActivity> {
    public void getSecuritySetting(){
        httpService.getSecurity().execute(new CallBackOption<BaseBean<SafeSettingBean>>() {
        }.loadBind(mView).execute(httpdata->{
            if(httpdata!=null&&httpdata.getData()!=null){
                mView.setUI(httpdata.getData());
            }
        }));
    }
}
