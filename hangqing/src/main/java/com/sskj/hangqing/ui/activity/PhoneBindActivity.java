package com.sskj.hangqing.ui.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yoojia.inputs.AndroidNextInputs;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.component.DaggerUserDataComponent;
import com.sskj.hangqing.presenter.PhoneBindActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.SchemeUtil;
import com.sskj.login.bean.rxbus.AddressBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = RConfig.PHONEBINDACTIVITY)
public class PhoneBindActivity extends BaseActivity<PhoneBindActivityPresenter> {
    @BindView(R2.id.ivBack)
    ImageView ivBack;
    @BindView(R2.id.et_phone)
    EditText et_phone;
    @BindView(R2.id.tv_denglu)
    TextView tvDenglu;
    @BindView(R2.id.btLogin)
    Button bt;
    @Autowired
    boolean isTrad;
    @BindView(R2.id.tv_1)
    Spinner tv_1;
    private AndroidNextInputs inputs;
    @Inject
    UserViewModel userViewModel;
    private SafeSettingBean userData;
    private Map<String, String> map =new HashMap<>();
    @Override
    protected int getLayoutId() {
        return R.layout.hang_bind_phone;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initEvent() {
       // super.initEvent();
        ClickUtil.click(ivBack, this::finish);
        ClickUtil.click(bt,()->{
            if(inputs.test()){
                BottomSheetUtil.showYanZhengMa(PhoneBindActivity.this, et_phone.getText().toString(), new BottomSheetUtil.NewOnSure() {
                    @Override
                    public void onSure(String code) {
                        if(code!=null){
                            if(isTrad){
                               mPresenter.updateBindPhone(et_phone.getText().toString(),code);
                            }else
                                mPresenter.bindPhone(map.get(tv_1.getSelectedItem().toString()),et_phone.getText().toString(),code);
                            //mPresenter.updateUserPwd(et_newPwd.getText().toString(),code);
                            // ARouter.getInstance().build(RConfig.SETTINGACTIVITY).withString("account",etAccount.getText().toString()).withString("code",code).withBoolean("isEmail",isEmail).navigation();
                        }
                    }
                }, new BottomSheetUtil.GetTime() {
                    @Override
                    public void getTime() {
                        if(isTrad){
                            mPresenter.getUpdatePhoneCode(map.get(tv_1.getSelectedItem().toString()),et_phone.getText().toString());
                        }else {
                            mPresenter.getBindPhone(map.get(tv_1.getSelectedItem().toString()),et_phone.getText().toString());
                        }

                    }
                });
            }

        });
    }

    @Override
    protected void initData() {
       // super.initData();
    mPresenter.getArray();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        //super.initView();
        DaggerUserDataComponent.create().inject(this);
        inputs = new ToastNextInputs();
        inputs.clear();
        inputs.add(et_phone, SchemeUtil.notEmpty(et_phone));
        if(isTrad){
            tvDenglu.setText(App.INSTANCE.getString(R.string.hang_updata_phone));
        }else {
            tvDenglu.setText(App.INSTANCE.getString(R.string.hang_bind_phone));
        }
    }

    @Override
    public PhoneBindActivityPresenter getPresenter() {
        return new PhoneBindActivityPresenter();
    }

    public void setUI(String message) {
        ToastUtil.showShort(message);
        userViewModel.update();
        SPUtil.put(SPConfig.MOBILE,et_phone.getText().toString());
        LiveDataBus.get().with(RxBusCode.REFRESHSAFETY,Integer.class).postValue(1);
        finish();
    }

    public void updateUI(String message) {
        ToastUtil.showShort(message);
    }
    private List<String> list = new ArrayList<>();
    public void setmess(AddressBean bean) {
        list.clear();
        for(AddressBean.DataBean data:bean.getData()){
            list.add(data.getAreaCode());
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.lib_list_item, list.toArray(new String[list.size()]));
        tv_1.setAdapter(adapter);
    }
}
