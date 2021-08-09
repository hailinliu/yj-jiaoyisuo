package com.sskj.mine.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.util.ImageUtil;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.presenter.ContactUsActivityPresenter;

import java.util.List;

import butterknife.BindView;
@Route(path = RConfig.MINE_CONTACT_US)
public class ContactUsActivity extends BaseActivity<ContactUsActivityPresenter> {
    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.ivBack)
    LinearLayout ivBack;
    @BindView(R2.id.image)
    ImageView image;
    @BindView(R2.id.image2)
    ImageView image2;
    String id = SPUtil.get(SPConfig.ID,"");
    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_contact_us;
    }

    @Override
    protected void initView() {
       tvTitle.setText("联系客服");
    }

    @Override
    protected void initData() {
    mPresenter.getAddress(id);
    }

    @Override
    protected void initEvent() {
      ivBack.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });
    }

    @Override
    public ContactUsActivityPresenter getPresenter() {
        return new ContactUsActivityPresenter();
    }

    public void updateUI(List<String> list) {
        ImageUtil.setImage(HttpConfig.BASE_URL+list.get(0),image);
        ImageUtil.setImage(HttpConfig.BASE_URL+list.get(1),image2);
    }
}
