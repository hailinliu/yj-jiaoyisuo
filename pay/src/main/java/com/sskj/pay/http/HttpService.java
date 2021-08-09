package com.sskj.pay.http;


import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.SPConfig;
import com.sskj.lib.bean.UploadBean;
import com.sskj.lib.bean.enums.PayTypeEnum;
import com.sskj.pay.bean.PayTypeResultBean;

import java.io.File;
import java.util.List;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-15 14:08
 */
public class HttpService {


    //上传图片
    public PostRequest<HttpData<String>> upload(String filePath) {
        return OkGo.<HttpData<String>>post(HttpConfig.BASE_URL + HttpConfig.UPLOAD_FILE)
                .params("file", new File(filePath));

    }

    public PostRequest<HttpData> addOrEditPayType(boolean isAdd, String type, String username, String account, String img, String bank, String branch, String pwd) {
        PostRequest<HttpData> params = OkGo.<HttpData>post(HttpConfig.BASE_URL + (isAdd ? HttpConfig.ADD_PAY_TYPE : HttpConfig.EDIT_PAY_TYPE))
                .params("id", isAdd ? SPUtil.get(SPConfig.ID, "") : null)
                .params("payWay", type)
                .params("username", username)
                .params("dealPswd", pwd, true)
                .params("wxAccount", account)
                .params("alipayAccount", account)
                .params("bankCardNo", account)
                .params("bankCardOpenBank", bank)
                .params("bankCardUnionNo", branch);
        if (TextUtils.isEmpty(img)) {
            return params;
        } else {
            if (type.equals(PayTypeEnum.WX.getType())) {
                return params
                        .params("wxImg", img)
                        ;
            } else if (type.equals(PayTypeEnum.ALIPAY.getType())) {
                return params
                        .params("aliImg", img)
                        ;
            }
            return params;
        }
    }

    public PostRequest<HttpData<List<PayTypeResultBean>>> getPayType() {
        return OkGo.<HttpData<List<PayTypeResultBean>>>post(HttpConfig.BASE_URL + HttpConfig.PAY_TYPE)

                ;
    }

    public PostRequest<HttpData> togglePayType(String status, String id) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.TOGGLE_PAY_TYPE)
                .params("userId", SPUtil.get(SPConfig.ID, ""))
                .params("payId", id)
                .params("flag", status)

                ;
    }
}
