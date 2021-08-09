package com.sskj.login.presenter;


import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.SPConfig;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.NewBaseBean;
import com.sskj.lib.bean.enums.CodeTypeEnum;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.router.provider.SendSmsProvider;
import com.sskj.login.bean.rxbus.AddressBean;
import com.sskj.login.http.HttpConfig;
import com.sskj.login.ui.activity.RegisterActivity;

import okhttp3.Headers;

/**
 * @author Hey
 * Create at  2019/08/21 11:29:56
 */
public class RegisterActivityPresenter extends BasePresenter<RegisterActivity> {
   /* public void sendCode(String slideCode, String account, String areaCode,String vcKey) {
        SendSmsProvider smsProvider = ARouter.getInstance().navigation(SendSmsProvider.class);
        smsProvider.send(this, account, areaCode, vcKey,CodeTypeEnum.STATUS_1, slideCode, new SendSmsProvider.OnSend() {
            @Override
            public void onSuccess() {
             //   mView.sendCheckCodeSuccess();
            }

        });
    }*/
public void getPhoneCode(String country,String phone){
httpService.phoneCode(country,phone).execute(new CallBackOption<NewBaseBean>() {
}.loadBind(mView).execute(httpData->{
    if(httpData.getCode()==0){
        mView.getPhoneSuccess(httpData.getMessage());

    }
}));
}
    public void getEmailCode(String email ){
        httpService.emailCode(email).execute(new CallBackOption<NewBaseBean>() {
        }.loadBind(mView).execute(httpData->{
            if(httpData.getCode()==0){
                mView.getPhoneSuccess(httpData.getMessage());

            }
        }));
    }
    public void getArray(){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/support/country")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        AddressBean bean = GSonUtil.GsonToBean(response.body(),AddressBean.class);
                        mView.setData(bean);
                    }
                });
    }

public void isRepetition(String phone){
httpService.isRepeat(phone).execute(new StringCallback(){
    @Override
    public void onSuccess(Response<String> response) {
        NewBaseBean bean =  GSonUtil.GsonToBean(response.body(),NewBaseBean.class);
        mView.isSuccess(bean.getMessage());

    }
}
   /*     new JsonCallBack<NewBaseBean>(){
    @Override
    public void onSuccess(NewBaseBean httpData) {
        //super.onSuccess(httpData);
        if(httpData.getCode()==0){
            mView.isSuccess(httpData.getMessage());
        }

    }

    @Override
    public void onError(Response<NewBaseBean> response) {
        super.onError(response);
    }
}*/);
}
}
