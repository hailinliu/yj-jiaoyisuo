package com.sskj.lightning.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.sskj.common.base.App;
import com.sskj.common.base.HttpData;
import com.sskj.common.http.WebSocketObserver;
import com.sskj.lib.bean.AppVersionBean;
import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.GonggaoBean;
import com.sskj.lib.util.APKVersionCodeUtils;
import com.sskj.lib.util.MyWebSocketServer;

import okhttp3.WebSocket;

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

   /* public MyWebSocketServer pushCoinType() {

        MyWebSocketServer socketServer = new MyWebSocketServer(HttpConfig.WS_BASE_URL,"/topic/market/kline/BTC/USDT");
        socketServer.connect();
        return socketServer;
       *//* return new WebSocketObserver(HttpConfig.WS_BASE_URL) {
            @Override
            public void onOpen(WebSocket webSocket, okhttp3.Response response, String msg) {
                webSocket.send("{\"type\":\"goods_list_all\"}");
            }
        };*//*
    }
    public MyWebSocketServer pushCoinType1() {
        MyWebSocketServer socketServer = new MyWebSocketServer(HttpConfig.WS_BASE_URL,"/topic/market/kline/BTC/USDT");
        socketServer.connect();
        return socketServer;
    }*/
    public MyWebSocketServer pushCoin() {
        MyWebSocketServer socketServer = new MyWebSocketServer(HttpConfig.WS_BASE_URL,"/topic/market/thumb");
        socketServer.connect();
        return socketServer;
    }
    public MyWebSocketServer pushCoin1() {
        MyWebSocketServer socketServer = new MyWebSocketServer("wss://www.yolocoin.uk/level-market/level-market-ws/websocket","/topic/level/thumb");
        socketServer.connect();
        return socketServer;
    }
    public PostRequest<HttpData<GonggaoBean>> getGonggao() {
        return OkGo.<HttpData<GonggaoBean>>post(HttpConfig.BASE_URL + HttpConfig.GET_GONGGAO)
                ;
    }

    public PostRequest<HttpData<AppVersionBean>> getVersion() {
        return OkGo.<HttpData<AppVersionBean>>post(HttpConfig.BASE_URL + HttpConfig.VERSION);



    }


}