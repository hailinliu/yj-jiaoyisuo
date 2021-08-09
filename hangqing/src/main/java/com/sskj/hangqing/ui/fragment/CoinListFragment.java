package com.sskj.hangqing.ui.fragment;


import android.arch.lifecycle.Lifecycle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.util.ClickUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.NewChartBean;
import com.sskj.hangqing.component.DaggerUserDataComponent;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.presenter.CoinFragmentPresenter;
import com.sskj.lib.BaseHttpConfig;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.bean.HangQingBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.CoinImgUtil;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.ImageUtil;
import com.sskj.lib.util.NumberUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

@SuppressWarnings("ALL")
@Route(path = RConfig.HANG_FRAGMENT_COIN_LIST)
public class CoinListFragment extends BaseFragment<CoinFragmentPresenter>{
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.includeEmpty)
    FrameLayout includeEmpty;
    private SlimAdapter<CoinBean1> slimAdapter;
    private Map<String, Disposable> timeMap = new HashMap<>();
    private HashMap<String, String> coinMap;
    @Inject
    UserViewModel userViewModel;
    @Autowired
    boolean isSlide = false;
    @Autowired
    String qu;
    @Autowired
    int type=2;
    @Autowired
    boolean isUpDown = false;
    @Autowired
    boolean isNew = false;
    @Autowired
    boolean isSelf = false;
    private SafeSettingBean userData;
    @BindView(R2.id.tv_title1)
    TextView textView1;
    @BindView(R2.id.tv_title2)
    TextView textView2;
    @BindView(R2.id.tv_title3)
    TextView textView3;
    private String rate;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_coin;
    }

    @Override
    public CoinFragmentPresenter getPresenter() {
        return new CoinFragmentPresenter();
    }
