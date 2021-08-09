package com.sskj.hangqing.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.util.GSonUtil;
import com.sskj.hangqing.ui.activity.PhoneBindActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.login.bean.rxbus.AddressBean;
import com.sskj.login.http.HttpConfig;

public class PhoneBindActivityPresenter extends BasePresenter<PhoneBindActivity> {
    public void getUpdatePhoneCode(String country,String phone){
        httpService.getUpdatePhoneCode(country,phone).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpdata->{
            mView.updateUI(httpdata.getMessage());
        }));
    }
    public void getBindPhone(String country,String phone){
        httpService.getPhoneCode(country,phone).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpdata->{
            mView.updateUI(httpdata.getMessage());
        }));
    }
    public void bindPhone(String country,String phone,String code){
    httpService.bindPhone(country, phone, code).execute(new CallBackOption<BaseBean>() {
    }.loadBind(mView).execute(httpdata->{
        mView.setUI(httpdata.getMessage());

    }));
        }
    public void updateBindPhone(String phone,String code){
    httpService.updateBindPhone(phone,code).execute(new CallBackOption<BaseBean>() {
    }.loadBind(mView).execute(httpdata->{
        mView.setUI(httpdata.getMessage());
    }));
    }
    public void getArray(){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/support/country")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        AddressBean bean = GSonUtil.GsonToBean(response.body(),AddressBean.class);
                        mView.setmess(bean);
                    }
                });
    }
}
