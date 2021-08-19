package com.sskj.hangqing.ui.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sskj.common.adapter.ItemClickSupport;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.rxbus2.Subscribe;
import com.sskj.common.rxbus2.ThreadMode;
import com.sskj.common.util.DisposUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.NewChartBean;
import com.sskj.hangqing.presenter.SpecialCoinFragmentPresenter;
import com.sskj.hangqing.util.MyViewPager;
import com.sskj.lib.BuildConfig;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.bean.HangQingBean;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.enums.ColorEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.util.CoinImgUtil;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.ImageUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

@SuppressWarnings("unchecked")
@Route(path = RConfig.HANG_FRAGMENT_SPECIAL_COIN)
public class SpecialCoinFragment extends BaseFragment<SpecialCoinFragmentPresenter> {
    @BindView(R2.id.viewPager)
    MyViewPager viewPager;
    @BindView(R2.id.viewDot0)
    View viewDot0;
    @BindView(R2.id.viewDot1)
    View viewDot1;
  /*  @BindView(R2.id.viewDot2)
    View viewDot2;*/
  private static final String TAG = "SpecialCoinFragment";
    private SlimAdapter<CoinBean1> slimAdapter;
    private List<SlimAdapter<CoinBean1>> list = new ArrayList<>();
    private Disposable coinDispo;
    private List<List<CoinBean1>> maps =new ArrayList<>();
    private RecyclerView recyclerView;
    // private RecyclerView recyclerView;
    private String rate;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_special_coin;
    }

    @Override
    public SpecialCoinFragmentPresenter getPresenter() {
        return new SpecialCoinFragmentPresenter();
    }

    @Override
    public void initView() {
        RxBus.getDefault().register(this);
    }

    @Override
    public void initData() {
        LiveDataBus.get().with(RxBusCode.RATE, RateBean.class).observe(this,this::refreshRate);
        LiveDataBus.get().with(RxBusCode.NEWCODEBEAN, CoinBean1.class)
                .observe(this, this::refreshCoin);
        mPresenter.initNewSocket();
       // LiveDataBus.get().with(RxBusCode.PUSH_COIN_BEAN1, NewChartBean.class).observe(this,this::refreshCoin);
   /*     LiveDataBus.get().with(RxBusCode.PUSH_COIN_BEAN, NewChartBean.class)
                .observe(this, this::refreshCoin);*/
    }

public boolean equalZero(BigDecimal decimal){
      return   decimal.compareTo(BigDecimal.ZERO)==0;
}
    private void initRecycle() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0){
                    viewDot0.setBackground(getResources().getDrawable(R.drawable.hang_shape_dot_select));
                    viewDot1.setBackground(getResources().getDrawable(R.drawable.hang_shape_dot_unselect));
                }else {
                    viewDot1.setBackground(getResources().getDrawable(R.drawable.hang_shape_dot_select));
                    viewDot0.setBackground(getResources().getDrawable(R.drawable.hang_shape_dot_unselect));
                }
              //  slimAdapter.updateData(maps.get(position));
            }

            @Override
            public void onPageSelected(int position) {
              //  slimAdapter.updateData(maps.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(BuildConfig.DEBUG){
                Log.e(TAG, "onPageScrollStateChanged: "+state);}
            }
        });
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return maps.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }
            public String multifyDouble(String a,String b){
                BigDecimal a1 =  new BigDecimal(a);
                BigDecimal b1 = new BigDecimal(b);
                return a1.multiply(b1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"";
            }
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view1 = View.inflate(getActivity(),R.layout.hang_fragment_special_coin_item,null);
                recyclerView = view1.findViewById(R.id.recycle_up);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
              //  recyclerView.setAdapter(new MyAdapter(R.layout.hang_item_home,maps.get(position)));
                slimAdapter = SlimAdapter.create().register(R.layout.hang_item_home, new SlimInjector<CoinBean1>() {
                    @Override
                    public void onInject(CoinBean1 data, IViewInjector iViewInjector, List list) {
                        BigDecimal bigDecimal = new BigDecimal(data.getClose()).setScale(CoinUtil.getPriceKeepNum(data.getSymbol()), RoundingMode.DOWN).stripTrailingZeros();
                       BigDecimal a =  new BigDecimal(Double.toString(data.getChg()));
                       BigDecimal b = new BigDecimal(Integer.toString(100));
                      String changerate = a.multiply(b).doubleValue()>0?"+"+a.multiply(b).doubleValue()+"%":a.multiply(b).doubleValue()+"%";
                  if(rate!=null){
                      iViewInjector.text(R.id.tvCoinRmbFirst, (equalZero(bigDecimal)?"0":"≈ "+unit+multifyDouble(bigDecimal.toPlainString(),rate)));
                  }
                          iViewInjector
                                  .text(R.id.tvCoinTypeFirst, CoinUtil.showName(data.getSymbol()))
                                  .text(R.id.tvCoinUSDTFirst, equalZero(bigDecimal)?"0":bigDecimal.toPlainString())
                                  .text(R.id.tvCoinRateFirst, changerate)
                                  .textColor(R.id.tvCoinUSDTFirst, color(data.isUp() ? ColorEnum.UP.getColor() : ColorEnum.DOWN.getColor()))
                                  .textColor(R.id.tvCoinRateFirst, color(data.isUp() ? ColorEnum.UP.getColor() : ColorEnum.DOWN.getColor()));
                          if (CoinImgUtil.img.containsKey(data.getSymbol())) {
                              ImageUtil.setImage(CoinImgUtil.img.get(data.getSymbol()), (ImageView) iViewInjector.findViewById(R.id.ivCoinTypeFirst));
                          }



                    }
                }).attachTo(recyclerView).updateData(maps.get(position));
                list.add(slimAdapter);
                container.addView(view1);
                ItemClickSupport.addTo(recyclerView)
                        .setOnItemClickListener((recyclerView, i, view) ->
                                ARouter.getInstance().build(RConfig.HANG_MARKET)
                                        .withString(Constans.CODE, slimAdapter.getDataItem(i).getSymbol())
                                        .withString(Constans.QU, "/USDT")
                                        .navigation());
                return view1;



            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
              container.removeView((View) object);
            }
        });

