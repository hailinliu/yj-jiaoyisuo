package com.sskj.lib.router.provider;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.sskj.lib.base.IPresenter;
import com.sskj.lib.bean.enums.CodeTypeEnum;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-09-20 21:50
 */
public interface SendSmsProvider extends IProvider {
    void send(IPresenter iPresenter, String mobile, String areaCode, String vcKey,CodeTypeEnum type, String sliderCode, OnSend onSend);

    interface OnSend {
        void onSuccess();

    }
}
