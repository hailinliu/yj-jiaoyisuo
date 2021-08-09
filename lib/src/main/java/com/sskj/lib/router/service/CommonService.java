package com.sskj.lib.router.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.bulong.rudeness.RudenessScreenHelper;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.utils.EncryptUtils;
import com.lzy.okgo.utils.RsaUtil;
import com.sskj.common.base.App;
import com.sskj.common.base.BaseActivityLifecycleCallbacks;
import com.sskj.common.box.glide.HttpsUtil;
import com.sskj.common.log.HttpLogger;
import com.sskj.common.log.LogUtil;
import com.sskj.lib.util.EncodeUtils;

import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;


@Route(path = "/lib/commonService")
public class CommonService implements IProvider {
    @Override
    public void init(Context context) {

        new RudenessScreenHelper(App.INSTANCE, 750).activate();

        Toasty.Config.getInstance()
                .apply(); // required
    }


    public void initOkGoHttp(Context context, boolean isDebug) {
        LogUtil.init(isDebug);
        Stetho.initializeWithDefaults(context);
        CookieJarImpl cookieJar = new CookieJarImpl(new SPCookieStore(context));
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        try {
            HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
            builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
//            setSSL(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.addNetworkInterceptor(new StethoInterceptor())
                .cookieJar(cookieJar)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .hostnameVerifier(new HttpsUtil.UnSafeHostnameVerifier())
//                .addInterceptor(new CookieInterceptor(cookieJar))
        ;
        if (isDebug) {
            setColorfulLog(builder);
        }
        OkHttpClient client = builder.build();
        OkGo.getInstance().init(App.INSTANCE)//必须调用初始化
                .setEncrypt(false)
                .encryptStratage(EncodeUtils::encodeAES2B)
                .setOkHttpClient(client)//必须设置OkHttpClient
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(0);
    }

    private void setColorfulLog(OkHttpClient.Builder builder) {
        okhttp3.logging.HttpLoggingInterceptor logInterceptor = new okhttp3.logging.HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logInterceptor);
    }

    private void setWithLog(OkHttpClient.Builder builder) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
    }

    private void setSSL(OkHttpClient.Builder builder) throws Exception {
        X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    X509Certificate[] chain,
                    String authType) {
            }

            @Override
            public void checkServerTrusted(
                    X509Certificate[] chain,
                    String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        TrustManager[] trustAllCerts = new TrustManager[]{x509TrustManager};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        HostnameVerifier hostnameVerifier = (hostname, session) -> true;
        builder.sslSocketFactory(sslContext.getSocketFactory(), x509TrustManager)
                .hostnameVerifier(hostnameVerifier);
    }

}
