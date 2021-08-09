package com.sskj.mine.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.sskj.common.base.App;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.SPConfig;
import com.sskj.lib.bean.AppVersionBean;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.util.APKVersionCodeUtils;
import com.sskj.mine.bean.CustomerBean;
import com.sskj.mine.bean.DataBean;
import com.sskj.mine.bean.ExchangeRecordBean;
import com.sskj.mine.bean.GoogleCode;
import com.sskj.mine.bean.MineShopBean;
import com.sskj.mine.bean.MyAssertBean;
import com.sskj.mine.bean.MyAssertDetailBean;
import com.sskj.mine.bean.QuestionBean;
import com.sskj.mine.bean.TwoCodeBean;

import java.math.BigDecimal;
import java.util.concurrent.Executor;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-15 14:08
 */
public class HttpService {
    public GetRequest<BaseBean> getRate(String fromUnit, String toUnit){
        return OkGo.<BaseBean>get(HttpConfig.BASE_URL+HttpConfig.GET_RATE+fromUnit+"-"+toUnit);
    }
    //推广
    public PostRequest<HttpData<DataBean>> getPromotion(String id) {
        return OkGo.<HttpData<DataBean>>post(HttpConfig.BASE_URL + HttpConfig.GETPROMOTION)
                .params("userId", id);
    }
    //获取二维码
    public GetRequest<HttpData> getErweiMa(String num) {
        return OkGo.<HttpData>get(HttpConfig.BASE_URL + HttpConfig.GETERWEIMA)
                .params("number", num);
    }
    //我的客户
    public GetRequest<HttpData<CustomerBean>> getCustomer(String id) {
        return OkGo.<HttpData<CustomerBean>>get(HttpConfig.BASE_URL + HttpConfig.GETCUSTOMER)
                .params("id", id);
    }
    //资产兑换
    public PostRequest<HttpData> getAssertsExchange(String id,String baseCode,String dealCode,String dealNum,String dealPswd) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.GETASSERTSEXCHANGE)
                .params("id", id)
                .params("baseCode", baseCode)
                .params("dealCode", dealCode)
                .params("dealNum", dealNum)
                .params("dealPswd", dealPswd,true);
    }
