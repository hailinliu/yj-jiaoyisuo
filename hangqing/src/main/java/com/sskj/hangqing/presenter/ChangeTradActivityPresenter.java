package com.sskj.hangqing.presenter;

import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.ui.activity.ChangeTradActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;

public class ChangeTradActivityPresenter extends BasePresenter<ChangeTradActivity> {
    public void updataUseerPwdCode(String email){
        httpService.updatePwdTradCode(email).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpdata->{
            mView.updateUI(httpdata.getMessage());
            // ToastUtil.showShort(httpdata.getMessage());
        }));
    }
    public void updataUseerPwdCodeemail(String email){
        httpService.updatePwdCodeTrademail(email).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpdata->{
            mView.updateUI(httpdata.getMessage());
           // ToastUtil.showShort(httpdata.getMessage());
        }));
    }
    public void updateTradPwd(String newPassword,String code){
        httpService.updatePwdtrad(newPassword, code).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpdata->{
                mView.setSuccess(httpdata.getMessage());
               // ToastUtil.showShort(httpdata.getMessage());
        }));
    }
    public void updateTradPwd1(String newPassword,String code){
        httpService.updatePwdtrad1(newPassword, code).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpdata->{
            mView.setSuccess(httpdata.getMessage());
            // ToastUtil.showShort(httpdata.getMessage());
        }));
    }
}

