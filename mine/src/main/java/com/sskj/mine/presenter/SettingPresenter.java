package com.sskj.mine.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.App;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.bean.AppVersionBean;
import com.sskj.lib.http.CallBackOption;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.util.APKVersionCodeUtils;
import com.sskj.mine.R;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.ui.activity.SettingActivity;


/**
 * @author Hey
 * Create at  2019/05/03
 */
public class SettingPresenter extends BasePresenter<SettingActivity> {


    /**
     * 检测版本
     */
    public void checkVersion() {
        httpService.checkVersion()
                .execute(new CallBackOption<HttpData<AppVersionBean>>() {
                    @Override
                    public void onError(Response<HttpData<AppVersionBean>> response) {
                        super.onError(response);
                        ToastUtil.showShort(App.INSTANCE.getString(R.string.minesettingPresenter1));
                    }
                }
                        .unLoadBind(mView)
                        .execute(httpData -> {
                            if (httpData.getStatus() == HttpConfig.OK && httpData.getData() != null) {
                                String verName = APKVersionCodeUtils.getVerName(App.INSTANCE);
                                if (APKVersionCodeUtils.compareVersion(httpData.getData().getVersion(), verName) == 1) {
                                    mView.onVersionDataSuccess(httpData.getData());
                                } else {
                                    ToastUtil.showShort(App.INSTANCE.getString(R.string.minesettingPresenter1));
                                }
                            } else {
                                ToastUtil.showShort(App.INSTANCE.getString(R.string.minesettingPresenter1));
                            }
                        }));
    }

}
