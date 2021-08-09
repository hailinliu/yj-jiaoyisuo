package com.sskj.login.ui.activity;

import android.graphics.Color;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.EditHintUtils;
import com.sskj.lib.util.EncodeUtils;
import com.sskj.lib.util.SchemeUtil;
import com.sskj.lib.widget.RudessMaterialDialog;
import com.sskj.login.R;
import com.sskj.login.R2;
import com.sskj.login.bean.rxbus.AddressBean;
import com.sskj.login.presenter.FindPwdActivityPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

@Route(path = RConfig.LOGIN_PWD_FORGET)
public class FindPwdActivity extends BaseActivity<FindPwdActivityPresenter> {
    @BindView(R2.id.et_account)
    EditText etAccount;
    @BindView(R2.id.bt_next)
    Button bt_next;
    @BindView(R2.id.tv_findyouxiang)
    TextView tv_youxiang;
    @BindView(R2.id.tv_findphone)
    TextView tv_phone;
    @BindView(R2.id.tv_1)
    Spinner tv_1;
    private ToastNextInputs inputs;
    private boolean isEmail =true;
    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_pwd_forget;
    }

    @Override
    public FindPwdActivityPresenter getPresenter() {
        return new FindPwdActivityPresenter();
    }
    public String newcode;

    @Override
    protected void initView() {
       // super.initView();
        inputs = new ToastNextInputs();
        inputs.add(etAccount, SchemeUtil.notEmpty(etAccount));
        mPresenter.getArray();
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        ClickUtil.click(bt_next,()->{
            if(inputs.test()){
                MaterialDialog dialog = new RudessMaterialDialog.Builder(this)
                        .customView(com.sskj.lib.R.layout.lib_dialog_slide, false)
                        .autoDismiss(false)
                        .cancelable(false)
                        .show();
                View customView = dialog.getCustomView();
                SeekBar seekBar = customView.findViewById(com.sskj.lib.R.id.sb);
                TextView tv = customView.findViewById(com.sskj.lib.R.id.tv);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (seekBar.getProgress() == seekBar.getMax()) {
                            tv.setVisibility(View.VISIBLE);
                            tv.setTextColor(Color.WHITE);
                            tv.setText("完成验证");
                            dialog.dismiss();
                            BottomSheetUtil.showYanZhengMa(FindPwdActivity.this, etAccount.getText().toString(), new BottomSheetUtil.NewOnSure() {
                                @Override
                                public void onSure(String code) {
                                    if(!etAccount.getText().toString().isEmpty()&&!code.isEmpty()){
                                        newcode = code;
                                        if(EncodeUtils.encodeAES(code).equals(key)) {
                                            if (isEmail) {
                                                mPresenter.refindEmailPwd(etAccount.getText().toString(), code);
                                            } else {
                                                mPresenter.refindPhonePwd(etAccount.getText().toString(), code);
                                            }
                                            ARouter.getInstance().build(RConfig.SETTINGACTIVITY).withString("account", etAccount.getText().toString()).withString("code", code).withBoolean("isEmail", isEmail).withBoolean("isfund", true).navigation();
                                        }else {
                                            ToastUtil.showShort(App.INSTANCE.getString(com.sskj.login.R.string.login_cuowu));
                                        }
                                    }
                                }
                            }, new BottomSheetUtil.GetTime() {
                                @Override
                                public void getTime() {
                                    if(isEmail){
                                        mPresenter.getEmailCode(etAccount.getText().toString());
                                    }else{
                                        String key= tv_1.getSelectedItem().toString();
                                        String coun= map.get(key);
                                        mPresenter.getPhoneCode(coun,etAccount.getText().toString());
                                    }

                                }
                            });
                        } else {
                            tv.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        if (seekBar.getProgress() != seekBar.getMax()) {
                            seekBar.setProgress(0);
                            tv.setVisibility(View.VISIBLE);
                            tv.setTextColor(Color.GRAY);
                            tv.setText("向右滑动验证");
                        }
                    }
                });
            }
        });
        ClickUtil.click(tv_youxiang,()->{
            isEmail = true;
            tv_youxiang.setTextColor(getResources().getColor(R.color.libGreen1));
            tv_phone.setTextColor(getResources().getColor(R.color.libTextGray));
            etAccount.setInputType(InputType.TYPE_CLASS_TEXT);
            etAccount.setText("");
            etAccount.setHint(EditHintUtils.setHintSizeAndContent(App.INSTANCE.getString(R.string.login_email_address),16,true));

            tv_1.setVisibility(View.GONE);
        });
        ClickUtil.click(tv_phone,()->{
            isEmail = false;
            tv_phone.setTextColor(getResources().getColor(R.color.libGreen1));
            tv_youxiang.setTextColor(getResources().getColor(R.color.libTextGray));
            etAccount.setInputType(InputType.TYPE_CLASS_NUMBER);
            etAccount.setText("");
            etAccount.setHint(EditHintUtils.setHintSizeAndContent(App.INSTANCE.getString(R.string.login_phone_code),16,true));
            tv_1.setVisibility(View.VISIBLE);
        });
    }

    public void result(String msg) {
        if(msg.contains("成功")||msg.contains("success")){
            ToastUtil.showShort(App.INSTANCE.getString(R.string.login_success));
            ARouter.getInstance().build(RConfig.SETTINGACTIVITY).withString("account",etAccount.getText().toString()).withString("code",newcode).withBoolean("isEmail",isEmail).navigation();
        }else {
            ToastUtil.showShort(msg);
        }
    }
    private List<String> list = new ArrayList<>();
    private Map<String, String> map=new HashMap<>();
    public void setData(AddressBean bean) {
        list.clear();
        for(AddressBean.DataBean data:bean.getData()){
            list.add(data.getAreaCode());
            map.put(data.getAreaCode(),data.getZhName());
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.lib_list_item, list.toArray(new String[list.size()]));
        tv_1.setAdapter(adapter);
    }

    String key;
    public void setP(String msg) {
        key = msg;
    }
    }
