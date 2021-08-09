package com.sskj.hangqing.presenter;

import com.lzy.okgo.OkGo;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.ui.fragment.BankFragment;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;

public class BankFragmentPresenter extends BasePresenter<BankFragment> {
    public void bindBank(String bank,String branch,String cardNo,String jyPassword,String realName){
        OkGo.<BaseBean<String>>post(HttpConfig.BASE_URL+"/uc/approve/bind/bank")
                .params("bank",bank)
                .params("branch",branch)
                .params("cardNo",cardNo)
                .params("realName",realName)
                .params("jyPassword",jyPassword,true)
                .execute(new CallBackOption<BaseBean<String>>() {
        }.loadBind(mView).execute(httpdata->{
            mView.setUI(httpdata.getMessage());

        }));
    }
    public void updteBank(String bank,String branch,String cardNo,String jyPassword,String realName){
        OkGo.<BaseBean<String>>post(HttpConfig.BASE_URL+"/uc/approve/update/bank")
                .params("bank",bank)
                .params("branch",branch)
                .params("cardNo",cardNo)
                .params("realName",realName)
                .params("jyPassword",jyPassword,true)
                .execute(new CallBackOption<BaseBean<String>>() {
                }.loadBind(mView).execute(httpdata->{
                    mView.setUI(httpdata.getMessage());

                }));
    }

}
