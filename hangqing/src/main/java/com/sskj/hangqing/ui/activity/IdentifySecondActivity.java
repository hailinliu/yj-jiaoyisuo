package com.sskj.hangqing.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.IdentifyActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;

import butterknife.BindView;

@Route(path = RConfig.IDENTIFYSECONDACTIVITY)
public class IdentifySecondActivity extends BaseActivity<IdentifyActivityPresenter> {
    @BindView(R2.id.ll_huzhao)
    LinearLayout ll_huzhao;
    @BindView(R2.id.ll_shenfenzheng)
    LinearLayout ll_shenfenzheng;
    @BindView(R2.id.logout_btn)
    Button button;
    @BindView(R2.id.image1)
    ImageView image1;
    @BindView(R2.id.image2)
    ImageView image2;
    int type;
    @BindView(R2.id.tv1)
    TextView tv1;
    @BindView(R2.id.tv2)
    TextView tv2;
    @Autowired
    String zcountry;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_identifytwo;
    }


    @Override
    protected void initEvent() {
        ARouter.getInstance().inject(this);
        //super.initEvent();
        ll_huzhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=1;
                ll_huzhao.setBackground(getResources().getDrawable(R.drawable.hang_shape_5e8_corner_new1));
                ll_shenfenzheng.setBackground(getResources().getDrawable(R.drawable.hang_shape_5e8_corner_new));
                image1.setImageDrawable(getDrawable(R.mipmap.hang_shangchuandiqiu1));
                image2.setImageDrawable(getDrawable(R.mipmap.hang_shangchuandiqiu));
                tv1.setTextColor(getResources().getColor(R.color.libText_3));
                tv2.setTextColor(getResources().getColor(R.color.libTextWhite));
            }
        });
        ll_shenfenzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=0;
                ll_huzhao.setBackground(getResources().getDrawable(R.drawable.hang_shape_5e8_corner_new));
                image1.setImageDrawable(getDrawable(R.mipmap.hang_shenfenrenzheng));
                image2.setImageDrawable(getDrawable(R.mipmap.hang_shenfenrenzheng1));
                tv1.setTextColor(getResources().getColor(R.color.libTextWhite));
                tv2.setTextColor(getResources().getColor(R.color.libText_3));
                ll_shenfenzheng.setBackground(getResources().getDrawable(R.drawable.hang_shape_5e8_corner_new1));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RConfig.IDENTIFYTHIRDACTIVITY).withString("zcountry",zcountry).withInt("type",type).navigation();
                finish();
            }
        });
    }

    @Override
    public IdentifyActivityPresenter getPresenter() {
        return new IdentifyActivityPresenter();
    }
}
