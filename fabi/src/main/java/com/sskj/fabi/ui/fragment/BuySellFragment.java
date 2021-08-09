package com.sskj.fabi.ui.fragment;


import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.ItemClickSupport;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.bean.BuySellBean;
import com.sskj.fabi.bean.FilterBean;
import com.sskj.fabi.bean.OrderDetailBean;
import com.sskj.fabi.bean.PayWayItem;
import com.sskj.fabi.component.DaggerUserDataComponent;
import com.sskj.fabi.presenter.BuySellFragmentPresenter;
import com.sskj.fabi.util.TipFabiUtil;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.bean.enums.PayTypeEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.lib.util.AppCircleCheckUtil;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.NumberUtil;
import com.sskj.lib.util.StringUtil;
import com.sskj.lib.util.TipUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = RConfig.FABI_FRAGMENT_BUY_SELL)
public class BuySellFragment extends BaseFragment<BuySellFragmentPresenter> {
    boolean isBuy;  // true 购买大厅  false 出售大厅
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    @Autowired
    String type;//币名字
    @Autowired
    int flag;//1.是买，2是卖
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;
    private SlimAdapter slimAdapter;
    private int curPos;
    private BottomSheetDialog orderSheet;
    private MVCCoolHelper<List<BuySellBean.ContextBean>> mvcHelper;
    private List<PayWayItem> listPay = new ArrayList<>();  // 当前用户支付方式
   // private String pType;
    private String minMoney;
    private BuySellBean.ContextBean dataItem;
    private String pType;
    private String currency="CNY";
    private BottomSheetDialog dialog;
    private ArrayList<BottomSheetUtil.ItemBean> list;
    private BottomSheetDialog mydialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fabi_fragment_buy_sell;
    }

    @Override
    public BuySellFragmentPresenter getPresenter() {
        return new BuySellFragmentPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        DaggerUserDataComponent.create().inject(this);
        if(flag==1){
            isBuy = true;
        }else if(flag==2){
            isBuy=false;
        }
        userViewModel.getUsers().observe(this,users->{
            if (users != null&&users.size()>0) {
                userData = users.get(0);
            }
        });
        //mPresenter.getData1("1","20",true,type);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setFirstDraw(false)
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.fabiDivider))
        );
        // 剩余数量
        slimAdapter = SlimAdapter.create().register(R.layout.fabi_recy_item_buy_sell, new SlimInjector<BuySellBean.ContextBean>() {

            @Override
            public void onInject(BuySellBean.ContextBean data, IViewInjector injector, List payloads) {
                BigDecimal decimal1 = new BigDecimal(data.getSuccessRete30());
                BigDecimal decimal2 =new BigDecimal(100);
                injector.text(R.id.tvName, data.getMemberName())
                        .text(R.id.tvDealNum, CoinUtil.keepUSDT(data.getSuccessCount30()))
                        .text(R.id.tvPercent,  decimal1.multiply(decimal2).setScale(2, RoundingMode.DOWN)+ "%")
                        .text(R.id.tv_num, App.INSTANCE.getString(R.string.fabitipFabiUtil61) + "("+type+")  " + data.getRemainAmount() )  // 剩余数量
                        .text(R.id.tvLimitMoney, App.INSTANCE.getString(R.string.fabibuySellFragment4) + "("+currency+")  " + data.getMinLimit() + "-" + data.getMaxLimit() + "")
                        .text(R.id.tv_china_money,  data.getPrice()+currency)
                        .textColor(R.id.tv_china_money,isBuy?getResources().getColor(R.color.libGreen1):getResources().getColor(R.color.libRed))
                        .text(R.id.tvPhoto, StringUtil.getFirstName(data.getMemberName()))
                        .text(R.id.btSale, isBuy ? App.INSTANCE.getString(R.string.fabibuySellFragment5) : App.INSTANCE.getString(R.string.fabibuySellFragment6))
                        .visibility(R.id.iv_wechat, data.getPayMode().contains("微信") ? View.VISIBLE : View.GONE)
                        .visibility(R.id.iv_bank, data.getPayMode().contains("银联") ? View.VISIBLE : View.GONE)
                        .visibility(R.id.iv_alipay, data.getPayMode().contains("支付宝") ? View.VISIBLE : View.GONE)
                        .clicked(R.id.btSale,isBuy?new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!AppCircleCheckUtil.checkLogin(getActivity(), userData)) {
                                    return;
                                }
                                dialog = TipFabiUtil.showTrade(getActivity(), type, currency, true, data.getPrice() + "",
                                        data.getMinLimit() + "-" + data.getMaxLimit(),
                                        data.getMinLimit(), data.getMaxLimit(), data.getRemainAmount() + "",
                                        data.getTimeLimit(), data.getMemberName(), new TipFabiUtil.OnInputList() {
                                            @Override
                                            public void onSure(String... str) {

                                                int i = str.length;
                                                String type = str[str.length-1];
                                                String Rmb = str[1];
                                                String count = str[0];
                                                list = new ArrayList<>();
                                                list.clear();
                                                if(data.getPayMode().contains("支付宝")){
                                                    BottomSheetUtil.ItemBean itemBean = new BottomSheetUtil.ItemBean(R.mipmap.lib_icon_alipay,"支付宝");
                                                    list.add(itemBean);
                                                }
                                                if(data.getPayMode().contains("微信")){
                                                    BottomSheetUtil.ItemBean itemBean = new BottomSheetUtil.ItemBean(R.mipmap.lib_icon_wx,"微信");
                                                    list.add(itemBean);
                                                }
                                                if(data.getPayMode().contains("银联")){
                                                    BottomSheetUtil.ItemBean itemBean = new BottomSheetUtil.ItemBean(R.mipmap.fabi_yinhangka,"银联");
                                                    list.add(itemBean);
                                                }
                                                mydialog = BottomSheetUtil.getBottomSheet(getActivity(),"请选择一种支付方式",new ItemClickSupport.OnItemClickListener(){

                                                    @Override
                                                    public void onItemClicked(RecyclerView recyclerView, int i, View view) {

                                                        if(type.equals("0")){
                                                            mPresenter.buyCoin("",data.getCoinId(),data.getAdvertiseId(),type,Rmb,list.get(i).content,data.getPrice()+"");
                                                        }else {
                                                            mPresenter.buyCoin(count,data.getCoinId(),data.getAdvertiseId(),type,"",list.get(i).content,data.getPrice()+"");
                                                        }
                                                        mydialog.dismiss();
                                                        dialog.dismiss();
                                                    }
                                                },list);
                                                mydialog.show();
                                            }
                                        });
                                dialog.show();
                            }
                        }:new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!AppCircleCheckUtil.checkLogin(getActivity(), userData)) {
                                    return;
                                }
                              /*  if (!checkPayMatch(isBuy, listPay, dataItem)) {
                                    return;
                                }*/
                                dialog = TipFabiUtil.showTrade(getActivity(), type, currency, false, data.getPrice() + "",
                                        data.getMinLimit() + "-" + data.getMaxLimit(),
                                        data.getMinLimit(), data.getMaxLimit(), data.getRemainAmount() + "",
                                        data.getTimeLimit(), data.getMemberName(), new TipFabiUtil.OnInputList() {
                                            @Override
                                            public void onSure(String... str) {
                                                int i = str.length;
                                                String type = str[str.length-1];
                                                String Rmb = str[1];
                                                String count = str[0];
                                                list = new ArrayList<>();
                                                list.clear();
                                                if(data.getPayMode().contains("支付宝")){
                                                    BottomSheetUtil.ItemBean itemBean = new BottomSheetUtil.ItemBean(R.mipmap.lib_icon_alipay,"支付宝");
                                                    list.add(itemBean);
                                                }
                                                if(data.getPayMode().contains("微信")){
                                                    BottomSheetUtil.ItemBean itemBean = new BottomSheetUtil.ItemBean(R.mipmap.lib_icon_wx,"微信");
                                                  list.add(itemBean);
                                                }
                                                if(data.getPayMode().contains("银联")){
                                                    BottomSheetUtil.ItemBean itemBean = new BottomSheetUtil.ItemBean(R.mipmap.fabi_yinhangka,"银联");
                                                    list.add(itemBean);
                                                }
                                                mydialog = BottomSheetUtil.getBottomSheet(getActivity(),"请选择一种支付方式",new ItemClickSupport.OnItemClickListener(){

                                                        @Override
                                                        public void onItemClicked(RecyclerView recyclerView, int i, View view) {

                                                            if(type.equals("0")){
                                                                mPresenter.sellCoin("",data.getCoinId(),data.getAdvertiseId(),type,Rmb,list.get(i).content,data.getPrice()+"");
                                                            }else {
                                                                mPresenter.sellCoin(count,data.getCoinId(),data.getAdvertiseId(),type,"",list.get(i).content,data.getPrice()+"");
                                                            }
                                                            mydialog.dismiss();
                                                            dialog.dismiss();
                                                        }
                                                    },list);
                                                mydialog.show();

                                            }
                                        });
                                dialog.show();
                            }
                        })
                        .background(R.id.btSale, isBuy ? R.drawable.fabi_solid_ff5_bg : R.drawable.fabi_solid_03c_bg);

            }
        });//(page, pageSize,legalCurrency,limit,isBuy,paymode,pType)
        slimAdapter.attachTo(recyclerView).updateData(new ArrayList());
        ModelRx2DataSource<BuySellBean.ContextBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<BuySellBean.ContextBean>)
                page -> mPresenter.getData(page + "", 20 + "",currency,minMoney,isBuy,pType, type), 20);
        mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();
        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener((recyclerView1, i, view) -> {
                    curPos = i;
                    if (!AppCircleCheckUtil.checkLogin(getActivity(), userData)) {
                        return;
                    }
                    if (!AppCircleCheckUtil.checkAuth(getActivity(), userData)) {
                        return;
                    }
                    BuySellBean.ContextBean bean= (BuySellBean.ContextBean)slimAdapter.getDataItem(i);
                    ARouter.getInstance().build(RConfig.MERCHANTCENTERACTIVITY).withString("name",bean.getMemberName()).navigation();

                    /*if (!AppCircleCheckUtil.checkTradePwd(getActivity(), userData)) {  // 资金密码
                        return;
                    }*/
                   // trade();
                });
    }

    @Override
    protected void initData() {
        LiveDataBus.get().with(RxBusCode.CHANGE_FABI_MARKET_FILTER, FilterBean.class)
                .observe(this, filterBean -> {
                    pType = filterBean.getpType();
                    minMoney = filterBean.getMinMoney();
                    if(!TextUtils.isEmpty(filterBean.getCurrency())){
                        currency = filterBean.getCurrency().substring(2,filterBean.getCurrency().length()-1);
                    }else {
                        currency = filterBean.getCurrency();
                    }

                    mvcHelper.refresh();
                });
        LiveDataBus.get().with(RxBusCode.PUBLISH_SUCCESS)
                .observe(this,o -> refresh());
    }

    private void trade() {
        if (!AppCircleCheckUtil.checkLogin(getActivity(), userData)) {
            return;
        }
        dataItem = (BuySellBean.ContextBean) slimAdapter.getDataItem(curPos);
       /* if (SPUtil.get(SPConfig.ID, "").equals(dataItem.getStockUserId())) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.fabibuySellFragment7));
            return;
        }*/
        if (isBuy) {
            if (!AppCircleCheckUtil.checkAuth(getActivity(), userData)) {
                return;
            }
            tryTrade(0);
        } else {
            if (!AppCircleCheckUtil.checkHighAuth(getActivity(), userData)) {
                return;
            }
            if (!AppCircleCheckUtil.checkTradePwd(getActivity(), userData)) {  // 资金密码
                return;
            }
            if (!checkPayMatch(isBuy, listPay, dataItem)) {
                return;
            }
            mPresenter.getPositionNum();
        }
       /* if (isBuy) {

        } else {

        }*/


    }



    public void orderSuccess(OrderDetailBean orderDetailBean) {
        if (orderSheet != null && orderSheet.isShowing()) {
            orderSheet.dismiss();
        }
        if (mvcHelper != null) {
            mvcHelper.refresh();
        }
        ARouter.getInstance().build(RConfig.FABI_ORDER_RECORD_DETAIL)
                .withString(Constans.ORDER_NUM, orderDetailBean.getDealEntrustNo())
                .withBoolean(Constans.IS_BUYER, isBuy)
                .navigation();

    }

    // 记录用户支付方式
    public void updateUi(List<PayWayItem> listPay) {
        this.listPay = listPay;
    }

    @Override
    public void onResume() {

        super.onResume();
       /* if (mPresenter != null && userData != null) {
            mPresenter.requsetPayWayList();
        }*/
    }

    //检测用户支付方式是否匹配的上买家支付方式
    public boolean checkPayMatch(boolean isBuy, List<PayWayItem> payList, BuySellBean.ContextBean bean) {
        StringBuffer sbText = new StringBuffer();

        if (bean.getPayMode().contains(PayTypeEnum.ALIPAY.getName())) {  // 买家有支付宝
            sbText.append("、" + App.INSTANCE.getString(R.string.fabipublishActivity21));
            for (int i = 0; i < payList.size(); i++) {
                if (payList.get(i).getPayType() == 2 && !payList.get(i).isStatus()) {  //卖家有支付宝
                    return true;
                }
            }
        }
        if (bean.getPayMode().contains(PayTypeEnum.WX.getName())) { // 买家有微信
            sbText.append("、" + App.INSTANCE.getString(R.string.fabipublishActivity20));
            for (int i = 0; i < payList.size(); i++) {
                if (payList.get(i).getPayType() == 1 && !payList.get(i).isStatus()) {  //卖家有微信
                    return true;
                }
            }
        }
        if (bean.getPayMode().contains(PayTypeEnum.BANK.getName())) { // 买家有银行卡
            sbText.append("、" + App.INSTANCE.getString(R.string.fabibuySellFragment11));
            for (int i = 0; i < payList.size(); i++) {
                if (payList.get(i).getPayType() == 3 && !payList.get(i).isStatus()) {  //卖家有微信
                    return true;
                }
            }
        }
        if (!isBuy) {
            TipUtil.getSureTip(getActivity(), App.INSTANCE.getString(R.string.fabibuySellFragment12), String.format(App.INSTANCE.getString(R.string.fabibuySellFragment13), sbText.substring(1)), App.INSTANCE.getString(R.string.fabibuySellFragment14), () -> {
                ARouter.getInstance().build(RConfig.MINE_SECURITY_CENTER).navigation();
            });
        } else {
            TipUtil.getSureTip(getActivity(), App.INSTANCE.getString(R.string.fabibuySellFragment12), String.format(App.INSTANCE.getString(R.string.fabibuySellFragment15), sbText.substring(1)), App.INSTANCE.getString(R.string.fabibuySellFragment14), () -> {
                ARouter.getInstance().build(RConfig.MINE_SECURITY_CENTER).navigation();
            });
        }
        return false;
    }

    public void refresh() {
        mvcHelper.refresh();
    }

    public void tryTrade(Integer data) {
        if (data > 0) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.fabibuySellFragment16));
            return;
        }
        ArrayList<PayTypeEnum> payTypeEnums = new ArrayList<>();
        if (!isBuy) {
            for (int i = 0; i < listPay.size(); i++) {
                if (dataItem.getPayMode().contains(listPay.get(i).getName() + "")&&!listPay.get(i).isStatus()) {
                    payTypeEnums.add(PayTypeEnum.getByType(listPay.get(i).getPayType() + ""));
                }
            }
        } else {
            String[] split = dataItem.getPayMode().split(",");
            for (int i = 0; i < split.length; i++) {
                payTypeEnums.add(PayTypeEnum.getByType(split[i]));
            }
        }

     /*   ARouter.getInstance()
                .build(RConfig.FABI_ORDER)
                .withBoolean(Constans.IS_BUY, isBuy)
                .withSerializable(Constans.DATA, dataItem)
                .withSerializable(Constans.PAY_TYPE, payTypeEnums)
                .navigation();*/
    }


    public void setUIBuy(String message,String orn) {
        ToastUtil.showShort(message);
        ARouter.getInstance().build(RConfig.PAYORDERACTIVITY).withString("orderSn",orn).navigation();
    }

    public void setUISell(String message,String orn) {
        ToastUtil.showShort(message);
        ARouter.getInstance().build(RConfig.PAYORDERSELLACTIVITY).withString("orderSn",orn).navigation();
    }
}

