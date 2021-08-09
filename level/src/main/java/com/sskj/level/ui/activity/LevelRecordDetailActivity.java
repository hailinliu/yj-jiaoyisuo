package com.sskj.level.ui.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.base.App;
import com.sskj.level.R;
import com.sskj.level.R2;
import com.sskj.level.bean.RecordDetailBean;
import com.sskj.level.bean.RecordHistoryBean;
import com.sskj.level.presenter.LevelRecordDetailActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.enums.ColorEnum;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.DateUtils;
import com.sskj.lib.util.TimeFormatUtil;

import java.math.BigDecimal;
import java.util.Date;

import butterknife.BindView;

@Route(path = RConfig.LEVELRECORDDETAILACTIVITY)
public class LevelRecordDetailActivity extends BaseActivity<LevelRecordDetailActivityPresenter> {
    @BindView(R2.id.tvTimeDown)
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
    String tradeType;
    @Autowired
    RecordHistoryBean.ContentBean contentbean;
    @BindView(R2.id.ll_shengyu)
    LinearLayout ll_shengyu;
    @Override
    protected int getLayoutId() {
        return R.layout.level_activity_record_detail;
    }
    TextView tv;
    @Override
    public LevelRecordDetailActivityPresenter getPresenter() {
        return new LevelRecordDetailActivityPresenter();
    }
    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        setTitle(getString(R.string.bibi_detail_record));
        // contentbean.getAmount();
        this.text(R.id.entrust_type, contentbean.getDirection().equals("BUY")? App.INSTANCE.getString(R.string.bibi_tipBibiUtil1) : App.INSTANCE.getString(R.string.bibi_recordActivity3))
                .textColor(R.id.entrust_type, color(contentbean.getDirection().equals("BUY") ? ColorEnum.UP.getColor() : ColorEnum.DOWN.getColor()))
                .text(R.id.entrust_time, TimeFormatUtil.SF_FORMAT_C.format(new Date(contentbean.getTime())))
                .text(R.id.entrust_prePrice_text, String.format("委托价(%s)", contentbean.getBaseSymbol()))
                .text(R.id.entrust_preNum_text,String.format("委托量(%s)", contentbean.getCoinSymbol()))
                .text(R.id.entrust_realNum_text,String.format("委托总额(%s)", contentbean.getBaseSymbol()))
                .text(R.id.entrust_prePrice,new BigDecimal(contentbean.getPrice()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                .text(R.id.entrust_preNum,new BigDecimal(contentbean.getAmount()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                .text(R.id.entrust_realNum,new BigDecimal(contentbean.getTurnover()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                .text(R.id.entrust_state,contentbean.getStatus()==0?
                        "交易中":contentbean.getStatus()==1?
                        "完成":contentbean.getStatus()==2?
                        "取消":contentbean.getStatus()==3?
                        "超时":"")
                .text(R.id.tvTitlePrice, String.format("成交总额(%s)", contentbean.getBaseSymbol()))
                .text(R.id.tvTitleNum,  String.format("实际成交(%s)", contentbean.getCoinSymbol()))
                .text(R.id.tvTitleDealNum, String.format("成交均价(%s)", contentbean.getBaseSymbol()))
                .text(R.id.tvPrice,new BigDecimal(contentbean.getPriceStr()).multiply(new BigDecimal(contentbean.getAmountStr())).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                .text(R.id.tvNum,new BigDecimal(contentbean.getAmountStr()).stripTrailingZeros().toPlainString())
                .text(R.id.tvDealNum, new BigDecimal(contentbean.getPriceStr()).stripTrailingZeros().toPlainString());
        if(contentbean.getDetail()!=null&&contentbean.getDetail().size()>0){
            this.text(R.id.tvTimeDown,TimeFormatUtil.SF_FORMAT_C.format(new Date(contentbean.getDetail().get(0).getTime())))
                    .text(R.id.tvPriceDown,contentbean.getDetail().get(0).getPrice()+"")
                    .text(R.id.tvNumDown,contentbean.getDetail().get(0).getAmount()+"")
                    .text(R.id.tvFeeDown,contentbean.getDetail().get(0).getFee()+"");
            ll_shengyu.setVisibility(View.VISIBLE);
        }else {
            ll_shengyu.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void initData() {
        mPresenter.getData(id, tradeType);
    }

    public void updateUI(RecordDetailBean data) {
      /*  tvAll.setText(CoinUtil.keepCoinPrice(data.getStockCode(), data.getTotalPrice()));
        tvPrice.setText(CoinUtil.keepCoinPrice(data.getStockCode(), data.getDealPrice()));
        tvNum.setText(CoinUtil.keepCoinNum(data.getStockCode(), data.getDealsRemainNum()));*/
      /*  tvType.setText(BuySellEnum.getByType(tradeType).getTitle());
        tvType.setTextColor(ContextCompat.getColor(App.INSTANCE, BuySellEnum.getByType(tradeType).getColorRes()));*/
        //tvCoin.setText(data.getStockCode());
        // tvFee.setText(CoinUtil.keepCoinPrice(data.getStockCode(), data.getFee()));
        if (tradeType.equals("1")) {
            tvFeeDown.setText(CoinUtil.keepCoinNum(data.getStockCode(),data.getFee()) + " " + data.getRCode());
        } else {
            tvFeeDown.setText(CoinUtil.keepCoinNum(data.getStockCode(),data.getFee()) + " " + data.getRCode());

        }
        tvTimeDown.setText(DateUtils.timeStamp2Date(data.getDealTime(),""));
        tvNumDown.setText(data.getDealsRemainNum() + " " + data.getLCode());
        tvPriceDown.setText(CoinUtil.keepCoinPrice(data.getStockCode(), data.getDealPrice()) + " " + data.getRCode());

       /* tvAllTitle.setText(String.format(App.INSTANCE.getString(R.string.bibi_recordDetailActivity2), data.getRCode()));
        if (tradeType.equals("1")) {
            tvFeeTitle.setText(String.format(App.INSTANCE.getString(R.string.bibi_recordDetailActivity3), data.getRCode()));
        } else {
            tvFeeTitle.setText(String.format(App.INSTANCE.getString(R.string.bibi_recordDetailActivity3), data.getRCode()));

        }
        tvPriceTitle.setText(String.format(App.INSTANCE.getString(R.string.bibi_recordDetailActivity4), data.getRCode()));*/
        //tvNumTitle.setText(String.format(App.INSTANCE.getString(R.string.bibi_recordDetailActivity5), data.getLCode()));


    }


    public LevelRecordDetailActivity text(int id, String s){
        tv = findViewById(id);
        tv.setText(s);
        return this;
    }
    public LevelRecordDetailActivity textColor(int id,int i){
        tv =  findViewById(id);
        tv.setTextColor(i);
        return this;
    }
}
