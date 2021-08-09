package com.sskj.hangqing.presenter;

import com.sskj.hangqing.ui.fragment.NewHangFragment;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.http.CallBackOption;

public class NewHangFragmentPresenter extends BasePresenter<NewHangFragment> {
    public void getRate(String fromUnit,String toUnit){
        httpService.getRate(fromUnit,toUnit).execute(new CallBackOption<BaseBean>() {
        }.loadBind(mView).execute(httpService->{
            String rate=  httpService.getData().toString();
            RateBean bean = new RateBean();
            bean.setName(toUnit);
            bean.setRate(rate);
            mView.setUI(bean);
            // LiveDataBus.get().with(RxBusCode.RATE, RateBean.class).postValue(bean);
            //RxBus.getDefault().send(RxBusCode.RATE,rate);
        }));
    }
}
