package com.sskj.login.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.login.R;
import com.sskj.login.R2;
import com.sskj.login.bean.rxbus.AgreementBean;
import com.sskj.login.presenter.AgreementActivityPresenter;
import com.zzhoujay.richtext.RichText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.AGREEMENTACTIVITY)
public class AgreementActivity extends BaseActivity<AgreementActivityPresenter> {
    @BindView(R2.id.rv)
    RecyclerView rv;
    @BindView(R2.id.tv_new_content)
    TextView tvContent;
    @Autowired
    int isflag;
    private SlimAdapter slimAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_agreement_content;
    }

    @Override
    public AgreementActivityPresenter getPresenter() {
        return new AgreementActivityPresenter();
    }

    @Override
    protected void initData() {
       // super.initData();
        ARouter.getInstance().inject(this);
      if (isflag==2){
          rv.setLayoutManager(new LinearLayoutManager(this));
            slimAdapter = SlimAdapter.create().register(R.layout.login_activity_agreement_content_item, new SlimInjector<AgreementBean.DataBean>() {
                @Override
                public void onInject(AgreementBean.DataBean bean, IViewInjector iViewInjector, List list) {
                    iViewInjector.text(R.id.tv_title,bean.getTitle().equals("法币指南")?App.INSTANCE.getString(R.string.login_fabi):App.INSTANCE.getString(R.string.login_shangjia))
                            .text(R.id.tv_time,bean.getCreateTime())
                            .clicked(R.id.tv_chakan, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ARouter.getInstance().build(RConfig.AGREENMENTCONTENTACTIVITY).withString("content",bean.getContent()).navigation();
                                }
                            });
                }
            }).attachTo(rv).updateData(new ArrayList());
        }

        if(isflag==1){
            setTitle(getString(R.string.login_xieyi));
            tvContent.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
            mPresenter.getContent("UCENTER");
        }else if(isflag==2){
            setTitle(getString(R.string.login_xinshou));
            tvContent.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
            mPresenter.getContent("HELP");
        }

    }

    public void updataUI(AgreementBean bean) {
        for(AgreementBean.DataBean data:bean.getData()){
            if(data.getTitle().equals("注册协议")||data.getTitle().contains("the registration agreement")){
                RichText.fromHtml(data.getContent()).into(tvContent);
            }
         if(isflag==2){
             slimAdapter.updateData(bean.getData());
         }


        }
    }
}
