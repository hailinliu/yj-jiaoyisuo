package com.sskj.lib.util;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.sskj.common.log.LogUtil;
import com.sskj.common.util.ToastUtil;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompMessage;

public class MyWebSocketServer extends Flowable<String> {
    private Subscriber<? super String> observer;
    private String baseUrl;
    private String mUrl;
    private StompClient mStompClient;
    public CompositeDisposable compositeDisposable;
    public MyWebSocketServer(String baseUrl,String mUrl){
        this.baseUrl = baseUrl;
        this.mUrl = mUrl;
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP,baseUrl);
        resetSubscriptions();
    }

    private void resetSubscriptions() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void subscribeActual(Subscriber<? super String> subscriber) {
        this.observer = subscriber;

    }
    public void connect(){
                mStompClient.withClientHeartbeat(1000).withServerHeartbeat(1000);
                resetSubscriptions();
                Disposable dispLifecycle = mStompClient.lifecycle()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(lifecycleEvent -> {
                            switch (lifecycleEvent.getType()) {
                                case OPENED:
                                    LogUtil.d("我已经open");
                                    //   toast("Stomp connection opened");
                                    break;
                                case ERROR:
                                    LogUtil.d("我已经error");
                                    //  Log.e(TAG, "Stomp connection error", lifecycleEvent.getException());
                                    //toast("Stomp 连接错误");
                                    mStompClient.reconnect();
                                    break;
                                case CLOSED:
                                    LogUtil.d("我已经closed");
                                    //   toast("Stomp connection closed");
                                    resetSubscriptions();
                                    break;
                                case FAILED_SERVER_HEARTBEAT:
                                    LogUtil.d( "我已经FAILED_SERVER_HEARTBEAT");
                                  //  toast("Stomp failed server heartbeat");
                                    break;
                            }
                        });

           //     compositeDisposable.add(dispLifecycle);
// Receive greetings
                Disposable dispTopic = mStompClient.topic(mUrl)
                        //Disposable dispTopic = mStompClient.topic("/topic")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((StompMessage topicMessage) -> {
                            MyWebSocketServer.this.observer.onNext(topicMessage.getPayload());
                            //Log.d(TAG, "Received " + topicMessage.getPayload());
                            //DataModel response1 = mGson.fromJson(topicMessage.getPayload(), DataModel.class);
                            //添加你的数据逻辑

                        },throwable -> {
                            LogUtil.d("错误数据:"+throwable.getMessage());
                        });

                compositeDisposable.add(dispTopic);

                mStompClient.connect();



    }
    public void disconnectStomp(){
        mStompClient.disconnect();
        if (compositeDisposable != null)
            compositeDisposable.dispose();
    }
    private void toast(String text) {
        ToastUtil.showShort(text);
    }

}
