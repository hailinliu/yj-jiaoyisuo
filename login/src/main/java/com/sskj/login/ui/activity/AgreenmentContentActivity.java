package com.sskj.login.ui.activity;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.login.R;
import com.sskj.login.R2;
import com.sskj.login.presenter.AgreenmentContentActivityPresenter;
import com.zzhoujay.richtext.RichText;

import butterknife.BindView;

@Route(path = RConfig.AGREENMENTCONTENTACTIVITY)
public class AgreenmentContentActivity extends BaseActivity<AgreenmentContentActivityPresenter> {
    @Autowired
    String content;
    @BindView(R2.id.tv_content1)
    TextView tvContent1;
    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_agreement_content1;
    }

    @Override
    public AgreenmentContentActivityPresenter getPresenter() {
        return new AgreenmentContentActivityPresenter();
    }

    @Override
    protected void initView() {
       // super.initView();
        ARouter.getInstance().inject(this);
        RichText.fromHtml(content).into(tvContent1);

    }
}
