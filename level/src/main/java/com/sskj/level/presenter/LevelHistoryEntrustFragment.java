package com.sskj.level.presenter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.mvc.MVCHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.ItemClickSupport;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.level.R;
import com.sskj.level.R2;
import com.sskj.level.bean.RecordHistoryBean;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.enums.ColorEnum;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.lib.util.TimeFormatUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
@Route(path=RConfig.LEVELHISTORYENTRUSTFRAGMENT)
public class LevelHistoryEntrustFragment extends BaseFragment<LevelHistoryEntrustFragmentPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    @Autowired
    String code;
    String type;

    private SlimAdapter slimAdapter;


    private MVCHelper mvcHelper;
    private HashMap<String, String> stateMap;


    @Override
    protected int getLayoutId() {
        return R.layout.level_activity_history_entrust;
    }

    @Override
    public LevelHistoryEntrustFragmentPresenter getPresenter() {
        return new LevelHistoryEntrustFragmentPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        stateMap = new HashMap<>();
        stateMap.put("0", App.INSTANCE.getString(R.string.bibi_historyEntrustFragment1));
        stateMap.put("1", App.INSTANCE.getString(R.string.bibi_historyEntrustFragment2));
        stateMap.put("2", App.INSTANCE.getString(R.string.bibi_recordActivity4));
        stateMap.put("3", App.INSTANCE.getString(R.string.bibi_recordActivity5));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setFirstDraw(false)
                .setLastDraw(false)
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 20))
                .setPaintColor(ContextCompat.getColor(App.INSTANCE, R.color.libBg))
        );
        slimAdapter = SlimAdapter.create().register(R.layout.level_recy_item_entrust_history, new SlimInjector<RecordHistoryBean.ContentBean>() {
            @Override
            public void onInject(RecordHistoryBean.ContentBean data, IViewInjector injector, List<Object> payloads) {
                if(data.getType()==0&&data.getDirection().equals("BUY")){
                    injector.text(R.id.entrust_realNum,new BigDecimal(data.getAmountStr()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                }else {
                    injector.text(R.id.entrust_realNum,new BigDecimal(data.getAmountStr()).multiply(new BigDecimal(data.getPriceStr())).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                }
                injector.text(R.id.entrust_type, data.getDirection().equals("BUY")? App.INSTANCE.getString(R.string.bibi_tipBibiUtil1) : App.INSTANCE.getString(R.string.bibi_recordActivity3))
                        .textColor(R.id.entrust_type, color(data.getDirection().equals("BUY") ? ColorEnum.UP.getColor() : ColorEnum.DOWN.getColor()))
                        .text(R.id.entrust_time, TimeFormatUtil.SF_FORMAT_C.format(new Date(data.getTime())))
                        .text(R.id.entrust_prePrice_text, String.format(App.INSTANCE.getString(R.string.level_weituo), data.getBaseSymbol()))
                        .text(R.id.entrust_preNum_text,String.format("委托量(%s)", data.getCoinSymbol()))
                        .text(R.id.entrust_realNum_text,String.format("委托总额(%s)", data.getBaseSymbol()))
                        .text(R.id.entrust_prePrice,new BigDecimal(data.getPrice()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                        .text(R.id.entrust_preNum,new BigDecimal(data.getAmountStr()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())

                        // .text(R.id.entrust_realNum,new BigDecimal(data.getTurnover()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                        .text(R.id.entrust_state,data.getStatus()==0?
                                "交易中":data.getStatus()==1?
                                "完成":data.getStatus()==2?
                                "取消":data.getStatus()==3?
                                "超时":"")
                        .text(R.id.tvTitlePrice, String.format("成交总额(%s)", data.getBaseSymbol()))
                        .text(R.id.tvTitleNum,  String.format("实际成交(%s)", data.getCoinSymbol()))
                        .text(R.id.tvTitleDealNum, String.format("成交均价(%s)", data.getBaseSymbol()))
                        .text(R.id.tvPrice,new BigDecimal(data.getTurnover()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                        .text(R.id.tvNum,new BigDecimal(data.getAmountStr()).stripTrailingZeros().toPlainString())
                        .text(R.id.tvDealNum, new BigDecimal(data.getTurnover()).divide(new BigDecimal(data.getAmountStr()),BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
      /*          injector.text(R.id.entrust_type, data.isBuy() ? App.INSTANCE.getString(R.string.bibi_tipBibiUtil1) : App.INSTANCE.getString(R.string.bibi_recordActivity3))
                        .textColor(R.id.entrust_type,color(data.isBuy() ? ColorEnum.UP.getColor() : ColorEnum.DOWN.getColor()))
                        .text(R.id.entrust_name, data.getLCode() + "/" + data.getRCode())
                        .text(R.id.entrust_time, data.getDealTime())
                        .text(R.id.entrust_prePrice, data.isMarket() ? App.INSTANCE.getString(R.string.bibi_historyEntrustFragment7) : data.getEntrustPrice())
                        .text(R.id.entrust_prePrice_text, String.format(App.INSTANCE.getString(R.string.bibi_allEntrustFragment4), data.getRCode()))
                        .text(R.id.entrust_totalPrice, data.getDealTotalPrice())
                        .text(R.id.entrust_totalPrice_text, String.format(App.INSTANCE.getString(R.string.bibi_recordDetailActivity2), data.getRCode()))
                        .text(R.id.entrust_realNum, data.getDealsRemainNum())
                        .text(R.id.entrust_realNum_text, String.format(App.INSTANCE.getString(R.string.bibi_recordDetailActivity5), data.getLCode()))
                        .text(R.id.entrust_realPrice, data.getDealPrice())
                        .text(R.id.entrust_realPrice_text, String.format(App.INSTANCE.getString(R.string.bibi_historyEntrustFragment11), data.getRCode()))
                        .text(R.id.entrust_state, stateMap.get(data.getState() + "") );
                if(data.getState()==0){
                    injector.textColor(R.id.entrust_state,R.color.bibiHistorytext2);
                }else if(data.getState()==1){
                    injector.textColor(R.id.entrust_state,R.color.bibiTextContent);
                }else if(data.getState()==2){
                    injector.textColor(R.id.entrust_state,R.color.bibiHistorytext);
                }else if(data.getState()==3){
                    injector.textColor(R.id.entrust_state,R.color.bibiHistorytext1);
                }


                if (data.isBuyAndMarket()) {  //市价买入
                    injector.text(R.id.entrust_preNum_text, String.format(App.INSTANCE.getString(R.string.bibi_historyEntrustFragment12), data.getRCode()))
                            .text(R.id.entrust_preNum, data.getTotalPrice());
                } else { //其他
                    injector.text(R.id.entrust_preNum_text, String.format(App.INSTANCE.getString(R.string.bibi_allEntrustFragment6), data.getLCode()))
                            .text(R.id.entrust_preNum, data.getTotalNum());
                }*/
            }
        }).attachTo(recyclerView);
        slimAdapter.updateData(new ArrayList());

        mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        ModelRx2DataSource refreshDataSource = new ModelRx2DataSource<RecordHistoryBean.ContentBean>(
                (ModelRx2DataSource.OnLoadSource<RecordHistoryBean.ContentBean>)
                        page -> mPresenter.getEntrustHistoryFlow(page + "", "10", code), 10);
        mvcHelper.setDataSource(refreshDataSource);
        mvcHelper.setAdapter(slimAdapter);
        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener((recyclerView1, i, view) -> {
                    RecordHistoryBean.ContentBean bean = (RecordHistoryBean.ContentBean)slimAdapter.getDataItem(i);
                    ARouter.getInstance()
                            .build(RConfig.BIBI_RECORD_DETAIL).withSerializable("contentbean",bean).navigation();
                   /* ARouter.getInstance()
                            .build(RConfig.BIBI_RECORD_DETAIL)
                            .withString(Constans.ID, dataItem.getId())
                            .withString(Constans.TRADE_TYPE, dataItem.getTradeType() + "")
                            .navigation();*/
                  /*  RecordHistoryBean dataItem = (RecordHistoryBean) slimAdapter.getDataItem(i);
                    if (dataItem.getState() == 2) {
                        ARouter.getInstance()
                                .build(RConfig.BIBI_RECORD_DETAIL)
                                .withString(Constans.ID, dataItem.getId())
                                .withString(Constans.TRADE_TYPE, dataItem.getTradeType() + "")
                                .navigation();
                    }*/
                });
    }

    @Override
    protected void initData() {
        mvcHelper.refresh();
        //mPresenter.getEntrustHistoryFlow1(1 + "", "10", code);
    }

}
