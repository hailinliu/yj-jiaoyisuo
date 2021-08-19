package com.sskj.mine.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.SuperTextView;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.shizhefei.view.coolrefreshview.SimpleOnPullListener;
import com.sskj.common.adapter.MyFragmentPagerAdapter;
import com.sskj.common.base.App;
import com.sskj.common.rxbus2.Subscribe;
import com.sskj.common.rxbus2.ThreadMode;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.RxSchedulersHelper;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.AppVersionBean;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.AppCircleCheckUtil;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.CopyUtil;
import com.sskj.lib.util.HeySlidingTabLayout;
import com.sskj.lib.util.HideUtil;
import com.sskj.lib.util.TipUtil;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.bean.BIBIBean;
import com.sskj.mine.bean.DataBean;
import com.sskj.mine.bean.MyAssertBean;
import com.sskj.mine.bean.TotalMoneyBean;
import com.sskj.mine.component.DaggerUserDataComponent;
import com.sskj.mine.presenter.MineFragmentPresenter;
import com.sskj.mine.ui.activity.SettingActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

/**
 * @author Hey
 * Create at  2019/05/01
 */
@Route(path = RConfig.MINE_FRAGMENT_MAIN)
public class MineFragment extends BaseFragment<MineFragmentPresenter> {


    @Inject
    UserViewModel userViewModel;
    @BindView(R2.id.supertextview)
    SuperTextView superTextView;
    @BindView(R2.id.image_eye)
    ImageView image_eye;
    @BindView(R2.id.tv_zongzichan)
    TextView tv_zong;
    @BindView(R2.id.tv_price)
    TextView tvPrice;
    @BindView(R2.id.tv_cny)
    TextView tvCny;
    @BindView(R2.id.tv_congbi)
    TextView tvRecharge;
    @BindView(R2.id.tv_tibi)
    TextView tvWithdraw;
    @BindView(R2.id.tv_huazhuan)
    TextView tvHuazhuan;
    @BindView(R2.id.tabs)
    HeySlidingTabLayout tabLayout;
    @BindView(R2.id.viewpager)
    ViewPager viewpager;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView refreshView;
    private SafeSettingBean userData;
    private boolean isSee = true;
    private String[] titles = {App.INSTANCE.getString(R.string.mine_bibi_zhanghu),App.INSTANCE.getString(R.string.mine_fabi_zhanghu),App.INSTANCE.getString(R.string.mine_heyue_zhanghu)};
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<BottomSheetUtil.ItemBean> list = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.mine_fragment_mine;
    }

    @Override
    public MineFragmentPresenter getPresenter() {
        return new MineFragmentPresenter();
    }

    @Override
    public void initView() {
        DaggerUserDataComponent.create().inject(this);
        fragments.clear();
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.ONEFRAGMENT).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.TWOFRAGMENT).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(RConfig.THIRDFRAGMENT).navigation());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(),fragments);
        viewpager.setCurrentItem(0);
        viewpager.setOffscreenPageLimit(1);
        viewpager.setAdapter(adapter);
        tabLayout.setTabSpaceEqual(true);
        tabLayout.setIndicatorWidthEqualTitle(false);
        tabLayout.setTextsize(15);
        tabLayout.setUnderlineGravity(Gravity.BOTTOM);
        tabLayout.setIndicatorColor(getResources().getColor(R.color.libGreen));
        tabLayout.setTextSelectColor(getResources().getColor(R.color.libGreen));
        tabLayout.setTextUnselectColor(getResources().getColor(R.color.libTextGray));
        tabLayout.setViewPager(viewpager,titles);
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_cny,"CNY"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_eru,"EUR"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_hkd,"HKD"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_rub,"RUB"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_krw,"KRW"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_myr,"MYR"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_twd,"TWD"));
        list.add(new BottomSheetUtil.ItemBean(R.mipmap.lib_usd,"USD"));
        refreshView.addOnPullListener(new SimpleOnPullListener() {
            @Override
            public void onRefreshing(CoolRefreshView refreshView) {
                initData();
               // refreshView.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
            } else {
                userData = null;
            }

        });
        mPresenter.getRate("USD","USD");
        superTextView.setLeftString("$");
        LiveDataBus.get().with(RxBusCode.REFRESH).postValue(1);
        LiveDataBus.get().with(RxBusCode.LOGOUT).observe(this,this::updataView);

    }

    private void updataView(Object o) {
        initView();
    }

    private BottomSheetDialog priceModeSheet;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initEvent() {
    tabLayout.setOnTabSelectListener(new HeySlidingTabLayout.OnTabSelectListener() {
    @Override
    public boolean onTabSelect(int position) {
        viewpager.setCurrentItem(position);
        LiveDataBus.get().with(RxBusCode.REFRESH).postValue(1);
        return false;
    }

    @Override
    public void onTabReselect(int position) {

    }
});
ClickUtil.click(image_eye,()->{
    if(isSee){
        image_eye.setImageDrawable(getResources().getDrawable(R.mipmap.mine_icon_hide_white));
        tvPrice.setText("********");
        tvCny.setText("********");
        isSee = false;
    }else {
        image_eye.setImageDrawable(getResources().getDrawable(R.mipmap.mine_icon_see));
        tvPrice.setText(price+" USDT");
        tvCny.setText(unit+" "+pricecny);
        isSee = true;
    }


});


        ClickUtil.click(superTextView,()->{
            priceModeSheet = BottomSheetUtil.getBottomSheet1(getActivity(), App.INSTANCE.getString(R.string.mine_select), (recyclerView, position, v) -> {
                priceModeSheet.dismiss();
                mPresenter.getRate("USD",list.get(position).content);
                superTextView.setLeftString(list.get(position).content);

            },list);
            priceModeSheet.show();
        });
        ClickUtil.click(tvRecharge, () -> {
            if (!AppCircleCheckUtil.checkLogin(getActivity(), userData)) {
                return;
            }
            ARouter.getInstance().build(RConfig.TIBI_ACTIVITY_RECHARGE).navigation();
        });


        ClickUtil.click(tvWithdraw, () -> {
            if (!AppCircleCheckUtil.checkLogin(getActivity(), userData)) {
                return;
            }

            if (!AppCircleCheckUtil.checkHighAuth(getActivity(), userData)) {
                return;
            }
            ARouter.getInstance().build(RConfig.TIBI_WITHDRAW).withString("email",userData.getEmail()).withString("phone",userData.getMobilePhone()).navigation();
        });


        ClickUtil.click(tvHuazhuan, () -> {
            if (!AppCircleCheckUtil.checkLogin(getActivity(), userData)) {
                return;
            }
            if (!AppCircleCheckUtil.checkHighAuth(getActivity(), userData)) {
                return;
            }
            ARouter.getInstance().build(RConfig.MINER_TRANSFER).navigation();
        });


    }



    @Override
    public void onDestroy() {
        //DisposUtil.close(refreshDispo);
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (userViewModel != null) {
            userViewModel.update();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (userViewModel != null) {
                userViewModel.update();
            }
        }
    }


    public void showEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return;
        }
        TipUtil.getSureTip(getActivity(), "", email, App.INSTANCE.getString(R.string.minemineFragment3), () -> {
            CopyUtil.copy(email);
        });

    }

    public void onVersionDataSuccess(AppVersionBean versionBean) {
        if (versionBean == null) {
            return;
        }
        LiveDataBus.get().with(RxBusCode.SHOW_VERSION)
                .postValue(versionBean);
    }

//获取账户的资金信息
    public void getAssert(MyAssertBean data) {
        if (userData != null) {

            BigDecimal bigDecimal =  new BigDecimal(String.valueOf(Double.valueOf(data.getTotalUsdt())));

        }

    }

    public void setNodata() {
        userData=null;

    }
    @Subscribe(threadMode = ThreadMode.MAIN,code =1100)
    public void setLayout(){
        userData=null;

    }
    String rate;
    String name;
    String unit="￥";
    public void setRate(RateBean bean) {
        if(refreshView!=null&&refreshView.isRefreshing()){
            refreshView.setRefreshing(false);
        }
       name = bean.getName();
       rate =  bean.getRate();
       unit = bean.getSimple();
       // pricecny = new BigDecimal(price).multiply(new BigDecimal(rate)).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
       // tvCny.setText(unit+" "+pricecny);
        mPresenter.getwallet("0","");
    }
    String price;
    String pricecny;
    public void setData(TotalMoneyBean bean) {
        price = bean.getTotal();
        pricecny = new BigDecimal(price).multiply(new BigDecimal(rate)).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        price = new BigDecimal(price).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        tvPrice.setText(price);
        tvCny.setText(unit+" "+pricecny);
    }


}
