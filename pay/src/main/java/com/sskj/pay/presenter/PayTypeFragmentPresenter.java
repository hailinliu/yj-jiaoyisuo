package com.sskj.pay.presenter;

import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.bean.enums.PayTypeEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.pay.bean.PayTypeBean;
import com.sskj.pay.bean.PayTypeResultBean;
import com.sskj.pay.http.HttpConfig;
import com.sskj.pay.ui.fragment.PayTypeFragment;

import java.util.ArrayList;
import java.util.List;


public class PayTypeFragmentPresenter extends BasePresenter<PayTypeFragment> {
    public void getData() {
        httpService.getPayType()
                .execute(new JsonCallBack<HttpData<List<PayTypeResultBean>>>() {
                    @Override
                    public void onSuccess(Response<HttpData<List<PayTypeResultBean>>> response) {

                        HttpData<List<PayTypeResultBean>> data = response.body();


                        ArrayList<PayTypeBean> payTypeBeans = new ArrayList<>();
                        if (data.getData() != null) {

                            for (PayTypeResultBean status : data.getData()) {
                                if (status.getPayType().equals(PayTypeEnum.WX.getType())) {
                                    PayTypeBean payTypeBeanWX = new PayTypeBean(PayTypeEnum.WX);
                                    payTypeBeanWX.setId(status.getId());
                                    payTypeBeanWX.setAdd(true);
                                    payTypeBeanWX.setOpen(!status.isStatus());
                                    payTypeBeanWX.setAccount(status.getAccount());
                                    payTypeBeanWX.setName("");
                                    payTypeBeanWX.setImg(status.getQrCode());
                                    payTypeBeans.add(payTypeBeanWX);
                                }

                                if (status.getPayType().equals(PayTypeEnum.ALIPAY.getType())) {
                                    PayTypeBean payTypeBeanAlipay = new PayTypeBean(PayTypeEnum.ALIPAY);
                                    payTypeBeanAlipay.setId(status.getId());
                                    payTypeBeanAlipay.setAdd(true);
                                    payTypeBeanAlipay.setOpen(!status.isStatus());
                                    payTypeBeanAlipay.setAccount(status.getAccount());
                                    payTypeBeanAlipay.setName("");
                                    payTypeBeanAlipay.setImg(status.getQrCode());
                                    payTypeBeans.add(payTypeBeanAlipay);
                                }

                                if (status.getPayType().equals(PayTypeEnum.BANK.getType())) {
                                    PayTypeBean payTypeBeanBank = new PayTypeBean(PayTypeEnum.BANK);
                                    payTypeBeanBank.setAdd(true);
                                    payTypeBeanBank.setId(status.getId());
                                    payTypeBeanBank.setOpen(!status.isStatus());
                                    payTypeBeanBank.setAccount(status.getAccount());
                                    payTypeBeanBank.setName("");
                                    payTypeBeanBank.setBank(status.getBankCardOpenBank());
                                    payTypeBeanBank.setBranch(status.getAccountOpeningBranch());
                                    payTypeBeans.add(payTypeBeanBank);
                                }
                            }

                        }

                        LiveDataBus.get().with(RxBusCode.PAY_TYPE,Object.class).postValue(payTypeBeans);
                    }
                });
    }

    public void togglePayType(boolean isOpen, String id) {
        httpService.togglePayType(isOpen ? "1" : "0", id)
                .execute(new JsonCallBack<HttpData>(this) {
                    @Override
                    public void onSuccess(Response<HttpData> response) {
                        HttpData httpData = response.body();
                        ToastUtil.showShort(httpData.getMsg());
                        if (httpData.getStatus() == HttpConfig.OK) {
                            mView.toggleSuccess();
                        }
                    }
                });
    }
}
