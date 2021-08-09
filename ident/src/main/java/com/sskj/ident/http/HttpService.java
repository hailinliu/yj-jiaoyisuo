package com.sskj.ident.http;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;
import com.sskj.common.base.HttpData;
import com.sskj.lib.http.CallBackOption;

import java.io.File;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-15 14:08
 */
public class HttpService {


    public PostRequest<HttpData> verificationFirst(String username, String idCardNo) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.VERIFICATION_FIRST)
                .params("username", username)
                .params("idCardNo", idCardNo);
    }

    /**
     * 高级认证
     *
     * @param front_img    身份证正面
     * @param back_img     身份证反面
     * @param handheld_img 手持身份证照
     * @return
     */
    public PostRequest<HttpData> apiAuthenticationAdvancedcertification(String front_img, String back_img, String handheld_img) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.API_AUTHENTICATION_ADVANCEDCERTIFICATION)
                .params("idCardFrontImg", front_img)
                .params("idCardBackImg", back_img)
                .params("selfieImg", handheld_img)
                ;
    }

    public PostRequest<HttpData<String>>  submitVerify(File file) {
       return OkGo.<HttpData<String>>post(HttpConfig.BASE_URL + HttpConfig.HIGH_VERIFY_IMG)
                .params("file", file);
    }
}