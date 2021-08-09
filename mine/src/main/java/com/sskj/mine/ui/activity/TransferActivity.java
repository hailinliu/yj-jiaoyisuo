package com.sskj.mine.ui.activity;




import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import com.sskj.common.base.App;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;

import com.sskj.lib.bean.BaseBean;
import com.sskj.lib.bean.CoinListBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.ImageUtil;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.bean.BIBIBean;
import com.sskj.mine.bean.BaseCoinBean;
import com.sskj.mine.bean.FaBiBean;
import com.sskj.mine.bean.LevelBean;
import com.sskj.mine.bean.MyAssertBean;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.presenter.TransferActivityPresenter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;


@Route(path = RConfig.MINER_TRANSFER)
public class TransferActivity extends BaseActivity<TransferActivityPresenter> {

    @BindView(R2.id.tvTitle)
    TextView tvTitle;
    @BindView(R2.id.tv_keyong)
    TextView tvKeyong;
    @BindView(R2.id.ivBack)
    LinearLayout ivBack;
    @BindView(R2.id.ll_select)
    LinearLayout ll_select;
    @BindView(R2.id.ivRight)
    ImageView ivRight;
    int type;
    @BindView(R2.id.tv_cong)
    TextView tvCong;
    @BindView(R2.id.tv_first)
    TextView tvFirst;
    @BindView(R2.id.image_right1)
    ImageView imageRight1;
    @BindView(R2.id.ll_first)
    LinearLayout llFirst;
    @BindView(R2.id.tv_dao)
    TextView tvDao;
    @BindView(R2.id.tv_all)
    TextView tvAll;
    @BindView(R2.id.tv_second)
    TextView tvSecond;
    @BindView(R2.id.image_right2)
    ImageView imageRight2;
    @BindView(R2.id.ll_second)
    LinearLayout llSecond;
    @BindView(R2.id.imagezhuan)
    ImageView imagezhuan;
    @BindView(R2.id.imagecoin)
    ImageView imagecoin;
    @BindView(R2.id.et_transfernum)
    EditText etTransfernum;
    @BindView(R2.id.btOrder)
    Button btOrder;
    private boolean isflag = true;
    private ArrayList<String> list = new ArrayList<>();
    private BottomSheetDialog bottomSheet;
    // String id = SPUtil.get(SPConfig.ID,"");
    int dealType;
    boolean isIsflag;
    private String d;
    @BindView(R2.id.tv_type1)
    TextView tvType1;
    @BindView(R2.id.tv_type_quan)
    TextView tvTypeQuan;
    @BindView(R2.id.tv_unit)
    TextView tvUnit;
    private BottomSheetDialog bottomSheet1;
    private Flowable<List<BIBIBean.DataBean>> bibi;
    private Flowable<List<FaBiBean.DataBean>> fabi;
    private ArrayList<BaseCoinBean> list1 = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_transfer;
    }

    @Override
    public TransferActivityPresenter getPresenter() {
        return new TransferActivityPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        tvTitle.setText(App.INSTANCE.getString(R.string.mine_tranfer));
        ivRight.setImageResource(R.mipmap.mine_icon_record);
        list.add(App.INSTANCE.getString(R.string.mine_BiBi_account));
        list.add(App.INSTANCE.getString(R.string.mine_fabi_account));
        list.add(App.INSTANCE.getString(R.string.mine_heyue_account));
        tvFirst.setText(App.INSTANCE.getString(R.string.mine_BiBi_account));
        tvSecond.setText(App.INSTANCE.getString(R.string.mine_fabi_account));
        bottomSheet = BottomSheetUtil.getBottomSheet( this, null,  (recyclerView, i, view) -> {
            bottomSheet.dismiss();
            tvFirst.setText(list.get(i));
            if(tvFirst.getText().equals(tvSecond.getText().toString())){
                ToastUtil.showShort(App.INSTANCE.getString(R.string.mine_xiangtong));
            }
            String s1= App.INSTANCE.getString(R.string.mine_BiBi_account);
            String s2 =  App.INSTANCE.getString(R.string.mine_fabi_account);
            String s3 = App.INSTANCE.getString(R.string.mine_heyue_account);
            if(tvFirst.getText().toString().equals(s1)){
                mPresenter.getBiBi("0",tvType1.getText().toString(),false);
            }else if (tvFirst.getText().toString().equals(s2)){
                mPresenter.getFaBi("0",tvType1.getText().toString(),false);
            }else if(tvFirst.getText().toString().equals(s3)){
                list1.clear();
                tvType1.setText("USDT");
                imagecoin.setImageResource(R.mipmap.mine_usdt);
                tvTypeQuan.setText("USDT");
                tvAll.setText("USDT"+App.INSTANCE.getString(R.string.mine_all));
                tvUnit.setText("USDT");
                list1.add(new BaseCoinBean("","USDT","USDT"));
                mPresenter.getLevel("0","USDT");
            }
        },list);
        bottomSheet1 = BottomSheetUtil.getBottomSheet( this, null,  (recyclerView, i, view) -> {
            bottomSheet1.dismiss();
            tvSecond.setText(list.get(i));
            if(tvFirst.getText().equals(tvSecond.getText().toString())){
                ToastUtil.showShort(App.INSTANCE.getString(R.string.mine_xiangtong));
            }
            if(tvSecond.getText().toString().equals(App.INSTANCE.getString(R.string.mine_heyue_zhanghu))){
                imagecoin.setImageResource(R.mipmap.mine_usdt);
                tvType1.setText("USDT");
                tvTypeQuan.setText("USDT");
                tvAll.setText("USDT"+App.INSTANCE.getString(R.string.mine_all));
                tvUnit.setText("USDT");
                list1.clear();
                list1.add(new BaseCoinBean("","USDT","USDT"));
                if(tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_BiBi_account))){
                    mPresenter.getBiBi("0","USDT",false);
                }else if(tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_fabi_account))){
                    mPresenter.getFaBi("0","USDT",false);
                }
            }
        },list);
    }


    @Override
    protected void initData() {

        mPresenter.getFaBi("0","",true);
        mPresenter.getLevel("0","");
        // mPresenter.getCoin("1");
    }
    @Override
    protected void initEvent() {
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RConfig.CONGRECORDACTIVITY).withInt("flag",3).navigation();
            }
        });
        imagezhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first= tvFirst.getText().toString();
                String second = tvSecond.getText().toString();
                tvFirst.setText(second);
                tvSecond.setText(first);
                if(tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_BiBi_account))){
                    mPresenter.getBiBi("0",tvType1.getText().toString(),false);
                }else if (tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_fabi_account))){
                    mPresenter.getFaBi("0",tvType1.getText().toString(),false);
                }else if(tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_heyue_account))){
                    mPresenter.getLevel("0",tvType1.getText().toString());
                }
            }
        });
        ll_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_heyue_account))||tvSecond.getText().toString().equals(App.INSTANCE.getString(R.string.mine_heyue_account))){
                    ARouter.getInstance().build(RConfig.NEWSELECTCOINACTIVITY).withSerializable("list",list1).navigation(TransferActivity.this,1);
                }else
                    ARouter.getInstance().build(RConfig.NEWSELECTCOINACTIVITY).withSerializable("list",newlist).navigation(TransferActivity.this,1);
            }
        });
        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTransfernum.setText(tvKeyong.getText().toString());
                etTransfernum.setSelection(etTransfernum.getText().toString().length());
            }
        });


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        llFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bottomSheet == null) {
                    return;
                }
                bottomSheet.show();
            }
        });
        llSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheet1 == null) {
                    return;
                }
                bottomSheet1.show();
            }
        });

        btOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_BiBi_account))&&tvSecond.getText().toString().equals(App.INSTANCE.getString(R.string.mine_fabi_account))){
                    type=0;
                }else if (tvSecond.getText().toString().equals(App.INSTANCE.getString(R.string.mine_BiBi_account))&&tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_fabi_account))){
                    type = 1;
                }else if(tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_heyue_account))&&tvSecond.getText().toString().equals(App.INSTANCE.getString(R.string.mine_BiBi_account))){
                    type=3;
                }else if(tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_BiBi_account))&&tvSecond.getText().toString().equals(App.INSTANCE.getString(R.string.mine_heyue_account))){
                    type = 2;
                }else if(tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_fabi_account))&&tvSecond.getText().toString().equals(App.INSTANCE.getString(R.string.mine_heyue_account))){
                    type=4;
                }else if(tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_heyue_account))&&tvSecond.getText().toString().equals(App.INSTANCE.getString(R.string.mine_fabi_account))){
                    type=5;
                }
                if(!etTransfernum.getText().toString().isEmpty()){
                    mPresenter.submit(etTransfernum.getText().toString(),tvType1.getText().toString(),String.valueOf(type));
                    //  mPresenter.submit(id,type,dealType,"USDT", etTransfernum.getText().toString());
                }else {
                    ToastUtil.showShort(App.INSTANCE.getString(R.string.mine_hint));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==1){
            BaseCoinBean  bean = (BaseCoinBean) data.getSerializableExtra("data");
            ImageUtil.setImage(HttpConfig.BASE_URL+ bean.getImageUrl(),imagecoin);
            tvType1.setText(bean.getSimpletype());
            tvTypeQuan.setText(bean.getType());
            tvAll.setText(bean.getSimpletype()+App.INSTANCE.getString(R.string.mine_all));
            tvUnit.setText(bean.getSimpletype());
            if(tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_BiBi_account))){
                mPresenter.getBiBi("0",bean.getSimpletype(),false);
            }else if(tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_fabi_account))){
                mPresenter.getFaBi("0",bean.getSimpletype(),false);
            }else {
                mPresenter.getLevel("0","USDT");
            }

        }

    }

    public void isSuccess(String msg) {
        ToastUtil.showShort(msg);
        etTransfernum.setText("");
        if(tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_BiBi_account))){
            isIsflag =true;
            mPresenter.getBiBi("0",tvType1.getText().toString(),false);
        }else if (tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_fabi_account))){
            isIsflag =true;
            mPresenter.getFaBi("0",tvType1.getText().toString(),false);
        }else if(tvFirst.getText().toString().equals(App.INSTANCE.getString(R.string.mine_heyue_account))){
            isIsflag =true;
            mPresenter.getLevel("0",tvType1.getText().toString());
        }
    }

    public void setBIBI(List<BIBIBean.DataBean> data,boolean b) {
        if(b){
            bibi = Flowable.fromIterable(data).toList().toFlowable();
            Flowable.zip(bibi,fabi,(bibidata,fabidata)->{
                newlist.clear();
                for(FaBiBean.DataBean bean:fabidata){
                    for(BIBIBean.DataBean bean1:bibidata){
                        if(bean1.getCoin().getUnit().equals(bean.getCoin().getUnit())){
                            BaseCoinBean coinBean = new BaseCoinBean(bean.getCoin().getImgUrl(),bean.getCoin().getUnit(),bean.getCoin().getName());
                            newlist.add(coinBean);
                        }
                    }
                }
                for(BaseCoinBean bean:newlist){
                    if(bean.getSimpletype().equals("USDT")){
                        ImageUtil.setImage(HttpConfig.BASE_URL+bean.getImageUrl(),imagecoin);
                        tvType1.setText(bean.getSimpletype());
                        tvTypeQuan.setText(bean.getType());
                    }
                }

                tvAll.setText(tvType1.getText().toString()+App.INSTANCE.getString(R.string.mine_all));
                tvUnit.setText(tvType1.getText().toString());
                mPresenter.getBiBi("0",tvType1.getText().toString(),false);
                return "1";
            }).subscribe(s -> {
            }, e -> {
                System.out.println(e);
            });
        }else
            tvKeyong.setText(new BigDecimal(data.get(0).getBalance()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
    }
    private ArrayList<BaseCoinBean> newlist = new ArrayList<>();
    public void setFaBi(List<FaBiBean.DataBean> data,boolean b) {
        if(b){
            fabi = Flowable.fromIterable(data).toList().toFlowable();
            mPresenter.getBiBi("0","",true);
        }else
            tvKeyong.setText(new BigDecimal(data.get(0).getBalance()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
    }

    public void setLevel(LevelBean.DataBean data) {
        imagecoin.setImageResource(R.mipmap.mine_usdt);
        //  ImageUtil.setImage(HttpConfig.BASE_URL+data.get,image1);
        tvKeyong.setText(new BigDecimal(data.getBalance()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
    }

    public void update(String msg) {
        if(msg.contains("成功")){
            ToastUtil.showShort("成功");
        }else {
            ToastUtil.showShort(msg);
        }

        LiveDataBus.get().with(RxBusCode.REFRESH).postValue(1);//更新各个类型资产数据
        if(type==0||type==2){
            mPresenter.getBiBi("0",tvType1.getText().toString(),false);
        }else if(type==1||type==4){
            mPresenter.getFaBi("0",tvType1.getText().toString(),false);
        }else {
            mPresenter.getLevel("0","USDT");
        }
    }

   /* public void setData(ArrayList<CoinListBean.DataBean> data) {
        ImageUtil.setImage(HttpConfig.BASE_URL+data.get(0).getCoin().getImgUrl(),image1);
        tvType1.setText(data.get(0).getCoin().getUnit());
        tvTypeQuan.setText(data.get(0).getCoin().getName());
        list1 = data;
    }*/
}