//兑换记录
public GetRequest<HttpData<ExchangeRecordBean>> getChangeRecord(String id) {
    return OkGo.<HttpData<ExchangeRecordBean>>get(HttpConfig.BASE_URL + HttpConfig.GETASSERTSEXCHANGERECORD)
            .params("id", id);
}

    //获取划转资产这块儿
    public GetRequest<HttpData<MyAssertBean>> getAsserts(String id, int type) {
        return OkGo.<HttpData<MyAssertBean>>get(HttpConfig.BASE_URL + HttpConfig.GETASSERTS)
                .params("id", id)
                .params("type", type);
    }
    public PostRequest<HttpData> transfersubmit(String id, int baseType, int dealType, String code, String num) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.TRANSFERSUBMIT)
                .params("id", id)
                .params("baseType", baseType)
                .params("dealType", dealType)
                .params("code", code)
                .params("num", num);
    }
    public GetRequest<HttpData<MyAssertDetailBean>> getAssertsDetails(String id, String code, int pageNumber, int pageSize, int walletId) {
        return OkGo.<HttpData<MyAssertDetailBean>>get(HttpConfig.BASE_URL + HttpConfig.GETASSERTSDETAILS)
                .params("id", id)
                .params("code", code)
                .params("pageNumber", pageNumber)
                .params("pageSize", pageSize)
                .params("walletId", walletId);
    }

    public PostRequest<HttpData> feedBack(String content, String contact) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.FEEDBACK)
                .params("id", SPUtil.get(SPConfig.ID, ""))
                .params("content", content)
                .params("contactInformation", contact);
    }

    public GetRequest<HttpData<PageBean<QuestionBean>>> getQuestionList(int page) {
        return OkGo.<HttpData<PageBean<QuestionBean>>>get(HttpConfig.BASE_URL + HttpConfig.QUESTION_LIST)
                .params("pageNumber", page)
                .params("pageSize", 10);
    }



    public PostRequest<HttpData> bindGoogle(String chromeCode, String authCode, String secret, String qrcode) {
        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.BINDCHROME)
                .params("code", chromeCode)
                .params("stockUserId", SPUtil.get(SPConfig.ID, ""))
                .params("telCode", authCode)
                .params("secret", secret)
                .params("qrcode", qrcode)
                .params("areaCode", SPUtil.get(SPConfig.AREA_CODE, "").replace("+", ""));

    }

    public PostRequest<HttpData<GoogleCode>> googleQr() {
        return OkGo.<HttpData<GoogleCode>>post(HttpConfig.BASE_URL + HttpConfig.GOOGLE_QR);
    }

    public PostRequest<HttpData> bindMobile(String mobile, String code, String tpwd, String areaCode) {

        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.BIND_MOBILE_OR_EMAIL)
                .params("account", mobile) // 手机号
                .params("code", code)  // 验证码
                .params("dealPswd", tpwd, true)
                .params("areaCode", areaCode.replace("+", ""));
    }

    public PostRequest<HttpData> updateMobile(String mobile, String id, String tpwd) {

        return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.UPDATE_MOBILE_OR_EMAIL)
                .params("account", mobile) // 手机号
                .params("id", id) // 手机号
                .params("tradePswd", tpwd, true);
    }

    public GetRequest<HttpData<String>> getContract() {
        return OkGo.<HttpData<String>>get(HttpConfig.BASE_URL + HttpConfig.CONTRACT);

    }

    public PostRequest<HttpData<AppVersionBean>> checkVersion() {
        return OkGo.<HttpData<AppVersionBean>>post(HttpConfig.BASE_URL + HttpConfig.VERSION)
                .params("type", "2");
    }

    public GetRequest<HttpData> cancelShop() {
        return OkGo.<HttpData>get(HttpConfig.BASE_URL + HttpConfig.SHOPCANCEL)
                .params("id", SPUtil.get(SPConfig.ID, ""));
    }

    public GetRequest<HttpData> openGoogleVerify(String code) {
        return OkGo.<HttpData>get(HttpConfig.BASE_URL + HttpConfig.OPENCHROME)
                .params("code", code)
                .params("stockUserId", SPUtil.get(SPConfig.ID, ""));
    }

    public GetRequest<HttpData> closeGoogleVerify(String code, String telCode) {
        return OkGo.<HttpData>get(HttpConfig.BASE_URL + HttpConfig.CLOSECHROME)
                .params("code", code)
                .params("stockUserId", SPUtil.get(SPConfig.ID, ""))
                .params("telCode", telCode);
    }

    public GetRequest<HttpData> untriedGoogleVerify(String code, String telCode) {
        return OkGo.<HttpData>get(HttpConfig.BASE_URL + HttpConfig.UNBINDCHROME)
                .params("code", code)
                .params("stockUserId", SPUtil.get(SPConfig.ID, ""))
                .params("telCode", telCode)
                .params("areaCode", SPUtil.get(SPConfig.AREA_CODE, "").replace("+", ""));
    }

    public PostRequest<HttpData<MineShopBean>> getShopMoney() {
        return OkGo.<HttpData<MineShopBean>>post(HttpConfig.BASE_URL + HttpConfig.GETMONEY);

    }

    public PostRequest<HttpData<Object>> applyShop() {
        return OkGo.<HttpData<Object>>post(HttpConfig.BASE_URL + HttpConfig.SHOPVERIFY);
    }

    public PostRequest<HttpData> setLoginPwd(String newPwd, String oldPwd) {

        return OkGo.<HttpData>post(HttpConfig.BASE_URL + (HttpConfig.SET_LOGIN_PWD))
                .params("pswd", newPwd, true)
                .params("oldPswd", oldPwd, true);
    }

    public PostRequest<HttpData> setFundPwd(String code,String pwd) {
        String account = SPUtil.get(SPConfig.USER_ACCOUNT, "");
       return OkGo.<HttpData>post(HttpConfig.BASE_URL + HttpConfig.SETFUNDPWD)
                .params("id", SPUtil.get(SPConfig.ID, ""))
                .params("code", code)
                .params("areaCode",account.contains("@")?null:SPUtil.get(SPConfig.AREA_CODE,"").replace("+",""))
                .params("account", SPUtil.get(SPConfig.USER_ACCOUNT, ""))
                .params("dealPswd", pwd,true);
    }

    public GetRequest<HttpData<TwoCodeBean>> getData(String id) {
        return OkGo.<HttpData<TwoCodeBean>>get(HttpConfig.BASE_URL + (HttpConfig.GET_TWO_CODE))
                .params("id",id)
                ;
    }
}