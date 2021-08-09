package com.sskj.hangqing.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.bean.InviteBean;
import com.sskj.hangqing.bean.InviteRecordBean;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.ui.activity.InviteActivity;
import com.sskj.lib.RConfig;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;

public class InviteActivityPresenter extends BasePresenter<InviteActivity> {
    public void getInviteCode(){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/promotion/summary")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        InviteBean bean = GSonUtil.GsonToBean(response.body(), InviteBean.class);
                        if(bean.getCode()==4000){
                            ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
                        }else {
                            mView.setUI(bean);
                        }
                    }
                });
    }
    public void getPro(String pageNo,String pageSize){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/promotion/record")
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        InviteRecordBean bean = GSonUtil.GsonToBean(response.body(), InviteRecordBean.class);
                        if(bean.getCode()==0){
                            mView.setData(bean.getData().getContent());
                        }else if(bean.getCode()==4000){
                            ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
                        }else {
                            ToastUtil.showShort(bean.getMessage());
                        }
                    }
                });
    }
}
