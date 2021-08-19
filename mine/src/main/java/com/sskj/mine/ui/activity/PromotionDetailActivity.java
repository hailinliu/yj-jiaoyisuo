package com.sskj.mine.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.common.base.App;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.util.ImageUtil;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.presenter.PromotionDetailActivityPresenter;

import butterknife.BindView;
@Route(path= RConfig.PROMOTIONDETAILACTIVITY)
public class PromotionDetailActivity extends BaseActivity<PromotionDetailActivityPresenter> {
    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.ivBack)
    LinearLayout ivBack;
    @BindView(R2.id.image)
    ImageView image;
    @BindView(R2.id.tv_yaoqing)
    TextView tvYaoqing;
    @BindView(R2.id.tv_copy)
    TextView tvCopy;
  /*  @BindView(R2.id.tv_lianjie)
    TextView tvLianjie;
    @BindView(R2.id.tv_copy1)
    TextView tvCopy1;*/
    String id = SPUtil.get(SPConfig.ID,"");
    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_promotion_detail;
    }

    @Override
    protected void initData() {
       tvTitle.setText(App.INSTANCE.getString(R.string.mine_my_tuiguang));

     /* if(!TextUtils.isEmpty(invitationCode)){
          mPresenter.geterweima(invitationCode);
      }*/

    }

    @Override
    protected void initView() {
        String  invitationCode =  SPUtil.get("invitationCode","");
        ImageUtil.setCircleImage(HttpConfig.BASE_URL + HttpConfig.GETERWEIMA+"?id="+id+"&number="+invitationCode,image);
        tvYaoqing.setText(invitationCode);
       // tvLianjie.setText("https://www.bitflnex.pro/register/index.html?invitationCode="+invitationCode);
    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
// 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）
                ClipData clipData = ClipData.newPlainText(null,tvYaoqing.getText());
                // 把数据集设置（复制）到剪贴板
                clipboard.setPrimaryClip(clipData);
            }
        });
      /*  tvCopy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
// 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）
                ClipData clipData = ClipData.newPlainText(null,tvLianjie.getText());
                // 把数据集设置（复制）到剪贴板
                clipboard.setPrimaryClip(clipData);
            }
        });*/
    }

    @Override
    public PromotionDetailActivityPresenter getPresenter() {
        return new PromotionDetailActivityPresenter();
    }


}
