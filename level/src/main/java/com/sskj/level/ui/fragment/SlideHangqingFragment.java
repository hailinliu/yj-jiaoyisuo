package com.sskj.level.ui.fragment;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.ItemClickSupport;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.util.DisposUtil;
import com.sskj.level.R;
import com.sskj.level.R2;
import com.sskj.level.presenter.SlideHangqingFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.BibiCoinType;
import com.sskj.lib.bean.CoinBean;
import com.sskj.lib.bean.LevelCoinType;
import com.sskj.lib.bean.enums.ColorEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.mvchelper.RefreshDataSource;
import com.sskj.lib.util.CoinImgUtil;
import com.sskj.lib.util.CoinUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

/**
 * 合约侧滑
 */
@Route(path = RConfig.LEVEL_FRAGMENT_SLIDE)
public class SlideHangqingFragment extends BaseFragment<SlideHangqingFragmentPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    @Autowired
    String qu;
    private MVCCoolHelper<List<CoinBean>> mvcHelper;
    private SlimAdapter<CoinBean> slimAdapter;
    private Disposable refreshDispo;

    @Override
    protected int getLayoutId() {
        return R.layout.level_fragment_slide_hangqing;
    }

    @Override
    public SlideHangqingFragmentPresenter getPresenter() {
        return new SlideHangqingFragmentPresenter();
    }


    public void refreshCoin(CoinBean coinBean) {
        refreshDispo = Flowable.fromIterable(slimAdapter.getData())
                .filter(item -> item.getCode().equals(coinBean.getCode()))
                .subscribe(item -> {
                    slimAdapter.getData().set(slimAdapter.getData().indexOf(item), coinBean);
                    slimAdapter.notifyItemChanged(slimAdapter.getData().indexOf(coinBean), 1);
                });
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        initAdapter();
    }

    @Override
    protected void initData() {
        LiveDataBus.get().with(RxBusCode.PUSH_COIN_BEAN1, CoinBean.class)
                .observe(this, this::refreshCoin);
    }

    @SuppressWarnings("unchecked")
    private void initAdapter() {
        slimAdapter = SlimAdapter.create().register(R.layout.level_recy_item_slide, new SlimInjector<CoinBean>() {
            @Override
            public void onInject(CoinBean stock, IViewInjector injector, List<Object> payloads) {
                injector
                        .text(R.id.tvType, stock.getCode().toUpperCase().replace("_", "/").split("/")[0])
                        .text(R.id.tvPrice, CoinUtil.keepCoinPrice(stock.getCode(), stock.getPrice()))
                        .text(R.id.tvTypeRight, "/" + stock.getCode().toUpperCase().replace("_", "/").split("/")[1])
                        .textColor(R.id.tvPrice, color(stock.isUp() ? ColorEnum.UP.getColor() : ColorEnum.DOWN.getColor()));
            }
        }).attachTo(recyclerView).updateData(new ArrayList());
        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    LiveDataBus.get().with(RxBusCode.BIBI_CHANGE_COIN, BibiCoinType.class)
                            .postValue(new BibiCoinType(slimAdapter.getData().get(position).getCode()));
                    getActivity().finish();
                });
        RefreshDataSource<CoinBean> refreshDataSource = new RefreshDataSource<CoinBean>() {
            @Override
            public Flowable<List<CoinBean>> loadData() {
                return mPresenter.getProduct(qu, null);
            }
        };
        mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(refreshDataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();
    }

    @Override
    public void onDestroy() {
        DisposUtil.close(refreshDispo);
        super.onDestroy();
    }
}
