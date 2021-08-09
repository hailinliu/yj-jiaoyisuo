package com.sskj.mine.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.sskj.common.util.GSonUtil;
import com.sskj.mine.bean.FaBiBean;
import com.sskj.mine.bean.LevelBean;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.ui.fragment.ThirdFragment;

public class ThirdFragmentPresenter extends BasePresenter<ThirdFragment> {
    public void getData(String balance,String name){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/asset/level-wallet")
                .params("balance",balance)
                .params("name",name)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LevelBean bean = GSonUtil.GsonToBean(response.body(), LevelBean.class);
                        mView.setLevel(bean.getData());
                        // mView.setBIBI(bean.getData());
                    }

                });

    }
}
