package com.sskj.fabi.ui.fragment;

import android.annotation.SuppressLint;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.adapter.ItemClickSupport;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.fabi.R;
import com.sskj.fabi.R2;
import com.sskj.fabi.component.DaggerUserDataComponent;
import com.sskj.fabi.presenter.FaBiQuickItemFragmentPresenter;
import com.sskj.fabi.ui.activity.PayOrderSellActivity;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.AppCircleCheckUtil;
import com.sskj.lib.util.BottomSheetUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = RConfig.FABIQUICKITEMFRAGMENT)
public class FaBiQuickItemFragment extends BaseFragment<FaBiQuickItemFragmentPresenter> {
    @BindView(R2.id.etNum)
    EditText etNum;
    @BindView(R2.id.tvTypeTitle)
    TextView tvTypeTitle;
  /*  @BindView(R2.id.tvUnitCNY)
    TextView tvUnitCNY;*/
    @BindView(R2.id.tvUnitUSDT)
    TextView tvUnitUSDT;
    @BindView(R2.id.tvPrice)
    TextView tvPrice;
    @BindView(R2.id.tvBuyType)
    TextView tvBuyType;
    @BindView(R2.id.btSubmit)
    Button btSubmit;
    @Autowired
    int flag;//flag：1买，2卖
    @Autowired
    String id;
    @Autowired
    String type;
    @BindView(R2.id.tvFee)
    TextView tvFee;
    @BindView(R2.id.llFee)
    LinearLayout llFee;
    @BindView(R2.id.ll_unit)
    LinearLayout llUnit;
    @BindView(R2.id.image)
    ImageView imageView;
    private boolean isNum = false;
    int mode;
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;
    private BottomSheetDialog submitBottomSheetDialog;
    private BottomSheetDialog payTypeBottomDialog;
    private BottomSheetDialog mydialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fabi_activity_quick_item;
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        DaggerUserDataComponent.create().inject(this);
       // super.initView();
        isNum = false;
        changeType();
    }

    @Override
    protected void initData() {
        //super.initData();
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
            } else {
                userData = null;
            }
        });
    }
    private List<String> list = new ArrayList<>();
    @Override
    protected void initEvent() {
       // super.initEvent();
        list.clear();
        list.add("银联");
        list.add("支付宝");
        list.add("微信");
        ClickUtil.click(tvBuyType,()->{
            if(tvBuyType.getText().toString().equals(App.INSTANCE.getString(R.string.fabiquickBuyFragment6))){
               tvBuyType.setText(App.INSTANCE.getString(R.string.fabiquickBuyFragment7));
            }else {
                tvBuyType.setText(App.INSTANCE.getString(R.string.fabiquickBuyFragment6));
            }
            isNum=!isNum;
            changeType();
                });

        ClickUtil.click(btSubmit, () -> {
            if (!AppCircleCheckUtil.checkLogin(getActivity(), userData)) {
                return;
            }
            if (TextUtils.isEmpty(etNum.getText()) && isNum) {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.fabiorderActivity8));
                return;
            }
            if (TextUtils.isEmpty(etNum.getText()) && !isNum) {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.fabiorderActivity10));
                return;
            }
            if(isNum){
                mode=0;
            }else {
                mode=1;
            }

            mydialog = BottomSheetUtil.getBottomSheet(getActivity(),"请选择一种支付方式",new ItemClickSupport.OnItemClickListener(){

                @Override
                public void onItemClicked(RecyclerView recyclerView, int i, View view) {
                    if(flag==1){
                        if(isNum){
                            mPresenter.getQuickBuy(etNum.getText().toString(),id,"1","",list.get(i));
                        }else {
                            mPresenter.getQuickBuy("",id,"1",etNum.getText().toString(),list.get(i));
                        }

                    }else {
                        if(isNum){
                            mPresenter.getQuickSell(etNum.getText().toString(),id,"1","",list.get(i));
                        }else {
                            mPresenter.getQuickSell("",id,"1",etNum.getText().toString(),list.get(i));
                        }

                    }
                    mydialog.dismiss();
                }
            },list);
            mydialog.show();


         //   mPresenter.buy(null, isNum ? "USD" : "", etNum.getText().toString());

//            if (payTypeBottomDialog == null) {
//                mPresenter.buy(null, isNum ? "USDT" : "CNY", etNum.getText().toString());


//                payTypeBottomDialog = TipFabiUtil.showQuickPayType(getActivity(), str -> {
//                    payTypeBottomDialog.dismiss();
//                    mPresenter.buy(str[0], isNum ? "USDT" : "CNY", etNum.getText().toString());
//                });
//                payTypeBottomDialog.show();
//                Flowable.fromIterable(PayTypeEnum.list())
//                        .filter(payTypeEnum -> payTypeEnum != PayTypeEnum.WX)
//                        .map(payTypeEnum -> payTypeEnum.getName())
//                        .toList()
//                        .subscribe((strings, throwable) -> {
//                            payBottomDialog = BottomSheetUtil.getBottomSheet(getActivity(), App.INSTANCE.getString(R.string.fabipublishActivity4), (recyclerView, i, view) -> {
//                                payBottomDialog.dismiss();
//                                mPresenter.buy(PayTypeEnum.list().get(i + 1).getQuickPayType(), isNum ? "USDT" : "CNY", etNum.getText().toString());
//
////                                submitBottomSheetDialog = TipFabiUtil.showQuickBuy(getActivity(), PayTypeEnum.list().get(i), null, null, null, str -> {
////                                    submitBottomSheetDialog.dismiss();
////                                });
////                                submitBottomSheetDialog.show();
//                            }, strings);
//                        });

//            }
//            payTypeBottomDialog.show();
        });
    }

    @Override
    protected FaBiQuickItemFragmentPresenter getPresenter() {
        return new FaBiQuickItemFragmentPresenter();
    }
    @SuppressLint("NewApi")
    private void changeType() {
        if(flag==1){
            tvTypeTitle.setText(isNum ? App.INSTANCE.getString(R.string.fabiorderActivity6) : App.INSTANCE.getString(R.string.fabiquickBuyFragment5));
            tvBuyType.setTextColor(getActivity().getColor(R.color.libGreen));
            imageView.setImageResource(R.mipmap.fabi_icon_switch1);
            btSubmit.setText("购买");
            btSubmit.setBackground(getResources().getDrawable(R.drawable.lib_button));
        }else if(flag==2){
            tvTypeTitle.setText(isNum ? App.INSTANCE.getString(R.string.fabiorderActivity61) : App.INSTANCE.getString(R.string.fabiquickBuyFragment51));
            tvBuyType.setTextColor(getActivity().getColor(R.color.libRed));
            imageView.setImageResource(R.mipmap.fabi_icon_switch2);
            btSubmit.setText("出售");
            btSubmit.setBackground(getResources().getDrawable(R.drawable.lib_button2));
        }

        llUnit.setVisibility(isNum ? View.GONE : View.VISIBLE);
        tvUnitUSDT.setVisibility(isNum ? View.VISIBLE : View.GONE);
        tvUnitUSDT.setText(type);
        tvBuyType.setText(isNum ? App.INSTANCE.getString(R.string.fabiquickBuyFragment6) : App.INSTANCE.getString(R.string.fabiquickBuyFragment7));
        tvPrice.setText("参考单价："+6.43+"CNY/"+type);
    }

    public void setUI(String message) {
        ToastUtil.showShort(message);
    }
}
