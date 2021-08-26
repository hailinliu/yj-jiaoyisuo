package com.sskj.level.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.widget.RxRadioGroup;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.sskj.common.base.App;
import com.sskj.common.box.inputfilter.MoneyValueFilter;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.depth.view.DepthMapView;
import com.sskj.level.R;
import com.sskj.level.R2;
import com.sskj.level.bean.CoinDetailBean;
import com.sskj.level.bean.DealDetailBean;
import com.sskj.level.bean.GangGanBean;
import com.sskj.level.bean.LevelBean;
import com.sskj.level.bean.MinNumBean;
import com.sskj.level.component.DaggerUserDataComponent;
import com.sskj.level.presenter.NewBuyAndSellFragmentPresenter;
import com.sskj.lib.BuildConfig;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.bean.BibiCoinType;
import com.sskj.lib.bean.CoinBean1;
import com.sskj.lib.bean.RateBean;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.WSFiveBean1;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.CoinUtil;
import com.sskj.lib.widget.MyRadioGroup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = RConfig.LEVEL_FRAGMENT_BUY_AND_SELL)
public class NewBuyAndSellFragment extends BaseFragment<NewBuyAndSellFragmentPresenter> {
    @BindView(R2.id.rbBuy)
    RadioButton rbBuy;
    @BindView(R2.id.rbSell)
    RadioButton rbSell;
    @BindView(R2.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R2.id.spinner)
    TextView spinner;
    @BindView(R2.id.llPriceMode)
    LinearLayout llPriceMode;
    @BindView(R2.id.etMyPrice)
    EditText etPrice1;
    @BindView(R2.id.llAddAndDe)
    LinearLayout llAddAndDe;
    @BindView(R2.id.llAdd2)
    LinearLayout llAdd2;
    @BindView(R2.id.llAddAndDe1)
    LinearLayout llAddAndDe1;
    @BindView(R2.id.rlEditPrice)
    RelativeLayout rlEditPrice;
    @BindView(R2.id.llHidePrice)
    LinearLayout llHidePrice;
    @BindView(R2.id.etMyNum)
    EditText etNum1;
    @BindView(R2.id.tvDecrease)
    TextView tvDecrease;
    @BindView(R2.id.tvIncrease)
    TextView tvIncrease;
    @BindView(R2.id.tvDecrease1)
    TextView tvDecrease1;
    @BindView(R2.id.tvIncrease1)
    TextView tvIncrease1;
    @BindView(R2.id.tvCoinType)
    TextView tvCoinType;
    @BindView(R2.id.tvMoney)
    TextView tvMoney;
    @BindView(R2.id.tvTradeMoney)
    TextView tvTradeMoney;
    @BindView(R2.id.btBuy)
    Button btBuy;
    @BindView(R2.id.rgLevel)
    RadioGroup rgLevel;
    @BindView(R2.id.tvNumTitle)
    TextView tvNumTitle;
    @BindView(R2.id.tvMoneyTitle)
    TextView tvMoneyTitle;
    @BindView(R2.id.tvMoneyType)
    TextView tvMoneyType;
    @BindView(R2.id.tvHideMoneyType)
    TextView tvHideMoneyType;
    @BindView(R2.id.tvAllMoneyTitle)
    TextView tvAllMoneyTitle;
    @BindView(R2.id.depthMapView)
    DepthMapView depthMapView;
    /*  @BindView(R2.id.indicatorSeekBar)
      IndicatorSeekBar indicatorSeekBar;*/
    @BindView(R2.id.etPriceCf)
    EditText etPriceCf;
    @BindView(R2.id.add_cf)
    TextView addCf;
    @BindView(R2.id.del_cf)
    TextView delCf;
    @BindView(R2.id.zyzs)
    ConstraintLayout zyzs;
    @BindView(R2.id.tv_cny)
    TextView tvCny;
    @BindView(R2.id.myradiogroup)
    MyRadioGroup group;
    @BindView(R2.id.tv_unit)
    TextView tv_unit;
    @BindView(R2.id.llSelectgang)
    LinearLayout llSelectgang;
    @BindView(R2.id.spinnergang)
    TextView spinnerGang;
    @Autowired(required = true)
    String code = "BTC/USDT";//币种类型code
    String type = "BTC";//币种类型
    String moneyType = "USDT";//币种类型
    private BottomSheetDialog priceModeSheet;
    private boolean isBuy = true;
    private boolean isMarketPrice = true;
    private String marketPrice = "5000";//市价
    private String currentPrice = "5000";//限价
    private String currentNum = "0";
    private String usdtMoney = "0";//可用USDT
    //  private String coinMoney = "0";//可用币
    private String rate = "0.0001";//加减的点
    private String maxTradeCoin = "0";//最大可交易币种数量
    private double fee = 0;//手续费
    //private int level =30;
    private SafeSettingBean userData;
    private int numKeepDot;
    private boolean isSetText;

