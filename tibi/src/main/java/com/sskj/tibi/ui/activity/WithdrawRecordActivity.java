package com.sskj.tibi.ui.activity;



import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.tibi.R;
import com.sskj.tibi.R2;
import com.sskj.tibi.bean.CongRecordBean;
import com.sskj.tibi.bean.TibiRecordBean;
import com.sskj.tibi.presenter.WithdrawRecordActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;



@Route(path = RConfig.TIBI_WITHDRAW_RECORD)
public class WithdrawRecordActivity extends BaseActivity<WithdrawRecordActivityPresenter> {
    @BindView(R2.id.rv)
    RecyclerView rv;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    private SlimAdapter slimAdapter;
    @Autowired
    String type;
    @Override
    protected int getLayoutId() {
        return R.layout.tibi_activity_withdraw_record;
    }

    @Override
    public WithdrawRecordActivityPresenter getPresenter() {
        return new WithdrawRecordActivityPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        setTitle(App.INSTANCE.getString(R.string.tibiwithdrawRecordActivity1));
    }

    @Override
    protected void initData() {
       // mPresenter.getTibiRecord("1","20",type);
        rv.setLayoutManager(new LinearLayoutManager(this));
        slimAdapter = SlimAdapter.create().register(R.layout.tibi_record_item, new SlimInjector<TibiRecordBean.DataBean.ContentBean>() {
            @Override
            public void onInject(TibiRecordBean.DataBean.ContentBean bean, IViewInjector iViewInjector, List list) {
                iViewInjector.text(R.id.tv_num,""+bean.getTotalAmount())
                        .text(R.id.tv_status,""+(bean.getStatus()==0?App.INSTANCE.getString(R.string.tibi_sure):bean.getStatus()==1?App.INSTANCE.getString(R.string.tibi_already):bean.getStatus()==2?App.INSTANCE.getString(R.string.tibi_fail):""))
                        .text(R.id.tv_time,""+bean.getCreateTime());
            }
        }).attachTo(rv).updateData(new ArrayList());
        ModelRx2DataSource<TibiRecordBean.DataBean.ContentBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<TibiRecordBean.DataBean.ContentBean>)
                page -> mPresenter.getTibiRecord(page + "", "20",type), 20);
        MVCCoolHelper<List<TibiRecordBean.DataBean.ContentBean>> mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();
    }

    public void setData(List<TibiRecordBean.DataBean.ContentBean> content) {
    }
}
