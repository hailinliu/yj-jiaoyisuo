package com.sskj.mine.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.SuperTextView;
import com.sskj.common.base.App;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.bean.DataBean;
import com.sskj.mine.presenter.PromotionActivityPresenter;
import butterknife.BindView;


@Route(path = RConfig.PROMOTIONACTIVITY)
public class PromotionActivity extends BaseActivity<PromotionActivityPresenter> {
    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.ivBack)
    LinearLayout ivBack;
    @BindView(R2.id.tv_num)
    TextView tvNum;
    @BindView(R2.id.tv_tuandui_num)
    TextView tvTuanduiNum;
    @BindView(R2.id.sp_tv_tuiguang)
    SuperTextView spTvTuiguang;
    @BindView(R2.id.sp_tv_guess)
    SuperTextView spTvGuess;
    String id = SPUtil.get(SPConfig.ID,"");
    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_promotion;
    }

    @Override
    protected void initData() {
        tvTitle.setText(App.INSTANCE.getString(R.string.mine_help_center));
       mPresenter.getPromotion(id);
    }
    @Override
    protected void initEvent() {
       ivBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
     spTvTuiguang.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             ARouter.getInstance().build(RConfig.PROMOTIONDETAILACTIVITY).navigation();
         }
     });
     spTvGuess.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            ARouter.getInstance().build(RConfig.MYCUSTOMERACTIVITY).navigation();
         }
     });
    }

    @Override
    public PromotionActivityPresenter getPresenter() {
        return new PromotionActivityPresenter();
    }

    public void setData(DataBean data) {
        tvNum.setText(data.getTotalDirectUser());
        tvTuanduiNum.setText(data.getTotalDirectUser());
    }
}
