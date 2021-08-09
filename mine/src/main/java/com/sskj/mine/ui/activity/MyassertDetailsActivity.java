package com.sskj.mine.ui.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;

import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.lib.util.DateUtils;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.bean.MyAssertDetailBean;
import com.sskj.mine.presenter.MyassertDetailsActivityPresenter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;


@Route(path = RConfig.MYASSERTDETAILSACTIVITY)
public class MyassertDetailsActivity extends BaseActivity<MyassertDetailsActivityPresenter> {
    @Autowired
    String code;
    @Autowired
    String keyong;
    @Autowired
    String dongjie;
    @Autowired
    String zehe;
    @Autowired
    int type;
    @BindView(R2.id.tv_keyong)
    TextView tvKeyong;
    @BindView(R2.id.tv_US)
    TextView tvUs;
    @BindView(R2.id.tv_tousdt)
    TextView tvToUsdt;
    @BindView(R2.id.tv_dongjie)
    TextView tvDongjie;
   /* @BindView(R2.id.tv_zehe)
    TextView tvZehe;*/

    @BindView(R2.id.rv)
    RecyclerView rv;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.ivBack)
    LinearLayout ivBack;
    String id = SPUtil.get(SPConfig.ID,"");
    private SlimAdapter<MyAssertDetailBean.ListBean> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_asserts_detail;
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        tvTitle.setText(App.INSTANCE.getString(R.string.mineUsdt_zong)+"("+code+")");
        tvUs.setText(new BigDecimal(keyong).add(new BigDecimal(dongjie)).toString());
       // tvToUsdt.setText(zehe);
        tvKeyong.setText(keyong);
        tvDongjie.setText(dongjie);
       // tvZehe.setText(zehe);
        setTitle(code);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = SlimAdapter.create().register(R.layout.mine_activity_asserts_detail_item, new SlimInjector<MyAssertDetailBean.ListBean>() {
            @Override
            public void onInject(MyAssertDetailBean.ListBean bean, IViewInjector iViewInjector, List list) {
                iViewInjector.text(R.id.tv_num,bean.getMoney())
                        .text(R.id.tv_type,bean.getDetail())
                        .text(R.id.tv_time, TextUtils.isEmpty(bean.getCreateTime())?"":DateUtils.timeStamp2Date(bean.getCreateTime()+"",""))
                        .visibility(R.id.view,TextUtils.isEmpty(bean.getCreateTime())?View.GONE:View.VISIBLE);
                if(bean.getStatus()==1){
                   iViewInjector.text(R.id.tv_status,getString(R.string.mine_assert_record_item));
                   iViewInjector.textColor(R.id.tv_status,getResources().getColor(R.color.libGreen));
                }else if(bean.getStatus()==2){
                    iViewInjector.text(R.id.tv_status,getString(R.string.mine_assert_record_item_deal));
                    iViewInjector.textColor(R.id.tv_status,getResources().getColor(R.color.minestatus));
                }else if(bean.getStatus()==3){
                    iViewInjector.text(R.id.tv_status,getString(R.string.mine_assert_record_item_fail));
                    iViewInjector.textColor(R.id.tv_status,getResources().getColor(R.color.libRed));
                }
            }
        }).attachTo(rv).updateData(new ArrayList());
        //super.initView();
    }

    @Override
    protected void initData() {
        ModelRx2DataSource<MyAssertDetailBean.ListBean> dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<MyAssertDetailBean.ListBean>)
                page -> mPresenter.getMyAssertDetails(id,code,page,10,type),10);
        MVCCoolHelper<List<MyAssertDetailBean.ListBean>> mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(adapter);
        mvcHelper.refresh();
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
      /*  coolRefreshView.addOnPullListener(new SimpleOnPullListener() {
            @Override
            public void onRefreshing(CoolRefreshView refreshView) {
                mPresenter.getMyAssertDetails(id,code,1,10,type);
            }
        });*/
    }

    @Override
    public MyassertDetailsActivityPresenter getPresenter() {
        return new MyassertDetailsActivityPresenter();
    }


  /*  public void getData(MyAssertDetailBean data) {
        if(coolRefreshView!=null&&coolRefreshView.isRefreshing()){
            coolRefreshView.setRefreshing(false);
        }
       List<MyAssertDetailBean.ListBean>  list = data.getList();
       adapter.updateData(list);
    }*/
}
