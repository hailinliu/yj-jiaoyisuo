package com.sskj.mine.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.bean.MineShopBean;
import com.sskj.mine.presenter.SellerApplyPresenter;

import butterknife.BindView;

/**
 * 申请商家
 */
@Route(path = RConfig.MINE_SELLER_APPLY)
public class SellerApplyActivity extends BaseActivity<SellerApplyPresenter> {


    @BindView(R2.id.check_agree)
    CheckBox checkAgree;
    @BindView(R2.id.submit_btn)
    Button submitBtn;
    @BindView(R2.id.shop_money)
    TextView shopMoney;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_seller_apply;
    }

    @Override
    public SellerApplyPresenter getPresenter() {
        return new SellerApplyPresenter();
    }

    @Override
    public void initView() {

        setTitle(App.INSTANCE.getString(R.string.minesellerApplyActivity1));
    }

    @Override
    public void initData() {
        mPresenter.getMoney();
    }

    @Override
    protected void initEvent() {
        ClickUtil.click(submitBtn, () -> {
            if (checkAgree.isChecked()) {
                mPresenter.shopVerify();
            } else {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.minesellerApplyActivity2));
            }
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SellerApplyActivity.class);
        context.startActivity(intent);
    }

    public void updateUi(MineShopBean shopBean) {
        shopMoney.setText(String.format(App.INSTANCE.getString(R.string.minesellerApplyActivity3), shopBean.getValue()));
        checkAgree.setText(String.format(App.INSTANCE.getString(R.string.minesellerApplyActivity4), shopBean.getValue()));
    }

}
