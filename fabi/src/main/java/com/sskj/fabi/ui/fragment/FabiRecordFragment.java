package com.sskj.fabi.ui.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bulong.rudeness.RudenessScreenHelper;
import com.shizhefei.mvc.MVCCoolHelper;
import com.shizhefei.view.coolrefreshview.CoolRefreshView;
import com.sskj.common.adapter.ItemClickSupport;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.bean.FabiOrderStatusEnum;
import com.sskj.fabi.bean.FabiRecordFilterBean;
import com.sskj.fabi.bean.OrderBean;
import com.sskj.fabi.bean.OrderRecordBean;
import com.sskj.fabi.component.DaggerUserDataComponent;
import com.sskj.fabi.http.HttpConfig;
import com.sskj.fabi.presenter.FabiRecordFragmentPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.bean.enums.FabiBuySellEnum;
import com.sskj.lib.bean.enums.PayTypeEnum;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.mvchelper.ModelRx2DataSource;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.util.ImageUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static android.content.Context.CLIPBOARD_SERVICE;


@Route(path = RConfig.FABI_FRAGMENT_ORDER_RECORD)
public class FabiRecordFragment extends BaseFragment<FabiRecordFragmentPresenter> {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.coolRefreshView)
    CoolRefreshView coolRefreshView;
    @Autowired
    int type;
    private MVCCoolHelper<List<OrderBean.ContentBean>> mvcHelper;
    @Inject
    UserViewModel userViewModel;
    private SlimAdapter slimAdapter;
    private SafeSettingBean userData;
    private String status="";
    private ModelRx2DataSource<OrderBean.ContentBean> dataSource;
    //剪切板管理工具类
    private ClipboardManager mClipboardManager;
    //剪切板Data对象
    private ClipData mClipData;
    @Override
    protected int getLayoutId() {
        return R.layout.fabi_layout_record;
    }

    @Override
    public FabiRecordFragmentPresenter getPresenter() {
        return new FabiRecordFragmentPresenter();
    }

    @SuppressWarnings("unchecked")

    @Override
    protected void initView() {
        DaggerUserDataComponent.create().inject(this);
        ARouter.getInstance().inject(this);
        mClipboardManager = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
            } else {
                userData = null;
            }
        });
        HashMap<String, String> statusMap = new HashMap<>();
        statusMap.put("1", FabiOrderStatusEnum.待付款.getTitle());
        statusMap.put("2", FabiOrderStatusEnum.待放币.getTitle());
        statusMap.put("3", FabiOrderStatusEnum.已完成.getTitle());
        statusMap.put("4", FabiOrderStatusEnum.申诉中.getTitle());
        statusMap.put("5", FabiOrderStatusEnum.已取消.getTitle());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setFirstDraw(false)
                .setLeftPadding((int) RudenessScreenHelper.pt2px(App.INSTANCE, 30))
                .setRightPadding((int) RudenessScreenHelper.pt2px(App.INSTANCE, 30))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.fabiDivider)));

        if(type==1||type==2){
            status = "";
        }else if(type==3){
            status= "APPEAL";
        }else if(type==4){
            status= "COMPLETED";
        }
        slimAdapter = SlimAdapter.create().register(R.layout.fabi_recy_item_order_record, new SlimInjector<OrderBean.ContentBean>() {

            @Override
            public void onInject(OrderBean.ContentBean data, IViewInjector injector, List<Object> list) {
               ImageView imageView =  (ImageView) injector.findViewById(R.id.image);
                ImageUtil.setImage(HttpConfig.BASE_URL+data.getImgUrl(),imageView);
                injector.text(R.id.tv_type,data.getUnit())
                .text(R.id.tvBuySellType,data.getStatus()==0?FabiOrderStatusEnum.已取消.getTitle():
                        data.getStatus()==1?FabiOrderStatusEnum.待付款.getTitle()
                                :data.getStatus()==2?App.INSTANCE.getString(R.string.fabi_yifukuan):data.getStatus()==3?App.INSTANCE.getString(R.string.fabi_already):data.getStatus()==4?App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum5):"无数据")
                        .text(R.id.tv_leixing,data.getUnit())
                        .text(R.id.tv_cny_price,data.getPrice()+"")
                        .text(R.id.tv_duixiang,data.getName())
                        .text(R.id.tv_price,data.getMoney()+"")
                        .text(R.id.tv_order_um,data.getOrderSn())
                        .text(R.id.tv_time,data.getCreateTime())
                        .backgroundColor(R.id.tv_chakan,data.getType()==0?getResources().getColor(R.color.libGreen1):getResources().getColor(R.color.libRed))
                        .clicked(R.id.tv_chakan,(v)->{
                            if(data.getType()==0){
                                ARouter.getInstance().build(RConfig.PAYORDERACTIVITY).withString("orderSn",data.getOrderSn()).navigation();
                            }else if(data.getType()==1){
                                ARouter.getInstance().build(RConfig.PAYORDERSELLACTIVITY).withString("orderSn",data.getOrderSn()).navigation();
                            }
                        })
                        .clicked(R.id.image_fuzhi,(v)->{
                            //复制
                            //创建一个新的文本clip对象
                            mClipData = ClipData.newPlainText("Simple test", data.getOrderSn());
                            //把clip对象放在剪贴板中
                            mClipboardManager.setPrimaryClip(mClipData);
                            Toast.makeText(getContext(), App.INSTANCE.getString(R.string.fabi_fuzhi),
                                    Toast.LENGTH_SHORT).show();
                        })
                ;
                        if(type==1){
                            injector.text(R.id.tv_leixing,App.INSTANCE.getString(R.string.fabi_goumai));
                        }else if(type==2){
                            injector.text(R.id.tv_leixing,App.INSTANCE.getString(R.string.fabi_chushou));
                        }else if(type==3){
                            injector.text(R.id.tv_leixing,App.INSTANCE.getString(R.string.fabifabiOrderStatusEnum5));
                        }else if(type==4){
                            injector.text(R.id.tv_leixing,App.INSTANCE.getString(R.string.fabi_already));
                        }

                       // .text(R.id.tvBuySellType,data.get)
               /* injector
                        .text(R.id.tvStatus, statusMap.get(data.getStatus() + ""))
                       // .text(R.id.tvTotalPrice, String.format("¥%s", CoinUtil.keepRMB(data.getTotalPrice())))
                        .text(R.id.tvBuySellType, data.getType() == 1 ?
                                FabiBuySellEnum.BUY.getTitle() :
                                FabiBuySellEnum.SELL.getTitle())
                        .textColor(R.id.tvBuySellType,
                                ContextCompat.getColor(App.INSTANCE, data.getType() == 1 ?
                                        FabiBuySellEnum.BUY.getColor() :
                                        FabiBuySellEnum.SELL.getColor()))
                        .text(R.id.tvLimit, String.format("%s-%s ", data.getMinPrice(), data.getMaxPrice()))
                        .text(R.id.tvPrice, "¥"+data.getPrice())
                        .text(R.id.tvNum, data.getTotalNum() + " USDT")
                        .text(R.id.tvTime, data.getCreateTime())
                        .image(R.id.ivPayType, PayTypeEnum.getByType(data.getPayType()).getRes())
                ;*/
            }
        });
        slimAdapter.attachTo(recyclerView).updateData(new ArrayList());
        if(type==1){
            dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<OrderBean.ContentBean>)
                    page -> mPresenter.queryOrder1(coinId,"BUY",page , 10,status,orderSn), 10);
        }else if(type==2){
            dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<OrderBean.ContentBean>)
                    page -> mPresenter.queryOrder1(coinId,"SELL",page , 10,status,orderSn), 10);
        }else if(type==3){
            dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<OrderBean.ContentBean>)
                    page -> mPresenter.queryOrder2(coinId,"",page , 10,status,orderSn), 10);
        }else if(type==4){
            dataSource = new ModelRx2DataSource<>((ModelRx2DataSource.OnLoadSource<OrderBean.ContentBean>)
                    page -> mPresenter.queryOrder2(coinId,"",page , 10,status,orderSn), 10);
        }

        mvcHelper = new MVCCoolHelper<>(coolRefreshView);
        mvcHelper.setDataSource(dataSource);
        mvcHelper.setAdapter(slimAdapter);
        mvcHelper.refresh();
       /* ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener((recyclerView1, position, v) -> {
                    OrderRecordBean dataItem = (OrderRecordBean) slimAdapter.getDataItem(position);
                    int type = dataItem.getType();
                    boolean isBuy;
                    if (type == 1) {
                        isBuy = true;
                    } else {
                        isBuy = false;
                    }
                    ARouter.getInstance().build(RConfig.FABI_ORDER_RECORD_DETAIL)
                            .withString(Constans.ORDER_NUM, dataItem.getDealEntrustNo())
                            .withBoolean(Constans.IS_BUYER, isBuy)
                            .navigation();
                });*/
    }
    private String coinId="";
    private String orderSn="";
    @Override
    protected void initData() {
        LiveDataBus.get().with(RxBusCode.CHANGE_FABI_ORDER_RECORD_TYPE, Integer.class)
                .observe(this, integer -> {
                    status = integer + "";
                    if (integer==0){
                        status=null;
                    }
                    mvcHelper.refresh();
                });
        LiveDataBus.get().with(RxBusCode.CHANGE_FABI_ORDER_FILTER, FabiRecordFilterBean.class).observe(this,this::filter);
    }

    private void filter(FabiRecordFilterBean fabiRecordFilterBean) {
        coinId = fabiRecordFilterBean.getCointype();
        status = fabiRecordFilterBean.getStatus();
        orderSn =fabiRecordFilterBean.getOrn();
        mvcHelper.refresh();
        /*if(type==1){

        }*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mvcHelper == null) {
            return;
        }
        if (isVisibleToUser) {
            mvcHelper.refresh();
        }
    }

}
