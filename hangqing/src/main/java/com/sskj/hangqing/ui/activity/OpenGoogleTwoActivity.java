package com.sskj.hangqing.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.JsonObject;
import com.sskj.common.log.LogUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.OpenGoogleActivityPresenter;
import com.sskj.hangqing.presenter.OpenGoogleTwoActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.util.QRCodeUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

@Route(path = RConfig.OPENGOOGLETWOACTIVITY)
public class OpenGoogleTwoActivity extends BaseActivity<OpenGoogleTwoActivityPresenter> {
    @BindView(R2.id.tv_secret)
    TextView tv;
    @BindView(R2.id.ll_fuzhi)
    LinearLayout ll_fuzhi;
    @BindView(R2.id.image)
    ImageView imageView;
    @BindView(R2.id.logout_btn)
    Button logout_btn;
    @BindView(R2.id.logout_btn1)
    Button logout_btn1;
    //剪切板管理工具类
    private ClipboardManager mClipboardManager;
    //剪切板Data对象
    private ClipData mClipData;
    private String secret;

    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_opengoogletwo;
    }

    @Override
    protected void initView() {
       // super.initView();
        ARouter.getInstance().inject(this);
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        ll_fuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个新的文本clip对象
                mClipData = ClipData.newPlainText("Simple test", secret);
                //把clip对象放在剪贴板中
                mClipboardManager.setPrimaryClip(mClipData);
                Toast.makeText(getApplicationContext(), "文本已经复制成功！",
                        Toast.LENGTH_SHORT).show();
            }
        });
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RConfig.OPENGOOGLETHIRDACTIVITY).withString("secret",secret).navigation();
            }
        });
        logout_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
               // ARouter.getInstance().build(RConfig.OPENGOOGLETHIRDACTIVITY).navigation();
            }
        });
    }

    @Override
    protected void initData() {
        //super.initData();
        mPresenter.getGoogleCode();
    }

    @Override
    public OpenGoogleTwoActivityPresenter getPresenter() {
        return new OpenGoogleTwoActivityPresenter();
    }

    public void updataUI(String data) {
            LogUtil.d("数据:"+data);
            String[] strings = data.split(",");
            String link = strings[0].split("link=")[1];
            secret = strings[1].split("=")[1];
           secret= secret.substring(0,secret.length()-1);
         //  String newdata = data.replaceAll("=",":");

           // JSONObject jsonObject = new JSONObject(link);
           // String url =  jsonObject.getString("link");
           // String secret = jsonObject.getString("secret");
            QRCodeUtil.createImage(link, 100, new QRCodeUtil.OnEncodeQRCodeCallback() {
                @Override
                public void onAnalyzeSuccess(Bitmap bitmap) {
                   imageView.setImageBitmap(bitmap);
                }

                @Override
                public void onAnalyzeFailed() {

                }
            });
            tv.setText(secret);

        //JsonObject object = new JsonObject(data);
    }
}
