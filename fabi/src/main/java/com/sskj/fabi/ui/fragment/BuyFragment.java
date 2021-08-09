package com.sskj.fabi.ui.fragment;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.sskj.common.adapter.ItemClickSupport;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.bean.NewBaseBean;
import com.sskj.fabi.bean.PayWayItem;
import com.sskj.fabi.http.HttpConfig;
import com.sskj.fabi.presenter.BuyFragmentPresenter;
import com.sskj.fabi.util.TipFabiUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.util.AppCircleCheckUtil;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.ImageUtil;
import com.sskj.lib.util.StringUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

@Route(path = RConfig.BUYFRAGMENT)
public class BuyFragment extends BaseFragment<BuyFragmentPresenter> {
    @Autowired
    int type;
    @Autowired
    ArrayList<NewBaseBean> data;
    boolean isBuy;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    private String currency="CNY";
    private BottomSheetDialog dialog;
    private BottomSheetDialog mydialog;
    private List<PayWayItem> listPay = new ArrayList<>();  // 当前用户支付方式
    private ArrayList<BottomSheetUtil.ItemBean> list;
    @Override
    protected int getLayoutId() {
        return R.layout.fabi_fragment_buy_sell;
    }

    @Override
    protected void initView() {
        //super.initView();
        ARouter.getInstance().inject(this);
        if (type == 1) {
            isBuy = true;
        } else if (type == 2) {
            isBuy = false;
        }
    }

    @Override
    protected void initData() {
        //super.initData();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setFirstDraw(false)
                .setLeftPadding((int) RudenessScreenHelper.pt2px(App.INSTANCE, 30))
                .setRightPadding((int) RudenessScreenHelper.pt2px(App.INSTANCE, 30))
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.fabiDivider))
        );
        // 剩余数量
        SlimAdapter  slimAdapter = SlimAdapter.create().register(R.layout.fabi_fragment_buy_sell_item, new SlimInjector<NewBaseBean>() {

            @Override
            public void onInject(NewBaseBean data, IViewInjector injector, List payloads) {
               // ImageView imageView = (ImageView) injector.findViewById(R.id.image1);

                injector.text(R.id.tv_name, data.getMemberName())
                        .text(R.id.tv_type,isBuy?"购买":"出售")
                        .textColor(R.id.tv_type,isBuy?getResources().getColor(R.color.libGreen1):getResources().getColor(R.color.libRed))
                        .text(R.id.tv_shengyu_content, data.getRemainAmount()+"") // 剩余数量
                        .text(R.id.tv_xiane_content, data.getMinLimit() + "-" + data.getMaxLimit() + "")
                        .text(R.id.tv_price,  data.getPrice()+currency)
                        .textColor(R.id.tv_price,isBuy?getResources().getColor(R.color.libGreen1):getResources().getColor(R.color.libRed))
                        .text(R.id.btSale, isBuy ? App.INSTANCE.getString(R.string.fabibuySellFragment5) : App.INSTANCE.getString(R.string.fabibuySellFragment6))
                        .visibility(R.id.iv_wechat, data.getPayMode().contains("微信") ? View.VISIBLE : View.GONE)
                        .visibility(R.id.iv_bank, data.getPayMode().contains("银行卡") ? View.VISIBLE : View.GONE)
                        .visibility(R.id.iv_alipay, data.getPayMode().contains("支付宝") ? View.VISIBLE : View.GONE)
                        .clicked(R.id.btSale,isBuy?new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog = TipFabiUtil.showTrade(getActivity(), data.getUnit(), currency, true, data.getPrice() + "",
                                            data.getMinLimit() + "-" + data.getMaxLimit(),
                                            data.getMinLimit()+"", data.getMaxLimit()+"", data.getRemainAmount() + "",
                                            data.getTimeLimit()+"", data.getMemberName(), new TipFabiUtil.OnInputList() {
                                                @RequiresApi(api = Build.VERSION_CODES.M)
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
                                                                mPresenter.buyCoin("",data.getCoinId()+"",data.getAdvertiseId()+"",type,Rmb,list.get(i).content,data.getPrice()+"");
                                                            }else {
                                                                mPresenter.buyCoin(count,data.getCoinId()+"",data.getAdvertiseId()+"",type,"",list.get(i).content,data.getPrice()+"");
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


                                dialog = TipFabiUtil.showTrade(getActivity(), data.getUnit(), currency, false, data.getPrice() + "",
                                        data.getMinLimit() + "-" + data.getMaxLimit(),
                                        data.getMinLimit()+"", data.getMaxLimit()+"", data.getRemainAmount() + "",
                                        data.getTimeLimit()+"", data.getMemberName(), new TipFabiUtil.OnInputList() {
                                            @RequiresApi(api = Build.VERSION_CODES.M)
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
                                                            mPresenter.sellCoin("",data.getCoinId()+"",data.getAdvertiseId()+"",type,Rmb,list.get(i).content,data.getPrice()+"");
                                                        }else {
                                                            mPresenter.sellCoin(count,data.getCoinId()+"",data.getAdvertiseId()+"",type,"",list.get(i).content,data.getPrice()+"");
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
        slimAdapter.attachTo(recyclerView).updateData(data);
    }
    @Override
    protected BuyFragmentPresenter getPresenter() {
        return new BuyFragmentPresenter();
    }


    public void setUI(String message) {
        ToastUtil.showShort(message);
    }

    public void tryTrade(Integer data) {

    }
}
