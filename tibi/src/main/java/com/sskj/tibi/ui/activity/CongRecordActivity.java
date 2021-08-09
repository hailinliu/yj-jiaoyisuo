package com.sskj.tibi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.CoinListBean;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.ImageUtil;
import com.sskj.mine.bean.TransferRecordBean;
import com.sskj.tibi.R;
import com.sskj.tibi.R2;
import com.sskj.tibi.bean.CongRecordBean;
import com.sskj.tibi.bean.OtherBean;
import com.sskj.tibi.bean.TibiRecordBean;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.presenter.CongRecordActivityPresenter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RConfig.CONGRECORDACTIVITY)
public class CongRecordActivity extends BaseActivity<CongRecordActivityPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @Autowired
    String type;
    @Autowired
    int flag =1;
    @BindView(R2.id.coolRefreshView)
    SmartRefreshLayout coolRefreshView;
    @BindView(R2.id.ll_select)
    LinearLayout llSelect;
    @BindView(R2.id.image)
    ImageView image;
    @BindView(R2.id.tv_type)
    TextView tvType;
    @BindView(R2.id.tv_stubs)
    TextView tv_stubs;
    @BindView(R2.id.tv_type_quan)
    TextView tvTypeQuan;
    @BindView(R2.id.ivRight)
    ImageView ivRight;
    private SlimAdapter slimAdapter;
    ArrayList<CoinListBean.DataBean> list;
    private BottomSheetDialog bottomSheet1;
    private List<String> list1 = new ArrayList<>();
    int page =1;
    int pageMax;
    @Override
    protected int getLayoutId() {
        return R.layout.tibi_activity_cong_list;
    }

    @Override
    protected void initView() {
        //super.initView();
        ARouter.getInstance().inject(this);
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.mipmap.tibi_icon_mo);
        list1.add(App.INSTANCE.getString(R.string.tibi_cong));
        list1.add(App.INSTANCE.getString(R.string.tibi_ti));
        list1.add(App.INSTANCE.getString(R.string.tibi_hua));
        list1.add(App.INSTANCE.getString(R.string.tibi_other));

    }

    @Override
    protected void initData() {
      type = tvType.getText().toString();
        if (flag == 1) {
            page=1;
            setTitle(App.INSTANCE.getString(R.string.tibi_cong_record));
            tv_stubs.setText(App.INSTANCE.getString(R.string.tibi_status));
            llSelect.setVisibility(View.VISIBLE);
            mPresenter.getCoin("0");
            if(listContgtnet.size()>0){
                listContgtnet.clear();
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            slimAdapter = SlimAdapter.create().register(R.layout.tibi_cong_record_item, new SlimInjector<CongRecordBean.DataBean.ContentBean>() {
                @Override
                public void onInject(CongRecordBean.DataBean.ContentBean bean, IViewInjector iViewInjector, List list) {
                    iViewInjector.text(R.id.tv_num, "" + bean.getAmount())
                            .text(R.id.tv_status, "" + (bean.getStatus() == 0 ? App.INSTANCE.getString(R.string.tibi_sure) : bean.getStatus() == 1 ? App.INSTANCE.getString(R.string.tibi_already) : bean.getStatus() == 2 ? App.INSTANCE.getString(R.string.tibi_fail) : ""))
                            .text(R.id.tv_time, "" + bean.getCreateTime());
                }
            }).attachTo(recyclerView).updateData(new ArrayList());
            coolRefreshView.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                if(page<pageMax){
                    mPresenter.getRecord(++page+"", "50", type);
                }

                }
            });
            coolRefreshView.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshLayout) {
                    listContgtnet.clear();
                    page=1;
                    mPresenter.getRecord(page+"", "50", type);
                }
            });
        } else if (flag == 2) {
            page=1;
            tv_stubs.setText(App.INSTANCE.getString(R.string.tibi_status));
            llSelect.setVisibility(View.VISIBLE);
            setTitle(App.INSTANCE.getString(R.string.tibi_tibirecord));
            tibi.clear();
            mPresenter.getCoin("2");
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
              slimAdapter = SlimAdapter.create().register(R.layout.tibi_record_item, new SlimInjector<TibiRecordBean.DataBean.ContentBean>() {
                @Override
                public void onInject(TibiRecordBean.DataBean.ContentBean bean, IViewInjector iViewInjector, List list) {
                    iViewInjector.text(R.id.tv_num, "" + bean.getTotalAmount())
                            .text(R.id.tv_status, "" + (bean.getStatus() == 0 ? App.INSTANCE.getString(R.string.tibi_sure) : bean.getStatus() == 1 ? App.INSTANCE.getString(R.string.tibi_already) : bean.getStatus() == 2 ? App.INSTANCE.getString(R.string.tibi_fail) : ""))
                            .text(R.id.tv_time, "" + bean.getCreateTime());
                }
            }).attachTo(recyclerView).updateData(new ArrayList());
            coolRefreshView.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    if(page<pageMax){
                        mPresenter.getTibiRecord(++page+"", "50", type);
                    }

                }
            });
            coolRefreshView.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshLayout) {
                    page=1;
                    tibi.clear();
                    mPresenter.getTibiRecord(page+"", "50", type);
                }
            });


        }else if(flag==3){
            page=1;
            mPresenter.getTransferRecord("1","50",type);
            mPresenter.getCoin("0");
            tv_stubs.setText(App.INSTANCE.getString(R.string.tibi_status));
            transfer.clear();
            llSelect.setVisibility(View.VISIBLE);
            setTitle(App.INSTANCE.getString(R.string.tibi_huazhuan));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            slimAdapter = SlimAdapter.create().register(R.layout.tibi_record_item, new SlimInjector<TransferRecordBean.DataBean.ContentBean>() {
                @Override
                public void onInject(TransferRecordBean.DataBean.ContentBean bean, IViewInjector iViewInjector, List list) {
                    iViewInjector.text(R.id.tv_num, new BigDecimal(bean.getAmount()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                            .text(R.id.tv_status, "" + bean.getType())
                            .text(R.id.tv_time, "" + bean.getCreateTime());
                }
            }).attachTo(recyclerView).updateData(new ArrayList());
            coolRefreshView.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    if(page<pageMax){
                        mPresenter.getTransferRecord(++page+"", "50", type);
                    }

                }
            });
            coolRefreshView.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshLayout) {
                    page=1;
                    transfer.clear();
                    mPresenter.getTransferRecord(page+"", "50", type);
                }
            });
        }
        else if(flag==4){
            page=1;
            llSelect.setVisibility(View.GONE);
            tv_stubs.setText(App.INSTANCE.getString(R.string.tibi_status));
            other.clear();
            mPresenter.checkRecord("1","50");
            setTitle(App.INSTANCE.getString(R.string.tibi_record));
            Map<Integer,String> map = new HashMap<>();
            map.put(0,App.INSTANCE.getString(R.string.tibi_congzhi));map.put(1,App.INSTANCE.getString(R.string.tibi_tixian));map.put(2,App.INSTANCE.getString(R.string.tibi_zhuanzhang));map.put(3,App.INSTANCE.getString(R.string.tibi_bibijiaoyi));map.put(4,App.INSTANCE.getString(R.string.tibi_fabi_buy));map.put(5,App.INSTANCE.getString(R.string.tibi_fabi_sell));map.put(6,App.INSTANCE.getString(R.string.tibi_huodong));
            map.put(7,App.INSTANCE.getString(R.string.tibi_tuiguang));map.put(8,App.INSTANCE.getString(R.string.tibi_fenhong));map.put(9,App.INSTANCE.getString(R.string.tibi_toupiao));map.put(10,App.INSTANCE.getString(R.string.tibi_rengong));map.put(11,App.INSTANCE.getString(R.string.tibi_peidui));map.put(12,App.INSTANCE.getString(R.string.tibi_shenqing));map.put(13,App.INSTANCE.getString(R.string.tibi_cancelshop));
            map.put(14,App.INSTANCE.getString(R.string.tibi_fabi_cong));map.put(15,App.INSTANCE.getString(R.string.tibi_bibi_dui));map.put(16,App.INSTANCE.getString(R.string.tibi_qudao));map.put(17,App.INSTANCE.getString(R.string.tibi_huazhuangang));map.put(18,App.INSTANCE.getString(R.string.tibi_gangganhuachu));map.put(19,App.INSTANCE.getString(R.string.tibi_qianbaokogntou));map.put(20,App.INSTANCE.getString(R.string.tibi_suocang));
            map.put(21,App.INSTANCE.getString(R.string.tibi_jiesuo));map.put(22,App.INSTANCE.getString(R.string.tibi_disanfang));map.put(23,App.INSTANCE.getString(R.string.tibi_disanfang_zhuanchu));map.put(24,App.INSTANCE.getString(R.string.tibi_bibizhuanfabi));map.put(25,App.INSTANCE.getString(R.string.tibi_fabizhuanbibi));map.put(26,App.INSTANCE.getString(R.string.tibi_jiedai));map.put(27,App.INSTANCE.getString(R.string.tibi_huankuan));
            map.put(28,App.INSTANCE.getString(R.string.tibi_bibizhuanheyue));map.put(29,App.INSTANCE.getString(R.string.tibi_heyuezhuanbibi));map.put(30,App.INSTANCE.getString(R.string.tibi_fabizhuanheyue));map.put(31,App.INSTANCE.getString(R.string.tibi_heyuezhuanfabi));map.put(32,App.INSTANCE.getString(R.string.tibi_heyuexiadan));map.put(33,App.INSTANCE.getString(R.string.tibi_heyuepingcang));map.put(34,App.INSTANCE.getString(R.string.tibi_guoyefei));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            slimAdapter = SlimAdapter.create().register(R.layout.tibi_record_item, new SlimInjector<OtherBean.DataBean.ContentBean>() {
                @Override
                public void onInject(OtherBean.DataBean.ContentBean bean, IViewInjector iViewInjector, List list) {
                    iViewInjector.text(R.id.tv_num, new BigDecimal(bean.getAmount()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString())
                            .text(R.id.tv_status, map.get(bean.getType()))
                            .text(R.id.tv_time, "" + bean.getCreateTime());
                }
            }).attachTo(recyclerView).updateData(new ArrayList());
            coolRefreshView.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    if(page<pageMax){
                        mPresenter.checkRecord(++page+"","50");
                    }

                }
            });
            coolRefreshView.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshLayout) {
                    page=1;
                    other.clear();
                    mPresenter.checkRecord(page+"","50");
                }
            });
        }
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        ClickUtil.click(ivRight,()->{
            bottomSheet1 = BottomSheetUtil.getBottomSheet(this, App.INSTANCE.getString(R.string.tibi_recode_type), (recyclerView, i, view) -> {
                bottomSheet1.dismiss();
                if(list1.get(i).contains(App.INSTANCE.getString(R.string.tibi_cong))){
                   // setTitle("充币记录");
                    type="ETH";
                //   type= list.get(0).getCoin().getUnit();
                    flag=1;

                        initData();

                }else if(list1.get(i).contains(App.INSTANCE.getString(R.string.tibi_ti))){
                   // setTitle("提币记录");
                    type="ETH";
                    flag=2;

                        initData();
                }else if(list1.get(i).contains(App.INSTANCE.getString(R.string.tibi_hua))){
                    //setTitle("划转记录");
                        type="USDT";
                    flag=3;
                    initData();

                }else {
                    //setTitle("其他");
                    flag=4;
                    initData();
                }

                // editText.setText(list1.get(i));
            }, list1);
            bottomSheet1.show();
        });
        ClickUtil.click(llSelect, () -> {
            ARouter.getInstance().build(RConfig.SELECTCOINACTIVITY).withSerializable("list", list).navigation(this, 2);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == 1) {
            CoinListBean.DataBean bean = (CoinListBean.DataBean) data.getSerializableExtra("data");
            ImageUtil.setImage(HttpConfig.BASE_URL + bean.getCoin().getImgUrl(), image);
            tvType.setText(bean.getCoin().getUnit());
            tvTypeQuan.setText(bean.getCoin().getName());
            //  mPresenter.getCoinAdress(bean.getCoin().getUnit());
            type= bean.getCoin().getUnit();
            if(flag==1){
                mPresenter.getRecord("1", "50", type);
               // mvcHelper.refresh();
            }else if(flag==2){
                mPresenter.getTibiRecord("1", "50", type);
            }else if(flag==3){
                mPresenter.getTransferRecord("1","50",type);
            }

        }
    }

    @Override
    public CongRecordActivityPresenter getPresenter() {
        return new CongRecordActivityPresenter();
    }

    List<CongRecordBean.DataBean.ContentBean> listContgtnet = new ArrayList<>();
    public void setdata(List<CongRecordBean.DataBean.ContentBean> content,int totalPage,boolean islast) {
        if(coolRefreshView!=null&&coolRefreshView.isRefreshing()){
            coolRefreshView.finishRefresh();
        }
        pageMax = totalPage;
        listContgtnet.addAll(content);
        if(islast){
            ToastUtil.showShort(App.INSTANCE.getString(R.string.tibi_jiazai));
            coolRefreshView.finishLoadMore(true);
        }
        slimAdapter.updateData(listContgtnet);
    }
    List<TibiRecordBean.DataBean.ContentBean> tibi = new ArrayList<>();
    public void setNewData(List<TibiRecordBean.DataBean.ContentBean> content,int totalPage,boolean islast) {
        if(coolRefreshView!=null&&coolRefreshView.isRefreshing()){
            coolRefreshView.finishRefresh();
        }
        pageMax = totalPage;
        tibi.addAll(content);
        if(islast){
            ToastUtil.showShort(App.INSTANCE.getString(R.string.tibi_jiazai));
            coolRefreshView.finishLoadMore(true);
        }
        slimAdapter.updateData(content);
    }
    List<TransferRecordBean.DataBean.ContentBean> transfer = new ArrayList<>();
    public void setNewData1(List<TransferRecordBean.DataBean.ContentBean> content,int totalPage,boolean islast) {
        if(coolRefreshView!=null&&coolRefreshView.isRefreshing()){
            coolRefreshView.finishRefresh();
        }
        pageMax = totalPage;
        transfer.addAll(content);
        if(islast){
            ToastUtil.showShort(App.INSTANCE.getString(R.string.tibi_jiazai));
            coolRefreshView.finishLoadMore(true);
        }
        slimAdapter.updateData(content);
    }
    List<OtherBean.DataBean.ContentBean> other = new ArrayList<>();
    public void setOtherData(List<OtherBean.DataBean.ContentBean> content,int totalPage,boolean islast) {
        if(coolRefreshView!=null&&coolRefreshView.isRefreshing()){
            coolRefreshView.finishRefresh();
        }
        pageMax = totalPage;
        other.addAll(content);
        if(islast){
            ToastUtil.showShort(App.INSTANCE.getString(R.string.tibi_jiazai));
            coolRefreshView.finishLoadMore(true);
        }
        slimAdapter.updateData(content);
    }

    public void setData(ArrayList<CoinListBean.DataBean> data) {
        list = data;
        tvType.setText(data.get(0).getCoin().getUnit());
        tvTypeQuan.setText(data.get(0).getCoin().getName());
        if(flag==1){
            mPresenter.getRecord("1", "50", type);

        }else if(flag==2){
            mPresenter.getTibiRecord("1", "50", type);
        }else if(flag==3){
            mPresenter.getTransferRecord("1","50",type);
        }
    }
}