public String multifyDouble(String a,String b,int scale){
    BigDecimal a1 =  new BigDecimal(a);
    BigDecimal b1 = new BigDecimal(b);
   return a1.multiply(b1).setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
}
    @Override
    public void initView(){
        ARouter.getInstance().inject(this);
        RxBus.getDefault().register(this);
        DaggerUserDataComponent.create().inject(this);
        qu = "/USDT";
        LiveDataBus.get().with(RxBusCode.RATE,RateBean.class).observe(this,this::refreshRate);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.hangDivider))
        );

        slimAdapter = SlimAdapter.create().register(R.layout.hang_recy_item_coin, new SlimInjector<CoinBean1>() {
            @Override
            public void onInject(CoinBean1 data, IViewInjector injector, List payloads) {
                BigDecimal bigDecimal = new BigDecimal(data.getClose()+"");
                BigDecimal a =  new BigDecimal(Double.toString(data.getChg()));
                BigDecimal b = new BigDecimal(Integer.toString(100));
                String changerate = a.multiply(b).doubleValue()+"%";
                if(rate!=null){
                    ImageView imageView= (ImageView)injector.findViewById(R.id.ivCoin);
                            imageView.setVisibility(View.VISIBLE);
                            ImageUtil.setImage(HttpConfig.BASE_URL+data.getImgUrl(),imageView);
                          /*  if(data.getImgUrl().equals("1")){
                                imageView.setImageResource(R.mipmap.hang_first);
                            }else if(data.getImgUrl().equals("2")){
                                imageView.setImageResource(R.mipmap.hang_second);
                            }else if(data.getImgUrl().equals("3")){
                                imageView.setImageResource(R.mipmap.hang_third);
                            }*/
                            //   imageView.setImageDrawable(getResources().getDrawable(Integer.valueOf(data.getImgUrl())));



                    injector
                            .text(R.id.tvUSDT,bigDecimal.stripTrailingZeros().toPlainString())
                            .text(R.id.tvRMB,"≈ "+unit+multifyDouble(Double.toString(data.getClose()),rate,data.getScale()))
                          //  .textColor(R.id.tvUSDT,data.isUp() ? getResources().getColor(R.color.hangGreen) : getResources().getColor(R.color.hangRed))
                            .text(R.id.tvCode, data.getLCode())
                          //  .text(R.id.tvRate, changerate+(data.isUp()?" ↑":" ↓"))
                            .background(R.id.llRate, data.isUp()?R.drawable.hang_shape_circle_zuixin1:R.drawable.hang_shape_circle_zuixin2)
                            .textColor(R.id.tvRate, getResources().getColor(R.color.hangBgDeep))
                    ;
                    if(type==1){
                        String code = data.getSymbol();
                   String p = String.valueOf(new BigDecimal(data.getVolume()).divide(new BigDecimal(1000),BigDecimal.ROUND_DOWN).intValue()).length()<7?
                           String.valueOf(new BigDecimal(data.getVolume()).divide(new BigDecimal(1000),BigDecimal.ROUND_DOWN).intValue())+"k":
                           String.valueOf(new BigDecimal(data.getVolume()).divide(new BigDecimal(1000000),BigDecimal.ROUND_DOWN).intValue()).length()>7?
                           String.valueOf(new BigDecimal(data.getVolume()).divide(new BigDecimal(1000000000),BigDecimal.ROUND_DOWN).intValue())+"B":String.valueOf(new BigDecimal(data.getVolume()).divide(new BigDecimal(1000000),BigDecimal.ROUND_DOWN).intValue())+"M";
                        injector.text(R.id.tvRate,p);
                    }else if(type==2){
                        injector.text(R.id.tvRate, changerate);
                    }
                }
                ClickUtil.click(injector.findViewById(R.id.hang_main), () -> {
                    if (!isSlide) {
                        ARouter.getInstance().build(RConfig.HANG_MARKET)
                                .withString(Constans.CODE, data.getSymbol())
                                .withString(Constans.QU, qu)
                                .navigation();
                    }
                });
            }
        });
        slimAdapter.attachTo(recyclerView).updateData(new ArrayList());
        LiveDataBus.get().with(1880,Integer.class).observe(this,this::refresh);
    }

    private void refresh(Integer integer) {
        type = integer;
        initData();
    }


    public void refreshRate(RateBean bean){
             rate = bean.getRate();
             unit = bean.getSimple();
             slimAdapter.notifyDataSetChanged();
        }
    public void updateUI(List<CoinBean1> data) {
        if (includeEmpty == null || slimAdapter == null) {
            return;
        }
        if (data == null || data.size() == 0) {
            slimAdapter.updateData(new ArrayList<>());
            includeEmpty.setVisibility(View.VISIBLE);
            return;
        }
        includeEmpty.setVisibility(View.GONE);
        Comparator<CoinBean1> comparator = new Comparator<CoinBean1>(){
            @Override
            public int compare(CoinBean1 o1, CoinBean1 o2) {
                if(type==1){
                  return o1.getVolume()-o2.getVolume()>0?-1:1;
                }else {
                    return o1.getChg()-o2.getChg()>0?-1:1;
                }

            }
        };
        Collections.sort(data,comparator);

          /*  for(CoinBean1 bean1:data){
                bean1.setImgUrl("");
            }*/
           /* data.get(0).setImgUrl("1");
            data.get(1).setImgUrl("2");
            data.get(2).setImgUrl("3");*/


        slimAdapter.updateData(data.subList(0,10));

    }


    public void refreshCoin(CoinBean1 coinBean) {
        if (slimAdapter == null || slimAdapter.getData() == null || slimAdapter.getData().size() == 0) {
            return;
        }

        Flowable.fromIterable(slimAdapter.getData())
                .filter(item -> item.getSymbol().equals(coinBean.getSymbol()))
                .take(10)
                .subscribe(item -> {
                    int position = slimAdapter.getData().indexOf(item);
                   List<CoinBean1> list=slimAdapter.getData();
                   coinBean.setImgUrl(item.getImgUrl());
                            list.set(position, coinBean);
                    Comparator<CoinBean1> comparator = new Comparator<CoinBean1>() {
                        @Override
                        public int compare(CoinBean1 o1, CoinBean1 o2) {
                            if(type==1){
                                return o1.getVolume()-o2.getVolume()>0?-1:1;
                            }else {
                                return o1.getChg()-o2.getChg()>0?-1:1;
                            }

                        }
                    };
                    Collections.sort(list,comparator);
                    /*  for(CoinBean1 bean1:list){
                          bean1.setImgUrl("");
                      }*/
                      /*  list.get(0).setImgUrl("1");
                        list.get(1).setImgUrl("2");
                        list.get(2).setImgUrl("3");*/

                    slimAdapter.updateData(list);
                   // slimAdapter.notifyItemChanged(position, 1);
                }, e -> {
                });
    }


    @Override
    public void initData(){
        if(type==1){
            textView1.setText(R.string.hang_name);
            textView2.setText(R.string.hang_new);
            textView3.setText(R.string.hang_chengjiao);
        }else if(type==2){
            textView1.setText(R.string.hang_hang_fragment_coin1300);
            textView2.setText(R.string.hang_hang_fragment_coin150);
            textView3.setText(R.string.hang_hang_activity_market_scroll350);
        }
        mPresenter.getProduct();
        mPresenter.getRate("USD","CNY");
        mPresenter.initNewSocket();
        LiveDataBus.get().with(RxBusCode.NEWCODEBEAN,CoinBean1.class)
                .observe(this, this::refreshCoin);
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
            } else {
                userData = null;
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
    String unit ="￥";
    public void setUI(RateBean bean){
      rate = bean.getRate();
      unit = bean.getSimple();
    }
}
