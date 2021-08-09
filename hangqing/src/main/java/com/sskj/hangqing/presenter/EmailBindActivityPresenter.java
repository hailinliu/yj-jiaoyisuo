package com.sskj.hangqing.presenter;

import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.ui.activity.EmailBindActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;

public class EmailBindActivityPresenter extends BasePresenter<EmailBindActivity> {

    public void getEmailCode(String email){
    httpService.getEmailCode(email).execute(new CallBackOption<BaseBean>() {
    }.loadBind(mView).execute(httpdata->{
        ToastUtil.showShort(httpdata.getMessage());
    }));
    }
    public void getChangeEmailCode(String email){
        httpService.getUpdateEmailCode(email).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpdata->{
            ToastUtil.showShort(httpdata.getMessage());
        }));
    }
    public void setEmail(String email,String code){
        httpService.bindEmail(email, code).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpdata->{
            mView.setUI(httpdata.getMessage());
          //  ToastUtil.showShort(httpdata.getMessage());
        }));
    }
    public void setChangeEmail(String email,String code){
        httpService.updateBindEmail(email, code).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpdata->{
            mView.setUI(httpdata.getMessage());
            //  ToastUtil.showShort(httpdata.getMessage());
        }));
    }
}
