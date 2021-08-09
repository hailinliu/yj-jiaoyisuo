package com.sskj.hangqing.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.http.HttpConfig;
import com.sskj.hangqing.ui.activity.AccountSafetyActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.PayInfoBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.http.CallBackOption;

public class AccountSafetyActivityPresenter extends BasePresenter<AccountSafetyActivity> {


    public void getSecuritySetting(){
        httpService.getSecurity().execute(new CallBackOption<BaseBean<SafeSettingBean>>() {
        }.loadBind(mView).execute(httpdata->{
            if(httpdata.getData()!=null){
                mView.setUI(httpdata.getData());
            }
        }));
        }
        public void closeGoogle(String code){
        httpService.unbindGoogle(code).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpdata->{
           mView.setCloseUI(httpdata.getMessage());
        }));
        }
        public void closeGoogleEmailCode(String email){
            OkGo.<HttpData>post(HttpConfig.BASE_URL+"/uc/email/offGoogle/email/code")
                   .params("email",email)
                    .execute(new CallBackOption<HttpData>() {
                    }.loadBind(mView).execute(httpData -> {
                        ToastUtil.showShort(httpData.getMsg());
                    }));
        }
    public void closeGooglePhoneCode(String email){
        OkGo.<HttpData>post(HttpConfig.BASE_URL+"/uc/mobile/off/google/code")
                .params("mobilePhone",email)
                .execute(new CallBackOption<HttpData>() {
                }.loadBind(mView).execute(httpData -> {
                    ToastUtil.showShort(httpData.getMsg());
                }));
    }
        public void getPayInfo(){
           OkGo.<BaseBean<PayInfoBean>>get(HttpConfig.BASE_URL+"/uc/approve/payInfo")
               .execute(
                       new CallBackOption<BaseBean<PayInfoBean>>() {
                   @Override
                   public void onSuccess(Response<BaseBean<PayInfoBean>> response) {
                       super.onSuccess(response);
                   }

                   @Override
                   public void onError(Response<BaseBean<PayInfoBean>> response) {
                       super.onError(response);
                   }
               }.loadBind(mView).execute(httpdata->{
                  mView.setData(httpdata.getData());
                }));
            }
        }

