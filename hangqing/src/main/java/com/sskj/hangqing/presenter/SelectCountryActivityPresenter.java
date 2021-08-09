package com.sskj.hangqing.presenter;

import com.sskj.hangqing.bean.CountryBean;
import com.sskj.hangqing.ui.activity.SelectCountryActivity;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.http.CallBackOption;

import java.util.List;

public class SelectCountryActivityPresenter extends BasePresenter<SelectCountryActivity> {

    public void getCountry() {
        httpService.getCountry().execute(new CallBackOption<BaseBean<List<CountryBean>>>() {
        }.loadBind(mView).execute(httpdata -> {
            mView.setUI(httpdata.getData());

        }));
    }
}