//slimAdapter.updateData(maps.get(0));
    }
    String unit ="￥";
    public void refreshRate(RateBean bean){
        rate = bean.getRate();
        unit = bean.getSimple();
        mPresenter.getData();
       // slimAdapter.notifyDataSetChanged();
    }
    public void updateCoin(List<CoinBean1> coinBeanList) {
        if (coinBeanList != null) {
            coinDispo = Flowable.fromIterable(coinBeanList)
                    .filter(item->item.getSymbol().equals("BTC/USDT")
                            ||item.getSymbol().equals("ETH/USDT")
                            ||item.getSymbol().equals("XRP/USDT")
                            ||item.getSymbol().equals("BCH/USDT")
                            ||item.getSymbol().equals("EOS/USDT")
                            ||item.getSymbol().equals("ADA/USDT"))
                    .take(3)
                    .toList()
                    .subscribe((coinBeans, throwable) -> maps.add(coinBeans.subList(0,3)));
        }
        initRecycle();
    }

    public void refreshCoin(CoinBean1 coinBean) {
        if (slimAdapter == null || slimAdapter.getData() == null || slimAdapter.getData().size() == 0) {
            return;
        }
        for(SlimAdapter<CoinBean1> slimAdapter:list){
               coinDispo = Flowable.fromIterable(slimAdapter.getData())
                       .filter(item -> item.getSymbol().equals(coinBean.getSymbol()))
                       .subscribe(item -> {
                           int position = slimAdapter.getData().indexOf(item);
                           slimAdapter.getData().set(position, coinBean);
                           slimAdapter.notifyItemChanged(position, 1);
                       }, e -> {
                       });
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,code = RxBusCode.REFRESH_RE)
    public void getData(){
        mPresenter.getData();
        mPresenter.initNewSocket();
    }
    @Override
    public void onDestroy() {
        DisposUtil.close(coinDispo);
        RxBus.getDefault().unregister(this);
        super.onDestroy();
    }
    class MyAdapter extends BaseQuickAdapter<CoinBean, BaseViewHolder>{

        public MyAdapter(int layoutResId, @Nullable List<CoinBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CoinBean data) {
            BigDecimal bigDecimal = new BigDecimal(data.getPrice()).setScale(CoinUtil.getPriceKeepNum(data.getCode()), RoundingMode.DOWN).stripTrailingZeros();
            helper
                    .setText(R.id.tvCoinTypeFirst, CoinUtil.showName(data.getCode()))
                    .setText(R.id.tvCoinUSDTFirst, equalZero(bigDecimal)?"0":bigDecimal.toPlainString())
                    .setText(R.id.tvCoinRmbFirst, (equalZero(bigDecimal)?"0":bigDecimal.toPlainString())+" $")
                    .setText(R.id.tvCoinRateFirst, data.getChangeRate())
                    .setTextColor(R.id.tvCoinUSDTFirst, color(data.isUp() ? ColorEnum.UP.getColor() : ColorEnum.DOWN.getColor()))
                    .setTextColor(R.id.tvCoinRateFirst, color(data.isUp() ? ColorEnum.UP.getColor() : ColorEnum.DOWN.getColor()))
                    .setOnItemLongClickListener(R.id.hang_main, new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    return false;
                }
            });
            if (CoinImgUtil.img.containsKey(data.getCode())) {

                ImageUtil.setImage(CoinImgUtil.img.get(data.getCode()), (ImageView) helper.getView(R.id.ivCoinTypeFirst));
            }
        }
    }
}