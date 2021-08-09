package com.sskj.hangqing.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.tablayout.CommonTabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.base.App;
import com.sskj.common.box.imageloader.GlideRoundImageLoader;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.rxbus2.Subscribe;
import com.sskj.common.rxbus2.ThreadMode;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.HangqingBannerBean;
import com.sskj.hangqing.bean.NoticeBean;
import com.sskj.hangqing.bean.ZixunListBean;
import com.sskj.hangqing.box.ZoomOutPageTransformer;
import com.sskj.hangqing.component.DaggerUserDataComponent;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.presenter.HangqingFragmentPresenter;
import com.sskj.hangqing.ui.activity.GuideWebActivity;
import com.sskj.hangqing.util.MyViewPager;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.AppCircleCheckUtil;
import com.sskj.lib.util.ChineseUtil;
import com.sskj.lib.util.QRCodeUtil;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

@SuppressWarnings("ALL")
@Route(path = RConfig.HANG_HANGQING)
public class HangqingFragment extends BaseFragment<HangqingFragmentPresenter> {

    @BindView(R2.id.banner)
    Banner banner;
    @BindView(R2.id.tvNotice)
    TextSwitcher tvNotice;
    @BindView(R2.id.tvMoreNotice)
    ImageView tvMoreNotice;
    @BindView(R2.id.image_start)
    ImageView image;
    @BindView(R2.id.image_sousuo)
    ImageView image_sosuo;
    @BindView(R2.id.image_saoma)
    ImageView image_saoma;
    @BindView(R2.id.ll_fabi)
    LinearLayout ll_fabi;
   /* @BindView(R2.id.ll_choujiang)
    LinearLayout ll_choujiang;*/
    @BindView(R2.id.ll_yaoqing)
    LinearLayout ll_yaoqing;
    @BindView(R2.id.tv_zhangfubang)
    TextView tvZhangFuBang;
    @BindView(R2.id.tv_chengjiaobang)
    TextView tvChengJiaoBang;
    @BindView(R2.id.viewpager)
    MyViewPager viewPager;
   /* @BindView(R2.id.llHelp)
    LinearLayout llHelp;
    @BindView(R2.id.llPublish)
    LinearLayout llPublish;
    @BindView(R2.id.llQuickBuy)
    LinearLayout llQuickBuy;*/
   /*@BindView(R2.id.ll_jiaoyizhinan)
   LinearLayout llJiaoYiZhiNan;*/
   /* @BindView(R2.id.commonTabLayout)
    CommonTabLayout layout;*/
   /* @BindView(R2.id.ll_bibijiaoyi)
    LinearLayout llbibijiaoyi;
    @BindView(R2.id.ll_assert_list)
    LinearLayout llassertlist;
    @BindView(R2.id.ll_help_center)
    LinearLayout llHelpCenter;*/
    private Disposable noticeDispo;
    private NoticeBean.ContentBean noticeList;
    @BindView(R2.id.coolRefreshView)
    SmartRefreshLayout coolRefreshView;
    @BindView(R2.id.ll_haoyou)
    LinearLayout ll_haoyou;
/*    @BindView(R2.id.specialCoinFragment)
    SpecialCoinFragment specialCoinFragment;*/
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;
    private Disposable bannerDispo;
    private SlimAdapter<ZixunListBean> slimAdapter;
    private String QRString;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_hangqing;
    }

    @Override
    public HangqingFragmentPresenter getPresenter() {
        return new HangqingFragmentPresenter();
    }
    private List<Fragment> viewList = new ArrayList<>();
    @Override
    public void initView() {
        DaggerUserDataComponent.create().inject(this);
        RxBus.getDefault().register(this);
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
            } else {
                userData = null;
            }
        });
    viewList.add((Fragment)ARouter.getInstance().build(RConfig.HANG_FRAGMENT_COIN_LIST).withInt("type",2).navigation());
    viewList.add((Fragment)ARouter.getInstance().build(RConfig.HANG_FRAGMENT_COIN_LIST).withInt("type",1).navigation());
        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return viewList.get(position);
            }

            @Override
            public int getCount() {
                return viewList.size();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0){
                    tvZhangFuBang.setTextColor(getResources().getColor(R.color.libTextWhite));
                    tvChengJiaoBang.setTextColor(getResources().getColor(R.color.libText_6));
                    LiveDataBus.get().with(1880,Integer.class).postValue(2);

                }else if(position==1){
                    tvChengJiaoBang.setTextColor(getResources().getColor(R.color.libTextWhite));
                    tvZhangFuBang.setTextColor(getResources().getColor(R.color.libText_6));
                    LiveDataBus.get().with(1880,Integer.class).postValue(1);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
      /*  ClickUtil.click(llJiaoYiZhiNan, () -> {
           // RxBus.getDefault().send(1230);
            ARouter.getInstance().build(RConfig.HANG_FRAGMENT_GUIDE_LIST).navigation();

        });*/
        ClickUtil.click(ll_haoyou, () -> {
           // ARouter.getInstance().build(RConfig.PROMOTIONACTIVITY).navigation();

        });
        ClickUtil.click(ll_fabi,()->{
           // ARouter.getInstance().build(RConfig.FABI_FRAGMENT_FABI_MAIN).navigation();
            ToastUtil.showShort(App.INSTANCE.getString(R.string.hang_zanwei));

        });
        ClickUtil.click(image_saoma,()->{
            //ToastUtil.showShort("暂未开放");
            if(userData!=null){
                Postcard postcard = ARouter.getInstance()
                        .build(RConfig.QUICKMARK_QRCODE);
                LogisticsCenter.completion(postcard);
                Intent intent = new Intent(getActivity(), postcard.getDestination());
                intent.putExtras(postcard.getExtras());
                startActivityForResult(intent, Constans.SACN_QR);
                //Intent intent = new Intent(this,(BaseActivity)ARouter.getInstance().build(RConfig.QUICKMARK_QRCODE).navigation());
               // startActivityForResult();
                //ARouter.getInstance().build(RConfig.QUICKMARK_QRCODE).navigation(getActivity(),Constans.SACN_QR);
            }else {
                ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }

        });
        ClickUtil.click(image_sosuo,()->{
            //ToastUtil.showShort("暂未开放");
            ARouter.getInstance().build(RConfig.SOLOACTIVITY).navigation();
        });
        ClickUtil.click(image,()->{
            ARouter.getInstance().build(RConfig.BIBI_NE_SLIDE).navigation();
        });
      /*  ClickUtil.click(ll_choujiang,()->{
            ToastUtil.showShort("暂未开放");
        });*/
        ClickUtil.click(ll_yaoqing,()->{
            ARouter.getInstance().build(RConfig.INVITEACTIVITY).navigation();
           // ToastUtil.showShort("暂未开放");
        });
        ClickUtil.click(tvZhangFuBang,()->{
            tvZhangFuBang.setTextColor(getResources().getColor(R.color.libTextWhite));
            tvChengJiaoBang.setTextColor(getResources().getColor(R.color.libTextGray));
            viewPager.setCurrentItem(0);
           // ARouter.getInstance().build(RConfig.HANG_FRAGMENT_COIN_LIST).withInt("type",1).navigation();

        });
        ClickUtil.click(tvChengJiaoBang,()->{
            tvChengJiaoBang.setTextColor(getResources().getColor(R.color.libTextWhite));
            tvZhangFuBang.setTextColor(getResources().getColor(R.color.libTextGray));
            viewPager.setCurrentItem(1);
        });
       /* ClickUtil.click(llbibijiaoyi, () -> {
           RxBus.getDefault().send(1234);

        });
        ClickUtil.click(llassertlist, () -> {
            ARouter.getInstance().build(RConfig.MINER_ASSERTS_ACTIVITY).navigation();

        });
        ClickUtil.click(llHelpCenter, () -> {
            ARouter.getInstance().build(RConfig.HANG_GUIDE_WEB).withBoolean("isHelp",true).navigation();

        });*/
     /*   ClickUtil.click(llHelp, () -> {
            ARouter.getInstance().build(RConfig.MINE_HELP_CENTER).navigation();

        });*/

      /*  ClickUtil.click(llPublish, () -> {
            if (!AppCircleCheckUtil.checkLogin(getActivity(), userData)) {
                return;
            }
            if (!AppCircleCheckUtil.checkHighAuth(getActivity(), userData)) {  // 高级实名
                return;
            }
            if (!AppCircleCheckUtil.checkShop(getActivity(), userData)) {  //是否是商家
                return;
            }
            if (!AppCircleCheckUtil.checkTradePwd(getActivity(), userData)) {  // 资金密码
                return;
            }
            ARouter.getInstance().build(RConfig.FABI_PUBLISH)
                    .withBoolean(Constans.IS_BUY, true)
                    .navigation();
        });*/
      /*  ClickUtil.click(llQuickBuy, () -> {
            if(userData==null){
                ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            }else
            ARouter.getInstance().build(RConfig.HANG_GUIDE_WEB).withBoolean("isQuickBuy",true).navigation();
           *//* LiveDataBus.get().with(RxBusCode.CHANGE_MAIN_TAB, Integer.class).postValue(4);
            LiveDataBus.get().with(RxBusCode.CHANGE_QUICK_BUY).postValue(1);*//*

        });*/

        ClickUtil.click(tvMoreNotice, () -> ARouter.getInstance().build(RConfig.HANG_GONGGAO_LIST).navigation());
        banner.setImageLoader(new GlideRoundImageLoader());
        tvNotice.setFactory(() -> {
            TextView textView = new TextView(getActivity());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PT, 26);
            textView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setSingleLine();
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextColor(ContextCompat.getColor(App.INSTANCE, R.color.libTextWhite));
            return textView;
        });

       /* getChildFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayout, (Fragment) ARouter.getInstance().build(RConfig.HANG_FRAGMENT_COIN_LIST).withInt("type",1).navigation())
                .commitAllowingStateLoss();*/

    }

    @Override
    public void initData() {

        mPresenter.getBanner();
        mPresenter.getNotice();

    }

    @Override
    protected void initEvent() {
       // super.initEvent();
        coolRefreshView.setEnableLoadMore(false);
        coolRefreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.getBanner();
               // mPresenter.getNotice();
                RxBus.getDefault().send(RxBusCode.REFRESH_RE);
               // LiveDataBus.get().with(RxBusCode.REFRESH).postValue(1);
            }
        });
    }

    public void updateBanner(List<HangqingBannerBean> data) {
        if (coolRefreshView != null && coolRefreshView.isRefreshing()) {
            coolRefreshView.finishRefresh();
        }
        if (banner == null)
            return;

        ArrayList<String> strings = new ArrayList<>();
        for (HangqingBannerBean bannerItem : data) {
            strings.add(HttpConfig.BASE_URL+ bannerItem.getUrl());
        }

        banner.setImages(strings);
        banner.setImageLoader(new GlideRoundImageLoader());
        banner.setOffscreenPageLimit(3);
        banner.setDelayTime(2000);
        banner.start();
    }

    @Override
    public void onDestroy() {
        DisposUtil.close(noticeDispo);
        DisposUtil.close(bannerDispo);
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constans.SACN_QR) {
            String scan_result = data.getStringExtra("scan_result");
           // etAddress.setText(TextUtils.isEmpty(scan_result) ? "" : scan_result);
            QRString = TextUtils.isEmpty(scan_result) ? "" : scan_result;
            mPresenter.checkCode(QRString);
        }
    }
    public void updateNotice(List<NoticeBean.ContentBean> notice) {
        if (coolRefreshView != null && coolRefreshView.isRefreshing()) {
            coolRefreshView.finishRefresh();
        }
        if (tvNotice != null) {
            if (notice.size() > 0) {
                DisposUtil.close(noticeDispo);
                noticeDispo = Flowable.interval(0, 5, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(i -> {
                            if (tvNotice != null) {
                                noticeList = notice.get((int) (i % notice.size()));
                                tvNotice.setText(noticeList.getTitle());
                                ClickUtil.click(tvNotice, () -> {
                                    Intent intent = new Intent(getContext(), GuideWebActivity.class);
                                    intent.putExtra(Constans.IS_HANGQING_NOTICE, true);
                                    intent.putExtra(Constans.HEAD, App.INSTANCE.getString(R.string.hangqinggonggaoListActivity2));
                                    intent.putExtra(Constans.TITLE, noticeList.getTitle());
                                    intent.putExtra(Constans.CONTENT, noticeList.getContent());
                                    intent.putExtra(Constans.TIME, noticeList.getCreateTime());
                                    startActivity(intent);
                                });
                            }
                        }, System.out::println);
            }
        }
    }

    public void setData(String msg) {
        if(msg.contains("SUCCESS")){
        ARouter.getInstance().build(RConfig.QRLOGINACTIVITY).withString("code",QRString).navigation();
           //mPresenter.saomaLogin(QRString);
        }
    }


}
