package com.sskj.bibi.ui.activity;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.bibi.R;
import com.sskj.bibi.R2;
import com.sskj.bibi.bean.RecordDetailBean;
import com.sskj.bibi.bean.RecordHistoryBean;
import com.sskj.bibi.presenter.RecordDetailActivityPresenter;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.enums.BuySellEnum;
import com.sskj.lib.bean.enums.ColorEnum;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.DateUtils;
import com.sskj.lib.util.TimeFormatUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

@Route(path = RConfig.BIBI_RECORD_DETAIL)//成交明细
public class RecordDetailActivity extends BaseActivity<RecordDetailActivityPresenter> {

   /* @BindView(R2.id.tvTimeDown)
    TextView tvTimeDown;
    @BindView(R2.id.tvPriceDown)
    TextView tvPriceDown;
    @BindView(R2.id.tvNumDown)
    TextView tvNumDown;
    @BindView(R2.id.tvFeeDown)
    TextView tvFeeDown;
    @Autowired
    String id;
    @Autowired
    String tradeType;*/
   @BindView(R2.id.rv)
    RecyclerView rv;
    @Autowired
    ArrayList<RecordHistoryBean.ContentBean.DetailBean> contentbean;
    @Autowired
    String type;
    /*@BindView(R2.id.ll_shengyu)
    LinearLayout ll_shengyu;*/
    @Override
    protected int getLayoutId() {
        return R.layout.bibi_activity_record_detail;
    }
    TextView tv;
    @Override
    public RecordDetailActivityPresenter getPresenter() {
        return new RecordDetailActivityPresenter();
    }
    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        setTitle(getString(R.string.bibi_detail_record));
       // contentbean.getAmount();
        rv.setLayoutManager(new LinearLayoutManager(this));
        SlimAdapter slimAdapter = SlimAdapter.create().register(R.layout.bibi_activity_record_detail_item, new SlimInjector<RecordHistoryBean.ContentBean.DetailBean>() {
            @Override
            public void onInject(RecordHistoryBean.ContentBean.DetailBean bean, IViewInjector iViewInjector, List list) {
                iViewInjector
                        .text(R.id.entrust_time, TimeFormatUtil.SF_FORMAT_C.format(new Date(bean.getTime())))
                        .text(R.id.entrust_realNum_text,String.format(App.INSTANCE.getString(R.string.bibi_fee)+"(%s)", type.equals("BUY")?bean.getFeeSymbol():"USDT"))
                        .text(R.id.entrust_preNum_text,App.INSTANCE.getString(R.string.bibi_count)+"("+bean.getFeeSymbol()+")")
                        .text(R.id.entrust_prePrice,new BigDecimal(bean.getPrice()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                        .text(R.id.entrust_preNum,new BigDecimal(bean.getAmount()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                        .text(R.id.entrust_realNum,new BigDecimal(bean.getFee()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                        .text(R.id.entrust_realcurrency,new BigDecimal(bean.getTurnover()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());


            }
        }).attachTo(rv).updateData(contentbean);


    }







}
