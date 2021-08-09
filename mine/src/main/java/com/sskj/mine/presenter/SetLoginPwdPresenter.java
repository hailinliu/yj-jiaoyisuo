package com.sskj.mine.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.SPConfig;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.ui.activity.SetLoginPwdActivity;


/**
 * @author Hey
 * Create at  2019/05/01
 */
public class SetLoginPwdPresenter extends BasePresenter<SetLoginPwdActivity> {


    //重置登录密码
    public void setLoginPwd(String newPwd, String oldPwd) {
        httpService.setLoginPwd(newPwd, oldPwd)
                .execute(new CallBackOption<HttpData>() {
                }
                        .loadBind(mView)
                        .execute(httpData -> {
                            ToastUtil.showShort(httpData.getMsg());
                            if (httpData.getStatus() == 200) {
                                mView.updataUI();
                            }
                        }));
    }

}
