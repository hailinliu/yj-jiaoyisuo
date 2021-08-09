package com.sskj.hangqing.presenter;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.GSonUtil;
import com.sskj.hangqing.bean.InfoBean;
import com.sskj.hangqing.bean.SummaryBean;
import com.sskj.hangqing.ui.fragment.SummaryFragment;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.ILoadBind;


public class SummaryFragmentPresenter extends BasePresenter<SummaryFragment> {

    public void getSummary(String code) {
        httpService.getSummary(code)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                      InfoBean bean = GSonUtil.GsonToBean(response.body(), InfoBean.class);
                        mView.setData(bean);
                    }
                });
                /*.execute(httpData-> mView.updateUI(httpData.getData())))*/

    }
}
