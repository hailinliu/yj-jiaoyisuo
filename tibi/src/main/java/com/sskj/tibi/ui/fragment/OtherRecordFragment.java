package com.sskj.tibi.ui.fragment;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.tibi.R;
import com.sskj.tibi.R2;
import com.sskj.tibi.bean.OtherRecordBean;
import com.sskj.tibi.presenter.OtherRecordFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 账单记录其他
 */
@Route(path = RConfig.TIBI_FRAGMENT_OTHER_RECORD)
public class OtherRecordFragment extends BaseFragment<OtherRecordFragmentPresenter> {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    @Autowired
    String type;
    @Autowired
    String code;
    private MVCCoolHelper<List<OtherRecordBean>> mvcHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.tibi_fragment_other_record;
    }

    @Override
    public OtherRecordFragmentPresenter getPresenter() {
        return new OtherRecordFragmentPresenter();
    }

    @Override
    protected void initData() {
//        mPresenter.getSearchType();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        code = "USDT";

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 20))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.tibiDivider))
        );
        SlimAdapter slimAdapter = SlimAdapter.create().register(R.layout.tibi_recy_item_other_record, new SlimInjector<OtherRecordBean>() {
            @Override
            public void onInject(OtherRecordBean data, IViewInjector injector, List payloads) {

                injector
                        .text(R.id.tvDesc, data.getDetail())
                        .text(R.id.tvNum, data.getMoney() + " " + data.getStockCode())
                        .text(R.id.tvTime, data.getCreateTime())
                ;
            }
        }).attachTo(recyclerView).updateData(new ArrayList());
        ModelRx2DataSource<OtherRecordBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<OtherRecordBean>)
                page -> mPresenter.getData(page + "", code));
        mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();
    }


}
