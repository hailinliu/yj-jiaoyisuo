package com.sskj.hangqing.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.OpenGoogleActivityPresenter;
import com.sskj.hangqing.presenter.OpenGoogleThirdActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;

import butterknife.BindView;

@Route(path = RConfig.OPENGOOGLETHIRDACTIVITY)
public class OpenGoogleThirdActivity extends BaseActivity<OpenGoogleThirdActivityPresenter> {
    @Autowired
    String secret;
    @BindView(R2.id.ll_miyao)
    LinearLayout ll_miyao;
    @BindView(R2.id.tv_miyao)
    TextView tv_miyao;
    @BindView(R2.id.logout_btn)
    Button logout_btn;
    @BindView(R2.id.logout_btn1)
    Button logout_btn1;
    //剪切板管理工具类
    private ClipboardManager mClipboardManager;
    //剪切板Data对象
    private ClipData mClipData;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_opengooglethird;
    }

    @Override
    public OpenGoogleThirdActivityPresenter getPresenter() {
        return new OpenGoogleThirdActivityPresenter();
    }

    @Override
    protected void initView() {
        //super.initView();
        ARouter.getInstance().inject(this);
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    }

    @Override
    protected void initEvent() {
       // super.initEvent();
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RConfig.OPENGOOGLEFOURACTIVITY).navigation();
            }
        });
        logout_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll_miyao.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    protected void initData() {
        tv_miyao.setText(secret);
        //super.initData();

    }
}