    private boolean isZyZs = false;
    private String cfPrice = "0";
    private double buyRate = 100;
    private double sellRate = 100;
    private String newRate;
    @Inject
    UserViewModel userViewModel;
    private List<String> gangGan = new ArrayList<>();
    private BottomSheetDialog priceModeSheet1;

    @Override
    protected int getLayoutId() {
        return R.layout.level_fragment_buy_and_sell;
    }

    @Override
    protected NewBuyAndSellFragmentPresenter getPresenter() {
        return new NewBuyAndSellFragmentPresenter();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView() {
        //super.initView();
        DaggerUserDataComponent.create().inject(this);
        btBuy.setBackground(ContextCompat.getDrawable(App.INSTANCE, isBuy ? R.drawable.level_shape_green : R.drawable.level_shape_red));
        ClickUtil.click(btBuy, () -> {
            ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
        });
        if (isMarketPrice){
            tvCny.setVisibility(View.GONE);
        }else {
            tvCny.setVisibility(View.VISIBLE);
        }
        depthMapView.setDrawText(false);
        code = code.replace("_", "/").toUpperCase();
        initClick();
        updateUI();
    }
    String unit="$";
    String level = "100";
    private void updateUI() {
        etNum1.setText("");
        tvTradeMoney.setText("0");

        if (radioGroup == null) {
            return;
        }
        numKeepDot = CoinUtil.getNumKeepNum(code);
        tvHideMoneyType.setText(moneyType);
        calculateMaxTrade();
        radioGroup.check(isBuy ? R.id.rbBuy : R.id.rbSell);
        // updateIndicator();
        if (isZyZs) {
            spinner.setText(App.INSTANCE.getString(R.string.bibi_zyzs));
            zyzs.setVisibility(View.VISIBLE);
            rlEditPrice.setVisibility(View.VISIBLE);
            llHidePrice.setVisibility(View.GONE);
            etPriceCf.setText("");
        } else {
            zyzs.setVisibility(View.GONE);
            spinner.setText(isMarketPrice ? App.INSTANCE.getString(R.string.bibi_tipBibiUtil2) :App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment2));
            rlEditPrice.setVisibility(isMarketPrice ? View.GONE : View.VISIBLE);
            llHidePrice.setVisibility(isMarketPrice ? View.VISIBLE : View.GONE);
        }
        // etNum1.setFilters(new InputFilter[]{new MoneyValueFilter().setDigits(CoinUtil.getNumKeepNum(code))});
        etPrice1.setFilters(new InputFilter[]{new MoneyValueFilter().setDigits(CoinUtil.getNumKeepNum(code))});
        etPriceCf.setFilters(new InputFilter[]{new MoneyValueFilter().setDigits(CoinUtil.getNumKeepNum(code))});
        etPrice1.setText(TextUtils.isEmpty(marketPrice) ? "0" : CoinUtil.keepCoinPrice(code, marketPrice));
        // etNum1.setText("0");
        // indicatorSeekBar.setProgress(0);
        if (isMarketPrice && isBuy) {
            tvNumTitle.setText(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment3));
        } else {
            tvNumTitle.setText(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment4));
        }
        rgLevel.clearCheck();
        tvMoney.setText(CoinUtil.keepCoinMoney(code, usdtMoney)+"USDT");
        tvCoinType.setText(moneyType);
        btBuy.setBackground(ContextCompat.getDrawable(App.INSTANCE, isBuy ? R.drawable.level_shape_32b_corner10 : R.drawable.level_shape_ff5_corner10));
        if (userData != null) {
            btBuy.setText(isBuy ? App.INSTANCE.getString(R.string.level_zuoduo):App.INSTANCE.getString(R.string.level_zuokong));
            ClickUtil.click(btBuy, () -> {
                level = spinnerGang.getText().toString();
                if (checkParam()) {
                    if(isBuy&&isMarketPrice){
                        mPresenter.submit(marketPrice,etNum1.getText().toString(),"BUY",code,"MARKET_PRICE",level);
                    }else if(isBuy&&!isMarketPrice){
                        mPresenter.submit(currentPrice,etNum1.getText().toString(),"BUY",code,"LIMIT_PRICE",level);
                    }else if(!isBuy&&isMarketPrice){
                        mPresenter.submit(marketPrice,etNum1.getText().toString(),"SELL",code,"MARKET_PRICE",level);
                    }else if(!isBuy&&!isMarketPrice){
                        mPresenter.submit(currentPrice,etNum1.getText().toString(),"SELL",code,"LIMIT_PRICE",level);
                    }

                }
            });
        } else {
            btBuy.setText(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment8));
            ClickUtil.click(btBuy, () -> {
                ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            });
        }
        if(!TextUtils.isEmpty(minxianTradNum)&&!TextUtils.isEmpty(mincommonTypeTradNum)){
            String data = new BigDecimal(minxianTradNum).stripTrailingZeros().toPlainString();
            if(isMarketPrice&&isBuy){
                etNum1.setHint(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment4)+"\n"+data);
            }else {
                data = new BigDecimal(mincommonTypeTradNum).stripTrailingZeros().toPlainString();
                etNum1.setHint(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment4)+"\n"+data);
            }
        }
    }
    /**
     * 检测购买条件
     *
     * @return
     */
    private boolean checkParam() {
        if (TextUtils.isEmpty(SPUtil.get(SPConfig.TOKEN, ""))) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment9));
            return false;
        }
        if (isMarketPrice) {
            if (TextUtils.isEmpty(etNum1.getText()) || Double.valueOf(etNum1.getText().toString()) == 0) {
                ToastUtil.showShort(String.format(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment10), isBuy ? App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment11) : App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment12)));
                return false;
            }
        } else {
            /*if (isZyZs) {
                if (TextUtils.isEmpty(etPriceCf.getText().toString())) {
                    ToastUtil.showShort(getString(R.string.bibi_bibicftint));
                    return false;
                }
             *//*   if (isBuy) {
                    if (Double.valueOf(etPriceCf.getText().toString()) > (buyRate / 100) * Double.valueOf(etPrice1.getText().toString())) {
                        ToastUtil.showShort(String.format("委托价不能高于触发价的%s", buyRate + "%"));
                        return false;
                    }
                } else {
                    if (Double.valueOf(etPriceCf.getText().toString()) < (sellRate / 100) * Double.valueOf(etPrice1.getText().toString())) {
                        ToastUtil.showShort(String.format("委托价不能低于触发价的%s", sellRate + "%"));
                        return false;
                    }s
                }*//*
            }*/
            if (TextUtils.isEmpty(etPrice1.getText()) || Double.valueOf(etPrice1.getText().toString()) == 0) {
                ToastUtil.showShort(String.format(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment13), isBuy ? App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment11) : App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment12)));
                return false;
            }
            if (TextUtils.isEmpty(etNum1.getText()) || Double.valueOf(etNum1.getText().toString()) == 0) {
                ToastUtil.showShort(String.format(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment10), isBuy ? App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment11) : App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment12)));
                return false;
            }

        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("CheckResult")
    private void initClick() {
        initInputChange();
        RxRadioGroup.checkedChanges(radioGroup)
                .subscribe(clickId -> {
                    if (!isBuy && clickId == rbBuy.getId()) {//购买
                        mPresenter.getData("0","");
                        isBuy = true;
                        group.clearCheck();
                        tv_unit.setText(moneyType);
//                        isMarketPrice = true;
                        updateUI();
                    } else if (isBuy && clickId == rbSell.getId()) {//出售
                        isBuy = false;
                        mPresenter.getData("0","");
                        group.clearCheck();
                        if(isMarketPrice){
                            tv_unit.setText(moneyType);
                        }else {
                            tv_unit.setText(moneyType);
                        }

//                        isMarketPrice = true;
                        updateUI();
                    }
                });

        ClickUtil.click(llPriceMode, () -> {
            if (priceModeSheet1 == null) {
                priceModeSheet1 = BottomSheetUtil.getBottomSheet(getActivity(), null, (recyclerView, position, v) -> {
                    priceModeSheet1.dismiss();
                    if (position == 0) {
                        isMarketPrice = false;
                        isZyZs = false;
                        tvCny.setVisibility(View.VISIBLE);
                        group.clearCheck();
                        tv_unit.setText(moneyType);
                        updateUI();

                    } else if (position == 1) {
                        isMarketPrice = true;
                        isZyZs = false;
                        tvCny.setVisibility(View.GONE);
                        group.clearCheck();
                        if(isBuy){
                            tv_unit.setText(moneyType);
                        }else {
                            tv_unit.setText(moneyType);
                        }
                        updateUI();
                    } /*else if (position == 2) {
                           isMarketPrice = false;
                           isZyZs = true;
                           tvCny.setVisibility(View.GONE);
                           updateUI();
                       }*/
                }, App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment2), App.INSTANCE.getString(R.string.bibi_tipBibiUtil2));
            }
            priceModeSheet1.show();
        });
        ClickUtil.click(100, tvDecrease, () -> {
            if (TextUtils.isEmpty(etPrice1.getText())) {
                currentPrice = "0";
            }
            String decrease = "-" + rate;
            BigDecimal priceDecimal = new BigDecimal(currentPrice).add(new BigDecimal(decrease));
            if (priceDecimal.doubleValue() <= 0) {
                etPrice1.setText("0");
                etPrice1.setSelection(etPrice1.getText().length());
            } else {
                String data =  CoinUtil.keepCoinPrice(code, priceDecimal.toString());
                etPrice1.setText(data);
                etPrice1.setSelection(etPrice1.getText().toString().length());
                tvCny.setText("≈"+unit+new BigDecimal(newRate).multiply(new BigDecimal(data)).toString());
                //  currentPrice = data;
            }
        });
        ClickUtil.click(100, tvIncrease, () -> {
            if (TextUtils.isEmpty(etPrice1.getText())) {
                currentPrice = "0";
            }
            String increase = rate;
            BigDecimal priceDecimal = new BigDecimal(currentPrice).add(new BigDecimal(increase));
            String data =  CoinUtil.keepCoinPrice(code, priceDecimal.toString());
            etPrice1.setText(data);
            etPrice1.setSelection(etPrice1.getText().toString().length());
            tvCny.setText("≈"+unit+new BigDecimal(newRate).multiply(new BigDecimal(data)).toString());
            // currentPrice = data;

        });
        ClickUtil.click(100, llAdd2, () -> {
            if (TextUtils.isEmpty(etNum1.getText())) {
                currentNum = "0";
            }else {
                currentNum = etNum1.getText().toString();
            }
            String decrease = "-" + rate;
            BigDecimal priceDecimal = new BigDecimal(currentNum).add(new BigDecimal(decrease));
            if (priceDecimal.doubleValue() <= 0) {
                etNum1.setText("0");
                etNum1.setSelection(etNum1.getText().length());
            } else {
                String data =  CoinUtil.keepCoinPrice(code, priceDecimal.toString());
                etNum1.setText(data);
                etNum1.setSelection(etNum1.getText().toString().length());
                //  currentPrice = data;
            }
        });
        ClickUtil.click(100, llAddAndDe1, () -> {
            if (TextUtils.isEmpty(etNum1.getText())) {
                currentNum = "0";
            }else {
                currentNum = etNum1.getText().toString();
            }
            String increase = rate;
            BigDecimal priceDecimal = new BigDecimal(currentNum).add(new BigDecimal(increase));
            String data =  CoinUtil.keepCoinPrice(code, priceDecimal.toString());
            etNum1.setText(data);
            etNum1.setSelection(etNum1.getText().toString().length());
            // currentPrice = data;

        });
        ClickUtil.click(llSelectgang,()->{
            priceModeSheet = BottomSheetUtil.getBottomSheet(getActivity(), null, (recyclerView, position, v) -> {
                priceModeSheet.dismiss();
                spinnerGang.setText(gangGan.get(position));
                level = gangGan.get(position);
                group.clearCheck();
                calculateMaxTrade();
                changeShowData();
                updateUI();
            }, gangGan);

            priceModeSheet.show();
        });
        ClickUtil.click(100, delCf, () -> {
            if (TextUtils.isEmpty(etPriceCf.getText())) {
                cfPrice = "0";
            } else {
                cfPrice = etPriceCf.getText().toString();
            }
            String decrease = "-" + rate;
            BigDecimal priceDecimal = new BigDecimal(cfPrice).add(new BigDecimal(decrease));
            if (priceDecimal.doubleValue() <= 0) {
                etPriceCf.setText("0");
                etPriceCf.setSelection(etPriceCf.getText().length());
            } else {
                String data =  CoinUtil.keepCoinPrice(code, priceDecimal.toString());
                etPriceCf.setText(data);
                etPriceCf.setSelection(etPriceCf.getText().toString().length());
            }
        });
        ClickUtil.click(100, addCf, () -> {
            if (TextUtils.isEmpty(etPriceCf.getText())) {
                cfPrice = "0";
            }
            String increase = rate;
            BigDecimal priceDecimal = new BigDecimal(cfPrice).add(new BigDecimal(increase));
            String data =  CoinUtil.keepCoinPrice(code, priceDecimal.toString());
            etPriceCf.setText(data);
            etPriceCf.setSelection(etPriceCf.getText().toString().length());


        });

    }

    private static final String TAG = "NewBuyAndSellFragment";
    @SuppressLint({"CheckResult", "SetTextI18n"})
    private void initInputChange() {
        /*RxTextView.textChanges(et_num).subscribe(text->{
            Log.e(TAG, "initInputChange: "+text);
        });*/
        //输入价格
        RxTextView.textChanges(etPrice1).subscribe(data->{
            if(BuildConfig.DEBUG){
                Log.e(TAG, "initInputChange3:"+data);
            }

                    String text = data.toString();
                    if(BuildConfig.DEBUG){
                    Log.e(TAG, "initInputChange2: "+text);}
                    if (TextUtils.isEmpty(text)) {
                        currentPrice = "0";
                    } else {
                        currentPrice = text;
                    }
                    if(BuildConfig.DEBUG){
                    Log.e("rx_binding_test", "textChanges:etRxTextView内容变化了:" + text);}
                    calculateMaxTrade();
                    // changeShowData();
                    etNum1.setText(etNum1.getText());

          /*  getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });*/

                },e-> System.out.println("错误信息为:"+e)
        );
        //添加数量
        RxTextView.textChanges(etNum1).subscribe(data->{
                    if (userData == null) {
                        return;
                    }
                    String text = data.toString();
                    if (!TextUtils.isEmpty(text) && text.equals(".")) {
                        etNum1.setText("0");
                        currentNum = "0";
                        text = "0";
                    }
                    if(BuildConfig.DEBUG){
                    Log.e("rx_binding_test", "textChanges:etRxTextView内容变化了1:" + text);}
                    if (TextUtils.isEmpty(text) || Double.valueOf(text) == 0 ||
                            TextUtils.isEmpty(etPrice1.getText()) || Double.valueOf(etPrice1.getText().toString()) == 0) {

                    } else {
                        if (Double.valueOf(text) > Double.valueOf(maxTradeCoin)) {
                            etNum1.setText(maxTradeCoin);
                            currentNum = maxTradeCoin;
//                            ToastUtil.showShort("资金不足");
                        } else {

//                            int progress = new BigDecimal(text).divide(new BigDecimal(maxTradeCoin), 8, RoundingMode.HALF_UP).multiply(new BigDecimal("1000")).intValue();
//                            progress = progress > 1000 ? 1000 : progress;
                        }
                    }
                    changeShowData();

                },e-> System.out.println("错误信息为:"+e)
        );

    }
    /**
     * 计算最大可输入数量
     */
    private void calculateMaxTrade() {
        if (TextUtils.isEmpty(currentPrice) || Double.valueOf(currentPrice) == 0) {
            maxTradeCoin = "0";
        } else {
            if (isBuy) {
                if (isMarketPrice) {
                    maxTradeCoin =new BigDecimal(usdtMoney)
                            .multiply(new BigDecimal(1))
                            .divide(new BigDecimal(marketPrice).add(new BigDecimal(speed)),BigDecimal.ROUND_DOWN)
                            .divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee))
                                    ,BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString();
                } else {
                    maxTradeCoin = new BigDecimal(usdtMoney).multiply(new BigDecimal(1)).divide(new BigDecimal(etPrice1.getText().toString()).add(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString();
                }
            } else {
                if(isMarketPrice){
                    maxTradeCoin= new BigDecimal(usdtMoney).multiply(new BigDecimal(1)).divide(new BigDecimal(marketPrice).subtract(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString();
                }else {
                    maxTradeCoin= new BigDecimal(usdtMoney).multiply(new BigDecimal(1)).divide(new BigDecimal(etPrice1.getText().toString()).subtract(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString();
                }
                // maxTradeCoin = new BigDecimal(usdtMoney).toString();
            }
        }


    }
    //改变数字
    private void changeShowData() {
        if (TextUtils.isEmpty(etPrice1.getText()) || TextUtils.isEmpty(etNum1.getText())) {
            if (isBuy) {
                tvAllMoneyTitle.setText(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment14));
            } else {
                if (isMarketPrice) {
                    tvAllMoneyTitle.setText(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment14));
                } else {
                    tvAllMoneyTitle.setText(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment14));
                }
            }
            tvTradeMoney.setText("0");
        } else {
            if (!isMarketPrice) {
                String usdtFull;
                if (isBuy) {
                    String num =  etNum1.getText().toString();
                    usdtFull = new BigDecimal(num)
                            .multiply(new BigDecimal(etPrice1.getText().toString()).subtract(new BigDecimal(speed))).multiply(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee))).toString();
                } else {
                    usdtFull = new BigDecimal(etNum1.getText().toString()).multiply(new BigDecimal(etPrice1.getText().toString()).add(new BigDecimal(speed))).multiply(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee))).toString();
                }
                String usdt;
                if (isBuy) {
                    //限价买入
                    usdt = CoinUtil.keepCoinMoney(code, usdtFull);
                    tvTradeMoney.setText(usdt);
                    tvAllMoneyTitle.setText(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment14));

                } else {//限价卖出
                    usdt = CoinUtil.keepCoinMoney(code, usdtFull);
                    tvTradeMoney.setText(usdt);
                    tvAllMoneyTitle.setText(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment14));
                }
            } else {
                String usdtFull;
                if (isBuy) {
                    usdtFull = new BigDecimal("1").multiply(new BigDecimal(etNum1.getText().toString())).multiply(new BigDecimal(marketPrice).subtract(new BigDecimal(speed))).multiply(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee))).toString();

                } else {
                    usdtFull = new BigDecimal("1").multiply(new BigDecimal(etNum1.getText().toString())).multiply(new BigDecimal(marketPrice).add(new BigDecimal(speed))).multiply(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee))).toString();
                }
                String usdt;
                if (isBuy) {
                    //市价买入
                    usdt = CoinUtil.keepCoinMoney(code, usdtFull);
                    tvAllMoneyTitle.setText(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment14));
                    tvTradeMoney.setText(usdt);
                } else {//市价卖出
                    usdt = CoinUtil.keepCoinNum(code, usdtFull);
                    tvAllMoneyTitle.setText(App.INSTANCE.getString(R.string.bibi_bibiBuyAndSellFragment14));
                    tvTradeMoney.setText(usdt);
                }
            }
        }
        boolean b =   TextUtils.isEmpty(etPrice1.getText());
    }
    @Override
    protected void initEvent() {
        //计算带拉杆
        group.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {

                RadioButton button =  group.findViewById(checkedId);
                if(button==null){
                    return;
                }
                if(button.getText().toString().equals("25%")){
                    if(isBuy){

                        if(isMarketPrice){
                            etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.25)).divide(new BigDecimal(marketPrice).add(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                        }else {
                            etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.25)).divide(new BigDecimal(etPrice1.getText().toString()).add(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                        }
                        tvTradeMoney.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.25)).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                    }else if(isMarketPrice){
                        etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.25)).divide(new BigDecimal(marketPrice).subtract(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                    }else {
                        etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.25)).divide(new BigDecimal(etPrice1.getText().toString()).subtract(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                    }
                }else if(button.getText().toString().equals("50%")){
                    if(isBuy){

                        if(isMarketPrice){
                            etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.5)).divide(new BigDecimal(marketPrice).add(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                        }else {
                            etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.5)).divide(new BigDecimal(etPrice1.getText().toString()).add(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                        }
                        tvTradeMoney.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.5)).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                    }else if(isMarketPrice){
                        etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.5)).divide(new BigDecimal(marketPrice).subtract(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                    }else {
                        etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.5)).divide(new BigDecimal(etPrice1.getText().toString()).subtract(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                    }
                }else if(button.getText().toString().equals("75%")){
                    if(isBuy){
                        if(isMarketPrice){
                            etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.75)).divide(new BigDecimal(marketPrice).add(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                        }else {
                            etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.75)).divide(new BigDecimal(etPrice1.getText().toString()).add(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                        }
                        tvTradeMoney.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.75)).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                    }else if(isMarketPrice){
                        etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.75)).divide(new BigDecimal(marketPrice).subtract(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                    }else {
                        etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(0.75)).divide(new BigDecimal(etPrice1.getText().toString()).subtract(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                    }
                }else if(button.getText().toString().equals("100%")){
                    if(isBuy){

                        if(isMarketPrice){
                            etNum1.setText(new BigDecimal(usdtMoney)
                                    .multiply(new BigDecimal(1))
                                    .divide(new BigDecimal(marketPrice).add(new BigDecimal(speed)),BigDecimal.ROUND_DOWN)
                                    .divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee))
                                            ,BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                        }else {
                            etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(1)).divide(new BigDecimal(etPrice1.getText().toString()).add(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                        }
                        tvTradeMoney.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(1)).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                    }else if(isMarketPrice){
                        etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(1)).divide(new BigDecimal(marketPrice).subtract(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                    }else {
                        etNum1.setText(new BigDecimal(usdtMoney).multiply(new BigDecimal(1)).divide(new BigDecimal(etPrice1.getText().toString()).subtract(new BigDecimal(speed)),BigDecimal.ROUND_DOWN).divide(new BigDecimal(1/Double.valueOf(level)).add(new BigDecimal(fee)),BigDecimal.ROUND_DOWN).setScale(4,BigDecimal.ROUND_DOWN).toPlainString());
                    }
                }

            }
        });

    }

    @Override
    public void initData() {
        mPresenter.getRate("USD","USD");
        // mPresenter.getDealDetail(code);
        mPresenter.getData("0","");
        mPresenter.getGanggan(code);
        mPresenter.getPankou(code);
        mPresenter.getMinNum(code);
        //  mPresenter.getCoinInfo(code, userData);
        // mPresenter.getDepthData(code);
        // mPresenter.initSocket(code);
        userViewModel.getUsers().observe(this, users -> {
            if (users != null && users.size() > 0) {
                userData = users.get(0);
            } else {
                userData = null;
            }
            // mPresenter.getCoinInfo(code, userData);
            updateUI();
        });
        LiveDataBus.get().with(RxBusCode.LEVEL_FRESH).observe(this, o -> refresh());
        LiveDataBus.get().with(RxBusCode.RATE, RateBean.class).observe(this,this::refreshRate);
        LiveDataBus.get().with(RxBusCode.LEVEL_CHANGE_COIN, BibiCoinType.class).observe(this, this::changeCoin);
        LiveDataBus.get().with(RxBusCode.LEVEL_CHANGE_COIN2,BibiCoinType.class).observe(this, this::changeCoin1);
        LiveDataBus.get().with(RxBusCode.LOGOUT).observe(this,this::updataView);
        LiveDataBus.get().with(RxBusCode.NEWCODEBEAN1, CoinBean1.class)
                .observe(this, this::refreshCoin);
    }

    private void refreshCoin(CoinBean1 coinBean1) {
        if(code.equals(coinBean1.getSymbol())){
            currentPrice = marketPrice = coinBean1.getCloseStr();
            calculateMaxTrade();
        }

    }

    private void updataView(Object o) {
        initView();
    }

    private void refreshRate(RateBean rateBean) {
        newRate = rateBean.getRate();
        unit = rateBean.getSimple();
        tvCny.setText("≈ "+unit+new BigDecimal(newRate).multiply(new BigDecimal(etPrice1.getText().toString())).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
    }
    /**
     * 更换币种,更改市价限价
     *
     * @param coinType
     */
    public void changeCoin(BibiCoinType coinType) {
        code = coinType.getCode();
        int priceKeepNum = CoinUtil.getNumKeepNum(code);
        double rateInit = 1;
        while (priceKeepNum > 0) {
            rateInit *= 10;
            priceKeepNum--;
        }
        rate = 1d / rateInit + "";
        moneyType = code.replace("_", "/").split("/")[1].toUpperCase();
        type = code.replace("_", "/").split("/")[0].toUpperCase();
        isMarketPrice = true;
        isBuy = true;
        updateUI();
       /* tvShiJia.setTextColor(getResources().getColor(R.color.bibiHistorytext));
        tvXianJia.setTextColor(getResources().getColor(R.color.bibiTextHint));
        tvZYZS.setTextColor(getResources().getColor(R.color.bibiTextHint));
        tvShiJia.setBackground(getResources().getDrawable(R.drawable.bibi_shape_xianjia));
        tvXianJia.setBackground(getResources().getDrawable(R.drawable.bibi_shape_xianjia_un));
        tvZYZS.setBackground(getResources().getDrawable(R.drawable.bibi_shape_xianjia_un));*/
        isMarketPrice = true;
        isZyZs = false;
        refresh();
    }

    private void changeCoin1(BibiCoinType bibiCoinType) {
        code = bibiCoinType.getCode();
        int priceKeepNum = CoinUtil.getNumKeepNum(code);
        double rateInit = 1;
        while (priceKeepNum > 0) {
            rateInit *= 10;
            priceKeepNum--;
        }
        rate = 1d / rateInit + "";
        moneyType = code.replace("_", "/").split("/")[1].toUpperCase();
        type = code.replace("_", "/").split("/")[0].toUpperCase();
        isMarketPrice = true;
        isBuy = false;
        updateUI();
        isMarketPrice = true;
        isZyZs = false;
        refresh();
    }
    public void refresh() {
        mPresenter.getRate("USD","USD");
        mPresenter.getPankou(code);
        // mPresenter.getDealDetail(code);
        mPresenter.getMinNum(code);
        mPresenter.getGanggan(code);
        mPresenter.getData("0","");


    }
    @SuppressLint("SetTextI18n")
    public void updateFive(WSFiveBean1 wsFiveBean) {
        if(isBuy){
            if(wsFiveBean.getAsk().getItems()!=null&&wsFiveBean.getAsk().getItems().size()>0){
                if(wsFiveBean.getAsk().getItems().size()>5){
                    marketPrice= wsFiveBean.getAsk().getItems().get(4).getPriceStr();
                }else {
                    marketPrice=  wsFiveBean.getAsk().getItems().get(wsFiveBean.getAsk().getItems().size()-1).getPriceStr();

                }

            }else {
                marketPrice ="5000";
            }


        }else {
            if(wsFiveBean.getBid().getItems()!=null&&wsFiveBean.getBid().getItems().size()>0){
                marketPrice = wsFiveBean.getBid().getItems().get(0).getPriceStr();
            }else {
                marketPrice="5000";
            }

        }
        if(!marketPrice.contains("5000")&&newRate!=null){
            tvCny.setText("≈"+unit+new BigDecimal(newRate).multiply(new BigDecimal(marketPrice)).toString());
        }
        LiveDataBus.get().with(RxBusCode.CHANGE_PRICE, String.class)
                .observe(getActivity(), s -> {
                    if (!isMarketPrice) {
                        etPrice1.setText(CoinUtil.keepCoinPrice(code, s));
                        etPrice1.setSelection(etPrice1.getText().toString().length());
                        tvCny.setText("≈"+unit+new BigDecimal(newRate).multiply(new BigDecimal(s)).toString());
                        // String string = etPrice1.getText().toString();
                    }
                });
        calculateMaxTrade();
    }
    public void setRate(RateBean bean) {
        newRate = bean.getRate();
        unit = bean.getSimple();
        //  mPresenter.getPankou(code);
    }
    String minxianTradNum;
    String mincommonTypeTradNum;
    public void getDealDetail(DealDetailBean bean) {
        //fee = bean.getFee();
        //  minxianTradNum = bean.getMinTurnover();
        mincommonTypeTradNum = new BigDecimal(bean.getMinVolume()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
    }

    public void updateData(LevelBean.DataBean bean) {

        usdtMoney = new BigDecimal(bean.getBalance()).setScale(4,BigDecimal.ROUND_DOWN).toPlainString();
        updateUI();


    }

    public void success(String message) {
       // ToastUtil.showShort(message);
        if(message.contains("成功")||message.contains("success")){
            ToastUtil.showShort(App.INSTANCE.getString(R.string.level_success));
        }
        LiveDataBus.get().with(RxBusCode.LEVEL_FRESH).postValue(1);
        refresh();
    }
    //获取杠杆以及税率
    double speed;
    public void getGangGan(GangGanBean data) {
        if(data!=null){
            String[] arr = data.getLevelType().split(",");
            minxianTradNum = new BigDecimal(data.getMinVolume()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            mincommonTypeTradNum =  new BigDecimal(data.getMinVolume()).setScale(4,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            speed = data.getSpread();
            fee = data.getFee();
            gangGan = Arrays.asList(arr);
            level = gangGan.get(0);
            spinnerGang.setText(gangGan.get(0));
            calculateMaxTrade();
            updateUI();
        }

    }
    public void setUi(MinNumBean bean) {
        if(bean!=null){
          /*  String d = new BigDecimal(bean.getMinVolume()).toPlainString();
           BigDecimal b=new BigDecimal(d);
           BigDecimal c= b.stripTrailingZeros();
            mincommonTypeTradNum = c.toPlainString();*/
            mincommonTypeTradNum = minxianTradNum = new BigDecimal(bean.getMinVolume()).compareTo(BigDecimal.ZERO)==0?"0":new BigDecimal(bean.getMinVolume()).stripTrailingZeros().toPlainString();
            updateUI();
        }
    }
}
