package com.sskj.hangqing.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.base.App;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.CountryBean;
import com.sskj.hangqing.presenter.IdentifyActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;

import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.IDENTIFYACTIVITY)
public class IdentifyActivity extends BaseActivity<IdentifyActivityPresenter> {
    @BindView(R2.id.ll_select_country)
    LinearLayout ll_select_country;
    @BindView(R2.id.tv_guoji)
    TextView tv_guoji;
    @BindView(R2.id.logout_btn)
    Button button;
    String zcountry;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_identifyone;
    }

    @Override
    public IdentifyActivityPresenter getPresenter() {
        return new IdentifyActivityPresenter();
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        ll_select_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RConfig.SELECTCOUNTRYACTIVITY).navigation(IdentifyActivity.this,1);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_guoji.getText().toString().contains("+")){
                ARouter.getInstance().build(RConfig.IDENTIFYSECONDACTIVITY).withString("zcountry",zcountry).navigation();
                finish();
                }else {
                    ToastUtil.showShort(App.INSTANCE.getString(R.string.hang_pealse_select_country));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1&&requestCode==1){
            if(data!=null){
                String[] arr = data.getStringExtra("country").split("\\(");
                zcountry=  arr[0];
              String country = "("+arr[1];
                tv_guoji.setText(country);
                tv_guoji.setTextColor(getResources().getColor(R.color.libTextWhite));
            }
        }
    }

    @Override
    protected void initData() {
        ARouter.getInstance().inject(this);
       // super.initData();

    }


    public void putList(String data) {
    }

    public void uploadFail() {
    }
}
