package com.sskj.hangqing.presenter;

import com.sskj.hangqing.ui.activity.ChangeUserActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;

public class ChangeUserActivityPresenter extends BasePresenter<ChangeUserActivity> {
    public void updateUserPwd(String newPassword,String code){
        httpService.updatePwd(newPassword,code).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpdata->{
            mView.updateUI(httpdata.getMessage());
            //ToastUtil.showShort(httpdata.getMessage());
        }));
    }
    public void updataUseerPwdCode(String email){
        httpService.updatePwdCode(email).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpdata->{
            mView.setdata(httpdata.getMessage());
          //  ToastUtil.showShort(httpdata.getMessage());
        }));
    }
    public void updataUseerPwdCodeemail(String email){
        httpService.updatePwdCodeemail(email).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpdata->{
            mView.setdata(httpdata.getMessage());
           // ToastUtil.showShort(httpdata.getMessage());
        }));
    }
}
