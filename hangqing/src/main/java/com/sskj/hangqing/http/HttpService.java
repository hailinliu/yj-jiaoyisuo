package com.sskj.hangqing.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.sskj.common.base.App;
import com.sskj.common.base.HttpData;
import com.sskj.common.http.WebSocketObserver;
import com.sskj.common.util.LanguageUtil;
import com.sskj.hangqing.bean.CountryBean;
import com.sskj.hangqing.bean.GuideListBean;
import com.sskj.hangqing.bean.HangqingBannerBean;
import com.sskj.hangqing.bean.HelpBean;
import com.sskj.hangqing.bean.NoticeBean;
import com.sskj.hangqing.bean.SelfCoinBean;
import com.sskj.hangqing.bean.Stock;
import com.sskj.hangqing.bean.SummaryBean;
import com.sskj.hangqing.bean.TradeBean;
import com.sskj.hangqing.bean.ZixunListBean;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.bean.PageBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.WSFiveBean;
import com.sskj.lib.util.CommonUtil;
import com.sskj.lib.util.MyWebSocketServer;

import java.util.List;

import okhttp3.WebSocket;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-15 14:08
 */
public class HttpService {
    public MyWebSocketServer pushCoin() {
        MyWebSocketServer socketServer = new MyWebSocketServer(HttpConfig.WS_BASE_URL,"/topic/market/thumb");
        socketServer.connect();
        return socketServer;
    }
    public MyWebSocketServer pushCoin1() {
        MyWebSocketServer socketServer = new MyWebSocketServer("wss://www.bitflnex.pro/market/market-ws/websocket","/topic/level/thumb");
        socketServer.connect();
        return socketServer;
    }
    /**
     * 支持国籍
     */
    public PostRequest<BaseBean<List<CountryBean>>> getCountry(){
        return OkGo.<BaseBean<List<CountryBean>>>post(HttpConfig.BASE_URL+HttpConfig.SUPPERTCOUNTRY);
    }
    /**
     * 关闭谷歌验证
     */
    public PostRequest<BaseBean> unbindGoogle(String code){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.UNBINDGOOGLE)
                .params("code",code);
    }
    /**
     * 谷歌绑定
     */
    public PostRequest<BaseBean> bindGoogle(String codes,String secret){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.ABINDGOOGLE)
                .params("codes",codes)
                .params("secret",secret);
    }
    /**
     * 生成谷歌验证码
     */
    public GetRequest<BaseBean> getGoogleCode(){
        return OkGo.<BaseBean>get(HttpConfig.BASE_URL+HttpConfig.GOOGLECODE);
    }
    /**
     * 更改邮箱验证码
     */
    public PostRequest<BaseBean> getUpdateEmailCode(String email){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.UPDATEBINDEMAILCODE)
                .params("email",email);
    }
    /**
     * 更改邮箱
     */
    public PostRequest<BaseBean> updateBindEmail(String newEmail,String newEmailCode){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.UPDATEBINDEMAIL)
                .params("newEmailCode",newEmailCode)
                .params("newEmail",newEmail);
    }
    /**
     * 绑定邮箱验证码
     */
    public PostRequest<BaseBean> getEmailCode(String email){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.BINDEMAILCODE)
                .params("email",email);
    }
    /**
     * 绑定邮箱
     */
    public PostRequest<BaseBean> bindEmail(String email,String code){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.BINDEMAIL)
                .params("code",code)
                .params("email",email);
    }
    /**
     * 更改手机号验证码
     */
    public PostRequest<BaseBean> getUpdatePhoneCode(String country ,String phone){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.UPDATEBINDPHONECODE)
                .params("country",country)
                .params("phone",phone);
    }
    /**
     * 修改手机号
     */
    public PostRequest<BaseBean> updateBindPhone(String phone,String code){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.UPDATEBINDPHONE)
                .params("code",code)
                .params("phone",phone);
    }
    /**
     * 绑定手机号
     */
    public PostRequest<BaseBean> bindPhone(String country,String phone,String code){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.BINDPHONE)
                .params("country",country)
                .params("code",code)
                .params("phone",phone);
    }
    /*
    *安全设置总信息
     */
    public GetRequest<BaseBean<SafeSettingBean>> getSecurity(){
        return OkGo.<BaseBean<SafeSettingBean>>get(HttpConfig.BASE_URL+HttpConfig.SECURITYSETTING);
    }
    /**
     * 绑定手机号验证码
     */
    public PostRequest<BaseBean> getPhoneCode(String country,String phone){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.BINDPHONECODE)
                .params("country",country)
                .params("phone",phone);
    }
    /**
     * 修改资金密码手机验证码
     * @param email
     * @return
     */
    public PostRequest<BaseBean> updatePwdTradCode(String email){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.UPDATAPWDTRAD);
    }
    public PostRequest<BaseBean> updatePwdCodeTrademail(String email){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.UPDATAPWDTRADEMAIL);
    }
    public PostRequest<BaseBean> updatePwdtrad(String newPassword,String code){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.UPDATETRADPWD)
                .params("jyPassword",newPassword,true)
                .params("code",code);
    }
    public PostRequest<BaseBean> updatePwdtrad1(String newPassword,String code){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.UPDATETRADPWD1)
                .params("jyPassword",newPassword,true)
                .params("code",code);
    }
    public PostRequest<BaseBean> updatePwd(String newPassword,String code){
    return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.UPDATAPWD)
            .params("newPassword",newPassword,true)
            .params("code",code);
    }
    public PostRequest<BaseBean> updatePwdCode(String email){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.UPDATECODE);
    }
    public PostRequest<BaseBean> updatePwdCodeemail(String email){
        return OkGo.<BaseBean>post(HttpConfig.BASE_URL+HttpConfig.UPDATECODEEMAIL);
    }
    public GetRequest<BaseBean> getRate(String fromUnit, String toUnit){
        return OkGo.<BaseBean>get(HttpConfig.BASE_URL+HttpConfig.GET_RATE+fromUnit+"-"+toUnit);
    }

    /**
     * 行情数据
     *
     * @param code 标识符
     * @return
     */
   /* public GetRequest<HttpData<List<CoinBean1>>> getProduct(String code) {
        return OkGo.<HttpData<List<CoinBean1>>>get(HttpConfig.BASE_URL1 + HttpConfig.GET_PRODUCT)
             ;
               //.params("type", 3);
    }*/
    public GetRequest<String> getProduct2(String code) {
        return OkGo.<String>get(HttpConfig.BASE_URL1 + HttpConfig.GET_PRODUCT)
                ;
        //.params("type", 3);
    }
    public GetRequest<String> getProduct1(String code) {
        return OkGo.<String>get(HttpConfig.BASE_URL + "/market/symbol-thumb");
        //.params("type", 3);
    }
    /**
     *
     * @param code 标识符
     * @return
     */
    public GetRequest<HttpData<List<CoinBean1>>> getProduct(String code, String type) {
        return OkGo.<HttpData<List<CoinBean1>>>get(HttpConfig.BASE_URL + HttpConfig.GET_PRODUCT)
                .params("code", CommonUtil.dealReuqestCode(code))
                .params("type", type);
    }

    /**
     * 自选币列表
     *
     * @param code 标识符
     * @return
     */
    public GetRequest<HttpData<PageBean<SelfCoinBean>>> getSelfCoinList(String code, String page) {
        return OkGo.<HttpData<PageBean<SelfCoinBean>>>get(HttpConfig.BASE_URL + HttpConfig.GET_SELF_COIN)
                .params("code", CommonUtil.dealReuqestCode(code))
                .params("pageNumber", page)
                .params("pageSize", 20);
    }

    /**
     * 添加删除自选币
     *
     * @param id
     * @param status
     * @return
     */
    public PostRequest<HttpData<PageBean<SelfCoinBean>>> editSelfCoin(String id, String status) {
        return OkGo.<HttpData<PageBean<SelfCoinBean>>>post(HttpConfig.BASE_URL + HttpConfig.EDIT_SELF_COIN)
                .params("id", id)
                .params("status", status);
    }


    /**
     * 获取k线数据
     *
     * @param code      币种
     * @return
     */
    public GetRequest<String> getKData(long from,String resolution, String code,long to,boolean isLevel) {
        if(isLevel){
            return OkGo.<String>get(HttpConfig.BASE_URL1 + "/market/history")
                    .params("from", from)
                    .params("resolution", resolution)
                    .params("symbol", CommonUtil.dealReuqestCode(code))
                    .params("to",to)
                    ;
        }
        return OkGo.<String>get(HttpConfig.BASE_URL1 + HttpConfig.GET_K_DATA1)
                .params("from", String.valueOf(from))
                .params("resolution", resolution)
                .params("symbol", CommonUtil.dealReuqestCode(code))
                .params("to",String.valueOf(to))
                ;

    }


    /**
     * 获取轮播图及公告
     *
     * @return
     */
    public PostRequest<BaseBean<List<HangqingBannerBean>>> getBanner1(String sysAdvertiseLocation) {
        return OkGo.<BaseBean<List<HangqingBannerBean>>>post(HttpConfig.BASE_URL + HttpConfig.GET_BANNER)
                .params("sysAdvertiseLocation", sysAdvertiseLocation)

                ;
    }

    public PostRequest<HttpData<List<HangqingBannerBean>>> getBanner(String sysAdvertiseLocation) {
        return OkGo.<HttpData<List<HangqingBannerBean>>>post(HttpConfig.BASE_URL + HttpConfig.GET_BANNER)
                .params("sysAdvertiseLocation", sysAdvertiseLocation)

                ;
    }
    /**
     * 实时成交
     *
     * @param code
     * @return
     */
    public WebSocketObserver pushCoinTrade(String code,int type) {
if(type==1){
    return new WebSocketObserver(HttpConfig.WS_BASE_URL) {
        @Override
        public void onOpen(WebSocket webSocket, okhttp3.Response response, String msg) {
            if (msg == null) {
                webSocket.send("{\"type\":\"trader_info\",\"list\":[\"" + code + "\"]}");
            } else {
                webSocket.send(msg);
            }
        }
    };
}
        return new WebSocketObserver(HttpConfig.WS_BASE_URL) {
            @Override
            public void onOpen(WebSocket webSocket, okhttp3.Response response, String msg) {
                if (msg == null) {
                    webSocket.send("{\"type\":\"trader_info\",\"list\":[\"" + code + "\"]}");
                } else {
                    webSocket.send(msg);
                }
            }
        };
    }


    public PostRequest<String> getSummary(String code) {

            return OkGo.<String>post(HttpConfig.BASE_URL + HttpConfig.GET_SUMMARY1)
                    .params("symbol", code)
                    ;

      /*  return OkGo.<HttpData<SummaryBean>>get(HttpConfig.BASE_URL + HttpConfig.GET_SUMMARY)
                .params("stockCode", CommonUtil.dealReuqestCode(code))
                ;*/
    }
    public GetRequest<String> getPankou(String code) {
        return OkGo.<String>get(HttpConfig.BASE_URL + HttpConfig.GET_PANKOU)
                .params("symbol", CommonUtil.dealReuqestCode(code));

    }
    public GetRequest<HttpData<WSFiveBean>> getDeepData(String code,int type) {
        if(type==1){
            return OkGo.<HttpData<WSFiveBean>>get(HttpConfig.BASE_URL + HttpConfig.GET_DEEP1)
                    .params("code", CommonUtil.dealReuqestCode(code))
                    ;
        }
        return OkGo.<HttpData<WSFiveBean>>get(HttpConfig.BASE_URL + HttpConfig.GET_DEEP)
                .params("code", CommonUtil.dealReuqestCode(code))
                ;
    }

    public GetRequest<HttpData<PageBean<TradeBean>>> getAllTrade(String code,int type) {
        if(type==1){
            return OkGo.<HttpData<PageBean<TradeBean>>>get(HttpConfig.BASE_URL + HttpConfig.GET_ALL_TRADE1)
                    .params("stockCode", CommonUtil.dealReuqestCode(code))
                    .params("num", 15)
                    ;
        }
        return OkGo.<HttpData<PageBean<TradeBean>>>get(HttpConfig.BASE_URL + HttpConfig.GET_ALL_TRADE)
                .params("stockCode", CommonUtil.dealReuqestCode(code))
                .params("num", 15)
                ;
    }

    public PostRequest<HttpData<PageBean<ZixunListBean>>> getZixunList(String page) {
        return OkGo.<HttpData<PageBean<ZixunListBean>>>post(HttpConfig.BASE_URL + HttpConfig.GET_ZIXUN_LIST)
                .params("pageNumber", page)
                .params("pageSize", 30)
                ;
    }

    public PostRequest<HttpData<PageBean<GuideListBean>>> getGuideList(String page) {
        return OkGo.<HttpData<PageBean<GuideListBean>>>post(HttpConfig.BASE_URL + HttpConfig.GET_GUIDE_LIST)
                .params("type", 2)
                .params("PageNumber", page)
                .params("PageSize", 20)
                ;
    }


    public WebSocketObserver pushCoinFiveDeep(String code,int type) {
        if(type==1){
            return new WebSocketObserver(HttpConfig.WS_BASE_URL) {
                @Override
                public void onOpen(WebSocket webSocket, okhttp3.Response response, String msg) {
                    if (msg == null) {
                        webSocket.send("{\"type\":\"depth_list\",\"list\":[\"" + code + "\"]}");
                    } else {
                        webSocket.send(msg);
                    }
                }
            };
        }
        return new WebSocketObserver(HttpConfig.WS_BASE_URL) {
            @Override
            public void onOpen(WebSocket webSocket, okhttp3.Response response, String msg) {
                if (msg == null) {
                    webSocket.send("{\"type\":\"depth_list\",\"list\":[\"" + code + "\"]}");
                } else {
                    webSocket.send(msg);
                }
            }
        };
    }


    public PostRequest<HttpData<HelpBean>> help(String type) {
        return OkGo.<HttpData<HelpBean>>post(HttpConfig.BASE_URL + HttpConfig.HELP)
                .params("type", type)
                ;
    }

    public GetRequest<HttpData<HelpBean>> aboutUs() {
        return OkGo.<HttpData<HelpBean>>get(HttpConfig.BASE_URL + HttpConfig.ABOUT_US)
                ;
    }

    public GetRequest<HttpData<HelpBean>> registerAgreement() {
        return OkGo.<HttpData<HelpBean>>get(HttpConfig.BASE_URL + HttpConfig.REGISTER_AGREEMENT)
                ;
    }

    public GetRequest<HttpData<HelpBean>> privateService() {
        return OkGo.<HttpData<HelpBean>>get(HttpConfig.BASE_URL + HttpConfig.PRIVATE_SERVICE)
                ;
    }
    public GetRequest<HttpData<List<HelpBean>>> tradeRule() {
        return OkGo.<HttpData<List<HelpBean>>>get(HttpConfig.BASE_URL + HttpConfig.TRADE_RULE)
                ;
    }
    public GetRequest<HttpData<HelpBean>> tradeAnti() {
        return OkGo.<HttpData<HelpBean>>get(HttpConfig.BASE_URL + HttpConfig.TRADE_ANTI)
                ;
    }

    public PostRequest<BaseBean<NoticeBean>> getNotice(String p, String size) {
        return OkGo.<BaseBean<NoticeBean>>post(HttpConfig.BASE_URL + HttpConfig.GET_NOTICE)
                .params("pageNumber", p)
                .params("pageSize", size)
                ;
    }

    public GetRequest<HttpData<List<String>>> getHorization() {
        return OkGo.<HttpData<List<String>>>get(HttpConfig.BASE_URL + HttpConfig.CODO_HORIZATION_LIST);
    }
}