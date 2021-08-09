package com.sskj.hangqing.ui.activity;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.base.App;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.rxbus2.Subscribe;
import com.sskj.common.rxbus2.ThreadMode;
import com.sskj.common.util.LanguageUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.widget.DensityWebView;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.GuideListBean;
import com.sskj.hangqing.bean.HelpBean;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.presenter.GuideWebActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.zzhoujay.richtext.RichText;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;

import static com.sskj.common.util.LanguageUtil.getAppLocale;

@Route(path = RConfig.HANG_GUIDE_WEB)
public class GuideWebActivity extends BaseActivity<GuideWebActivityPresenter> {
    @BindView(R2.id.tvContent)
    TextView tvContent;
    @BindView(R2.id.llHead)
    LinearLayout llHead;
    @BindView(R2.id.webView)
    DensityWebView webView;

    @Autowired
    String content;
    @Autowired
    String time;
    @Autowired
    String title;
    @Autowired
    String head;
    @Autowired
    boolean isHelp = false;//帮助中心
    @Autowired
    boolean isRule = false;//交易规则
    @Autowired
    boolean isAnti = false;//交易规则
    @Autowired
    boolean isBibi = false;//币币
    @Autowired
    boolean isFabi = false;//fabi
    @Autowired
    boolean isAboutUs = false;//关于我们
    @Autowired
    boolean isZixun = false;//资讯
    @Autowired
    boolean isPrivate = false;//隐私条款
    @BindView(R2.id.tvWebTitle)
    TextView tvWebTitle;
    @BindView(R2.id.tvTime)
    TextView tvTime;
    @Autowired
    boolean isRegisterAgreement = false;//服务协议
    @Autowired
    boolean isHangqingNotice = false;//行情公告
    @Autowired
    boolean isTradeGuide = false;//交易指南
    @Autowired
    boolean isGuset = false;//客服
    @Autowired
    boolean isQuickBuy = false;//快捷买币
   // boolean isflag = false;
   private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private final static int FILECHOOSER_RESULTCODE = 1;
    private static final String TAG = "GuideWebActivity";
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_guide_web;
    }

    @Override
    public GuideWebActivityPresenter getPresenter() {
        return new GuideWebActivityPresenter();
    }
    public static boolean isNewChinese() {
        return getAppLocale(App.INSTANCE) == Locale.TRADITIONAL_CHINESE;
    }
    @Override
    protected void initView() {
        RxBus.getDefault().register(this);
        ARouter.getInstance().inject(this);


        if (isHelp) {
            setTitle(App.INSTANCE.getString(R.string.hangqingguideWebActivity1));
            llHead.setVisibility(View.GONE);
            tvContent.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
//            webView.setBackgroundColor(0);
//            webView.getBackground().setAlpha(0);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(HttpConfig.BASE_URL + "/help/index.html?language=" + (LanguageUtil.isSimpleChinese() ? "cn" : isNewChinese()?"tw":"eu"));
            webView.setOnKeyListener((v, keyCode, event) -> {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                }
                return false;
            });
        } else if (isBibi) {
            setTitle(App.INSTANCE.getString(R.string.hangqingguideWebActivity2));
            mPresenter.help("11");
        } else if (isFabi) {
            setTitle(App.INSTANCE.getString(R.string.hangqingguideWebActivity3));
            mPresenter.help("10");
        } else if (isAboutUs) {
            setTitle(App.INSTANCE.getString(R.string.hangqingguideWebActivity4));
            mPresenter.aboutUs();
        } else if (isRegisterAgreement) {
            setTitle(App.INSTANCE.getString(R.string.hangqingguideWebActivity5));
            mPresenter.registerAgreement();
        } else if (isPrivate) {
            setTitle(App.INSTANCE.getString(R.string.hangqingguideWebActivity6));
            mPresenter.privateService();
        } else if (isRule) {
            setTitle(App.INSTANCE.getString(R.string.hangqingguideWebActivity7));
            mPresenter.tradeRule();
        }else if (isAnti) {
            setTitle(App.INSTANCE.getString(R.string.hangqingguideWebActivity8));
            mPresenter.tradeAnti();
        } else if (isZixun) {
            setTitle(App.INSTANCE.getString(R.string.hangqingguideWebActivity9));
            updateWebView();
        } else if (isHangqingNotice) {
            setTitle(head);
            updateText();
        } else if (isTradeGuide) {
            setTitle(head);
            updateWebView();
        } else if (isGuset) {
            setTitle(App.INSTANCE.getString(R.string.hangqingguideWebActivity10));
            llHead.setVisibility(View.GONE);
            tvContent.setVisibility(View.GONE);
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
            settings.setSupportZoom(true);
            settings.setDomStorageEnabled(true);
            settings.setAppCacheMaxSize(1024 * 1024 * 8);
            String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
            settings.setAppCachePath(appCachePath);
            settings.setAllowFileAccess(true);
            settings.setAppCacheEnabled(true);
            webView.setVisibility(View.VISIBLE);
            webView.setWebChromeClient(new WebChromeClient(){
                // For Android 3.0+
                public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                    Log.d(TAG, "openFileChoose(ValueCallback<Uri> uploadMsg)");
                    mUploadMessage = uploadMsg;
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("*/*");
                    GuideWebActivity.this.startActivityForResult(Intent.createChooser(i, "File Chooser"),
                            FILECHOOSER_RESULTCODE);
                }

                // For Android 3.0+
                public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                    Log.d(TAG, "openFileChoose( ValueCallback uploadMsg, String acceptType )");
                    mUploadMessage = uploadMsg;
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("*/*");
                    GuideWebActivity.this.startActivityForResult(Intent.createChooser(i, "File Browser"),
                            FILECHOOSER_RESULTCODE);
                }

                // For Android 4.1
                public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                    Log.d(TAG, "openFileChoose(ValueCallback<Uri> uploadMsg, String acceptType, String capture)");
                    mUploadMessage = uploadMsg;
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("*/*");
                    GuideWebActivity.this.startActivityForResult(Intent.createChooser(i, "File Browser"),
                            GuideWebActivity.FILECHOOSER_RESULTCODE);

                }

                // For Android 5.0+
                public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback,
                                                 WebChromeClient.FileChooserParams fileChooserParams) {
                    //  Log.d(TAG, "onShowFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)");
                    mUploadCallbackAboveL = filePathCallback;
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("*/*");
                    GuideWebActivity.this.startActivityForResult(Intent.createChooser(i, "File Browser"),
                            FILECHOOSER_RESULTCODE);
                    return true;
                }
            });
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl("https://tb.53kf.com/code/client/1761f33a99d51c79a288559f4a64ae597/1");
            webView.setOnKeyListener((v, keyCode, event) -> {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                }
                return false;
            });
        } else if (isQuickBuy) {
            setTitle(App.INSTANCE.getString(R.string.hangqingguideWebActivity11));
            /*if (TextUtils.isEmpty(content)) {
                return;
            }*/
            llHead.setVisibility(View.GONE);
            tvContent.setVisibility(View.GONE);
           // initWebView();
            webView.setVisibility(View.VISIBLE);
            String s = SPUtil.get(SPConfig.SIGN,"");
            // 支持获取手势焦点
            webView.loadUrl(HttpConfig.BASE_URL+"/app/otc/epay?token="+ s);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());
            webView.setWebViewClient(new WebViewClient());
           // webView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
        }

    }

    /**
     * 有二级标题时间，WebView展示内容
     */
    @Subscribe(threadMode =ThreadMode.MAIN,code=1989)
    public void getCode(){
        SPUtil.put("isflag",true);


    }
    @Subscribe(threadMode =ThreadMode.MAIN,code=1988)
    public void getOtherCode(){
        SPUtil.put("isflag",false);

    }
    private void updateWebView() {
        tvWebTitle.setText(title);
        tvTime.setText(time);
        tvContent.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
        initWebView();
        if(SPUtil.get("isflag",false)){
             content =
                "<html> \n" +
                        "<head> \n" +
                        "<style type=\"text/css\"> \n" +
                        "body {font-size:50px;\n background:#131625;\n color:#fff}\n" +
                        "</style> \n" +
                        " <style>img{width:100% !important;height:auto}</style>"+
                        "</head> \n" +
                        "<body>" +
                        "<script type='text/javascript'>" +
                        "window.onload = function(){\n" +
                        "var $img = document.getElementsByTagName('img');\n" +
                        "for(var p in  $img){\n" +
                        " $img[p].style.width = '100%%';\n" +
                        "$img[p].style.height ='auto'\n" +
                        "}\n" +
                        "}" +
                        "</script>" +
                        content +
                        "</body>" +
                        "</html>";

        }else {
        content =
                "<html> \n" +
                        "<head> \n" +
                        "<style type=\"text/css\"> \n" +
                        "body {font-size:50px;}\n" +
                        "</style> \n" +
                        " <style>img{width:100% !important;height:auto}</style>"+
                        "</head> \n" +
                        "<body>" +
                        "<script type='text/javascript'>" +
                        "window.onload = function(){\n" +
                        "var $img = document.getElementsByTagName('img');\n" +
                        "for(var p in  $img){\n" +
                        " $img[p].style.width = '100%%';\n" +
                        "$img[p].style.height ='auto'\n" +
                        "}\n" +
                        "}" +
                        "</script>" +
                        content +
                        "</body>" +
                        "</html>";}
        webView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
    }

    /**
     * 有二级标题时间，TextView展示内容
     */
    public void updateText() {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        webView.setVisibility(View.GONE);
        tvWebTitle.setText(title);
        tvTime.setText(time);

        RichText.from(content).into(tvContent);
    }

    private void initWebView() {
        WebSettings mSettings = webView.getSettings();
        // 支持获取手势焦点
        webView.requestFocusFromTouch();
        webView.setHorizontalFadingEdgeEnabled(true);
        webView.setVerticalFadingEdgeEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        // 支持JS
        mSettings.setJavaScriptEnabled(true);
        mSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mSettings.setBuiltInZoomControls(true);
        mSettings.setDisplayZoomControls(true);
        // 支持插件
        mSettings.setPluginState(WebSettings.PluginState.ON);
        mSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 自适应屏幕
        mSettings.setUseWideViewPort(true);
        mSettings.setLoadWithOverviewMode(true);
        // 支持缩放
        mSettings.setSupportZoom(false);//就是这个属性把我搞惨了，
        // 隐藏原声缩放控件
        mSettings.setDisplayZoomControls(false);
        // 支持内容重新布局
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mSettings.supportMultipleWindows();
        mSettings.setSupportMultipleWindows(true);
        // 设置缓存模式
        mSettings.setDomStorageEnabled(true);
        mSettings.setDatabaseEnabled(true);
        mSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        mSettings.setAppCacheEnabled(true);
        mSettings.setAppCachePath(getCacheDir().getAbsolutePath());
        // 设置可访问文件
        mSettings.setAllowFileAccess(true);
        mSettings.setNeedInitialFocus(true);
        mSettings.setBlockNetworkImage(false);
        // 支持自定加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            mSettings.setLoadsImagesAutomatically(true);
        } else {
            mSettings.setLoadsImagesAutomatically(false);
        }
        mSettings.setNeedInitialFocus(true);
        // 设定编码格式
        mSettings.setDefaultTextEncodingName("UTF-8");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request.getUrl().toString().equals(HttpConfig.BASE_URL + "/web/index.html")) {
                    finish();
                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();

        } else {
            super.onBackPressed();
        }
    }

    public void updateHelp(HelpBean data) {
        llHead.setVisibility(View.GONE);
        if (data != null) {
            content = data.getContent();
        } else {
            content = "";
        }
        updateText();
    }

    public void updatePrivate(HelpBean data) {
        llHead.setVisibility(View.GONE);
        if (data != null) {
            content = data.getContent();
        } else {
            content = "";
        }
        updateText();
    }

    public void updateTradeRule(List<HelpBean> data) {
        llHead.setVisibility(View.GONE);
        if (data != null) {
            content = data.get(0).getContent();
        } else {
            content = "";
        }
        updateText();
    }

    public void updateTradeAnti(HelpBean data) {
        llHead.setVisibility(View.GONE);
        if (data != null) {
            content = data.getContent();
        } else {
            content = "";
        }
        updateText();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage && null == mUploadCallbackAboveL)
                return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (mUploadCallbackAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(result);
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
        if (requestCode != FILECHOOSER_RESULTCODE || mUploadCallbackAboveL == null) {
            return;
        }

        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {

            } else {
                String dataString = data.getDataString();
                ClipData clipData = data.getClipData();

                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }

                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        mUploadCallbackAboveL.onReceiveValue(results);
        mUploadCallbackAboveL = null;
        return;
    }
}
