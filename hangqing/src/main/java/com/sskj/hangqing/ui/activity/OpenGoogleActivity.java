package com.sskj.hangqing.ui.activity;

import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.OpenGoogleActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;

import butterknife.BindView;

@Route(path = RConfig.OPENGOOGLEACTIVITY)
public class OpenGoogleActivity extends BaseActivity<OpenGoogleActivityPresenter> {
    @BindView(R2.id.logout_btn)
    Button btn;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_opengoogleone;
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RConfig.OPENGOOGLETWOACTIVITY).navigation();
            }
        });
    }

    @Override
    public OpenGoogleActivityPresenter getPresenter() {
        return new OpenGoogleActivityPresenter();
    }


}
